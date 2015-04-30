package com.cgk.game.gameobject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.cgk.game.event.DiscardedCardEvent;
import com.cgk.game.event.EventType;
import com.cgk.game.gameobject.card.Card;
import com.cgk.game.gameobject.eventresponses.AddCardResponse;
import com.cgk.game.gameobject.eventresponses.HandDiscardEventResponse;
import com.cgk.game.system.EventQueue;
import com.cgk.game.util.Constants;

public class Hand extends CardLibrary {

	private List<Card> cards = new ArrayList<>();
	private Card touchedCard;
	private Rectangle handArea;

	public Hand(EventQueue eventQueue) {
		super(eventQueue);
		cards = new ArrayList<>(Constants.MAX_HAND_SIZE);
		handArea = new Rectangle(0, 0, Constants.SCREEN_WIDTH,
				Constants.HAND_HEIGHT);
		setDefaultCardPositions();
	}

	private void setDefaultCardPositions() {
		float currentX = handArea.x;
		for (Card card : cards) {
			card.setStartX(currentX);
			currentX += Constants.HAND_AREA_BTWN_CARDS;
			card.setStartY(handArea.y);
		}
	}

	public Hand(EventQueue eventQueue, List<Card> cards) {
		super(eventQueue);
		this.cards.addAll(cards);
	}

	@Override
	public void draw(SpriteBatch batcher, TextureAtlas atlas) {
		batcher.draw(atlas.findRegion("assets/fullDeck.png"), handArea.x,
				handArea.y,
				handArea.width, handArea.height);
		for (Card card : cards) {
			card.draw(batcher, atlas);
		}
	}

	/**
	 * discards the specified card
	 * 
	 * @param card
	 * @throws InterruptedException
	 */
	public void discard(Card card) {
		removeCard(card);
		card.sendDiscardEvents();
		sendEvent(new DiscardedCardEvent(card));
		setDefaultCardPositions();
	}

	/**
	 * discards a card at random
	 */
	public void discard() {
		discard(getRandomCard());
	}

	private Card getRandomCard() {
		Random random = new Random();
		int randomNumber = random.nextInt(cards.size());
		return cards.get(randomNumber);
	}

	public void removeCard(Card card) {
		cards.remove(card);
	}

	public void addCard(Card card) {
		cards.add(card);
		setDefaultCardPositions();
	}

	public List<Card> getCards() {
		return cards;
	}

	@Override
	protected void setupEventResponses() {
		addResponse(EventType.DISCARD, new HandDiscardEventResponse());
		addResponse(EventType.DRAWN_CARD, new AddCardResponse());
	}

	@Override
	public void erase() {

	}

	@Override
	protected void setupAssets() {
		throw new UnsupportedOperationException("Not supported yet.");
		// TODO, change body of generated methods, choose tools/templates
	}

	/**
	 * processes a just touched event from the game loop
	 * 
	 * @param touchPos
	 */
	public void processJustTouched(Vector2 touchPos) {
		logInfo("processing just touched: (" + touchPos.x + ", " + touchPos.y
				+ ")");
		touchPos = adjustToOpenGLCoords(touchPos);
		if (handArea.contains(touchPos)) {
			logInfo("touched in the hand area");
			clearJustTouched();
			for (Card card : cards) {
				if (card.getCardArea().contains(touchPos)) {
					logInfo("touched card " + card.getCardName());
					touchedCard = card;
					card.processJustTouched(touchPos);
					break;
				}
			}
		}
	}

	private Vector2 adjustToOpenGLCoords(Vector2 touchPos) {
		touchPos.y = Constants.SCREEN_HEIGHT - touchPos.y;
		// logInfo("new Y:" + touchPos.y);
		return touchPos;
	}

	private void clearJustTouched() {
		resetTouches();
		if (touchedCard != null) {
			logInfo("clearing just touched");
			touchedCard.resetTouchStatus();
			touchedCard = null;
		}
	}

	public void processTouch(Vector2 touchPos) {
		touchPos = adjustToOpenGLCoords(touchPos);
		if (touchedCard != null) {
			touchedCard.processTouch(touchPos);
		}
	}

	public int getSize() {
		return cards.size();
	}

	public void resetTouches() {
		if (touchedCard != null) {
			moveCardsBack();
		}
	}

	private void moveCardsBack() {
		for (Card card : cards) {
			card.moveBack();
		}
	}

}
