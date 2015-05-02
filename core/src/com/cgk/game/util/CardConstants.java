package com.cgk.game.util;

/**
 * @author ianlukens May 1, 2015
 *
 */
public class CardConstants {

	public static final float CARD_ASPECT_RATIO_WH = 300f / 416f;
	public static final float DEFAULT_CARD_WIDTH = 300f;

	public static int WIDTH = 300;
	public static int HEIGHT = 416;
	public static int BORDER_BOTTOM = 7;
	public static int BORDER_SIDE = 9;
	public static int ART_MARGIN = 9;
	public static int RULE_MARGIN = 9;
	public static int ART_HEIGHT = 00;
	public static int ART_WIDTH = 00;
	public static int BACKGROUND_WIDTH = 00;
	public static int BACKGROUND_HEIGHT = 00;

	public static void initialize() {
		WIDTH = (int) (Constants.SCREEN_WIDTH / Constants.MAX_HAND_SIZE);
		HEIGHT = getAdjustedValue(HEIGHT);
		BORDER_BOTTOM = (int) getAdjustedValue(BORDER_BOTTOM);
		BORDER_SIDE = (int) getAdjustedValue(BORDER_SIDE);
		ART_MARGIN = BORDER_SIDE + getAdjustedValue(ART_MARGIN);
		RULE_MARGIN = BORDER_SIDE + getAdjustedValue(RULE_MARGIN);
		ART_HEIGHT = HEIGHT / 2 - (2 * ART_MARGIN);
		ART_WIDTH = WIDTH - (2 * ART_MARGIN);
		BACKGROUND_WIDTH = WIDTH - (2 * BORDER_SIDE);
		BACKGROUND_HEIGHT = HEIGHT - BORDER_SIDE - BORDER_BOTTOM;
	}

	public static int getAdjustedValue(int defaultValue) {
		return (int) ((defaultValue / DEFAULT_CARD_WIDTH) * WIDTH);
	}
}
