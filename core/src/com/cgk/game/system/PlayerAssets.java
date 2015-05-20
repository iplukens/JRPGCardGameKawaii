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
	public Deck getDeck() {
		// TODO Auto-generated method stub
		Deck deck = new Deck();
		deck.addCard(new FuelTheFire());
		deck.addCard(new FuelTheFire());
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
	public List<Hero> getHeroes() {
		// TODO Auto-generated method stub
		List<Hero> heroes = new ArrayList<>();
		heroes.add(new Hero(100, AttackType.BLUE));
		return heroes;
	}

	public static PlayerAssets getAssets() {
		// TODO Auto-generated method stub
		return new PlayerAssets();
	}

	public int getMaxHandSize() {
		// TODO Auto-generated method stub
		return 2;
	}

	public ComboTracker getComboTracker() {
		// TODO Auto-generated method stub
		return new ComboTracker(0, 0, AttackType.GREY);
	}

	public PlayerTurnTimer getTimer() {
		// TODO Auto-generated method stub
		return new PlayerTurnTimer(5, 10, 5);
	}

}
