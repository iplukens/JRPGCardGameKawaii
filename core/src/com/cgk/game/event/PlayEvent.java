package com.cgk.game.event;

import com.cgk.game.gameobject.card.Card;

public class PlayEvent extends CloneableEvent implements GameEvent {

	Card playedCard;
	
	public PlayEvent(Card card){
		this.playedCard = card;
	}

	public EventType getType() {
		return EventType.PLAY;
	}
	
}
