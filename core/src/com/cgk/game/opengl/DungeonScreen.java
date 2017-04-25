package com.cgk.game.opengl;

import java.util.logging.Logger;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.cgk.game.CardGameKawaii;
import com.cgk.game.system.Battlefield;
import com.cgk.game.util.BattlefieldConstants;

/**
 * @author ianlukens Jun 3, 2015
 *
 */
public class DungeonScreen extends CardGameKawaiiScreen {

	final Logger LOGGER = Logger.getLogger(DungeonScreen.class.toString());
	private Stage stage;
	private Skin buttonSkin;
	private TextureAtlas buttonAtlas;

	public DungeonScreen(CardGameKawaii game) {
		super(game);
	}

	private void setUpTable() {
		Table table = new Table();
		table.setX(BattlefieldConstants.SCREEN_WIDTH / 2);
		table.setY(BattlefieldConstants.SCREEN_HEIGHT / 2);

		buttonSkin = new Skin();
		buttonAtlas = new TextureAtlas("assets/MenuButton.txt");
		buttonSkin.addRegions(buttonAtlas);
		TextButtonStyle textButtonStyle = new TextButtonStyle();
		textButtonStyle.font = BattlefieldConstants.COMBO_BUBBLE_FONT;
		textButtonStyle.up = buttonSkin.getDrawable("ButtonUnpressed");
		textButtonStyle.down = buttonSkin.getDrawable("ButtonPressed");

		addDungeonButton(table, "1", textButtonStyle);

		stage.addActor(table);
	}

	private void addDungeonButton(Table table, String buttonText,
			TextButtonStyle style) {
		final Battlefield battlefield = new Battlefield(
				game.player.getActiveCharacter(), Integer.parseInt(buttonText));
		Button menuButton = new TextButton(buttonText, style);
		menuButton.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				changeScreen(new BattlefieldScreen(game, battlefield));
			}
		});
		table.add(menuButton).center();
		table.row();
	}

	@Override
	public void render(float timeDelta) {
		draw();
	}

	private void draw() {
		GL20 gl = Gdx.gl;
		gl.glClearColor(1, 0, 0, 1);
		gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		game.batcher.begin();
		// game.batcher.enableBlending();
		gl.glEnable(GL20.GL_BLEND);
		gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		stage.draw();
		game.batcher.end();
	}

	@Override
	protected void disposeOfAssets() {
		stage.dispose();
		buttonSkin.dispose();
		buttonAtlas.dispose();
	}

	@Override
	public void initialize() {
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		setUpTable();
	}

}
