package com.cgk.game.gameobject.units;

import com.cgk.game.event.EventType;
import com.cgk.game.gameobject.GameObject;
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
	protected int baseAttack = 0;
	protected int tempAttackAdditive = 0;
	protected int tempAttackMultiplicative = 1;

	public UnitObject() {
		super();
	}

	public UnitObject(EventQueue queue) {
		super(queue);
	}

	@Override
	protected void setupEventResponses() {
		addResponse(EventType.ADD_BUFF, new UnitAttackAdditiveEventResponse());
		addResponse(EventType.MULT_BUFF, new UnitAttackMultiplierEventResponse());
	}

	public int getAttack() {
		return (getBaseAttack() + getTempAttackAdditive())
				* getTempAttackMultiplicative();
	}
	
	public abstract void sendAttackEvent();

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

}
