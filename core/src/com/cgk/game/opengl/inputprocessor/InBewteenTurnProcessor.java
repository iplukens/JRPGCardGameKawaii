package com.cgk.game.opengl.inputprocessor;

import com.badlogic.gdx.math.Vector2;
import com.cgk.game.system.Battlefield;
import com.cgk.game.system.GameState;
import com.cgk.game.util.BattlefieldConstants;

/**
 * @author ianlukens May 6, 2015
 *
 */
public class InBewteenTurnProcessor extends TouchInputProcessor {

	private Vector2 touchDownPos;
	private Battlefield battlefield;

	public InBewteenTurnProcessor(Battlefield battlefield) {
		this.battlefield = battlefield;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		touchDownPos = new Vector2(screenX, screenY);
		Vector2 newTouch = adjustToOpenGLCoords(touchDownPos);
		boolean setTarget = battlefield.processTarget(newTouch);
		if (!setTarget) {
			battlefield.setCurrentTarget(null);
		}
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		if (battlefield.getGameState() == GameState.BETWEEN_TURNS) {
			startTurn(screenX);
		}
		return true;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		if (battlefield.getGameState() == GameState.BETWEEN_TURNS) {
			startTurn(screenX);
		}
		return false;
	}

	private void startTurn(float screenX) {
		if (touchDownPos != null
				&& touchDownPos.x + BattlefieldConstants.FLIP_DECK_X < screenX) {
			battlefield.getHand().flip();
			battlefield.startPlayerTurn();
			touchDownPos = null;
		}
	}

}
