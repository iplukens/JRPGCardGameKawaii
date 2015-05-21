package com.cgk.game.gameobject.card;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.cgk.game.event.cardevents.AttackAdditiveEvent;
import com.cgk.game.system.Asset;

public class FuelTheFire extends ValueCard {

	private static Asset<Texture> cardAsset = new Asset<Texture>(
			"assets/FuelTheFire.png",
			Texture.class);

	public FuelTheFire() {
		super("Fuel the Fire", "Fuels the fire", cardAsset, 50);
		this.cardEvents.add(new AttackAdditiveEvent(this));
    }

	@Override
	protected void setPlayEvents() {
		this.playEvents.add(new AttackAdditiveEvent(this));
	}

	@Override
	protected void setupEventResponses() {
		// TODO
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
