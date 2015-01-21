package com.cgk.game.event;

import com.cgk.game.gameobject.card.Card;

public class DiscardEvent implements GameEvent {

	Card card;
	
	public DiscardEvent(Card card){
		this.card = card;
	}

	public EventId getEventId() {
		return EventId.DISCARD;
	}
	
}
