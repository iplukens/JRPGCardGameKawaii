package com.cgk.game.event.cardevents;

import com.cgk.game.gameobject.card.ValueCard;

public abstract class ValueEvent extends CardEffectEvent {

	protected int value;

	public ValueEvent(int value) {
		super();
		this.value = value;
	}

	public ValueEvent(ValueCard card) {
		super(card);
	}

	public int getValue() {
		if (card == null) {
			return value;
		}
		return ((ValueCard) card).getValue();
	}

}
