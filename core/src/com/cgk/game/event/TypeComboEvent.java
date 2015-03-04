package com.cgk.game.event;

import com.cgk.game.gameobject.units.UnitAttack.AttackType;

public class TypeComboEvent extends BaseEvent {

	private AttackType type;

	public TypeComboEvent(AttackType startingCardType) {
		this.setType(startingCardType);
	}

	@Override
	public EventType getType() {
		return EventType.TYPE_COMBO;
	}

	public AttackType getAttackType() {
		return type;
	}

	public void setType(AttackType type) {
		this.type = type;
	}

}
