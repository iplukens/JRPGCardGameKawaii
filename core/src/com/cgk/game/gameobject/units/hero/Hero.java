package com.cgk.game.gameobject.units.hero;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;
import com.cgk.game.event.AttackEnemyEvent;
import com.cgk.game.gameobject.units.UnitObject;
import com.cgk.game.system.Asset;
import com.cgk.game.system.EventQueue;
import com.cgk.game.util.Constants;

public class Hero extends UnitObject {

    Asset heroTexture = new Asset("hero.png", Texture.class);
    Asset currentGraphic ;
    Rectangle heroGraphic = new Rectangle (0,0, Constants.DEFAULT_HERO_HEIGHT, Constants.DEFAULT_HERO_WIDTH);
    public Hero(EventQueue eventQueue) {
        super(eventQueue);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void draw(SpriteBatch batcher, TextureAtlas atlas) {
        batcher.draw(atlas.findRegion(currentGraphic.getFileName()).getTexture(),
        heroGraphic.x,
        heroGraphic.y,
        heroGraphic.width,
        heroGraphic.height);
    }

    @Override
    protected void setupEventResponses() {
        super.setupEventResponses();
    }

    @Override
    public void sendAttackEvent() {
        sendEvent(new AttackEnemyEvent(getAttack()));
    }

    @Override
    protected void setupAssets() {
        textureAssets.add(heroTexture);
        currentGraphic = heroTexture;
    }

}
