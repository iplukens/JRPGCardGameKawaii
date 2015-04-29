package com.cgk.game.gameobject.units.enemy;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.cgk.game.event.AttackAdditiveEvent;
import com.cgk.game.event.AttackMultiplierEvent;
import com.cgk.game.event.AttackPlayerEvent;
import com.cgk.game.event.EventType;
import com.cgk.game.gameobject.eventresponses.SendEventResponse;
import com.cgk.game.system.EventQueue;

public class DeBoOg extends Enemy {

	public DeBoOg(EventQueue eventQueue) {
		super(eventQueue);
	}

	@Override
	public void draw(SpriteBatch batcher, TextureAtlas atlas) {
		// TODO Auto-generated method stub
	}

	@Override
	protected void setupEventResponses() {
		super.setupEventResponses();
		addResponse(EventType.PLAY, new SendEventResponse(
				new AttackAdditiveEvent(50)));
		addResponse(EventType.ATTACK_PLAYER, new SendEventResponse(
				new AttackPlayerEvent(getAttack())));
		addResponse(EventType.ADD_BUFF, new SendEventResponse(
				new AttackMultiplierEvent(50)));
	}

    @Override
    protected void setupAssets() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
