package com.cgk.game.system;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

import com.cgk.game.event.GameEvent;
import com.cgk.game.gameobject.GameObject;

public class EventQueue extends LinkedBlockingQueue<GameEvent> {

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

	@Override
	public void put(GameEvent e) throws InterruptedException {
		super.put(e);
		evaluateEvent(e);
	}

	private void evaluateEvent(GameEvent e) {		
		if (peek() == e) {
			eventsThisSequence++;
			sendToObservers(e);
			remove();
			if (!isEmpty() && eventsThisSequence < MAX_EVENTS_IN_SEQUENCE) {
				evaluateEvent(peek());
			} else if (isEmpty()) {
				eventsThisSequence = 0;
			} else if (eventsThisSequence >= MAX_EVENTS_IN_SEQUENCE) {
				clear();
			}
		}
	}

	private void sendToObservers(GameEvent e) {
		for (GameObject object : GameObjects) {
			Class<? extends GameEvent> eventClass = e.getClass();
			object.respondToEvent(eventClass.cast(e));
		}
	}

	public boolean containsRegisteredObject(GameObject object) {
		return GameObjects.contains(object);
	}
}
