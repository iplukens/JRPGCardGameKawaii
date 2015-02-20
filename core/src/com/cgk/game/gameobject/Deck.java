package com.cgk.game.gameobject;

import java.util.PriorityQueue;
import java.util.Queue;

import com.cgk.game.event.DrawnCardEvent;
import com.cgk.game.event.EventType;
import com.cgk.game.gameobject.card.Card;
import com.cgk.game.gameobject.eventresponses.AddCardResponse;
import com.cgk.game.gameobject.eventresponses.DrawFromDeckResponse;
import com.cgk.game.system.EventQueue;

public class Deck extends CardLibrary {

	private Queue<Card> cards = new PriorityQueue<Card>();

	public Deck(EventQueue eventQueue) {
		super(eventQueue);
	}

	public Deck(EventQueue eventQueue, Queue<Card> cards) {
		super(eventQueue);
		this.cards.addAll(cards);
	}

	public void addCard(Card card) {
		cards.add(card);
	}

	public void removeCard(Card card) {
		cards.remove(card);
	}

	public void drawCard() {
		Card card = cards.remove();
		sendEvent(new DrawnCardEvent(card));
	}

	public int getSize() {
		return cards.size();
	}

	@Override
	public void draw() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void setUpEventResponses() {
		addResponse(EventType.CARD_DISCARDED, new DrawFromDeckResponse());
		addResponse(EventType.CARD_DISCARDED, new AddCardResponse());
		addResponse(EventType.PLAY, new DrawFromDeckResponse());
		addResponse(EventType.PLAY, new AddCardResponse());
	}

	@Override
	public void erase() {
		// TODO Auto-generated method stub

	}

}
