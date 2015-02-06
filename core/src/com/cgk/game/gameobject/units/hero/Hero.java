package com.cgk.game.gameobject.units.hero;

import com.cgk.game.event.AttackEnemyEvent;
import com.cgk.game.gameobject.units.UnitObject;
import com.cgk.game.system.EventQueue;

public class Hero extends UnitObject {

	public Hero(EventQueue eventQueue) {
		super(eventQueue);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw() {
		// TODO Auto-generated method stub		
	}

	@Override
	protected void setUpEventResponses() {
		super.setUpEventResponses();
	}

	@Override
	public void sendAttackEvent() {
		sendEvent(new AttackEnemyEvent(getAttack()));
	}

}
