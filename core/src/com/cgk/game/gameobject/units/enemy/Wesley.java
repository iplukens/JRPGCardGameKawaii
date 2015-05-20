package com.cgk.game.gameobject.units.enemy;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;
import com.cgk.game.gameobject.units.UnitAttack.AttackType;
import com.cgk.game.system.Asset;
import com.cgk.game.util.BattlefieldConstants;

public class Wesley extends Enemy {

	private static Asset<Texture> wesleyTexture = new Asset<>(
			"assets/wesley.png", Texture.class);

	public Wesley() {
		super();
		this.baseAttack = 10;
		this.maxHealth = 250;
		this.attackType = AttackType.GREEN;
		this.currentHealth = maxHealth;
		unitBox = new Rectangle(BattlefieldConstants.ENEMY_STARTING_X,
				BattlefieldConstants.ENEMY_STARTING_Y, BattlefieldConstants.DEFAULT_HERO_WIDTH,
				BattlefieldConstants.DEFAULT_HERO_HEIGHT);
		currentGraphic = wesleyTexture;
	}

	@Override
	public void draw(SpriteBatch batcher, TextureAtlas atlas) {
		batcher.draw(currentGraphic.getAssetFromAtlas(atlas), unitBox.x,
				unitBox.y, unitBox.width, unitBox.height);
		drawHealthBar(batcher, atlas, unitBox);
	}

	@Override
	protected void setupEventResponses() {
		super.setupEventResponses();
	}

	@Override
	public List<Asset<Texture>> getTextureAssets() {
		List<Asset<Texture>> textureAssets = new ArrayList<>();
		textureAssets.add(wesleyTexture);
		return textureAssets;
	}

	@Override
	public void erase() {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Asset<Sound>> getSoundAssets() {
		// TODO Auto-generated method stub
		return new ArrayList<>();
	}

	@Override
	public List<Asset<Music>> getMusicAssets() {
		// TODO Auto-generated method stub
		return new ArrayList<>();
	}

}
