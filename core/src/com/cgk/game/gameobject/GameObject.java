package com.cgk.game.gameobject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.cgk.game.event.EventType;
import com.cgk.game.event.GameEvent;
import com.cgk.game.event.UnregisterEvent;
import com.cgk.game.gameobject.eventresponses.EventResponse;
import com.cgk.game.system.Asset;
import com.cgk.game.system.Battlefield;
import com.cgk.game.system.EventQueue;

public abstract class GameObject {

	final Logger LOGGER = Logger.getLogger(GameObject.class.toString());
	private static Battlefield battlefield;
	private static EventQueue eventQueue;
	private Map<EventType, List<EventResponse<?, ?>>> eventResponses;
	private boolean registered;

	public GameObject() {
		eventQueue.registerGameObject(this);
		registered = true;
		eventResponses = new HashMap<>();
		setupEventResponses();
	}

	protected abstract void setupEventResponses();

	public abstract void draw(SpriteBatch batcher, TextureAtlas atlas);

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
		if (registered) {
			try {
				eventQueue.put(event);
			} catch (Exception e) {
				e.printStackTrace();
				LOGGER.log(Level.WARNING, "FAILURE" + e.getLocalizedMessage());
			}
		} else {
			LOGGER.log(Level.INFO,
					"Unregistered object, so not sending event to queue: "
							+ event.getType());
		}
	}

	public void setEventQueue(EventQueue queue) {
		GameObject.eventQueue = queue;
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

	public List<Asset<?>> getAllAssets() {
		List<Asset<?>> assets = new ArrayList<>();
		assets.addAll(getTextureAssets());
		assets.addAll(getMusicAssets());
		assets.addAll(getSoundAssets());
		return assets;
	}

	/**
	 * return a list of all texture assets associated with the game object
	 * 
	 * @return
	 */
	public abstract List<Asset<Texture>> getTextureAssets();

	/**
	 * return a list of all sound assets associated with the game object
	 * 
	 * @return
	 */
	public abstract List<Asset<Sound>> getSoundAssets();

	/**
	 * return a list of all music assets associated with the game object
	 * 
	 * @return
	 */
	public abstract List<Asset<Music>> getMusicAssets();

	protected void unregister() {
		sendEvent(new UnregisterEvent(this));
		registered = false;
	}

	public void logInfo(String info) {
		StackTraceElement[] stackTraceElements = Thread.currentThread()
				.getStackTrace();
		String stackInfo = "" + stackTraceElements[2];
		System.out.println(stackInfo + " INFO: " + info);
	}

	public static void setBattlefield(Battlefield setBattlefield) {
		battlefield = setBattlefield;
	}

	public static Battlefield getBattlefield() {
		return battlefield;
	}

	public static void setQueue(EventQueue queue) {
		eventQueue = queue;
	}

	public static EventQueue getQueue() {
		return eventQueue;
	}
}
