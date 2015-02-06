package com.cgk.game.event;

import com.cgk.game.gameobject.card.Card;

public class DiscardEvent extends CloneableEvent implements GameEvent {

	Card card;
	
	public DiscardEvent(Card card){
		this.card = card;
	}

	public EventType getType() {
		return EventType.DISCARD;
	}
	
}
