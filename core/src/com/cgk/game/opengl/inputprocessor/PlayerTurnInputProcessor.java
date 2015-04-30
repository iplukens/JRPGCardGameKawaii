package com.cgk.game.opengl.inputprocessor;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.cgk.game.system.Battlefield;

/**
 * @author ianlukens Apr 29, 2015
 *
 */
public class PlayerTurnInputProcessor implements InputProcessor {

	private Battlefield battlefield;

	/**
	 * @param battlefield
	 */
	public PlayerTurnInputProcessor(Battlefield battlefield) {
		this.battlefield = battlefield;
	}

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
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		Vector2 touchPos = new Vector2();
		touchPos.set(screenX, screenY);
		battlefield.getHand().processJustTouched(touchPos);
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		Vector2 releasePos = new Vector2();
		releasePos.set(screenX, screenY);
		battlefield.getHand().resetTouches(releasePos);
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		Vector2 touchPos = new Vector2();
		touchPos.set(screenX, screenY);
		battlefield.getHand().processTouch(touchPos);
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

}
