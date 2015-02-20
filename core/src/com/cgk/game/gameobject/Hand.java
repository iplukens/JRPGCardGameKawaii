package com.cgk.game.gameobject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.cgk.game.event.DiscardedCardEvent;
import com.cgk.game.event.EventType;
import com.cgk.game.gameobject.card.Card;
import com.cgk.game.gameobject.eventresponses.AddCardResponse;
import com.cgk.game.gameobject.eventresponses.HandDiscardEventResponse;
import com.cgk.game.system.EventQueue;

public class Hand extends CardLibrary{

	private List<Card> cards = new ArrayList<>();

	public Hand(EventQueue eventQueue) {
		super(eventQueue);
	}

	public Hand(EventQueue eventQueue, List<Card> cards) {
		super(eventQueue);
		this.cards.addAll(cards);
	}

	/**
	 * discards the specified card
	 * 
	 * @param card
	 * @throws InterruptedException
	 */
	public void discard(Card card) {
		removeCard(card);
		card.sendDiscardEvents();
		sendEvent(new DiscardedCardEvent(card));
	}

	/**
	 * discards a card at random
	 */
	public void discard() {
		discard(getRandomCard());
	}

	private Card getRandomCard() {
		Random random = new Random();
		int randomNumber = random.nextInt(cards.size());
		return cards.get(randomNumber);
	}

	public void removeCard(Card card) {
		cards.remove(card);
	}

	public void addCard(Card card) {
		cards.add(card);
	}

	public List<Card> getCards() {
		return cards;
	}

	@Override
	public void draw() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void setUpEventResponses() {
		addResponse(EventType.DISCARD, new HandDiscardEventResponse());
		addResponse(EventType.DRAWN_CARD, new AddCardResponse());
	}

	@Override
	public void erase() {
		// TODO Auto-generated method stub

	}

	public int getSize() {
		return cards.size();
	}

}
