package com.cgk.game.event.cardevents;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.cgk.game.event.EventType;
import com.cgk.game.gameobject.card.ValueCard;
import com.cgk.game.gameobject.card.ValueType;
import com.cgk.game.gameobject.units.UnitAttack.AttackType;
import com.cgk.game.gameobject.units.strategy.AdditiveResistanceModifier;

public class AddResistanceEvent extends
		ModifierEvent<AdditiveResistanceModifier> {

	private AttackType resistanceType;

	public AddResistanceEvent(AttackType resistanceType, ValueCard card) {
		super(card);
		this.resistanceType = resistanceType;
	}

	public EventType getType() {
		return EventType.ADD_RESISTANCE_MOD;
	}

	@Override
	public int drawPlayInfo(SpriteBatch batcher, int line, Rectangle cardArea) {
		drawLine(
				batcher,
				resistanceType.toString()
						+ " resistance +"
						+ ((ValueCard) card).getValue(getType(),
								ValueType.STRENGTH)
						+ " for "
						+ ((ValueCard) card).getValue(getType(),
								ValueType.DURATION), line, cardArea);
		return 1;
	}

	@Override
	public AdditiveResistanceModifier getModifier() {
		return new AdditiveResistanceModifier(resistanceType,
				((ValueCard) card).getValue(getType(), ValueType.STRENGTH),
				((ValueCard) card).getValue(getType(), ValueType.DURATION));
	}

}
