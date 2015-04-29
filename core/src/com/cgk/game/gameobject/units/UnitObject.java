package com.cgk.game.gameobject.units;

import java.util.HashMap;
import java.util.Map;

import com.cgk.game.event.EventType;
import com.cgk.game.event.GameEvent;
import com.cgk.game.gameobject.GameObject;
import com.cgk.game.gameobject.units.UnitAttack.AttackType;
import com.cgk.game.gameobject.units.eventresponses.UnitAttackAdditiveEventResponse;
import com.cgk.game.gameobject.units.eventresponses.UnitAttackMultiplierEventResponse;
import com.cgk.game.system.EventQueue;

/**
 * generic class representing the on-board units in the game
 * 
 * @author ianlukens
 *
 */
public abstract class UnitObject extends GameObject {
	protected int health = 1;
	protected int baseAttack = 0;
	protected int tempAttackAdditive = 0;
	protected int tempAttackMultiplicative = 1;
	protected AttackType attackType;
	protected Map<AttackType, Double> resistances;

	public UnitObject() {
		super();
		setUpResistances();
	}

	public UnitObject(EventQueue queue) {
		super(queue);
		setUpResistances();
	}

	protected void setUpResistances() {
		resistances = new HashMap<UnitAttack.AttackType, Double>();
		AttackType[] resistanceTypes = AttackType.values();
		double resistanceBaseValue = 1.0;
		for (AttackType type : resistanceTypes) {
			resistances.put(type, resistanceBaseValue);
		}
	}

	@Override
	public void respondToEvent(GameEvent event) {
		super.respondToEvent(event);
		if (isDead()) {
			unregister();
			sendOnDeathEvents();
		}
	}

	/**
	 * 
	 * @param attackType
	 * @return
	 */
	public double getResistanceTo(AttackType attackType) {
		return resistances.get(attackType);
	}

	/**
	 * 
	 * @param attackType
	 * @param newValue
	 */
	public void setResistanceTo(AttackType attackType, Double newValue) {
		resistances.put(attackType, newValue);
	}

	/**
	 * add to the current resistance value
	 * 
	 * @param attackType
	 * @param addedValue
	 */
	public void addResistanceTo(AttackType attackType, Double addedValue) {
		resistances.put(attackType, resistances.get(attackType) + addedValue);
	}

	/**
	 * events the unit sends when it dies
	 */
	protected abstract void sendOnDeathEvents();

	@Override
	protected void setupEventResponses() {
		addResponse(EventType.ADD_BUFF, new UnitAttackAdditiveEventResponse());
		addResponse(EventType.MULT_BUFF,
				new UnitAttackMultiplierEventResponse());
	}

	public UnitAttack getAttack() {
		return new UnitAttack(getAttackValue(), attackType);
	}

	public int getAttackValue() {
		return (getBaseAttack() + getTempAttackAdditive())
				* getTempAttackMultiplicative();
	}

	public abstract void sendAttackEvent();

	protected boolean isDead() {
		return health <= 0;
	}

	/*
	 * getters and setters
	 */

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

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	/**
	 * @return the attackType
	 */
	public AttackType getAttackType() {
		return attackType;
	}

	/**
	 * @param attackType
	 *            the attackType to set
	 */
	public void setAttackType(AttackType attackType) {
		this.attackType = attackType;
	}

	public void processAttack(UnitAttack attack) {
		double resistanceValue = getResistanceTo(attack.getType());
		this.health -= attack.getValue() * resistanceValue;
	}

}
