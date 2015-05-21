package com.cgk.game.event.cardevents;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.cgk.game.event.BaseEvent;
import com.cgk.game.gameobject.card.Card;
import com.cgk.game.util.BattlefieldConstants;
import com.cgk.game.util.CardConstants;

/**
 * events that happen upon playing cards
 * 
 * @author ianlukens
 *
 */
public abstract class CardEffectEvent extends BaseEvent {

	protected Card card;

	public CardEffectEvent() {
	}

	public CardEffectEvent(Card card) {
		this.card = card;
	}

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
		BattlefieldConstants.COMBO_BUBBLE_FONT.draw(batcher, text, cardArea.x
				+ CardConstants.RULE_MARGIN, cardArea.y
				+ (CardConstants.HEIGHT / 2));
	}

}
