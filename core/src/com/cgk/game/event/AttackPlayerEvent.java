package com.cgk.game.event;

import com.cgk.game.gameobject.GameObject;
import com.cgk.game.gameobject.units.UnitObject;
import com.cgk.game.gameobject.units.enemy.Enemy;
import com.cgk.game.gameobject.units.hero.Hero;

public class AttackPlayerEvent extends AttackEvent {

	public AttackPlayerEvent(Enemy enemy) {
		super(enemy, null);
	}

	public AttackPlayerEvent(Enemy enemy, Hero hero) {
		super(enemy, hero);
	}

	@Override
	public UnitObject getTarget() {
		if (target == null) {
			return GameObject.getBattlefield().getEnemyTarget();
		} else {
			return target;
		}
	}

	@Override
	public EventType getType() {
		return EventType.ATTACK_PLAYER;
	}

}
