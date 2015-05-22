package com.cgk.game.gameobject.card;

import java.util.Map;

/**
 * @author ianlukens May 20, 2015
 *
 */
public class CardFactory {

	public Card getCard(String cardName, Map<String, String> attributes) {
		try {
			Class<?> c = Class.forName(cardName);
			Card card = (Card) c.newInstance();
			card.setAttributes(attributes);
			return card;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new FuelTheFire();
	}
}
