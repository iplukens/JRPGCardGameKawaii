package com.cgk.game.opengl;

import com.badlogic.gdx.ScreenAdapter;
import com.cgk.game.CardGameKawaii;

/**
 * @author ianlukens Jun 3, 2015
 *
 */
public abstract class CardGameKawaiiScreen extends ScreenAdapter {

	protected final CardGameKawaii game;

	public CardGameKawaiiScreen(CardGameKawaii game) {
		this.game = game;
	}

	public void changeScreen(CardGameKawaiiScreen screen) {
		disposeOfAssets();
		screen.initialize();
		System.out.println("Changing to new screen "
				+ screen.getClass().toString());
		game.setScreen(screen);
	}

	protected abstract void disposeOfAssets();

	public abstract void initialize();

	public CardGameKawaii getGame() {
		return game;
	}
}
