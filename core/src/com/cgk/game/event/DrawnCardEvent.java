package com.cgk.game.event;

import com.cgk.game.gameobject.card.Card;

public class DrawnCardEvent extends CardEvent {

	public DrawnCardEvent(Card drawnCard) {
		super(drawnCard);
	}

	@Override
	public EventType getType() {
		return EventType.DRAWN_CARD;
	}

}
