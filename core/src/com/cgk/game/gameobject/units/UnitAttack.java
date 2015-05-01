package com.cgk.game.gameobject.units;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.Color;

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
		BLUE, PURPLE, YELLOW, RED, GREEN, GREY;

		public Color getColorTint() {
			Color color;
			switch (this) {
			case BLUE:
				color = Color.BLUE;
				break;
			case GREEN:
				color = Color.GREEN;
				break;
			case GREY:
				color = Color.GRAY;
				break;
			case PURPLE:
				color = Color.PURPLE;
				break;
			case RED:
				color = Color.RED;
				break;
			case YELLOW:
				color = Color.YELLOW;
				break;
			default:
				color = Color.WHITE;
				break;
			}
			return color;
		}

		public static List<AttackType> getTypes() {
			List<AttackType> attackTypes = new ArrayList<>();
			AttackType[] values = AttackType.values();
			for (int i = 0; i < values.length; i++) {
				if (values[i] != AttackType.GREY) {
					attackTypes.add(values[i]);
				}
			}
			return attackTypes;
		}
	}

}
