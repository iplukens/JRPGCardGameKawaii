package com.cgk.game.event;

public class AttackPlayerEvent extends ValueEvent implements GameEvent {

	public AttackPlayerEvent(int value) {
		super(value);
	}

	@Override
	public EventType getType() {
		return EventType.ATTACK_PLAYER;
	}

}
