package com.cgk.game.opengl;

import java.util.logging.Logger;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.cgk.game.CardGameKawaii;
import com.cgk.game.HeroCharacter;
import com.cgk.game.system.Asset;
import com.cgk.game.util.BattlefieldConstants;

/**
 * @author ianlukens Jun 3, 2015
 *
 */
public class NewCharacterScreen extends CardGameKawaiiScreen {

	final Logger LOGGER = Logger.getLogger(NewCharacterScreen.class.toString());
	private Stage stage;
	private Asset<Texture> hero1 = new Asset<>("assets/hero.png", Texture.class);
	private Asset<Texture> hero2 = new Asset<>("assets/hero2.png",
			Texture.class);
	private Asset<Texture> hero3 = new Asset<>("assets/hero3.png",
			Texture.class);

	public NewCharacterScreen(CardGameKawaii game) {
		super(game);
	}

	private void setUpTable() {
		Table table = new Table();
		table.setX(BattlefieldConstants.SCREEN_WIDTH / 2);
		table.setY(BattlefieldConstants.SCREEN_HEIGHT / 2);
		addHeroButton(table, hero1);
		addHeroButton(table, hero2);
		addHeroButton(table, hero3);
		table.row();
		LabelStyle style = new LabelStyle();
		style.font = BattlefieldConstants.COMBO_BUBBLE_FONT;
		Label label = new Label("CHOOSE A CHARACTER", style);
		table.add();
		table.add(label).center();
		table.add();
		stage.addActor(table);
	}

	private void addHeroButton(Table table, final Asset<Texture> hero) {
		ButtonStyle style = new ButtonStyle();
		style.up = new TextureRegionDrawable(hero.getTextureRegion());
		style.down = new TextureRegionDrawable(hero.getTextureRegion());
		Button heroButton = new Button(style);
		heroButton.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				HeroCharacter newChar = new HeroCharacter(hero);
				game.player.addCharacter(newChar);
				game.player.setActiveCharacter(newChar);
				changeScreen(new MainMenuScreen(game));
			}
		});
		table.add(heroButton);
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
	}

	@Override
	public void initialize() {
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		setUpTable();
	}

}
