package com.cgk.game.gameobject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;

import com.cgk.game.event.EventType;
import com.cgk.game.event.GameEvent;
import com.cgk.game.gameobject.eventresponses.EventResponse;
import com.cgk.game.system.EventQueue;

public abstract class GameObject {

	final Logger LOGGER = Logger.getLogger(GameObject.class.toString());
	@Autowired
	private EventQueue eventQueue;
	private Map<EventType, List<EventResponse<?, ?>>> eventResponses;

	public GameObject() {
		eventQueue.registerGameObject(this);
		eventResponses = new HashMap<>();
		setUpEventResponses();
	}

	public GameObject(EventQueue eventQueue) {
		this.eventQueue = eventQueue;
		eventQueue.registerGameObject(this);
		eventResponses = new HashMap<>();
		setUpEventResponses();
	}

	protected abstract void setUpEventResponses();

	public abstract void draw();
	
	public abstract void erase();

	public void respondToEvent(GameEvent event) {
		List<EventResponse<?, ?>> responses = eventResponses.get(event
				.getType());
		if (responses != null) {
			for (EventResponse<?, ?> response : responses) {
				response.respond(this, event);
			}
		}
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

	/**
	 * adds a response for the EventType
	 * 
	 * @param type
	 * @param response
	 * @return
	 */
	protected Map<EventType, List<EventResponse<?, ?>>> addResponse(
			EventType type, EventResponse<?, ?> response) {
		if (!eventResponses.containsKey(type)) {
			eventResponses.put(type, new ArrayList<EventResponse<?, ?>>());
		}
		eventResponses.get(type).add(response);
		return eventResponses;
	}

	/**
	 * replaces any existing event responses with the given event response
	 * 
	 * @param type
	 * @param newResponse
	 * @return
	 */
	protected Map<EventType, List<EventResponse<?, ?>>> replaceResponses(
			EventType type, EventResponse<?, ?> newResponse) {
		eventResponses.put(type, new ArrayList<EventResponse<?, ?>>());
		eventResponses.get(type).add(newResponse);
		return eventResponses;
	}

	/**
	 * replaces any existing event responses with the given event responses
	 * 
	 * @param type
	 * @param newResponses
	 * @return
	 */
	protected Map<EventType, List<EventResponse<?, ?>>> replaceResponses(
			EventType type, List<EventResponse<?, ?>> newResponses) {
		eventResponses.put(type, newResponses);
		return eventResponses;
	}

	protected void unregister() {
		eventQueue.unregisterGameObject(this);
	}
}
