package com.cgk.game.gameobject.card;

import com.badlogic.gdx.graphics.Texture;
import com.cgk.game.event.cardevents.AttackAdditiveEvent;
import com.cgk.game.system.Asset;
import com.cgk.game.system.EventQueue;

public class FuelTheFire extends Card {

	private static Asset<Texture> cardAsset = new Asset<Texture>(
			"assets/FuelTheFire.png",
			Texture.class);

    public FuelTheFire(EventQueue eventQueue) {
		super(eventQueue, "Fuel the Fire", "Fuels the fire", cardAsset);
        this.cardEvents.add(new AttackAdditiveEvent(50));
    }

	@Override
	protected void setPlayEvents() {
		this.playEvents.add(new AttackAdditiveEvent(50));
	}

	@Override
	protected void setupEventResponses() {
		// TODO
	}

	@Override
	protected void setupAssets() {
		textureAssets.add(cardAsset);
	}

}
