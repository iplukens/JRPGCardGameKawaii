package com.cgk.game.gameobject.eventresponses;

import com.cgk.game.event.GameEvent;
import com.cgk.game.gameobject.GameObject;

public class SendEventResponse extends EventResponse<GameObject, GameEvent> {

	private GameEvent sentEvent;

	public SendEventResponse(GameEvent sentEvent) {
		super();
		this.sentEvent = sentEvent;
	}

	@Override
	protected void handleEvent(GameObject gameObject, GameEvent event) {
		gameObject.sendEvent(sentEvent.cloneEvent()); // need unique spot in
														// memory, so we clone
														// the event
	}

}
