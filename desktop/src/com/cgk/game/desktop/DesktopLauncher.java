package com.cgk.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.cgk.game.CardGameKawaii;
import com.cgk.game.util.BattlefieldConstants;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.height = (int) BattlefieldConstants.SCREEN_WIDTH;
		config.width = (int) BattlefieldConstants.SCREEN_HEIGHT;
		config.resizable = false;
		new LwjglApplication(new CardGameKawaii(), config);
	}
}
