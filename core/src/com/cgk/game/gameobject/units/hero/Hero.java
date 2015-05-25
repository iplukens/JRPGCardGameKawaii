package com.cgk.game.gameobject.units.hero;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;
import com.cgk.game.event.AttackEnemyEvent;
import com.cgk.game.event.DefeatEvent;
import com.cgk.game.event.EventType;
import com.cgk.game.gameobject.units.UnitAttack.AttackType;
import com.cgk.game.gameobject.units.UnitObject;
import com.cgk.game.gameobject.units.eventresponses.ProcessAttackResponse;
import com.cgk.game.gameobject.units.eventresponses.UnitAttackResponse;
import com.cgk.game.gameobject.units.eventresponses.UnitHealResponse;
import com.cgk.game.gameobject.units.strategy.AttackStatsStrategy;
import com.cgk.game.system.Asset;
import com.cgk.game.util.BattlefieldConstants;

public class Hero extends UnitObject {

	private static Asset<Texture> heroTexture = new Asset<>("assets/hero.png",
			Texture.class);

	public Hero(int maxHealth, AttackType attackType) {
		super();
		this.maxHealth = maxHealth;
		attackStats = new AttackStatsStrategy(10, attackType);
		this.currentHealth = maxHealth;
		unitBox = new Rectangle(BattlefieldConstants.HERO_STARTING_X,
				BattlefieldConstants.HERO_STARTING_Y, BattlefieldConstants.DEFAULT_HERO_WIDTH,
				BattlefieldConstants.DEFAULT_HERO_HEIGHT);
		currentGraphic = heroTexture;
	}

	@Override
	public void draw(SpriteBatch batcher, TextureAtlas atlas) {
		batcher.draw(currentGraphic.getAssetFromAtlas(atlas), unitBox.x,
				unitBox.y, unitBox.width, unitBox.height);
		drawHealthBar(batcher, atlas, unitBox);
	}

	@Override
	public List<Asset<Texture>> getTextureAssets() {
		List<Asset<Texture>> textureAssets = new ArrayList<>();
		textureAssets.add(heroTexture);
		return textureAssets;
	}

	@Override
	protected void setupEventResponses() {
		super.setupEventResponses();
		addEventResponse(EventType.ATTACK_PLAYER, new ProcessAttackResponse());
		addEventResponse(EventType.END_HERO_TURN, new UnitAttackResponse());
		addEventResponse(EventType.HEAL_PLAYER, new UnitHealResponse());
	}

	@Override
	public void sendAttackEvent() {
		sendEvent(new AttackEnemyEvent(this, getBattlefield()
				.getCurrentTarget()));
	}

	@Override
	protected void sendOnDeathEvents() {
		sendEvent(new DefeatEvent());
		getBattlefield().removeHero(this);
	}

	@Override
	public void erase() {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Asset<Sound>> getSoundAssets() {
		// TODO Auto-generated method stub
		return new ArrayList<Asset<Sound>>();
	}

	@Override
	public List<Asset<Music>> getMusicAssets() {
		// TODO Auto-generated method stub
		return new ArrayList<Asset<Music>>();
	}

	public void setCurrentHealth(float newHealth) {
		this.currentHealth = newHealth;
	}

	public float getMaxhealth() {
		return maxHealth;
	}

}
