package com.cgk.game.event.cardevents;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.cgk.game.event.EventType;
import com.cgk.game.gameobject.card.ValueCard;

public class AttackMultiplierEvent extends ValueEvent {

	public AttackMultiplierEvent(int value) {
		super(value);
	}

	public AttackMultiplierEvent(ValueCard card) {
		super(card);
	}

	public EventType getType() {
		return EventType.MULT_BUFF;
	}

	@Override
	public int drawPlayInfo(SpriteBatch batcher, int line, Rectangle cardArea) {
		// TODO Auto-generated method stub
		return 1;
	}

}
