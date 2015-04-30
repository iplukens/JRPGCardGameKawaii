package com.cgk.game.gameobject.eventresponses;

import com.cgk.game.event.GameEvent;
import com.cgk.game.gameobject.Deck;

public class DrawFromDeckResponse extends EventResponse<Deck, GameEvent> {

	@Override
	protected void handleEvent(Deck gameObject, GameEvent event) {
		gameObject.printInfo("draw from deck");
		gameObject.drawCard();
	}

}
