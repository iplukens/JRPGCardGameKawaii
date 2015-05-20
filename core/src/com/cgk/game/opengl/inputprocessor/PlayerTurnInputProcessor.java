package com.cgk.game.opengl.inputprocessor;

import com.badlogic.gdx.math.Vector2;
import com.cgk.game.system.Battlefield;

/**
 * @author ianlukens Apr 29, 2015
 *
 */
public class PlayerTurnInputProcessor extends TouchInputProcessor {

	private Battlefield battlefield;

	/**
	 * @param battlefield
	 */
	public PlayerTurnInputProcessor(Battlefield battlefield) {
		this.battlefield = battlefield;
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

}
