package com.cgk.game.system;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.cgk.game.event.EndHeroTurnEvent;
import com.cgk.game.event.EventType;
import com.cgk.game.gameobject.GameObject;
import com.cgk.game.system.eventresponses.AddTimeResponse;
import com.cgk.game.system.eventresponses.LoseTimeResponse;
import com.cgk.game.util.BattlefieldConstants;

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
	public PlayerTurnTimer(float baseTurnTime,
			float baseDroppedTime, float baseAddedTime) {
		super();
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
		dropTime(currentDropTime);
	}

	public void dropTime(float time) {
		timeRemaining -= time;
	}

	public void addTime() {
		addTime(currentAddTime);
	}

	public void addTime(float time) {
		timeRemaining += time;
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
		if (turnOver()) {
			sendEvent(new EndHeroTurnEvent());
		}
	}

	public boolean turnOver() {
		return timeRemaining <= 0;
	}

	@Override
	protected void setupEventResponses() {
		addResponse(EventType.DROP_COMBO, new LoseTimeResponse());
		addResponse(EventType.TYPE_COMBO, new AddTimeResponse());
		addResponse(EventType.RESOURCE_COMBO_EVENT, new AddTimeResponse());
		addResponse(EventType.SUPER_COMBO, new AddTimeResponse());
		addResponse(EventType.SUPER_COMBO, new AddTimeResponse());
	}

	@Override
	public void draw(SpriteBatch batcher, TextureAtlas atlas) {
		String timerString = getTimerString();
		BattlefieldConstants.COMBO_BUBBLE_FONT.draw(batcher, timerString, 0,
				BattlefieldConstants.SCREEN_HEIGHT);
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

	public float getTimeRemaining() {
		return timeRemaining;
	}

	@Override
	public void erase() {
		// TODO Auto-generated method stub

	}

	public float getBaseTurnTime() {
		return baseTurnTime;
	}

	public void setBaseTurnTime(float baseTurnTime) {
		this.baseTurnTime = baseTurnTime;
	}

	@Override
	public List<Asset<Texture>> getTextureAssets() {
		// TODO Auto-generated method stub
		return new ArrayList<Asset<Texture>>();
	}

	@Override
	public List<Asset<Sound>> getSoundAssets() {
		// TODO Auto-generated method stub
		return new ArrayList<Asset<Sound>>();
	}

	@Override
	public List<Asset<Music>> getMusicAssets() {
		// TODO Auto-generated method stub
		return new ArrayList<Asset<Music>>();
	}

}
