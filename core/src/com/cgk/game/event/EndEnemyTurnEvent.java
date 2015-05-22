package com.cgk.game.event;

public class EndEnemyTurnEvent extends BaseEvent implements GameEvent {

	@Override
	public EventType getType() {
		return EventType.END_ENEMY_TURN;
	}

}
