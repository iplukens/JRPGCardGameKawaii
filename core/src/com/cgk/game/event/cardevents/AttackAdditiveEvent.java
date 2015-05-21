package com.cgk.game.event.cardevents;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.cgk.game.event.EventType;
import com.cgk.game.gameobject.card.ValueCard;

public class AttackAdditiveEvent extends ValueEvent {

	public AttackAdditiveEvent(int value) {
		super(value);
	}

	public AttackAdditiveEvent(ValueCard card) {
		super(card);
	}

	public EventType getType() {
		return EventType.ADD_BUFF;
	}

	@Override
	public int drawPlayInfo(SpriteBatch batcher, int line, Rectangle cardArea) {
		drawLine(batcher, "Attack + " + ((ValueCard) card).getValue(), line,
				cardArea);
		return 1;
	}

}
