package com.cgk.game.gameobject;

import java.util.List;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.cgk.game.event.DrawnCardEvent;
import com.cgk.game.event.EventType;
import com.cgk.game.gameobject.card.Card;
import com.cgk.game.gameobject.eventresponses.AddCardResponse;
import com.cgk.game.gameobject.eventresponses.DrawFromDeckResponse;
import com.cgk.game.system.Asset;
import com.cgk.game.system.EventQueue;
import com.cgk.game.util.Constants;

public class Deck extends CardLibrary {

	private static Asset<Texture> fullDeck = new Asset<>("assets/fullDeck.png",
			Texture.class);

	public Deck(EventQueue eventQueue) {
		super(eventQueue);
	}

	public List<Card> getCards() {
		return cards;
	}

	public void setCards(List<Card> cards) {
		this.cards = cards;
	}

	public Deck(EventQueue eventQueue, List<Card> cards) {
		super(eventQueue);
		this.cards.addAll(cards);
	}

	public void addCard(Card card) {
		cards.add(card);
	}

	public void removeCard(Card card) {
		cards.remove(card);
	}

	public void drawCard() {
		if (cards.size() > 0) {
			Card card = cards.remove(0);
			sendEvent(new DrawnCardEvent(card));
		}
	}

	public int getSize() {
		return cards.size();
	}

	@Override
	public void draw(SpriteBatch batcher, TextureAtlas atlas) {
		batcher.draw(getDeckState().getAssetFromAtlas(atlas),
				Constants.DECK_X_POSITION, Constants.DECK_Y_POSITION,
				Constants.DECK_WIDTH, Constants.DECK_HEIGHT);
	}

	// Look at this getting all those states.
	private Asset<Texture> getDeckState() {
		return fullDeck;
	}

	@Override
	protected void setupAssets() {
		textureAssets.add(fullDeck);
	}

	@Override
	protected void setupEventResponses() {
		addResponse(EventType.CARD_DISCARDED, new AddCardResponse());
		addResponse(EventType.CARD_DISCARDED, new DrawFromDeckResponse());
		addResponse(EventType.PLAY, new AddCardResponse());
		addResponse(EventType.PLAY, new DrawFromDeckResponse());
	}

	@Override
	public void erase() {
		// TODO Auto-generated method stub
	}

}
