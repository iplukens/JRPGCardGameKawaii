package com.cgk.game.gameobject.card;

import java.util.Map;

/**
 * @author ianlukens May 20, 2015
 *
 */
public class CardFactory {

	public Card getCard(String cardName, Map<String, String> attributes) {
		return new FuelTheFire();
	}
}
