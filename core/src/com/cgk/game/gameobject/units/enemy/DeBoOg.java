package com.cgk.game.gameobject.units.enemy;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.cgk.game.event.AttackPlayerEvent;
import com.cgk.game.event.EventType;
import com.cgk.game.event.cardevents.AttackAdditiveEvent;
import com.cgk.game.event.cardevents.AttackMultiplierEvent;
import com.cgk.game.gameobject.eventresponses.SendEventResponse;
import com.cgk.game.gameobject.units.UnitAttack.AttackType;
import com.cgk.game.gameobject.units.strategy.AttackStatsStrategy;
import com.cgk.game.system.Asset;

public class DeBoOg extends Enemy {

	Asset<Texture> deboogTexture;

	public DeBoOg() {
		super();
		attackStats = new AttackStatsStrategy(10, AttackType.BLUE);
	}

	@Override
	public void draw(SpriteBatch batcher, TextureAtlas atlas) {
		// TODO Auto-generated method stub
	}

	@Override
	public void erase() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void setupEventResponses() {
		super.setupEventResponses();
		addEventResponse(EventType.PLAY, new SendEventResponse(
				new AttackAdditiveEvent(50)));
		addEventResponse(EventType.ATTACK_PLAYER, new SendEventResponse(
				new AttackPlayerEvent(this)));
		addEventResponse(EventType.ADD_BUFF, new SendEventResponse(
				new AttackMultiplierEvent(50)));
	}

	@Override
	public List<Asset<Texture>> getTextureAssets() {
		List<Asset<Texture>> textureAssets = new ArrayList<>();
		textureAssets.add(deboogTexture);
		return textureAssets;
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

}
