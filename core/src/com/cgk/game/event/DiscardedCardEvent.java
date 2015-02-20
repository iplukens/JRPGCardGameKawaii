package com.cgk.game.event;

import com.cgk.game.gameobject.card.Card;

public class DiscardedCardEvent extends CardEvent {

	public DiscardedCardEvent(Card card) {
		super(card);
	}

	public EventType getType() {
		return EventType.CARD_DISCARDED;
	}

}
