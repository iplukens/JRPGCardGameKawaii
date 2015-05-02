package com.cgk.game.event.cardevents;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.cgk.game.event.EventType;

public class AttackAdditiveEvent extends ValueEvent {

	public AttackAdditiveEvent(int value) {
		super(value);
	}

	public EventType getType() {
		return EventType.ADD_BUFF;
	}

	@Override
	public int drawPlayInfo(SpriteBatch batcher, int line, Rectangle cardArea) {
		drawLine(batcher, "Attack + " + value, line, cardArea);
		return 1;
	}

}
