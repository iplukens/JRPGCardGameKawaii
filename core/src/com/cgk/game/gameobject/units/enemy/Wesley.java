package com.cgk.game.gameobject.units.enemy;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.cgk.game.system.EventQueue;

public class Wesley extends Enemy {

	public Wesley() {
		super();
	}

	public Wesley(EventQueue eventQueue) {
		super(eventQueue);
	}

	@Override
	public void draw(SpriteBatch batcher, TextureAtlas atlas) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void setupEventResponses() {
		super.setupEventResponses();
	}

    @Override
    protected void setupAssets() {
		throw new UnsupportedOperationException("Not supported yet.");
		// To change body of generated methods, choose Tools | Templates.
    }

	@Override
	public void erase() {
		// TODO Auto-generated method stub
		
	}

}
