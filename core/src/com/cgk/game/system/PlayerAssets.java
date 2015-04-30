package com.cgk.game.system;

import java.util.ArrayList;
import java.util.List;

import com.cgk.game.gameobject.Deck;
import com.cgk.game.gameobject.card.FuelTheFire;
import com.cgk.game.gameobject.units.UnitAttack.AttackType;
import com.cgk.game.gameobject.units.hero.Hero;

/**
 * 
 * @author ianlukens
 *
 */
public class PlayerAssets {

	/**
	 * gets the player's deck
	 * 
	 * @param queue
	 * @return
	 */
	public Deck getDeck(EventQueue queue) {
		// TODO Auto-generated method stub
		Deck deck = new Deck(queue);
		deck.addCard(new FuelTheFire(queue));
		deck.addCard(new FuelTheFire(queue));
		return deck;
	}

	/**
	 * gets the player's heroes
	 * 
	 * @param queue
	 * 
	 * @return
	 */
	public List<Hero> getHeroes(EventQueue queue) {
		// TODO Auto-generated method stub
		return new ArrayList<>();
	}

	public static PlayerAssets getAssets() {
		// TODO Auto-generated method stub
		return new PlayerAssets();
	}

	public int getMaxHandSize() {
		// TODO Auto-generated method stub
		return 2;
	}

	public ComboTracker getComboTracker(EventQueue queue) {
		// TODO Auto-generated method stub
		return new ComboTracker(queue, 0, 0, AttackType.GREY);
	}

}
