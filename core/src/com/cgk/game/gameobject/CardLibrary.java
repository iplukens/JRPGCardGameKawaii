package com.cgk.game.gameobject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.cgk.game.gameobject.card.Card;

public abstract class CardLibrary extends GameObject {

	protected List<Card> cards = new ArrayList<Card>();

	public CardLibrary() {
		super();
	}

	public abstract void addCard(Card card);

	public abstract void removeCard(Card card);

	public void printInfo(String calledFrom) {
		logInfo(this.getClass().toString() + " called from: " + calledFrom);
		logInfo("Current cards size: " + cards.size());
		String cardsString = "";
		for (Card card : cards) {
			cardsString += " |" + card.getCardName() + "| ";
		}
		logInfo("Cards: " + cardsString);
	}

	protected Card getRandomCard() {
		Random random = new Random();
		int randomNumber = random.nextInt(cards.size());
		return cards.get(randomNumber);
	}
}
