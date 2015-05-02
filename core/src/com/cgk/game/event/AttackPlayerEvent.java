package com.cgk.game.event;

import com.cgk.game.gameobject.units.enemy.Enemy;

public class AttackPlayerEvent extends AttackEvent {

	public AttackPlayerEvent(Enemy enemy) {
		super(enemy);
	}

	@Override
	public EventType getType() {
		return EventType.ATTACK_PLAYER;
	}

}
