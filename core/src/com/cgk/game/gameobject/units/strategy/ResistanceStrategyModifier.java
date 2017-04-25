package com.cgk.game.gameobject.units.strategy;

import java.util.Map;

import com.cgk.game.gameobject.strategy.Modifier;
import com.cgk.game.gameobject.units.UnitAttack.AttackType;

/**
 * @author ianlukens May 22, 2015
 *
 */
public abstract class ResistanceStrategyModifier extends Modifier {

	public ResistanceStrategyModifier(int duration) {
		super(duration);
	}

	/**
	 * returns a modified table of the base resistance values according to the
	 * modifier
	 * 
	 * @param currentResistanceValue
	 * @return
	 */
	public abstract Map<AttackType, Double> adjustValue(
			Map<AttackType, Double> currentResistances);

}
