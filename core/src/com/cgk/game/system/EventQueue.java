package com.cgk.game.system;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.cgk.game.event.EventType;
import com.cgk.game.event.GameEvent;
import com.cgk.game.event.UnregisterEvent;
import com.cgk.game.gameobject.GameObject;

public class EventQueue {

	private final Logger LOGGER = Logger.getLogger(EventQueue.class.toString());
	private static int MAX_EVENTS_IN_SEQUENCE = 1000;
	private static int eventsThisSequence = 0;
	private List<GameObject> gameObjects = new ArrayList<>();
	private List<GameEvent> events;

	public EventQueue() {
		events = new ArrayList<>(MAX_EVENTS_IN_SEQUENCE);
		eventsThisSequence = 0;
	}

	public void registerGameObject(GameObject object) {
		gameObjects.add(object);
	}

	private void unregisterGameObject(GameObject object) {
		gameObjects.remove(object);
		int itemsRemoved = 0;
		int size = events.size();
		for (int i = 0; i < size; i++) {
			int index = i - itemsRemoved;
			if (events.get(index).getOriginObject() != null
					&& events.get(index).getOriginObject().equals(object)) {
				GameEvent event = events.remove(index);
				itemsRemoved++;
				LOGGER.log(Level.INFO, "Removed event " + event.getType());
			}
		}
	}

	public synchronized void put(GameEvent e) {
		LOGGER.log(Level.INFO, "Adding event " + e.getType());
		if (events.isEmpty()) {
			events.add(e);
		} else {
			for (int index = events.size() - 1; index >= 0; index--) {
				if (events.get(index).getPriority() >= e.getPriority()) {
					events.add(index + 1, e);
					break;
				} else if (index == 0) {
					// if has highest priority, make it next event to execute
					events.add(index + 1, e);
				}
			}
		}
		evaluateEvent(e);
	}

	private void evaluateEvent(GameEvent e) {
		if (peek() == e) {
			LOGGER.log(Level.INFO, "Evaluating event " + e.getType());
			eventsThisSequence++;
			if (e.getType() == EventType.UNREGISTER) {
				unregisterGameObject(((UnregisterEvent) e).getGameObject());
			} else {
				sendToObservers(e);
			}
			if (!isEmpty()) {
				remove(e);
			}
			LOGGER.log(Level.INFO, "Finished evaluating event " + e.getType());
			if (e.getType() == EventType.DEFEAT
					|| eventsThisSequence >= MAX_EVENTS_IN_SEQUENCE) {
				clear();
			}
			if (!isEmpty()) {
				evaluateEvent(peek());
			} else {
				eventsThisSequence = 0;
			}
		}
	}

	public int size() {
		return events.size();
	}

	public GameEvent peek() {
		if (events.isEmpty()) {
			return null;
		}
		return events.get(0);
	}

	public boolean isEmpty() {
		return events.isEmpty();
	}

	public GameEvent remove() {
		return events.remove(0);
	}

	public boolean remove(GameEvent e) {
		return events.remove(e);
	}

	public void clear() {
		events.clear();
	}

	private void sendToObservers(GameEvent e) {
		for (GameObject object : gameObjects) {
			object.respondToEvent(e);
		}
	}

	public boolean containsRegisteredObject(GameObject object) {
		return gameObjects.contains(object);
	}

	public int getMaxEvents() {
		return MAX_EVENTS_IN_SEQUENCE;
	}
}
