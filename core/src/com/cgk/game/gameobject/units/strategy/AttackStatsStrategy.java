package com.cgk.game.gameobject.units.strategy;

import com.cgk.game.gameobject.strategy.AttackTypeStrategyModifier;
import com.cgk.game.gameobject.strategy.Strategy;
import com.cgk.game.gameobject.units.UnitAttack;
import com.cgk.game.gameobject.units.UnitAttack.AttackType;

/**
 * @author ianlukens May 21, 2015
 *
 */
public class AttackStatsStrategy extends Strategy<AttackTypeStrategyModifier> {

	private int baseAttack = 0;
	private int tempAttackAdditive = 0;
	private int tempAttackMultiplicative = 1;
	private AttackType attackType;

	public AttackStatsStrategy(int baseAttack, AttackType attackType) {
		super();
		this.baseAttack = baseAttack;
		this.attackType = attackType;
	}

	public int getBaseAttack() {
		return baseAttack;
	}

	public void setBaseAttack(int baseAttack) {
		this.baseAttack = baseAttack;
	}

	public int getTempAttackAdditive() {
		return tempAttackAdditive;
	}

	public void setTempAttackAdditive(int tempAttackAdditive) {
		this.tempAttackAdditive = tempAttackAdditive;
	}

	public int getTempAttackMultiplicative() {
		return tempAttackMultiplicative;
	}

	public void setTempAttackMultiplicative(int tempAttackMultiplicative) {
		this.tempAttackMultiplicative = tempAttackMultiplicative;
	}

	public AttackType getAttackType() {
		return attackType;
	}

	public void setAttackType(AttackType attackType) {
		this.attackType = attackType;
	}

	public UnitAttack getAttack() {
		return new UnitAttack(getAttackValue(), attackType);
	}

	public int getAttackValue() {
		return (getBaseAttack() + getTempAttackAdditive())
				* getTempAttackMultiplicative();
	}

}
