package com.cgk.game.gameobject.strategy;

/**
 * @author ianlukens May 22, 2015
 *
 */
public abstract class Modifier {

	private int duration;

	public Modifier(int duration) {
		this.duration = duration;
	}

	public void updateDuration() {
		updateDuration(1);
	}

	public void updateDuration(int i) {
		duration -= i;
	}

	public boolean isExpired() {
		return duration <= 0;
	}
}
