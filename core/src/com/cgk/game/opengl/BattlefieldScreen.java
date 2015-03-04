/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cgk.game.opengl;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Rectangle;
import com.cgk.game.CardGameKawaii;
import com.cgk.game.gameobject.GameObject;
import com.cgk.game.system.Battlefield;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author cgmcandrews
 */
public class BattlefieldScreen extends ScreenAdapter {

    CardGameKawaii game;
    OrthographicCamera guiCam;
    ArrayList<Rectangle> cardBounds;
    Battlefield battlefield;
    Music music;
    @Autowired AssetManager assetManager;
    
    public BitmapFont font;

    public BattlefieldScreen(CardGameKawaii game) {
        this.game = game;

        guiCam = new OrthographicCamera(320, 480);
        guiCam.position.set(320 / 2, 480 / 2, 0);
    }

    public void update() {
        if (Gdx.input.justTouched()) {
            /**
             * guiCam.unproject(touchPoint.set(Gdx.input.getX(),
             * Gdx.input.getY(), 0));
             *
             * if (playBounds.contains(touchPoint.x, touchPoint.y)) {
             * Assets.playSound(Assets.clickSound); game.setScreen(new
             * GameScreen(game)); return; } if
             * (highscoresBounds.contains(touchPoint.x, touchPoint.y)) {
             * Assets.playSound(Assets.clickSound); game.setScreen(new
             * HighscoresScreen(game)); return; } if
             * (helpBounds.contains(touchPoint.x, touchPoint.y)) {
             * Assets.playSound(Assets.clickSound); game.setScreen(new
             * HelpScreen(game)); return; } if
             * (soundBounds.contains(touchPoint.x, touchPoint.y)) {
             * Assets.playSound(Assets.clickSound); Settings.soundEnabled =
             * !Settings.soundEnabled; if (Settings.soundEnabled) {
             * Assets.music.play(); } else { Assets.music.pause(); } }
             *
             *
             */
        }
    }

    public void setBattlefield(Battlefield battlefield) {
        this.battlefield = battlefield;
        
    }
  
    public void draw() {
        GL20 gl = Gdx.gl;
        gl.glClearColor(1, 0, 0, 1);
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        guiCam.update();
        game.batcher.setProjectionMatrix(guiCam.combined);

        game.batcher.disableBlending();
        game.batcher.begin();
        //game.batcher.draw(Assets.backgroundRegion, 0, 0, 320, 480);
        game.batcher.end();

        game.batcher.enableBlending();
        game.batcher.begin();
        //game.batcher.draw(Assets.logo, 160 - 274 / 2, 480 - 10 - 142, 274, 142);
        //game.batcher.draw(Assets.mainMenu, 10, 200 - 110 / 2, 300, 110);
        //game.batcher.draw(Settings.soundEnabled ? Assets.soundOn : Assets.soundOff, 0, 0, 64, 64);
        game.batcher.end();
    }

    @Override
    public void render(float delta) {
        update();
        draw();
    }

    @Override
    public void pause() {
        //Settings.save();
    }

    //TODO consider using AssetsClass for this. Need to talk with ian.
    public void loadAssets() {
        //Setup font
        font = new BitmapFont(Gdx.files.internal("data/font.fnt"), Gdx.files.internal("data/font.png"), false);

        //Setup Music that will play
        music = Gdx.audio.newMusic(Gdx.files.internal(battlefield.getMusic()));
        music.setLooping(true);
        music.setVolume(0.5f);

        //Get and load all sounds/graphics objects will use
        //Classes are responsbile for their sounds, animations,bitmaps 
        ArrayList<GameObject> objectsToLoad = new ArrayList<>();
        objectsToLoad.addAll(battlefield.getEnemies());
        objectsToLoad.addAll(battlefield.getDeck().getCards());
        objectsToLoad.addAll(battlefield.getHeroes());
        for (GameObject object : objectsToLoad) {
            object.loadAssets();
        }
    }
}

