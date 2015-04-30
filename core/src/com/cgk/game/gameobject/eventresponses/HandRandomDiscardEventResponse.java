package com.cgk.game.gameobject.eventresponses;

import com.cgk.game.event.cardevents.RandomDiscardEvent;
import com.cgk.game.gameobject.Hand;

public class HandRandomDiscardEventResponse extends EventResponse<Hand, RandomDiscardEvent> {

	@Override
	protected void handleEvent(Hand gameObject, RandomDiscardEvent event) {
		gameObject.discardRandomCard();
	}

}
