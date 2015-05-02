package com.cgk.game.event.cardevents;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.cgk.game.event.BaseEvent;
import com.cgk.game.util.CardConstants;
import com.cgk.game.util.Constants;

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
	 * @param batcher
	 * @param line
	 * @param cardArea
	 * @return - number of lines text took up
	 */
	public abstract int drawPlayInfo(SpriteBatch batcher, int line,
			Rectangle cardArea);

	protected void drawLine(SpriteBatch batcher, String text, int line,
			Rectangle cardArea) {
		Constants.COMBO_BUBBLE_FONT.draw(batcher, text, cardArea.x
				+ CardConstants.RULE_MARGIN, cardArea.y
				+ (CardConstants.HEIGHT / 2));
	}

}
