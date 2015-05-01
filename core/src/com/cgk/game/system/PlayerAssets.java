package com.cgk.game.system;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.cgk.game.gameobject.Deck;
import com.cgk.game.gameobject.card.Card;
import com.cgk.game.gameobject.card.FuelTheFire;
import com.cgk.game.gameobject.units.UnitAttack.AttackType;
import com.cgk.game.gameobject.units.hero.Hero;

/**
 * 
 * @author ianlukens
 *
 */
public class PlayerAssets {

	private static final Random random = new Random(System.currentTimeMillis());

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
		paintDeck(deck);
		return deck;
	}

	private void paintDeck(Deck deck) {
		for (Card card : deck.getCards()) {
			card.setStartingCardType(getRandomAttackType());
			card.setEndingCardType(getRandomAttackType());
		}
	}

	/**
	 * TODO: make this function base itself on probabilities of colors
	 * 
	 * @return
	 */
	private AttackType getRandomAttackType() {
		List<AttackType> possibleValues = AttackType.getTypes();
		int chosen = random.nextInt(possibleValues.size());
		return possibleValues.get(chosen);
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
