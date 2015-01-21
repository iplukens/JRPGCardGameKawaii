package com.cgk.game.event;

import com.cgk.game.gameobject.card.Card;

public class PlayEvent implements GameEvent {

	Card playedCard;
	
	public PlayEvent(Card card){
		this.playedCard = card;
	}

	public EventId getEventId() {
		return EventId.PLAY;
	}
	
}
