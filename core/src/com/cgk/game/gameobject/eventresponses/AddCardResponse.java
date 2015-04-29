package com.cgk.game.gameobject.eventresponses;

import com.cgk.game.event.CardEvent;
import com.cgk.game.gameobject.CardLibrary;

public class AddCardResponse extends EventResponse<CardLibrary, CardEvent> {

	@Override
	protected void handleEvent(CardLibrary gameObject, CardEvent event) {
		gameObject.addCard(event.getCard());
	}

}
