package com.cgk.game.event;

import com.cgk.game.gameobject.units.UnitAttack;

public class AttackEnemyEvent extends AttackEvent implements GameEvent {

	// TODO implement targeting
	public AttackEnemyEvent(UnitAttack attack) {
		super(attack);
	}

	@Override
	public EventType getType() {
		return EventType.ATTACK_ENEMY;
	}

}
