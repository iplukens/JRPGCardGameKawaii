package com.cgk.game.event;

import com.cgk.game.gameobject.units.hero.Hero;

public class AttackEnemyEvent extends AttackEvent implements GameEvent {

	// TODO implement targeting
	public AttackEnemyEvent(Hero unit) {
		super(unit);
	}

	@Override
	public EventType getType() {
		return EventType.ATTACK_ENEMY;
	}

}
