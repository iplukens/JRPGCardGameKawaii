package com.cgk.game.gameobject.units.strategy;

import java.util.Map;

import com.cgk.game.gameobject.units.UnitAttack.AttackType;

/**
 * @author ianlukens May 22, 2015
 *
 */
public class AdditiveResistanceModifier extends ResistanceStrategyModifier {

	private double amount;
	private AttackType resistanceType;

	public AdditiveResistanceModifier(AttackType resistanceType, double amount,
			int duration) {
		super(duration);
		this.setResistanceType(resistanceType);
		this.amount = amount;
	}

	@Override
	public Map<AttackType, Double> adjustValue(
			Map<AttackType, Double> currentResistances) {
		double newValue = currentResistances.get(getResistanceType()) + amount;
		currentResistances.put(getResistanceType(), newValue);
		return currentResistances;
	}

	public AttackType getResistanceType() {
		return resistanceType;
	}

	public void setResistanceType(AttackType resistanceType) {
		this.resistanceType = resistanceType;
	}

}
