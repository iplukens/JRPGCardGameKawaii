package com.cgk.game.gameobject.util;

import com.cgk.game.event.PlayEvent;
import com.cgk.game.gameobject.eventresponses.EventResponse;

public class ComboTrackerPlayResponse extends EventResponse<ComboTracker, PlayEvent> {

	@Override
	protected void handleEvent(ComboTracker gameObject, PlayEvent event) {
		gameObject.updateCombo(event.getCard());
	}

}
