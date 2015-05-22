package com.cgk.game.gameobject.strategy;

import com.cgk.game.gameobject.units.UnitAttack.AttackType;

/**
 * @author ianlukens May 22, 2015
 *
 */
public class AttackTypeStrategyModifier extends Modifier {

	private AttackType startingColor;
	private AttackType endingColor;

	/**
	 * 
	 * @param startingColor
	 * @param endingColor
	 * @param duration
	 */
	public AttackTypeStrategyModifier(AttackType startingColor,
			AttackType endingColor, int duration) {
		super(duration);
		this.startingColor = startingColor;
		this.endingColor = endingColor;
	}

	public AttackType getStartingColor() {
		return startingColor;
	}

	public AttackType getEndingColor() {
		return endingColor;
	}

	public boolean modifiesStartingColor() {
		return startingColor != null;
	}

	public boolean modifiesEndingColor() {
		return endingColor != null;
	}

}
