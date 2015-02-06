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
	private Map<EventType, List<EventResponse<?, ?>>> eventHandlers;

	public GameObject() {
		eventQueue.registerGameObject(this);
		eventHandlers = new HashMap<>();
		setUpEventResponses();
	}

	public GameObject(EventQueue eventQueue) {
		this.eventQueue = eventQueue;
		eventQueue.registerGameObject(this);
		eventHandlers = new HashMap<>();
		setUpEventResponses();
	}

	protected abstract void setUpEventResponses();

	public abstract void draw();

	public void respondToEvent(GameEvent event) {
		List<EventResponse<?, ?>> handlers = eventHandlers.get(event.getType());
		if (handlers != null) {
			for (EventResponse<?, ?> handler : handlers) {
				handler.respond(this, event);
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
	 * @param handler
	 * @return
	 */
	protected Map<EventType, List<EventResponse<?, ?>>> addResponse(
			EventType type, EventResponse<?, ?> handler) {
		if (!eventHandlers.containsKey(type)) {
			eventHandlers.put(type, new ArrayList<EventResponse<?, ?>>());
		}
		eventHandlers.get(type).add(handler);
		return eventHandlers;
	}

	/**
	 * replaces any existing event responses with the given event response
	 * 
	 * @param type
	 * @param newHandler
	 * @return
	 */
	protected Map<EventType, List<EventResponse<?, ?>>> replaceHandlers(
			EventType type, EventResponse<?, ?> newHandler) {
		eventHandlers.put(type, new ArrayList<EventResponse<?, ?>>());
		eventHandlers.get(type).add(newHandler);
		return eventHandlers;
	}

	/**
	 * replaces any existing event responses with the given event responses
	 * 
	 * @param type
	 * @param newHandlers
	 * @return
	 */
	protected Map<EventType, List<EventResponse<?, ?>>> replaceHandlers(
			EventType type, List<EventResponse<?, ?>> newHandlers) {
		eventHandlers.put(type, newHandlers);
		return eventHandlers;
	}
}
