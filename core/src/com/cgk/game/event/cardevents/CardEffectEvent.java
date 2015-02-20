package com.cgk.game.event.cardevents;

import com.cgk.game.event.BaseEvent;

/**
 * events that happen upon playing cards
 * 
 * @author ianlukens
 *
 */
public abstract class CardEffectEvent extends BaseEvent {

	/**
	 * this puts the necessary text onto the card to indicate what happens when
	 * it's played
	 * 
	 * @param start
	 *            - where to draw.... TODO
	 */
	public abstract void drawPlayInfo(int start);

	/**
	 * number of lines the play info takes up
	 * 
	 * @return
	 */
	public int getPlayInfoSize() {
		return 1;
	}
}
