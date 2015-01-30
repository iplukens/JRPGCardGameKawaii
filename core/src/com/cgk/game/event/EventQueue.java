package com.cgk.game.event;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

import com.cgk.game.gameobject.GameObject;

public class EventQueue extends LinkedBlockingQueue<GameEvent> {

	private static final long serialVersionUID = 1L;
	private static int MAX_EVENTS_IN_SEQUENCE = 1000;
	private static int eventsThisSequence = 0;
	private List<GameObject> gameObjects = new ArrayList<>();

	public EventQueue() {
		super();
	}

	public void registerGameObject(GameObject object) {
		gameObjects.add(object);
	}

	@Override
	public void put(GameEvent e) throws InterruptedException {
		super.put(e);
		evaluateEvent(e);
	}

	private void evaluateEvent(GameEvent e) {
		if (peek().equals(e)) {
			eventsThisSequence++;
			sendToObservers(e);
			remove();
		}
		if (!isEmpty() && eventsThisSequence < MAX_EVENTS_IN_SEQUENCE) {
			evaluateEvent(peek());
		} else if (isEmpty()) {
			eventsThisSequence = 0;
		} else if (eventsThisSequence >= MAX_EVENTS_IN_SEQUENCE) {
			clear();
		}
	}

	private void sendToObservers(GameEvent e) {
		for (GameObject object : gameObjects) {
			object.respondToEvent(e);
		}
	}
}
