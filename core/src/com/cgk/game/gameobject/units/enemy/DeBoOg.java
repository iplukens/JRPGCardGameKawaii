package com.cgk.game.gameobject.units.enemy;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.cgk.game.event.AttackPlayerEvent;
import com.cgk.game.event.EventType;
import com.cgk.game.event.cardevents.AttackAdditiveEvent;
import com.cgk.game.event.cardevents.AttackMultiplierEvent;
import com.cgk.game.gameobject.eventresponses.SendEventResponse;
import com.cgk.game.gameobject.units.UnitAttack.AttackType;
import com.cgk.game.system.Asset;
import com.cgk.game.system.EventQueue;

public class DeBoOg extends Enemy {

	Asset<Texture> deboogTexture;

	public DeBoOg(EventQueue eventQueue) {
		super(eventQueue);
		attackType = AttackType.BLUE;
	}

	@Override
	public void draw(SpriteBatch batcher, TextureAtlas atlas) {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void erase() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void setupEventResponses() {
		super.setupEventResponses();
		addResponse(EventType.PLAY, new SendEventResponse(
				new AttackAdditiveEvent(50)));
		addResponse(EventType.ATTACK_PLAYER, new SendEventResponse(
				new AttackPlayerEvent(this)));
		addResponse(EventType.ADD_BUFF, new SendEventResponse(
				new AttackMultiplierEvent(50)));
	}

    @Override
    protected void setupAssets() {
		textureAssets.add(deboogTexture);
    }

}
