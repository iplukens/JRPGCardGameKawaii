package com.cgk.game.system.eventresponses;

import com.cgk.game.event.PlayEvent;
import com.cgk.game.gameobject.eventresponses.EventResponse;
import com.cgk.game.system.ComboTracker;

public class ComboTrackerPlayResponse extends EventResponse<ComboTracker, PlayEvent> {

	@Override
	protected void handleEvent(ComboTracker gameObject, PlayEvent event) {
		gameObject.updateCombo(event.getCard());
	}

}
