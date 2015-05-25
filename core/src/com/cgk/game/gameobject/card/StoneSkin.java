package com.cgk.game.gameobject.card;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.cgk.game.event.EventType;
import com.cgk.game.event.cardevents.AddResistanceEvent;
import com.cgk.game.gameobject.units.UnitAttack.AttackType;
import com.cgk.game.system.Asset;

public class StoneSkin extends ValueCard {

	private static Asset<Texture> cardAsset = new Asset<Texture>(
			"assets/StoneSkin.png", Texture.class);

	public StoneSkin() {
		super("Stone Skin", cardAsset);
		this.resourceNumber = 3;
		addPlayEventWithValue(new AddResistanceEvent(AttackType.GREEN, this),
				ValueType.STRENGTH, 14);
		addValue(EventType.ADD_RESISTANCE_MOD, ValueType.DURATION, 4);
	}

	@Override
	protected void setupEventResponses() {
		super.setupEventResponses();
	}

	@Override
	public List<Asset<Texture>> getTextureAssets() {
		List<Asset<Texture>> textureAssets = new ArrayList<>();
		textureAssets.add(cardAsset);
		return textureAssets;
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

	@Override
	public void setAttributes(Map<String, String> attributes) {
		// TODO Auto-generated method stub

	}

}
