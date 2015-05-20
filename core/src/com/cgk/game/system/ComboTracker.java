package com.cgk.game.system;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.cgk.game.event.DroppedComboEvent;
import com.cgk.game.event.EventType;
import com.cgk.game.event.ResourceComboIncrementEvent;
import com.cgk.game.event.SuperComboEvent;
import com.cgk.game.event.TypeComboEvent;
import com.cgk.game.gameobject.GameObject;
import com.cgk.game.gameobject.card.Card;
import com.cgk.game.gameobject.units.UnitAttack.AttackType;
import com.cgk.game.system.eventresponses.ComboTrackerPlayResponse;

public class ComboTracker extends GameObject {

	private int comboBaseValue;

	public enum ComboType {
		SUPER, COLOR, VALUE, BASE;
	}

	private List<ComboElement> combos = new ArrayList<>();
	private int resourceValue;
	private AttackType attackType;
	private static Asset<Texture> comboTop = new Asset<>(
			"assets/topComboElement.png", Texture.class);
	private static Asset<Texture> comboBottom = new Asset<>(
			"assets/bottomComboElement.png", Texture.class);	

	public ComboTracker(int comboBaseValue,
			int resourceValue, AttackType attackType) {
		super();
		this.setComboBaseValue(comboBaseValue);
		this.resourceValue = resourceValue;
		this.attackType = attackType;
	}

	@Override
	protected void setupEventResponses() {
		addResponse(EventType.PLAY, new ComboTrackerPlayResponse());
	}

	@Override
	public void erase() {
		// TODO Auto-generated method stub

	}

	/**
	 * updates the combo based on the card's attackType, value numbers
	 * 
	 * @param card
	 */
	public void updateCombo(Card card) {
		AttackType startingCardType = card.getStartingCardType();
		AttackType endCardType = card.getEndingCardType();
		int cardResourceNumber = card.getResourceNumber();
		ComboType comboType;
		if (isColorCombo(startingCardType) && isValueCombo(cardResourceNumber)) {
			comboType = ComboType.SUPER;
			sendEvent(new SuperComboEvent());
		} else if (isColorCombo(startingCardType)) {
			comboType = ComboType.COLOR;
			sendEvent(new TypeComboEvent(startingCardType));
		} else if (isValueCombo(cardResourceNumber)) {
			comboType = ComboType.VALUE;
			sendEvent(new ResourceComboIncrementEvent(cardResourceNumber));
		} else {
			comboType = ComboType.BASE;
			if (getSize() > 0) {
				sendEvent(new DroppedComboEvent());
			}
			combos.clear();
		}
		combos.add(new ComboElement(startingCardType, endCardType,
				cardResourceNumber, comboType));
		attackType = endCardType;
		resourceValue = cardResourceNumber;
	}

	private boolean isValueCombo(int cardResourceNumber) {
		return cardResourceNumber == resourceValue + 1;
	}

	public boolean isColorCombo(AttackType startingCardType) {
		return startingCardType == attackType
				&& startingCardType != AttackType.GREY;
	}

	public int getSize() {
		return combos.size();
	}

	public List<ComboElement> getCombos() {
		return combos;
	}

	public AttackType getAttackType() {
		return attackType;
	}

	public int getResourceValue() {
		return resourceValue;
	}

	@Override
	public void draw(SpriteBatch batcher, TextureAtlas atlas) {
		for (int comboNumber = 0; comboNumber < combos.size(); comboNumber++) {
			combos.get(comboNumber).draw(batcher,
					comboTop.getAssetFromAtlas(atlas),
					comboBottom.getAssetFromAtlas(atlas), comboNumber);
			if (comboNumber + 1 < combos.size()) {
				switch (combos.get(comboNumber).getType()) {
				case BASE:
					break;
				case COLOR:
					drawColorConnector(comboNumber, combos.get(comboNumber)
							.getEndColor());
					break;
				case SUPER:
					drawSuperConnector(comboNumber, combos.get(comboNumber)
							.getEndColor());
					break;
				case VALUE:
					drawValueConnector(comboNumber);
					break;
				}
			}
		}
	}

	private void drawValueConnector(int comboNumber) {
		// TODO Auto-generated method stub

	}

	private void drawSuperConnector(int comboNumber, AttackType attackType) {
		// TODO Auto-generated method stub

	}

	private void drawColorConnector(int comboNumber, AttackType attackType2) {
		// TODO Auto-generated method stub

	}

	public int getComboBaseValue() {
		return comboBaseValue;
	}

	public void setComboBaseValue(int comboBaseValue) {
		this.comboBaseValue = comboBaseValue;
	}

	@Override
	public List<Asset<Texture>> getTextureAssets() {
		List<Asset<Texture>> textureAssets = new ArrayList<>();
		textureAssets.add(comboBottom);
		textureAssets.add(comboTop);
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

	public void reset() {
		combos.clear();
		resourceValue = -2;
		attackType = AttackType.GREY;
	}

}
