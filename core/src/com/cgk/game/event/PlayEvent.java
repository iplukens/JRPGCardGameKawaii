package com.cgk.game.event;

import com.cgk.game.gameobject.card.Card;

public class PlayEvent extends CardEvent {

	public PlayEvent(Card card) {
		super(card);
	}

	public EventType getType() {
		return EventType.PLAY;
	}

}
