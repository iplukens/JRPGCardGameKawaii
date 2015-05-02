package com.cgk.game.gameobject.units.hero;

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
import com.cgk.game.system.Asset;
import com.cgk.game.system.EventQueue;
import com.cgk.game.util.Constants;

public class Hero extends UnitObject {

	private static Asset<Texture> heroTexture = new Asset<>("assets/hero.png",
			Texture.class);
	private Asset<Texture> currentGraphic;

	public Hero(EventQueue eventQueue, int maxHealth, AttackType attackType) {
		super(eventQueue);
		this.maxHealth = maxHealth;
		this.attackType = attackType;
		this.currentHealth = maxHealth;
		unitBox = new Rectangle(Constants.HERO_STARTING_X,
				Constants.HERO_STARTING_Y, Constants.DEFAULT_HERO_WIDTH,
				Constants.DEFAULT_HERO_HEIGHT);
	}

	@Override
	public void draw(SpriteBatch batcher, TextureAtlas atlas) {
		batcher.draw(currentGraphic.getAssetFromAtlas(atlas), unitBox.x,
				unitBox.y, unitBox.width, unitBox.height);
		drawHealthBar(batcher, atlas, unitBox);
	}

	@Override
	protected void setupAssets() {
		textureAssets.add(heroTexture);
		currentGraphic = heroTexture;
	}

	@Override
	protected void setupEventResponses() {
		super.setupEventResponses();
		addResponse(EventType.ATTACK_PLAYER, new ProcessAttackResponse());
	}

	@Override
	public void sendAttackEvent() {
		sendEvent(new AttackEnemyEvent(this));
	}

	@Override
	protected void sendOnDeathEvents() {
		sendEvent(new DefeatEvent());
	}

	@Override
	public void erase() {
		// TODO Auto-generated method stub

	}

}
