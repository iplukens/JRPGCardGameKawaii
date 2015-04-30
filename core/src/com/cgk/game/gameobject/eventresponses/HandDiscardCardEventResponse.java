package com.cgk.game.gameobject.eventresponses;

import com.cgk.game.event.DiscardedCardEvent;
import com.cgk.game.gameobject.Hand;

public class HandDiscardCardEventResponse extends
		EventResponse<Hand, DiscardedCardEvent> {

	@Override
	protected void handleEvent(Hand gameObject, DiscardedCardEvent event) {
		gameObject.discard(event.getCard());
	}

}
