package com.cgk.game.gameobject;

import com.cgk.game.gameobject.card.Card;
import com.cgk.game.system.EventQueue;
import java.util.ArrayList;
import java.util.List;

public class Deck extends GameObject {

    List<Card> cards = new ArrayList<>();
    
    public Deck(EventQueue eventQueue) {
        super(eventQueue);
    }

    public void setCards(List<Card> cards){
        this.cards = cards;
    }
    
    @Override
    public void draw() {
		// TODO Auto-generated method stub

    }

    @Override
    protected void setUpEventResponses() {
        // TODO Auto-generated method stub	
    }

    public List<Card> getCards() {
        return cards;
    }

}
