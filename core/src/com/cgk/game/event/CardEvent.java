package com.cgk.game.event;

import com.cgk.game.gameobject.GameObject;
import com.cgk.game.gameobject.card.Card;

public abstract class CardEvent extends BaseEvent {

	protected Card card;
	
	public CardEvent(Card card){
		this.card = card;
	}
	
	public Card getCard(){
		return card;
	}
	
	public void setCard(Card card){
		this.card = card;
	}

	@Override
	public GameObject getOriginObject() {
		return card;
	}

}
