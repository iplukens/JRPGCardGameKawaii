package com.cgk.game.gameobject.card;

import java.util.ArrayList;
import java.util.List;

import com.cgk.game.event.EventQueue;
import com.cgk.game.event.GameEvent;
import com.cgk.game.event.PlayEvent;
import com.cgk.game.gameobject.GraphicalObject;

public abstract class Card extends GraphicalObject {

	private int cardImage;
	private String cardName;
	private String cardText;
	private int resourceNumber;
	protected List<GameEvent> cardEvents = new ArrayList<>();
	protected boolean alive = true;

	protected Card(EventQueue eventQueue, int cardImage, int resourceNumber,
			String cardName, String cardText) {
		super(eventQueue);
		this.cardImage = cardImage;
		this.resourceNumber = resourceNumber;
		this.cardName = cardName;
		this.cardText = cardText;
	}

	public void play() {
		sendEvent(new PlayEvent(this));
		if (alive) {
			sendCardEvents();
		}
	}

	private void sendCardEvents() {
		for (GameEvent event : cardEvents) {
			sendEvent(event);
		}
	}

	public GameEvent getEvent(int index) {
		return cardEvents.get(index);
	}

}
