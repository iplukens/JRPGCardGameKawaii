package com.cgk.game.gameobject.card;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.cgk.game.event.DiscardEvent;
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
	private Rectangle cardArea;
	private Vector2 lastTouchedPos;

	protected Card(EventQueue eventQueue, String cardName, String cardText) {
		super(eventQueue);
		this.cardName = cardName;
		this.cardText = cardText;
		cardArea = new Rectangle(0, 0, Constants.DEFAULT_CARD_WIDTH,
				Constants.DEFAULT_CARD_HEIGHT);
		cardArea.setCenter(Constants.DEFAULT_CARD_WIDTH / 2,
				Constants.DEFAULT_CARD_HEIGHT / 2);
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
	 * @param cardName
	 *            the cardName to set
	 */
	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	/**
	 * @param cardText
	 *            the cardText to set
	 */
	public void setCardText(String cardText) {
		this.cardText = cardText;
	}

	/**
	 * @param alive
	 *            the alive to set
	 */
	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	/**
	 * @Return cardGraphic get the graphical representation of the card. OMG
	 */
	public Rectangle getCardArea() {
		return cardArea;
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

	protected void setupAssets(List<Asset> assets) {
		cardAssets = assets;
		setupAssets();
	}

	public void discard() {
		sendEvent(new DiscardEvent(this));
	}

	@Override
	public void draw(SpriteBatch batcher, TextureAtlas atlas) {
		batcher.draw(atlas.findRegion(currentGraphic.getFileName())
				.getTexture(), cardArea.x, cardArea.y, cardArea.width,
				cardArea.height);
	}

	public void processJustTouched(Vector2 touchPos) {
		lastTouchedPos = touchPos;
	}

	public void resetTouchStatus() {
		lastTouchedPos = null;
	}

	public void processTouch(Vector2 touchPos) {
		if (touchPos.y > lastTouchedPos.y + Constants.PLAY_CARD_THRESHOLD) {
			play();
		} else if (touchPos.y < lastTouchedPos.y
				- Constants.PLAY_CARD_THRESHOLD
				|| touchPos.y < Constants.DISCARD_SCREEN_FLOOR) {
			discard();
		}
		// TODO: move cards based on input
	}

}
