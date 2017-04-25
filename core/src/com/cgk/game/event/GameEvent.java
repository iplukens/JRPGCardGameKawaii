package com.cgk.game.event;

import com.cgk.game.gameobject.GameObject;

public interface GameEvent extends Cloneable {

	public EventType getType();
	
	public GameEvent cloneEvent();

	public int getPriority();

	public GameObject getOriginObject();
}
