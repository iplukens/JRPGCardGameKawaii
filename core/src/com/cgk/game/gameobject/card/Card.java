package com.cgk.game.gameobject.card;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.cgk.game.event.DiscardedCardEvent;
import com.cgk.game.event.GameEvent;
import com.cgk.game.event.PlayEvent;
import com.cgk.game.event.cardevents.CardEffectEvent;
import com.cgk.game.gameobject.GameObject;
import com.cgk.game.gameobject.units.UnitAttack.AttackType;
import com.cgk.game.system.Asset;
import com.cgk.game.system.EventQueue;
import com.cgk.game.util.Constants;

public abstract class Card extends GameObject {

	private String cardName;
	private String cardText;
	protected List<Asset> cardAssets = new ArrayList<>();
	private Asset currentGraphic;
	protected List<GameEvent> cardEvents = new ArrayList<>();
	private Rectangle cardArea;
	private Vector2 lastTouchedPos;
	private int resourceNumber;
	private AttackType startingCardType;
	private AttackType endingCardType;
	protected List<CardEffectEvent> playEvents = new ArrayList<>();
	protected List<GameEvent> discardEvents = new ArrayList<>();
	protected boolean alive = true;
	private float startX;
	private float startY;

	protected Card(EventQueue eventQueue, String cardName, String cardText) {
		super(eventQueue);
		this.cardName = cardName;
		this.cardText = cardText;
		cardArea = new Rectangle(0, 0, Constants.DEFAULT_CARD_WIDTH,
				Constants.DEFAULT_CARD_HEIGHT);
		setPlayEvents();
		setDiscardEvents();
	}

	/**
	 * set up the events that occur when the card is discarded
	 */
	protected void setDiscardEvents() {

	}

	/**
	 * set up the events that occur when the card is played
	 */
	protected abstract void setPlayEvents();

	public void play() {
		sendEvent(new PlayEvent(this));
		if (alive) {
			sendPlayEvents();
		}
	}

	private void sendPlayEvents() {
		for (GameEvent event : playEvents) {
			sendEvent(event);
		}
	}

	public void sendDiscardEvents() {
		for (GameEvent event : discardEvents) {
			sendEvent(event);
		}
	}

	public GameEvent getEvent(int index) {
		return playEvents.get(index);
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
	 * 
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
		sendEvent(new DiscardedCardEvent(this));
	}

	@Override
	public void draw(SpriteBatch batcher, TextureAtlas atlas) {
		batcher.draw(atlas.findRegion(currentGraphic.getFileName()),
				cardArea.x, cardArea.y, cardArea.width,
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
			logInfo("Playing card");
			play();
		} else if (touchPos.y < lastTouchedPos.y
				- Constants.PLAY_CARD_THRESHOLD
				|| touchPos.y < Constants.DISCARD_SCREEN_FLOOR) {
			logInfo("Discarding card");
			discard();
		}
		logInfo("new coordinate: (" + touchPos.x + ", " + touchPos.y + ")");
		cardArea.x = touchPos.x - cardArea.width / 2;
		cardArea.y = touchPos.y - cardArea.height / 2;
	}

	@Override
	public void erase() {
		// TODO
	}

	public AttackType getStartingCardType() {
		return startingCardType;
	}

	public void setStartingCardType(AttackType startingCardType) {
		this.startingCardType = startingCardType;
	}

	public AttackType getEndingCardType() {
		return endingCardType;
	}

	public void setEndingCardType(AttackType endingCardType) {
		this.endingCardType = endingCardType;
	}

	public int getResourceNumber() {
		return resourceNumber;
	}

	public void setStartX(float newX) {
		startX = newX;
		cardArea.x = startX;
	}

	public void setStartY(float newY) {
		startY = newY;
		cardArea.y = startY;
	}

	public void moveBack() {
		cardArea.x = startX;
		cardArea.y = startY;
	}

}
