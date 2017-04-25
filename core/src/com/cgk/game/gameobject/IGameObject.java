package com.cgk.game.gameobject;

import java.util.List;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.cgk.game.event.GameEvent;
import com.cgk.game.system.Asset;

/**
 * @author ianlukens May 22, 2015
 *
 */
public interface IGameObject {

	public void draw(SpriteBatch batcher, TextureAtlas atlas);

	public void erase();

	public void respondToEvent(GameEvent event);

	public void sendEvent(GameEvent event);

	public List<Asset<?>> getAllAssets();

	/**
	 * return a list of all texture assets associated with the game object
	 * 
	 * @return
	 */
	public List<Asset<Texture>> getTextureAssets();

	/**
	 * return a list of all sound assets associated with the game object
	 * 
	 * @return
	 */
	public List<Asset<Sound>> getSoundAssets();

	/**
	 * return a list of all music assets associated with the game object
	 * 
	 * @return
	 */
	public List<Asset<Music>> getMusicAssets();

}
