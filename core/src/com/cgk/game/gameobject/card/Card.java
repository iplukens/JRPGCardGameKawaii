package com.cgk.game.gameobject.card;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;
import java.util.ArrayList;
import java.util.List;

import com.cgk.game.event.GameEvent;
import com.cgk.game.event.PlayEvent;
import com.cgk.game.gameobject.GameObject;
import com.cgk.game.system.Asset;
import com.cgk.game.system.EventQueue;
import com.cgk.game.util.Constants;

public abstract class Card extends GameObject {

    private String cardName;
    private String cardText;
    private List<Asset> cardAssets;
    private Asset currentGraphic;
    protected List<GameEvent> cardEvents = new ArrayList<>();
    protected boolean alive = true;
    private Rectangle cardGraphic;

    protected Card(EventQueue eventQueue, 
            String cardName, String cardText) {
        super(eventQueue);
        this.cardName = cardName;
        this.cardText = cardText;
        cardGraphic = new Rectangle(0, 0, Constants.DEFAULT_CARD_WIDTH, Constants.DEFAULT_CARD_HEIGHT);
        cardGraphic.setCenter(Constants.DEFAULT_CARD_WIDTH / 2, Constants.DEFAULT_CARD_HEIGHT / 2);
    }

    public void play() {
        sendEvent(new PlayEvent(this));
        if (alive) {
            sendCardEvents();
        }
    }

    private void sendCardEvents() {
        for (GameEvent event : cardEvents) {
            sendEvent(event);
        }
    }

    public GameEvent getEvent(int index) {
        return cardEvents.get(index);
    }


    /**
     * @return the cardName
     */
    public String getCardName() {
        return cardName;
    }

    /**
     * @return the cardText
     */
    public String getCardText() {
        return cardText;
    }

    /**
     * @return the alive
     */
    public boolean isAlive() {
        return alive;
    }


    /**
     * @param cardName the cardName to set
     */
    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    /**
     * @param cardText the cardText to set
     */
    public void setCardText(String cardText) {
        this.cardText = cardText;
    }

    /**
     * @param alive the alive to set
     */
    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    /**
     * @Return cardGraphic get the graphical representation of the card. OMG
     */
    public Rectangle getCardGraphic() {
        return cardGraphic;
    }

    @Override
    protected void setupAssets() {
        for (Asset asset : cardAssets) {
            if (asset.getAssetClass().equals(Texture.class)) {
                textureAssets.add(asset);
            } else {
                soundAssets.add(asset);
            }
        }

        if (!textureAssets.isEmpty()) {
            currentGraphic = textureAssets.get(0);
        }
    }

    protected void setupAssets(List<Asset> assets){
        cardAssets = assets;
        setupAssets();
    }
    
    @Override
    public void draw(SpriteBatch batcher, TextureAtlas atlas) {
        batcher.draw(atlas.findRegion(currentGraphic.getFileName()).getTexture(),
                cardGraphic.x,
                cardGraphic.y,
                cardGraphic.width,
                cardGraphic.height);
    }

}
