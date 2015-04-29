package com.cgk.game.gameobject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.cgk.game.event.EventType;
import com.cgk.game.event.GameEvent;
import com.cgk.game.gameobject.eventresponses.EventResponse;
import com.cgk.game.system.Asset;
import com.cgk.game.system.EventQueue;

public abstract class GameObject {

	final Logger LOGGER = Logger.getLogger(GameObject.class.toString());
	@Autowired
	private EventQueue eventQueue;
	private Map<EventType, List<EventResponse<?, ?>>> eventResponses;
	protected List<Asset> textureAssets = new ArrayList<>();
	protected List<Asset> musicAssets = new ArrayList<>();
	protected List<Asset> soundAssets = new ArrayList<>();
	@Autowired
	AssetManager assetManager;

	public GameObject() {
		eventQueue.registerGameObject(this);
		eventResponses = new HashMap<>();
		setupEventResponses();
	}

	public GameObject(EventQueue eventQueue) {
		this.eventQueue = eventQueue;
		eventQueue.registerGameObject(this);
		eventResponses = new HashMap<>();
		setupEventResponses();
	}

	protected abstract void setupEventResponses();

	public abstract void draw(SpriteBatch batcher, TextureAtlas atlas);

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
	protected Map<EventType, List<EventResponse<?, ?>>> replaceEvents(
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
	protected Map<EventType, List<EventResponse<?, ?>>> replaceEvents(
			EventType type, List<EventResponse<?, ?>> newResponses) {
		eventResponses.put(type, newResponses);
		return eventResponses;
	}

	public List<Asset> getAssets() {
		List<Asset> assets = new ArrayList<>();
		assets.addAll(textureAssets);
		assets.addAll(musicAssets);
		assets.addAll(soundAssets);
		return assets;
	}

	/**
	 * put all assets into their respective array lists (textureAssets,
	 * soundAssets, or musicAssets)
	 */
	protected abstract void setupAssets();

	public List<Asset> getTextureAssets() {
		return textureAssets;
	}

	public List<Asset> getSoundAssets() {
		return soundAssets;
	}

	public List<Asset> getMusicAssets() {
		return musicAssets;
	}
}
