package com.cgk.game.gameobject.units.enemy;

import java.util.ArrayList;
import java.util.List;

import com.cgk.game.event.AttackPlayerEvent;
import com.cgk.game.event.DeadEnemyEvent;
import com.cgk.game.event.EventType;
import com.cgk.game.event.GameEvent;
import com.cgk.game.gameobject.units.UnitObject;
import com.cgk.game.gameobject.units.eventresponses.ProcessAttackResponse;
import com.cgk.game.system.EventQueue;

public abstract class Enemy extends UnitObject {

	protected List<GameEvent> onDeathEvents;

	public Enemy() {
		super();
	}

	public Enemy(EventQueue eventQueue) {
		super(eventQueue);
		onDeathEvents = new ArrayList<>();
	}

	public void sendAttackEvent() {
		sendEvent(new AttackPlayerEvent(getAttack()));
	}

	protected void sendOnDeathEvents() {
		for (GameEvent event : onDeathEvents) {
			sendEvent(event);
		}
	}

	protected void setUpOnDeathEvents() {
		onDeathEvents = new ArrayList<>();
		onDeathEvents.add(new DeadEnemyEvent(this));
	}

	@Override
	protected void setupEventResponses() {
		super.setupEventResponses();
		addResponse(EventType.ATTACK_ENEMY, new ProcessAttackResponse());
	}

}
