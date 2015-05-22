package com.cgk.game.gameobject;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.cgk.game.event.DrawnCardEvent;
import com.cgk.game.event.EventType;
import com.cgk.game.gameobject.card.Card;
import com.cgk.game.gameobject.eventresponses.AddCardResponse;
import com.cgk.game.gameobject.eventresponses.DrawFromDeckResponse;
import com.cgk.game.system.Asset;
import com.cgk.game.util.BattlefieldConstants;

public class Deck extends CardLibrary {

	private static Asset<Texture> fullDeck = new Asset<>("assets/fullDeck.png",
			Texture.class);

	public Deck() {
		super();
	}

	public List<Card> getCards() {
		return cards;
	}

	public void setCards(List<Card> cards) {
		this.cards = cards;
	}

	public Deck(List<Card> cards) {
		super();
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
				BattlefieldConstants.DECK_X_POSITION,
				BattlefieldConstants.DECK_Y_POSITION,
				BattlefieldConstants.DECK_WIDTH,
				BattlefieldConstants.DECK_HEIGHT);
	}

	// Look at this getting all those states.
	private Asset<Texture> getDeckState() {
		return fullDeck;
	}

	@Override
	public List<Asset<Texture>> getTextureAssets() {
		List<Asset<Texture>> textureAssets = new ArrayList<>();
		textureAssets.add(fullDeck);
		return textureAssets;
	}

	@Override
	protected void setupEventResponses() {
		addEventResponse(EventType.CARD_DISCARDED, new AddCardResponse());
		addEventResponse(EventType.CARD_DISCARDED, new DrawFromDeckResponse());
		addEventResponse(EventType.PLAY, new AddCardResponse());
		addEventResponse(EventType.PLAY, new DrawFromDeckResponse());
	}

	@Override
	public void erase() {
		// TODO Auto-generated method stub
	}

	@Override
	public List<Asset<Sound>> getSoundAssets() {
		// TODO Auto-generated method stub
		return new ArrayList<>();
	}

	@Override
	public List<Asset<Music>> getMusicAssets() {
		// TODO Auto-generated method stub
		return new ArrayList<>();
	}

	/**
	 * shuffles the cards of the deck
	 */
	public void shuffle() {
		List<Card> cardPool = new ArrayList<Card>();
		while (!cards.isEmpty()) {
			Card card = getRandomCard();
			cards.remove(card);
			cardPool.add(card);
		}
		cards = cardPool;
	}

}
