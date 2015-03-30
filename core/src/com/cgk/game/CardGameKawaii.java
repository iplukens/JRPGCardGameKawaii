package com.cgk.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.cgk.game.opengl.BattlefieldScreen;
import com.cgk.game.system.Battlefield;

public class CardGameKawaii extends Game {
	public SpriteBatch batcher;

	@Override
	public void create() {
		batcher = new SpriteBatch();
		// displayLoadScreen
		Battlefield battlefield = new Battlefield();
		battlefield.setMusicFileLocation("song.mp3");
		BattlefieldScreen screen = new BattlefieldScreen(this, battlefield);
		setScreen(screen);
	}
}
