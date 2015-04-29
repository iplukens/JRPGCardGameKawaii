package com.cgk.game.gameobject.card;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.Texture;
import com.cgk.game.event.AttackAdditiveEvent;
import com.cgk.game.system.Asset;
import com.cgk.game.system.EventQueue;

public class FuelTheFire extends Card {

	private List<Asset> assets = new ArrayList<>();
	private Asset cardAsset = new Asset("assets/FuelTheFire.png", Texture.class);

    public FuelTheFire(EventQueue eventQueue) {
        super(eventQueue, "Fuel the Fire", "Fuels the fire");
		assets.add(cardAsset);
        super.setupAssets(assets);
        this.cardEvents.add(new AttackAdditiveEvent(50));
    }

    @Override
    protected void setupEventResponses() {
		// TODO Auto-generated method stub

    }
}
