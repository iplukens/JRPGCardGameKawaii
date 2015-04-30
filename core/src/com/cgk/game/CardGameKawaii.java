package com.cgk.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.cgk.game.opengl.BattlefieldScreen;
import com.cgk.game.system.Battlefield;
import com.cgk.game.system.PlayerAssets;
import com.cgk.game.util.Constants;

public class CardGameKawaii extends Game {
	public SpriteBatch batcher;

	@Override
	public void create() {
		batcher = new SpriteBatch();
		float height = Gdx.graphics.getHeight();
		float width = Gdx.graphics.getWidth();
		System.out.println(width + ", " + height);
		Constants.initialize(height, width);
		// displayLoadScreen
		PlayerAssets assets = PlayerAssets.getAssets();
		Battlefield battlefield = new Battlefield(assets, 1);
		battlefield.setMusicFileLocation("assets/song.mp3");
		BattlefieldScreen screen = new BattlefieldScreen(this, battlefield);
		setScreen(screen);
	}
}
