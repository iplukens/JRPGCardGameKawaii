/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cgk.game.opengl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.cgk.game.CardGameKawaii;
import com.cgk.game.gameobject.GameObject;
import com.cgk.game.gameobject.card.Card;
import com.cgk.game.gameobject.units.UnitObject;
import com.cgk.game.gameobject.units.enemy.Enemy;
import com.cgk.game.opengl.inputprocessor.InBewteenTurnProcessor;
import com.cgk.game.opengl.inputprocessor.PlayerTurnInputProcessor;
import com.cgk.game.system.Asset;
import com.cgk.game.system.Battlefield;
import com.cgk.game.util.BattlefieldConstants;

/**
 *
 * @author cgmcandrews
 */
public class BattlefieldScreen extends CardGameKawaiiScreen {

	final Logger LOGGER = Logger.getLogger(BattlefieldScreen.class.toString());
	private OrthographicCamera guiCam;
	private Battlefield battlefield;
	private Music music;
	private TextureAtlas atlas;
	public boolean assetsLoaded = false;

	public BitmapFont font;
	private InputProcessor playerTurnProcessor;
	private InputProcessor inBetweenTurnProcessor;

	public BattlefieldScreen(CardGameKawaii game, Battlefield battlefield) {
		super(game);
		this.battlefield = battlefield;
	}

	/**
	 * 
	 * @param timedelta
	 *            time in seconds since the last render
	 */
	public void update(float timedelta) {
		/**
		 * Game states - YOUR_TURN - look for input CONSEQUENCES OF TURN - your
		 * attack - enemy attack - familiar attack
		 */
		switch (battlefield.getGameState()) {
		case PLAYER_TURN:
			setProcessor(playerTurnProcessor);
			battlefield.updateTimer(timedelta);
			break;
		case ENEMY_TURN:
			setProcessor(null);
			battlefield.enemyAttackProcess();
			break;
		case LEVEL_ADVANCE:
			break;
		case VICTORY:
			changeScreen(new MainMenuScreen(game));
			break;
		case DEFEAT:
			changeScreen(new MainMenuScreen(game));
			break;
		case BETWEEN_TURNS:
			setProcessor(inBetweenTurnProcessor);
			break;
		}

		/**
		 * //Update the card if if (Gdx.input.isTouched()) { Vector3 touchPos =
		 * new Vector3(); touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
		 * camera.unproject(touchPos); card.x = touchPos.x - card.width / 2;
		 * card.y = touchPos.y - card.height / 2; laser.play(); }
		 *
		 */
	}

	/**
	 * sets the current input processor
	 * 
	 * @param processor
	 */
	private void setProcessor(InputProcessor processor) {
		if (Gdx.input.getInputProcessor() != processor) {
			Gdx.input.setInputProcessor(processor);
		}
	}

	public void draw() {
		GL20 gl = Gdx.gl;
		gl.glClearColor(1, 0, 0, 1);
		gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		guiCam.update();
		game.batcher.setProjectionMatrix(guiCam.combined);
		game.batcher.begin();
		// game.batcher.enableBlending();
		gl.glEnable(GL20.GL_BLEND);
		gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		battlefield.draw(game.batcher, atlas);
		game.batcher.end();
	}

	@Override
	public void render(float timeDelta) {
		update(timeDelta);
		draw();
	}

	@Override
	public void pause() {
		// Settings.save();
	}

	public void loadAssets() {
		// TODO Determine how we managed sounds and loaded fonts and music
		// Setup font
		// font = new BitmapFont(Gdx.files.internal("data/font.fnt"),
		// Gdx.files.internal("data/font.png"), false);

		// Setup Music that will play
		music = Gdx.audio.newMusic(Gdx.files.internal(battlefield
				.getMusicFileLocation()));
		music.setLooping(true);
		music.setVolume(0.5f);

		// laser = Gdx.audio.newSound(Gdx.files.internal("laser.wav"));

		// Get and load all sounds/graphics objects will use
		// Classes are responsbile for their sounds, animations,bitmaps
		ArrayList<GameObject> objectsToLoad = new ArrayList<>();
		objectsToLoad.addAll(battlefield.getObjectsToLoad());

		// TODO figure out what to do here
		// I want each object to give me its textures
		// then we allocate 4MB to a texture sheet as a page
		// this can be referenced from atlas after that.
		HashSet<String> loadedFiles = new HashSet<>();
		atlas = new TextureAtlas();
		for (GameObject object : objectsToLoad) {
			loadTextures(atlas, object.getTextureAssets(), loadedFiles);
		}
		loadTextures(atlas, Card.getBaseTextureAssets(), loadedFiles);
		loadTextures(atlas, UnitObject.getBaseTextureAssets(), loadedFiles);
		loadTextures(atlas, Enemy.getBaseTextureAssets(), loadedFiles);
		assetsLoaded = true;
	}

	private void loadTextures(TextureAtlas atlas, List<Asset<Texture>> assets,
			HashSet<String> loadedFiles) {
		for (Asset<Texture> asset : assets) {
			if (loadedFiles.add(asset.getFileName())) {
				LOGGER.log(Level.INFO, "Loading asset " + asset.getFileName());
				atlas.addRegion(asset.getFileName(), new TextureRegion(
						new Texture(asset.getFileName())));
			}
		}
	}

	@Override
	protected void disposeOfAssets() {
		atlas.dispose();
		music.dispose();
	}

	@Override
	public void initialize() {
		guiCam = new OrthographicCamera(BattlefieldConstants.SCREEN_WIDTH,
				BattlefieldConstants.SCREEN_HEIGHT);
		guiCam.position.set(BattlefieldConstants.SCREEN_WIDTH / 2,
				BattlefieldConstants.SCREEN_HEIGHT / 2, 0);
		playerTurnProcessor = new PlayerTurnInputProcessor(battlefield);
		inBetweenTurnProcessor = new InBewteenTurnProcessor(battlefield);
		loadAssets();
	}
}
