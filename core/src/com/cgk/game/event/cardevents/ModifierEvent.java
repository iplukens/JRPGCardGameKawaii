package com.cgk.game.event.cardevents;

import com.cgk.game.gameobject.card.ValueCard;
import com.cgk.game.gameobject.strategy.Modifier;

/**
 * @author ianlukens May 22, 2015
 *
 */
public abstract class ModifierEvent<T extends Modifier> extends CardEffectEvent {

	public ModifierEvent(ValueCard card) {
		this.card = card;
	}

	public abstract T getModifier();

}
