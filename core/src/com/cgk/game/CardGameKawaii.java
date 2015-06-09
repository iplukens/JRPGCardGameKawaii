package com.cgk.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.cgk.game.opengl.CharacterSelectScreen;
import com.cgk.game.util.BattlefieldConstants;

public class CardGameKawaii extends Game {
	public SpriteBatch batcher;
	public Player player;
	public AssetManager manager;

	@Override
	public void create() {
		manager = new AssetManager();
		if (firstTimeOpen()) {
			player = createNewPlayerId();
		} else {
			player = retrievePlayer();
		}
		batcher = new SpriteBatch();
		float height = Gdx.graphics.getHeight();
		float width = Gdx.graphics.getWidth();
		BattlefieldConstants.initialize(height, width);
		CharacterSelectScreen screen = new CharacterSelectScreen(this);
		screen.initialize();
		setScreen(screen);
	}

	private Player retrievePlayer() {
		// TODO Auto-generated method stub
		return new Player();
	}

	private Player createNewPlayerId() {
		// TODO Auto-generated method stub
		return new Player();
	}

	private boolean firstTimeOpen() {
		// TODO Auto-generated method stub
		return true;
	}
}
