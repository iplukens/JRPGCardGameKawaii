package com.cgk.game.gameobject.enemy;

import com.cgk.game.event.AttackAdditiveEvent;
import com.cgk.game.event.AttackMultiplierEvent;
import com.cgk.game.event.EventQueue;
import com.cgk.game.event.GameEvent;
import com.cgk.game.gameobject.GameObject;

public abstract class Enemy extends GameObject {
	int baseAttack = 0;
	int tempAttackAdditive = 0;
	int tempAttackMultiplicative = 1;

	public Enemy(EventQueue eventQueue) {
		super(eventQueue);
	}

	public void respondToEvent(AttackAdditiveEvent event) {
		tempAttackAdditive += event.getValue();	
	}
	
	public void responseToEvent(AttackMultiplierEvent event) {
		tempAttackMultiplicative *= event.getValue();
	}
	
	public int getAttack(){
		return (baseAttack + tempAttackAdditive ) * tempAttackMultiplicative;
	}
}
