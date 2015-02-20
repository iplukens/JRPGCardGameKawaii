package com.cgk.game.gameobject.units.hero;

import com.cgk.game.event.AttackEnemyEvent;
import com.cgk.game.event.DefeatEvent;
import com.cgk.game.event.EventType;
import com.cgk.game.gameobject.units.UnitObject;
import com.cgk.game.gameobject.units.eventresponses.ProcessAttackResponse;
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
		addResponse(EventType.ATTACK_PLAYER, new ProcessAttackResponse());
	}

	@Override
	public void sendAttackEvent() {
		sendEvent(new AttackEnemyEvent(getAttack()));
	}

	@Override
	protected void sendOnDeathEvents() {
		sendEvent(new DefeatEvent());
	}

	@Override
	public void erase() {
		// TODO Auto-generated method stub
		
	}

}
