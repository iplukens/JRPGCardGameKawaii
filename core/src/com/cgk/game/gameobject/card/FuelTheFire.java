package com.cgk.game.gameobject.card;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.cgk.game.event.AttackAdditiveEvent;
import com.cgk.game.event.GameEvent;
import com.cgk.game.system.Asset;
import com.cgk.game.system.EventQueue;
import java.util.ArrayList;
import java.util.List;

public class FuelTheFire extends Card {

    List<Asset> assets = new ArrayList<>();
    Asset cardAsset = new Asset("FuelTheFire.png", Texture.class);

    public FuelTheFire(EventQueue eventQueue) {
        super(eventQueue, "Fuel the Fire", "Fuels the fire");
        super.setupAssets(assets);
        this.cardEvents.add(new AttackAdditiveEvent(50));
    }

    @Override
    protected void setUpEventResponses() {
		// TODO Auto-generated method stub

    }
}
