package com.cgk.game.event;

public interface GameEvent extends Cloneable {

	public EventType getType();
	
	public GameEvent cloneEvent();
}
