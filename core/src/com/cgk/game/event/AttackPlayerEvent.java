package com.cgk.game.event;

import com.cgk.game.gameobject.GameObject;
import com.cgk.game.gameobject.units.enemy.Enemy;
import com.cgk.game.gameobject.units.hero.Hero;

public class AttackPlayerEvent extends AttackEvent {

	public AttackPlayerEvent(Enemy enemy) {
		super(enemy, enemy);
		targets.clear();
		targets.add(GameObject.getBattlefield().getEnemyTarget());
	}

	public AttackPlayerEvent(Enemy enemy, Hero hero) {
		super(enemy, hero);
	}

	@Override
	public EventType getType() {
		return EventType.ATTACK_PLAYER;
	}

}
