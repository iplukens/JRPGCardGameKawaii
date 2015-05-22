package com.cgk.game.event.cardevents;

import com.cgk.game.gameobject.card.ValueCard;
import com.cgk.game.gameobject.card.ValueType;

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
		return value;
	}

	public int getValue(ValueType type) {
		if (card == null) {
			return value;
		}
		return ((ValueCard) card).getValue(getType(), type);
	}

}
