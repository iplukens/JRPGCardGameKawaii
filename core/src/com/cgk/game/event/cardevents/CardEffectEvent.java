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
	 * 
	 * @param start
	 *            - where to draw.... TODO
	 */
	public abstract void drawPlayInfo(int start);
}
