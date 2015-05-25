package com.cgk.game.event;

import com.cgk.game.gameobject.units.enemy.Enemy;
import com.cgk.game.gameobject.units.hero.Hero;

public class AttackEnemyEvent extends AttackEvent implements GameEvent {

	public AttackEnemyEvent(Hero unit, Enemy enemy) {
		super(unit, enemy);
	}

	@Override
	public EventType getType() {
		return EventType.ATTACK_ENEMY;
	}

	@Override
	public int getPriority() {
		return 2;
	}

}
