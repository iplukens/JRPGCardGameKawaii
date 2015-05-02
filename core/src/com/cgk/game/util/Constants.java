/**
 * Class for holding all constants.
 */
package com.cgk.game.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

/**
 *
 * @author cgmcandrews
 */
public class Constants {
	public static BitmapFont COMBO_BUBBLE_FONT;

	public static final int MAX_HAND_SIZE = 5;

	public static float HAND_AREA_BTWN_CARDS = 200;

	public static final int PIXELPACKER_PAGE_WIDTH = 2048;
	public static final int PIXELPACKER_PAGE_HEIGHT = 2048;
	public static final float DEFAULT_SCREEN_HEIGHT = 1024;
	public static float SCREEN_HEIGHT = 1024;
	public static float SCREEN_WIDTH = 720;

	public static float HAND_HEIGHT = SCREEN_HEIGHT / 2;

	public static float DEFAULT_HERO_WIDTH = 325f;
	public static float DEFAULT_HERO_HEIGHT = 500f;
	public static float HERO_STARTING_X = 30f;
	public static float HERO_STARTING_Y = 30f;

	public static float DECK_X_POSITION = 200f;
	public static float DECK_Y_POSITION = 200f;
	public static float DECK_WIDTH = 200f;
	public static float DECK_HEIGHT = 200f;

	public static final float PLAY_CARD_THRESHOLD = 100;

	public static final float DISCARD_SCREEN_FLOOR = 50;

	public static float HEALTHBAR_HEIGHT = 13f;

	public static float COMBO_AREA_BETWEEN = 20;

	public static float COMBO_WIDTH = 20;
	public static float COMBO_HEIGHT = 20;
	public static float CONNECTOR_WIDTH = 20;
	public static float COMBO_Y = (SCREEN_HEIGHT / 20);

	public static void initialize(float height, float width) {
		SCREEN_HEIGHT = height;
		SCREEN_WIDTH = width;
		HAND_HEIGHT = SCREEN_HEIGHT / 2;
		COMBO_HEIGHT = (SCREEN_HEIGHT / 20);
		COMBO_WIDTH = COMBO_HEIGHT;
		COMBO_AREA_BETWEEN = COMBO_WIDTH + CONNECTOR_WIDTH;
		CardConstants.initialize();
		HAND_AREA_BTWN_CARDS = CardConstants.WIDTH + 5;

		COMBO_Y = HAND_HEIGHT - COMBO_HEIGHT;
		initializeFonts();
		DEFAULT_HERO_HEIGHT = adjustValue(DEFAULT_HERO_HEIGHT);
		DEFAULT_HERO_WIDTH = adjustValue(DEFAULT_HERO_WIDTH);
		HERO_STARTING_X = adjustValue(HERO_STARTING_X);
		HERO_STARTING_Y = SCREEN_HEIGHT - adjustValue(HERO_STARTING_Y)
				- DEFAULT_HERO_HEIGHT;
		HEALTHBAR_HEIGHT = adjustValue(HEALTHBAR_HEIGHT);
	}

	private static float adjustValue(float defaultValue) {
		return (defaultValue / DEFAULT_SCREEN_HEIGHT) * SCREEN_HEIGHT;
	}

	private static void initializeFonts() {
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(
				Gdx.files.internal("assets/font.ttf"));
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		parameter.size = (int) COMBO_HEIGHT;
		parameter.borderWidth = 2;
		parameter.borderColor = Color.BLACK;
		COMBO_BUBBLE_FONT = generator.generateFont(parameter);
		generator.dispose();
	}

}
