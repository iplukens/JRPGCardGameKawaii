package com.cgk.game.gameobject.units.strategy;

import java.util.HashMap;
import java.util.Map;

import com.cgk.game.gameobject.strategy.Strategy;
import com.cgk.game.gameobject.units.UnitAttack;
import com.cgk.game.gameobject.units.UnitAttack.AttackType;

/**
 * @author ianlukens May 22, 2015
 *
 */
public class ResistancesStrategy extends Strategy<ResistanceStrategyModifier> {
	protected Map<AttackType, Double> baseResistances;

	public ResistancesStrategy() {
		setUpBaseResistances();
	}

	protected void setUpBaseResistances() {
		baseResistances = new HashMap<UnitAttack.AttackType, Double>();
		AttackType[] resistanceTypes = AttackType.values();
		double resistanceBaseValue = 1.0;
		for (AttackType type : resistanceTypes) {
			baseResistances.put(type, resistanceBaseValue);
		}
	}

	public double get(AttackType attackType) {
		Map<AttackType, Double> modifiedResistances = new HashMap<UnitAttack.AttackType, Double>(
				baseResistances);
		for (ResistanceStrategyModifier modifier : modifiers) {
			modifiedResistances = modifier.adjustValue(modifiedResistances);
		}
		return modifiedResistances.get(attackType);
	}

	public void setBaseResistanceTo(AttackType attackType, Double newValue) {
		baseResistances.put(attackType, newValue);
	}

}
