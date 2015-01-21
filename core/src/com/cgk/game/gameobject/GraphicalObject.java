package com.cgk.game.gameobject;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;

import com.cgk.game.event.EventQueue;
import com.cgk.game.event.GameEvent;

public abstract class GraphicalObject {

	final Logger LOGGER = Logger.getLogger(GraphicalObject.class.toString());
	@Autowired
	private EventQueue eventQueue;

	public GraphicalObject(EventQueue eventQueue) {
		this.eventQueue = eventQueue;
	}

	public abstract void draw();

	public void respondToEvent(GameEvent event) {
	}

	public void sendEvent(GameEvent event) {
		try {
			eventQueue.put(event);
		} catch (Exception e) {
			LOGGER.log(Level.WARNING, e.getLocalizedMessage());
		}
	}

	public void setEventQueue(EventQueue queue) {
		this.eventQueue = queue;
	}

}
