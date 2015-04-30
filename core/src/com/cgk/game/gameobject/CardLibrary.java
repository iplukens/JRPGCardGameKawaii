package com.cgk.game.gameobject;

import java.util.ArrayList;
import java.util.List;

import com.cgk.game.gameobject.card.Card;
import com.cgk.game.system.EventQueue;

public abstract class CardLibrary extends GameObject {

	protected List<Card> cards = new ArrayList<Card>();

	public CardLibrary(EventQueue eventQueue) {
		super(eventQueue);
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
}
