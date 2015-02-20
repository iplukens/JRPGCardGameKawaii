package com.cgk.game.gameobject.eventresponses;

import com.cgk.game.event.cardevents.DiscardEvent;
import com.cgk.game.gameobject.Hand;

public class HandDiscardEventResponse extends EventResponse<Hand, DiscardEvent> {

	@Override
	protected void handleEvent(Hand gameObject, DiscardEvent event) {
		gameObject.discard();
	}

}
