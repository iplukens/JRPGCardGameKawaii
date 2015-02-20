package com.cgk.game.event;

import com.cgk.game.gameobject.units.enemy.Enemy;

public class DeadEnemyEvent extends BaseEvent implements GameEvent {

	private Enemy enemy;

	public DeadEnemyEvent(Enemy enemy) {
		this.enemy = enemy;
	}

	@Override
	public EventType getType() {
		return EventType.DEAD_ENEMY;
	}

	public Enemy getEnemy() {
		return enemy;
	}

}
