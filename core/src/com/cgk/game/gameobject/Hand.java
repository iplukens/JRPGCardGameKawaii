package com.cgk.game.gameobject;

import java.util.List;

import com.cgk.game.event.DiscardEvent;
import com.cgk.game.event.EventQueue;
import com.cgk.game.event.GameEvent;
import com.cgk.game.gameobject.card.Card;

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
	public void respondToEvent(GameEvent event) {
		// TODO Auto-generated method stub
		
	}

}
