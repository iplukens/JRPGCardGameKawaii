package com.cgk.game.system;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.cgk.game.event.EventType;
import com.cgk.game.event.GameEvent;
import com.cgk.game.gameobject.GameObject;

public class EventQueue extends LinkedBlockingDeque<GameEvent> {

	private final Logger LOGGER = Logger.getLogger(EventQueue.class.toString());
	private static final long serialVersionUID = 1L;
	private static int MAX_EVENTS_IN_SEQUENCE = 1000;
	private static int eventsThisSequence = 0;
	private List<GameObject> GameObjects = new ArrayList<>();

	public EventQueue() {
		super();
	}

	public void registerGameObject(GameObject object) {
		GameObjects.add(object);
	}

	public void unregisterGameObject(GameObject object) {
		GameObjects.remove(object);
	}

	@Override
	public void put(GameEvent e) throws InterruptedException {
		LOGGER.log(Level.INFO, "Adding event " + e.getType());
		if (e.hasPriority()) {
			super.addFirst(e);
		} else {
			super.put(e);
		}
		evaluateEvent(e);
	}

	private void evaluateEvent(GameEvent e) {
		if (peek() == e) {
			LOGGER.log(Level.INFO, "Evaluating event " + e.getType());
			eventsThisSequence++;
			sendToObservers(e);
			if (!isEmpty()) {
				remove();
			}
			if (e.getType() == EventType.DEFEAT
					|| eventsThisSequence >= MAX_EVENTS_IN_SEQUENCE) {
				clear();
			} else if (!isEmpty()) {
				evaluateEvent(peek());
			} else {
				eventsThisSequence = 0;
			}
		}
	}

	private void sendToObservers(GameEvent e) {
		for (GameObject object : GameObjects) {
			object.respondToEvent(e);
		}
	}

	public boolean containsRegisteredObject(GameObject object) {
		return GameObjects.contains(object);
	}

	public int getMaxEvents() {
		return MAX_EVENTS_IN_SEQUENCE;
	}
}
