package com.cgk.game.event;


public class BuffAttackEvent implements GameEvent {

	int value;
	
	public BuffAttackEvent(int value) {
		this.value = value;
	}
	
}
