package com.cgk.game.system.eventresponses;

import com.cgk.game.event.GameEvent;
import com.cgk.game.gameobject.eventresponses.EventResponse;
import com.cgk.game.system.PlayerTurnTimer;

/**
 * @author ianlukens May 16, 2015
 *
 */
public class LoseTimeResponse extends EventResponse<PlayerTurnTimer, GameEvent> {

	@Override
	protected void handleEvent(PlayerTurnTimer gameObject, GameEvent event) {
		gameObject.dropTime();
	}

}
