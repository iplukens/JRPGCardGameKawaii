package com.cgk.game.gameobject.card;

import com.cgk.game.gameobject.strategy.AttackTypeStrategyModifier;
import com.cgk.game.gameobject.strategy.Strategy;
import com.cgk.game.gameobject.units.UnitAttack.AttackType;

/**
 * @author ianlukens May 21, 2015
 *
 */
public class AttackTypeStrategy extends Strategy {

	private AttackType baseStartingCardType;
	private AttackType baseEndingCardType;

	public AttackTypeStrategy(AttackType startingCardType,
			AttackType endingCardType) {
		super();
		this.baseStartingCardType = startingCardType;
		this.baseEndingCardType = endingCardType;
	}

	public AttackType getStartingCardType() {
		AttackType type;
		if ((type = getTemporaryStartingType()) != null) {
			return type;
		}
		return baseStartingCardType;
	}

	private AttackType getTemporaryStartingType() {
		for (int i = modifiers.size() - 1; i >= 0; i--) {
			AttackTypeStrategyModifier modifier = (AttackTypeStrategyModifier) modifiers
					.get(i);
			if (modifier.modifiesStartingColor()) {
				return modifier.getStartingColor();
			}
		}
		return null;
	}

	public AttackType getEndingCardType() {
		AttackType type;
		if ((type = getTemporaryEndingType()) != null) {
			return type;
		}
		return baseEndingCardType;
	}

	private AttackType getTemporaryEndingType() {
		for (int i = modifiers.size() - 1; i >= 0; i--) {
			AttackTypeStrategyModifier modifier = (AttackTypeStrategyModifier) modifiers
					.get(i);
			if (modifier.modifiesEndingColor()) {
				return modifier.getEndingColor();
			}
		}
		return null;
	}

	public void setBaseStartingCardType(AttackType startingCardType) {
		this.baseStartingCardType = startingCardType;
	}

	public AttackType getBaseEndingCardType() {
		return baseEndingCardType;
	}

	public void setBaseEndingCardType(AttackType endingCardType) {
		this.baseEndingCardType = endingCardType;
	}

}
