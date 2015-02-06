package com.cgk.game.event;

public class AttackEnemyEvent extends ValueEvent implements GameEvent {

	public AttackEnemyEvent(int value) {
		super(value);
	}

	@Override
	public EventType getType() {
		return EventType.ATTACK_ENEMY;
	}

}
