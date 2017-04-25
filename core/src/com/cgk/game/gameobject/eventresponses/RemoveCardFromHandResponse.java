package com.cgk.game.gameobject.eventresponses;

import com.cgk.game.event.PlayEvent;
import com.cgk.game.gameobject.Hand;

/**
 * @author ianlukens Apr 29, 2015
 *
 */
public class RemoveCardFromHandResponse extends EventResponse<Hand, PlayEvent> {

	@Override
	protected void handleEvent(Hand gameObject, PlayEvent event) {
		gameObject.removeCard(event.getCard());
		gameObject.printInfo("remove card");
	}

}
