package com.cgk.game.util;

import java.io.IOException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

/**
 * @author ianlukens May 1, 2015
 *
 */
public class CardConstants extends Constants {

	public static final float CARD_ASPECT_RATIO_WH = 300f / 416f;
	public static final float DEFAULT_CARD_WIDTH = 300f;
	public static float CARD_TEXT_AFTER_MARGIN = 5f;
	public static float WIDTH = 300f;
	public static float HEIGHT = 416f;
	public static float BORDER_BOTTOM = 7f;
	public static float BORDER_SIDE = 9f;
	public static float ART_MARGIN = 9f;
	public static float RULE_MARGIN = 9f;
	public static float ART_HEIGHT = 00f;
	public static float ART_WIDTH = 00f;
	public static float BACKGROUND_WIDTH = 00f;
	public static float BACKGROUND_HEIGHT = 00f;

	// fonts
	public static BitmapFont CARD_TEXT_FONT;

	public static void initialize() {
		Config config = new Config("CardConstants"
				+ BattlefieldConstants.SCREEN_WIDTH + "x"
				+ BattlefieldConstants.SCREEN_HEIGHT + ".config");
		if (config.loadConfig()) {
			try {
				loadFromConfig(CardConstants.class, config);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			WIDTH = (BattlefieldConstants.SCREEN_WIDTH / BattlefieldConstants.MAX_HAND_SIZE);
			HEIGHT = getAdjustedValue(HEIGHT);
			BORDER_BOTTOM = getAdjustedValue(BORDER_BOTTOM);
			BORDER_SIDE = getAdjustedValue(BORDER_SIDE);
			ART_MARGIN = BORDER_SIDE + getAdjustedValue(ART_MARGIN);
			RULE_MARGIN = BORDER_SIDE + getAdjustedValue(RULE_MARGIN);
			ART_HEIGHT = HEIGHT / 2 - (2 * ART_MARGIN);
			ART_WIDTH = WIDTH - (2 * ART_MARGIN);
			BACKGROUND_WIDTH = WIDTH - (2 * BORDER_SIDE);
			BACKGROUND_HEIGHT = HEIGHT - BORDER_SIDE - BORDER_BOTTOM;
			CARD_TEXT_AFTER_MARGIN = getAdjustedValue(CARD_TEXT_AFTER_MARGIN);
			try {
				saveConfig(CardConstants.class, config);
			} catch (IllegalArgumentException | IllegalAccessException
					| IOException e) {
				e.printStackTrace();
			}
		}
		initializeFonts();
	}

	public static float getAdjustedValue(float defaultValue) {
		return ((defaultValue / DEFAULT_CARD_WIDTH) * WIDTH);
	}

	private static void initializeFonts() {
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(
				Gdx.files.internal("assets/font.ttf"));
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		parameter.size = (int) getAdjustedValue(30f);
		parameter.borderWidth = 1;
		parameter.borderColor = Color.BLACK;
		CARD_TEXT_FONT = generator.generateFont(parameter);
		generator.dispose();
	}
}
