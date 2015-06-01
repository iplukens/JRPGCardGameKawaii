package com.cgk.game.event.cardevents;

import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.cgk.game.event.BaseEvent;
import com.cgk.game.gameobject.card.Card;
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
	 * @param startLine
	 * @param cardArea
	 * @return - number of lines text took up
	 */
	public abstract int drawPlayInfo(SpriteBatch batcher, int startLine,
			Rectangle cardArea);

	protected void drawLine(SpriteBatch batcher, String text, int startLine,
			Rectangle cardArea) {
		CardConstants.CARD_TEXT_FONT.draw(batcher, text, cardArea.x
				+ CardConstants.RULE_MARGIN, cardArea.y
				+ (CardConstants.HEIGHT / 2)
				- (startLine * CardConstants.CARD_TEXT_FONT.getCapHeight())
				- (startLine * CardConstants.CARD_TEXT_AFTER_MARGIN));
	}

	/**
	 * draws the specified text, dividing it among lines on the card
	 * 
	 * @param batcher
	 * @param text
	 * @param startLine
	 * @param cardArea
	 * @return the number of lines of text drawn
	 */
	protected int drawLines(SpriteBatch batcher, String text, int startLine,
			Rectangle cardArea) {
		int linesDrawn = 0;
		float cardWidth = cardArea.width - (2 * CardConstants.RULE_MARGIN);
		String line = "";
		float lineWidth = 0f;
		while (!text.isEmpty()) {
			int endWordIndex = text.indexOf(' ');
			if (endWordIndex == -1) {
				endWordIndex = text.length();
			}
			String currentWord = text.substring(0, endWordIndex);
			float wordWidth = getWordWidth(currentWord);
			if (wordWidth + lineWidth > cardWidth) {
				drawLine(batcher, line, startLine + linesDrawn, cardArea);
				linesDrawn++;
				line = "";
				lineWidth = 0f;
			}
			line += currentWord + " ";
			lineWidth += wordWidth
					+ CardConstants.CARD_TEXT_FONT.getSpaceWidth();
			if (endWordIndex + 1 < text.length() + 1) {
				text = text.substring(endWordIndex + 1);
			} else {
				break;
			}
		}
		drawLine(batcher, line, startLine + linesDrawn, cardArea);
		return ++linesDrawn;
	}

	private float getWordWidth(CharSequence c) {
		GlyphLayout layout = new GlyphLayout(CardConstants.CARD_TEXT_FONT, c);
		return layout.width;
	}

}
