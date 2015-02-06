package com.cgk.game.gameobject.units.enemy;

import com.cgk.game.event.AttackPlayerEvent;
import com.cgk.game.gameobject.units.UnitObject;
import com.cgk.game.system.EventQueue;

public abstract class Enemy extends UnitObject {

	public Enemy() {
		super();
	}

	public Enemy(EventQueue eventQueue) {
		super(eventQueue);
	}

	public void sendAttackEvent() {
		sendEvent(new AttackPlayerEvent(getAttack()));
	}
}
