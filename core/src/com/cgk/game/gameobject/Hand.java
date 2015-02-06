package com.cgk.game.gameobject;

import java.util.List;

import com.cgk.game.event.DiscardEvent;
import com.cgk.game.gameobject.card.Card;
import com.cgk.game.system.EventQueue;

public class Hand extends GameObject{

	public Hand(EventQueue eventQueue) {
		super(eventQueue);
	}

	private List<Card> cards;
	
	public void discard(Card card) throws InterruptedException{
		sendEvent(new DiscardEvent(card));
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
