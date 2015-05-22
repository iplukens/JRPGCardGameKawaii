package com.cgk.game.gameobject.card;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.cgk.game.event.cardevents.ChangeHandColorEvent;
import com.cgk.game.gameobject.units.UnitAttack.AttackType;
import com.cgk.game.system.Asset;

/**
 * @author ianlukens May 21, 2015
 *
 */
public class RoseColorGlass extends ValueCard {

	private static Asset<Texture> cardAsset = new Asset<Texture>(
			"assets/RoseColorGlass.png", Texture.class);

	public RoseColorGlass() {
		super("Rose Color Glass", cardAsset);
		this.resourceNumber = 6;
		addPlayValueEvent(new ChangeHandColorEvent(this, AttackType.RED),
				ValueType.DURATION, 0);
	}

	@Override
	public void setAttributes(Map<String, String> attributes) {
		// TODO Auto-generated method stub

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
		return null;
	}

	@Override
	public List<Asset<Music>> getMusicAssets() {
		// TODO Auto-generated method stub
		return null;
	}

}
