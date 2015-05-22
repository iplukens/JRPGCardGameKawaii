package com.cgk.game.event.cardevents;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.cgk.game.event.EventType;
import com.cgk.game.gameobject.card.ValueCard;
import com.cgk.game.gameobject.card.ValueType;

public class HealEvent extends ValueEvent {

	public HealEvent(int value) {
		super(value);
	}

	public HealEvent(ValueCard card) {
		super(card);
	}

	public EventType getType() {
		return EventType.HEAL_PLAYER;
	}

	@Override
	public int drawPlayInfo(SpriteBatch batcher, int line, Rectangle cardArea) {
		drawLine(
				batcher,
				"Heal + "
						+ ((ValueCard) card).getValue(getType(),
								ValueType.DURATION), line, cardArea);
		return 1;
	}

}
