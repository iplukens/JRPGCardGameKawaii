package com.cgk.game.gameobject.units.enemy;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.cgk.game.event.AttackPlayerEvent;
import com.cgk.game.event.DeadEnemyEvent;
import com.cgk.game.event.EventType;
import com.cgk.game.event.GameEvent;
import com.cgk.game.gameobject.units.UnitObject;
import com.cgk.game.gameobject.units.eventresponses.ProcessAttackResponse;
import com.cgk.game.gameobject.units.eventresponses.UnitAttackResponse;
import com.cgk.game.system.Asset;

public abstract class Enemy extends UnitObject {

	protected List<GameEvent> onDeathEvents;
	private static Asset<Texture> targetAsset = new Asset<Texture>(
			"assets/target.png", Texture.class);;

	public Enemy() {
		super();
		onDeathEvents = new ArrayList<>();
	}

	public void sendAttackEvent() {
		sendEvent(new AttackPlayerEvent(this, getBattlefield().getEnemyTarget()));
	}

	protected void sendOnDeathEvents() {
		for (GameEvent event : onDeathEvents) {
			sendEvent(event);
		}
		getBattlefield().removeEnemy(this);
	}

	protected void setUpOnDeathEvents() {
		onDeathEvents = new ArrayList<>();
		onDeathEvents.add(new DeadEnemyEvent(this));
	}

	@Override
	protected void setupEventResponses() {
		super.setupEventResponses();
		addResponse(EventType.ATTACK_ENEMY, new ProcessAttackResponse());
		addResponse(EventType.END_HERO_TURN, new UnitAttackResponse());
	}

	public void drawTarget(SpriteBatch batcher, TextureAtlas atlas) {
		batcher.draw(targetAsset.getAssetFromAtlas(atlas), unitBox.x,
				unitBox.y, unitBox.width, unitBox.height);
	}

	public static List<Asset<Texture>> getBaseTextureAssets() {
		List<Asset<Texture>> textureAssets = new ArrayList<Asset<Texture>>();
		textureAssets.add(targetAsset);
		return textureAssets;
	}

}
