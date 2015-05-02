package com.cgk.game.system;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.cgk.game.gameobject.GameObject;
import com.cgk.game.util.Constants;

/**
 * @author ianlukens May 1, 2015
 *
 */
public class PlayerTurnTimer extends GameObject {

	private float baseTurnTime;
	private float baseDropTime;
	private float currentDropTime;
	private float baseAddTime;
	private float currentAddTime;
	private float timeRemaining;

	/**
	 * 
	 * @param queue
	 * @param baseTurnTime
	 *            - base time in seconds for a player turn
	 * @param baseDroppedTime
	 *            - base time in seconds dropped when dropping a combo
	 * @param baseAddedTime
	 *            - base time in seconds added when getting combos
	 */
	public PlayerTurnTimer(EventQueue queue, float baseTurnTime,
			float baseDroppedTime, float baseAddedTime) {
		super(queue);
		this.setBaseTurnTime(baseTurnTime);
		this.baseDropTime = baseDroppedTime;
		this.baseAddTime = baseAddedTime;
	}

	public void startTimer() {
		timeRemaining = baseTurnTime;
		currentDropTime = baseDropTime;
		currentAddTime = baseAddTime;
	}

	public void dropTime() {
		timeRemaining -= currentDropTime;
	}

	public void addTime() {
		timeRemaining += currentAddTime;
	}

	public void addToDropTime(float value) {
		currentDropTime += value;
	}

	public void subtractFromDropTime(float value) {
		currentDropTime -= value;
	}

	public void addToAddTime(float value) {
		currentAddTime += value;
	}

	public void subtractFromAddTime(float value) {
		currentAddTime -= value;
	}

	/**
	 * 
	 * @param timePassed
	 *            - time passed in seconds
	 */
	public void update(float timePassed) {
		// logInfo("Time Remaining:" + timeRemaining);
		timeRemaining -= timePassed;
	}

	public boolean turnOver() {
		return timeRemaining <= 0;
	}

	@Override
	protected void setupEventResponses() {
		// TODO Auto-generated method stub

	}

	@Override
	public void draw(SpriteBatch batcher, TextureAtlas atlas) {
		String timerString = getTimerString();
		Constants.COMBO_BUBBLE_FONT.draw(batcher, timerString, 0,
				Constants.SCREEN_HEIGHT);
	}

	public String getTimerString() {
		if (!turnOver()) {
			String minutes = getMinutes();
			String seconds = getSeconds(minutes);
			String milliseconds = getMilliseconds(minutes, seconds);
			return minutes + ":" + seconds + ":" + milliseconds;
		} else {
			return "--Turn Over--";
		}
	}

	private String getMinutes() {
		int minutesFloat = (int) (timeRemaining / 60);
		return "" + minutesFloat;
	}

	private String getSeconds(String minutes) {
		int seconds = (int) (timeRemaining - (Integer.parseInt(minutes) * 60));
		String secondsString = "" + seconds;
		if (seconds < 10) {
			secondsString = "0" + secondsString;
		}
		return secondsString;
	}

	private String getMilliseconds(String minutes, String seconds) {
		float millis = (timeRemaining - (Integer.parseInt(minutes) * 60) - (Integer
				.parseInt(seconds))) * 1000;
		int millisInt = (int) millis;
		String millisString = "" + millisInt;
		if (millis < 10) {
			millisString = "00" + millisInt;
		} else if (millis < 100) {
			millisString = "0" + millisInt;
		}
		return millisString;
	}

	@Override
	public void erase() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void setupAssets() {
		// TODO Auto-generated method stub

	}

	public float getBaseTurnTime() {
		return baseTurnTime;
	}

	public void setBaseTurnTime(float baseTurnTime) {
		this.baseTurnTime = baseTurnTime;
	}

}
