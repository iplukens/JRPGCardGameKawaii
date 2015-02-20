package com.cgk.game.event;

public class EndEnemyTurn extends BaseEvent implements GameEvent {

	@Override
	public EventType getType() {
		return EventType.END_ENEMY_TURN;
	}

}
