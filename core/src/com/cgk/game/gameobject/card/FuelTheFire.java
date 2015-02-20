package com.cgk.game.gameobject.card;

import com.cgk.game.event.cardevents.AttackAdditiveEvent;
import com.cgk.game.system.EventQueue;

public class FuelTheFire extends Card {

	public FuelTheFire(EventQueue eventQueue) {
		super(eventQueue, 0, 1, "Fuel the Fire", "Fuels the fire");
	}

	@Override
	protected void setUpEventResponses() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void setPlayEvents() {
		this.playEvents.add(new AttackAdditiveEvent(50));
	}

}
