package com.cgk.game.gameobject.card;

import com.badlogic.gdx.graphics.Texture;
import com.cgk.game.system.Asset;

/**
 * @author ianlukens May 20, 2015
 *
 */
public abstract class ValueCard extends Card {

	protected ValueCard(String cardName, String cardText,
			Asset<Texture> cardGraphic, int value) {
		super(cardName, cardText, cardGraphic);
		this.value = value;
	}

	protected int value;

	public int getValue() {
		return value;
	}

}
