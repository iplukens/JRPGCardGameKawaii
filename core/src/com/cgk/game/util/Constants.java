/**
 * Class for holding all constants.
 */
package com.cgk.game.util;

/**
 *
 * @author cgmcandrews
 */
public class Constants {
	public static final int MAX_HAND_SIZE = 5;

	public static float HAND_AREA_BTWN_CARDS = 200;

	public static final int PIXELPACKER_PAGE_WIDTH = 2048;
	public static final int PIXELPACKER_PAGE_HEIGHT = 2048;
	public static float SCREEN_HEIGHT = 1024;
	public static float SCREEN_WIDTH = 720;

	public static float HAND_HEIGHT = SCREEN_HEIGHT / 2;

	public static float DEFAULT_HERO_WIDTH = 256f;
	public static float DEFAULT_HERO_HEIGHT = 256f;

	public static float DEFAULT_CARD_WIDTH = 256f;
	public static float DEFAULT_CARD_HEIGHT = 256f;

	public static float DECK_X_POSITION = 200f;
	public static float DECK_Y_POSITION = 200f;
	public static float DECK_WIDTH = 200f;
	public static float DECK_HEIGHT = 200f;

	public static final float PLAY_CARD_THRESHOLD = 100;

	public static final float DISCARD_SCREEN_FLOOR = 50;



	public static void initialize(float height, float width) {
		SCREEN_HEIGHT = height;
		SCREEN_WIDTH = width;
		HAND_HEIGHT = SCREEN_HEIGHT / 2;
		DEFAULT_CARD_HEIGHT = HAND_HEIGHT - (SCREEN_HEIGHT / 12);
		DEFAULT_CARD_WIDTH = DEFAULT_CARD_HEIGHT;
		HAND_AREA_BTWN_CARDS = SCREEN_WIDTH / MAX_HAND_SIZE;
	}

}
