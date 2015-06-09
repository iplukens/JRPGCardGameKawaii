package com.cgk.game.opengl.inputprocessor;

import com.cgk.game.opengl.CardGameKawaiiScreen;
import com.cgk.game.opengl.MainMenuScreen;

/**
 * @author ianlukens Jun 4, 2015
 *
 */
public class TouchToMainMenuProcessor extends TouchInputProcessor {

	private CardGameKawaiiScreen currentScreen;

	public TouchToMainMenuProcessor(CardGameKawaiiScreen currentScreen) {
		this.currentScreen = currentScreen;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		currentScreen.changeScreen(new MainMenuScreen(currentScreen.getGame()));
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

}
