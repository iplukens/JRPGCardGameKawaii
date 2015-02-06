package com.cgk.game.gameobject.card;

import com.cgk.game.event.AttackAdditiveEvent;
import com.cgk.game.event.GameEvent;
import com.cgk.game.system.EventQueue;

public class FuelTheFire extends Card {

	public FuelTheFire(EventQueue eventQueue) {
		super(eventQueue, 0, 1, "Fuel the Fire", "Fuels the fire");
		this.cardEvents.add(new AttackAdditiveEvent(50));
	}

	@Override
	public void draw() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void setUpEventResponses() {
		// TODO Auto-generated method stub
		
	}

}
