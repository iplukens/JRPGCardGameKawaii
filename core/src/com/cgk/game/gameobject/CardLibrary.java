package com.cgk.game.gameobject;

import com.cgk.game.gameobject.card.Card;
import com.cgk.game.system.EventQueue;

public abstract class CardLibrary extends GameObject {

	public CardLibrary(EventQueue eventQueue) {
		super(eventQueue);
	}

	public abstract void addCard(Card card);

	public abstract void removeCard(Card card);
}
