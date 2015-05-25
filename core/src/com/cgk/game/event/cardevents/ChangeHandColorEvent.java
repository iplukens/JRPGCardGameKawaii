package com.cgk.game.event.cardevents;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.cgk.game.event.EventType;
import com.cgk.game.gameobject.card.ValueCard;
import com.cgk.game.gameobject.card.ValueType;
import com.cgk.game.gameobject.strategy.AttackTypeStrategyModifier;
import com.cgk.game.gameobject.units.UnitAttack.AttackType;

/**
 * @author ianlukens May 21, 2015
 *
 */
public class ChangeHandColorEvent extends
		ModifierEvent<AttackTypeStrategyModifier> {

	private AttackType color;

	public ChangeHandColorEvent(ValueCard card, AttackType color) {
		super(card);
		this.color = color;
	}

	public AttackType getColor() {
		return color;
	}

	@Override
	public EventType getType() {
		return EventType.CHANGE_HAND_COLOR;
	}

	@Override
	public int drawPlayInfo(SpriteBatch batcher, int line, Rectangle cardArea) {
		drawLine(batcher, "Change hand to " + color.toString() + " for "
				+ ((ValueCard) card).getValue(getType(), ValueType.DURATION)
				+ " turns", line, cardArea);
		return 1;
	}

	@Override
	public AttackTypeStrategyModifier getModifier() {
		return new AttackTypeStrategyModifier(getColor(), getColor(),
				((ValueCard) card).getValue(getType(), ValueType.DURATION));
	}

}
