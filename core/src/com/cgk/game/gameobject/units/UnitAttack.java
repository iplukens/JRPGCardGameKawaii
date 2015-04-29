package com.cgk.game.gameobject.units;

public class UnitAttack {

	private int value;
	private AttackType type;

	public UnitAttack(int value, AttackType type) {
		this.setValue(value);
		this.setType(type);
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public AttackType getType() {
		return type;
	}

	public void setType(AttackType type) {
		this.type = type;
	}

	public enum AttackType {
		BLUE, PURPLE, YELLOW, RED, GREEN;
	}
}
