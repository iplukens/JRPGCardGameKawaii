/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import com.cgk.game.HeroCharacter;
import com.cgk.game.util.BattlefieldConstants;

/**
 *
 * @author iplukens June 3, 2015
 */
public class CharacterSelectScreen extends CardGameKawaiiScreen {

	final Logger LOGGER = Logger.getLogger(CharacterSelectScreen.class
			.toString());
	private Stage stage;
	private TextureAtlas buttonAtlas;
	private Skin buttonSkin;

	public CharacterSelectScreen(CardGameKawaii game) {
		super(game);
	}

	private void setUpTable() {
		Table table = new Table();
		buttonSkin = new Skin();
		buttonAtlas = new TextureAtlas("assets/MenuButton.txt");
		buttonSkin.addRegions(buttonAtlas);
		TextButtonStyle textButtonStyle = new TextButtonStyle();
		textButtonStyle.font = BattlefieldConstants.COMBO_BUBBLE_FONT;
		textButtonStyle.up = buttonSkin.getDrawable("ButtonUnpressed");
		textButtonStyle.down = buttonSkin.getDrawable("ButtonPressed");
		if (game.player.hasCharacters()) {
			for (final HeroCharacter character : game.player.getCharacters()) {
				Button characterButton = new TextButton(character.getName(),
						textButtonStyle);
				characterButton.addListener(new ChangeListener() {
					@Override
					public void changed(ChangeEvent event, Actor actor) {
						System.out.println("Going to character: "
								+ character.getName());
						game.player.setActiveCharacter(character);
						changeScreen(new MainMenuScreen(game));
					}
				});
				table.add(characterButton);
				table.row();
			}
		} else {
			Button newCharacterButton = new TextButton("New Character",
					textButtonStyle);
			newCharacterButton.addListener(new ChangeListener() {

				@Override
				public void changed(ChangeEvent event, Actor actor) {
					changeScreen(new NewCharacterScreen(game));
				}
			});
			table.add(newCharacterButton).center();
		}
		table.setX(BattlefieldConstants.SCREEN_WIDTH / 2);
		table.setY(BattlefieldConstants.SCREEN_HEIGHT / 2);
		stage.addActor(table);
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
	public void pause() {
		// Settings.save();
	}

	@Override
	protected void disposeOfAssets() {
		stage.dispose();
		buttonAtlas.dispose();
		buttonSkin.dispose();
	}

	@Override
	public void initialize() {
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		setUpTable();
	}

}
