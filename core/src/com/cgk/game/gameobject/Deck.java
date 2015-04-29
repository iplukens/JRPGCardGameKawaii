package com.cgk.game.gameobject;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.cgk.game.gameobject.card.Card;
import com.cgk.game.system.Asset;
import com.cgk.game.system.EventQueue;
import com.cgk.game.util.Constants;

public class Deck extends GameObject {

	List<Card> cards = new ArrayList<>();
	Asset fullDeck = new Asset("assets/fullDeck.png", Texture.class);

	public Deck(EventQueue eventQueue) {
		super(eventQueue);
		setupAssets();
	}

	public void setCards(List<Card> cards) {
		this.cards = cards;
	}

	@Override
	public void draw(SpriteBatch batcher, TextureAtlas atlas) {
		batcher.draw(atlas.findRegion(getDeckState()).getTexture(),
				Constants.DECK_X_POSITION, Constants.DECK_Y_POSITION,
				Constants.DECK_WIDTH, Constants.DECK_HEIGHT);
	}

	@Override
	protected void setupEventResponses() {
		// TODO Auto-generated method stub
	}

	public List<Card> getCards() {
		return cards;
	}

	// Look at this getting all those states.
	private String getDeckState() {
		return fullDeck.getFileName();
	}

	@Override
	protected void setupAssets() {
		textureAssets.add(fullDeck);
	}

	public void addCard(Card card) {
		cards.add(card);
	}

}
