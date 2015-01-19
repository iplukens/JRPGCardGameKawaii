package com.cgk.game.event;

import java.util.concurrent.LinkedBlockingQueue;

public class EventQueue extends LinkedBlockingQueue<GameEvent> {

	private static final long serialVersionUID = 1L;
	private static int MAX_EVENTS_IN_TURN = 1000;

	private EventQueue() {
		super();
	}

}
