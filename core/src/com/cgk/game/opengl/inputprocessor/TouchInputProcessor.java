package com.cgk.game.opengl.inputprocessor;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.cgk.game.util.BattlefieldConstants;

/**
 * @author ianlukens
 * May 6, 2015
 *
 */
public abstract class TouchInputProcessor implements InputProcessor {

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

	protected Vector2 adjustToOpenGLCoords(Vector2 touchPos) {
		return new Vector2(touchPos.x, BattlefieldConstants.SCREEN_HEIGHT
				- touchPos.y);
	}

}
