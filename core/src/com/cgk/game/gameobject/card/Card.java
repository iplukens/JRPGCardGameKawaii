package com.cgk.game.gameobject.card;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.Color;
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
import com.cgk.game.util.BattlefieldConstants;
import com.cgk.game.util.CardConstants;

public abstract class Card extends GameObject {

	private String cardName;
	private String cardText;
	protected List<Asset<?>> cardAssets = new ArrayList<>();
	private Asset<Texture> currentGraphic;
	protected List<GameEvent> cardEvents = new ArrayList<>();
	private Rectangle cardArea;
	private int resourceNumber;
	private AttackType startingCardType;
	private AttackType endingCardType;
	protected List<CardEffectEvent> playEvents = new ArrayList<>();
	protected List<GameEvent> discardEvents = new ArrayList<>();
	protected boolean alive = true;
	private float startX;
	private float startY;
	private static Asset<Texture> cardBack = new Asset<Texture>(
			"assets/cardBack.png", Texture.class);
	private static Asset<Texture> cardBackground = new Asset<Texture>(
			"assets/cardBackground.png", Texture.class);
	private static Asset<Texture> insideCardBackground = new Asset<Texture>(
			"assets/insideCardBorder.png", Texture.class);

	protected Card(String cardName, String cardText,
			Asset<Texture> cardGraphic) {
		super();
		this.cardName = cardName;
		this.cardText = cardText;
		cardArea = new Rectangle(0, 0, CardConstants.WIDTH,
				CardConstants.HEIGHT);
		setPlayEvents();
		setDiscardEvents();
		this.currentGraphic = cardGraphic;
		startingCardType = AttackType.GREY;
		endingCardType = AttackType.GREY;
	}

	public static List<Asset<Texture>> getBaseTextureAssets() {
		List<Asset<Texture>> textureAssets = new ArrayList<Asset<Texture>>();
		textureAssets.add(cardBack);
		textureAssets.add(cardBackground);
		textureAssets.add(insideCardBackground);
		return textureAssets;
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

	public void discard() {
		sendEvent(new DiscardedCardEvent(this));
	}

	@Override
	public void draw(SpriteBatch batcher, TextureAtlas atlas) {
		drawBackground(batcher, atlas);
		drawArt(batcher, atlas);
		drawResourceIndicator(batcher);
		drawEffectText(batcher);
	}

	public void drawBack(SpriteBatch batcher, TextureAtlas atlas) {
		drawCardBack(batcher, atlas);
	}

	private void drawArt(SpriteBatch batcher, TextureAtlas atlas) {
		batcher.draw(currentGraphic.getAssetFromAtlas(atlas), cardArea.x
				+ CardConstants.ART_MARGIN, cardArea.y + CardConstants.HEIGHT
				- CardConstants.ART_HEIGHT - CardConstants.ART_MARGIN,
				CardConstants.ART_WIDTH, CardConstants.ART_HEIGHT);
	}

	private void drawCardBack(SpriteBatch batcher, TextureAtlas atlas) {
		batcher.draw(cardBack.getAssetFromAtlas(atlas), cardArea.x, cardArea.y,
				cardArea.width, cardArea.height);
	}

	private void drawBackground(SpriteBatch batcher, TextureAtlas atlas) {
		batcher.draw(cardBackground.getAssetFromAtlas(atlas), cardArea.x,
				cardArea.y, cardArea.width, cardArea.height);
		batcher.setColor(startingCardType.getColorTint());
		batcher.draw(insideCardBackground.getAssetFromAtlas(atlas), cardArea.x
				+ CardConstants.BORDER_SIDE, cardArea.y
				+ CardConstants.BORDER_BOTTOM,
				CardConstants.BACKGROUND_WIDTH / 2,
				CardConstants.BACKGROUND_HEIGHT);
		batcher.setColor(endingCardType.getColorTint());
		batcher.draw(insideCardBackground.getAssetFromAtlas(atlas), cardArea.x
				+ CardConstants.BACKGROUND_WIDTH / 2
				+ CardConstants.BORDER_SIDE, cardArea.y
				+ CardConstants.BORDER_BOTTOM,
				CardConstants.BACKGROUND_WIDTH / 2,
				CardConstants.BACKGROUND_HEIGHT);
		batcher.setColor(Color.WHITE);
	}

	private void drawEffectText(SpriteBatch batcher) {
		int line = 0;
		for (CardEffectEvent playEvent : playEvents) {
			line += playEvent.drawPlayInfo(batcher, line, cardArea);
		}
	}

	private void drawResourceIndicator(SpriteBatch batcher) {
		BattlefieldConstants.COMBO_BUBBLE_FONT.draw(batcher, "" + resourceNumber,
				cardArea.x + CardConstants.BORDER_SIDE, cardArea.y
						+ CardConstants.HEIGHT - CardConstants.BORDER_SIDE);
	}

	public void processTouch(Vector2 touchPos) {
		logInfo("new coordinate: (" + touchPos.x + ", " + touchPos.y + ")");
		cardArea.x = touchPos.x - cardArea.width / 2;
		cardArea.y = touchPos.y - cardArea.height / 2;
	}

	public void processRelease(Vector2 touchPos) {
		if (touchPos != null) {
			touchPos.y = BattlefieldConstants.SCREEN_HEIGHT - touchPos.y;
			if (touchPos.y > (startY + CardConstants.HEIGHT / 2)
					+ BattlefieldConstants.PLAY_CARD_THRESHOLD) {
				logInfo("Playing card");
				play();
			} else if (touchPos.y < (startY + CardConstants.HEIGHT / 2)
					- BattlefieldConstants.PLAY_CARD_THRESHOLD
					|| touchPos.y < BattlefieldConstants.DISCARD_SCREEN_FLOOR) {
				logInfo("Discarding card");
				discard();
			}
		}
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

	public void setResourceNumber(int resourceNumber) {
		this.resourceNumber = resourceNumber;
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
