package com.cgk.game.gameobject.card;

import java.util.ArrayList;
import java.util.List;

import com.cgk.game.event.GameEvent;
import com.cgk.game.event.PlayEvent;
import com.cgk.game.event.cardevents.CardEffectEvent;
import com.cgk.game.gameobject.GameObject;
import com.cgk.game.gameobject.units.UnitAttack.AttackType;
import com.cgk.game.system.EventQueue;

public abstract class Card extends GameObject {

	private int cardImage;
	private String cardName;
	private String cardText;
	private int resourceNumber;
	private AttackType startingCardType;
	private AttackType endingCardType;
	protected List<CardEffectEvent> playEvents = new ArrayList<>();
	protected List<GameEvent> discardEvents = new ArrayList<>();
	protected boolean alive = true;	

	protected Card(EventQueue eventQueue, int cardImage, int resourceNumber,
			String cardName, String cardText) {
		super(eventQueue);
		this.cardImage = cardImage;
		this.resourceNumber = resourceNumber;
		this.cardName = cardName;
		this.cardText = cardText;
		setPlayEvents();
		setDiscardEvents();
	}

	/**
	 * set up the events that occur when the card is discarded
	 */
	protected void setDiscardEvents() {

	}

	/**
	 * set up the events that occur when the card is played
	 */
	protected abstract void setPlayEvents();

	public void play() {
		sendEvent(new PlayEvent(this));
		if (alive) {
			sendPlayEvents();
		}
	}

	private void sendPlayEvents() {
		for (GameEvent event : playEvents) {
			sendEvent(event);
		}
	}

	public void sendDiscardEvents() {
		for (GameEvent event : discardEvents) {
			sendEvent(event);
		}
	}

	public GameEvent getEvent(int index) {
		return playEvents.get(index);
	}

	/**
	 * @return the cardImage
	 */
	public int getCardImage() {
		return cardImage;
	}

	/**
	 * @return the cardName
	 */
	public String getCardName() {
		return cardName;
	}

	/**
	 * @return the cardText
	 */
	public String getCardText() {
		return cardText;
	}

	/**
	 * @return the resourceNumber
	 */
	public int getResourceNumber() {
		return resourceNumber;
	}

	/**
	 * @return the alive
	 */
	public boolean isAlive() {
		return alive;
	}

	/**
	 * @param cardImage
	 *            the cardImage to set
	 */
	public void setCardImage(int cardImage) {
		this.cardImage = cardImage;
	}

	/**
	 * @param cardName
	 *            the cardName to set
	 */
	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	/**
	 * @param cardText
	 *            the cardText to set
	 */
	public void setCardText(String cardText) {
		this.cardText = cardText;
	}

	/**
	 * @param resourceNumber
	 *            the resourceNumber to set
	 */
	public void setResourceNumber(int resourceNumber) {
		this.resourceNumber = resourceNumber;
	}

	/**
	 * @param alive
	 *            the alive to set
	 */
	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	/**
	 * all cards are probably going to be drawn the same way...?
	 */
	@Override
	public void draw() {
		// TODO Auto-generated method stub
		// draw all the card affects...!
		int start = 0;
		for (int i = 0; i < playEvents.size(); i++) {
			CardEffectEvent event = playEvents.get(i);
			event.drawPlayInfo(start);
			start += event.getPlayInfoSize();
		}
	}

	@Override
	public void erase() {
		// TODO
	}

	public AttackType getStartingCardType() {
		return startingCardType;
	}

	public void setStartingCardType(AttackType startingCardType) {
		this.startingCardType = startingCardType;
	}

	public AttackType getEndingCardType() {
		return endingCardType;
	}

	public void setEndingCardType(AttackType endingCardType) {
		this.endingCardType = endingCardType;
	}

}
