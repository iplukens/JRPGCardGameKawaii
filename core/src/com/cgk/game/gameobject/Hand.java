package com.cgk.game.gameobject;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import java.util.List;

import com.cgk.game.event.DiscardEvent;
import com.cgk.game.gameobject.card.Card;
import com.cgk.game.system.EventQueue;

public class Hand extends GameObject {

    public Hand(EventQueue eventQueue) {
        super(eventQueue);
    }

    private List<Card> cards;

    public void discard(Card card) throws InterruptedException {
        sendEvent(new DiscardEvent(card));
    }

    @Override
    public void draw(SpriteBatch batcher, TextureAtlas atlas) {
        for (Card card : cards){
            card.draw(batcher, atlas);
        }
    }

    @Override
    protected void setUpEventResponses() {
		// TODO Auto-generated method stub

    }

    @Override
    protected void setupAssets() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
