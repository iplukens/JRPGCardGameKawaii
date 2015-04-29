package com.cgk.game.gameobject;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.cgk.game.event.DiscardEvent;
import com.cgk.game.gameobject.card.Card;
import com.cgk.game.system.EventQueue;
import com.cgk.game.util.Constants;

public class Hand extends GameObject {

	private Rectangle handArea;

	public Hand(EventQueue eventQueue) {
		super(eventQueue);
		cards = new ArrayList<>(Constants.MAX_HAND_SIZE);
		handArea = new Rectangle(0, 0, Constants.SCREEN_WIDTH,
				Constants.HAND_HEIGHT);
		handArea.setCenter(Constants.DEFAULT_CARD_WIDTH / 2,
				Constants.DEFAULT_CARD_HEIGHT / 2);
	}

	private List<Card> cards;
	private Card touchedCard;

	public void discard(Card card) throws InterruptedException {
		sendEvent(new DiscardEvent(card));
	}

	@Override
	public void draw(SpriteBatch batcher, TextureAtlas atlas) {
		for (Card card : cards) {
			card.draw(batcher, atlas);
		}
	}

	@Override
	protected void setupEventResponses() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void setupAssets() {
		throw new UnsupportedOperationException("Not supported yet."); // To
																		// change
																		// body
																		// of
																		// generated
																		// methods,
																		// choose
																		// Tools
																		// |
																		// Templates.
	}

	/**
	 * processes a just touched event from the game loop
	 * 
	 * @param touchPos
	 */
	public void processJustTouched(Vector2 touchPos) {
		if (handArea.contains(touchPos)) {
			clearJustTouched();
			for (Card card : cards) {
				if (card.getCardArea().contains(touchPos)) {
					touchedCard = card;
					card.processJustTouched(touchPos);
					break;
				}
			}
		}
	}

	private void clearJustTouched() {
		if (touchedCard != null) {
			touchedCard.resetTouchStatus();
			touchedCard = null;
		}
	}

	public void processTouch(Vector2 touchPos) {
		if (touchedCard != null) {
			touchedCard.processTouch(touchPos);
		}
	}

}
