package com.cgk.game.event.cardevents;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.cgk.game.event.EventType;

public class RandomDiscardEvent extends CardEffectEvent {

	@Override
	public EventType getType() {
		return EventType.RANDOM_DISCARD;
	}

	@Override
	public int drawPlayInfo(SpriteBatch batcher, int line, Rectangle cardArea) {
		return drawLines(batcher, "Discard a card", line, cardArea);
	}

}
