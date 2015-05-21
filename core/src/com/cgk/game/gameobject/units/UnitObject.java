package com.cgk.game.gameobject.units;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.cgk.game.event.EventType;
import com.cgk.game.event.GameEvent;
import com.cgk.game.gameobject.GameObject;
import com.cgk.game.gameobject.units.UnitAttack.AttackType;
import com.cgk.game.gameobject.units.eventresponses.UnitAttackAdditiveEventResponse;
import com.cgk.game.gameobject.units.eventresponses.UnitAttackMultiplierEventResponse;
import com.cgk.game.system.Asset;
import com.cgk.game.util.BattlefieldConstants;

/**
 * generic class representing the on-board units in the game
 * 
 * @author ianlukens
 *
 */
public abstract class UnitObject extends GameObject {
	protected float maxHealth = 100f;
	protected float currentHealth = 1f;
	protected int baseAttack = 0;
	protected int tempAttackAdditive = 0;
	protected int tempAttackMultiplicative = 1;
	protected AttackType attackType;
	protected Map<AttackType, Double> resistances;
	protected Rectangle unitBox;
	protected Asset<Texture> currentGraphic;
	protected static Asset<Texture> healthBar = new Asset<Texture>(
			"assets/healthBar.png", Texture.class);

	public UnitObject() {
		super();
		setUpResistances();
	}

	public static List<Asset<Texture>> getBaseTextureAssets() {
		List<Asset<Texture>> textureAssets = new ArrayList<Asset<Texture>>();
		textureAssets.add(healthBar);
		return textureAssets;
	}

	protected void setUpResistances() {
		resistances = new HashMap<UnitAttack.AttackType, Double>();
		AttackType[] resistanceTypes = AttackType.values();
		double resistanceBaseValue = 1.0;
		for (AttackType type : resistanceTypes) {
			resistances.put(type, resistanceBaseValue);
		}
	}

	@Override
	public void respondToEvent(GameEvent event) {
		super.respondToEvent(event);
		if (isDead()) {
			sendOnDeathEvents();
			unregister();
		}
	}

	/**
	 * 
	 * @param attackType
	 * @return
	 */
	public double getResistanceTo(AttackType attackType) {
		return resistances.get(attackType);
	}

	/**
	 * 
	 * @param attackType
	 * @param newValue
	 */
	public void setResistanceTo(AttackType attackType, Double newValue) {
		resistances.put(attackType, newValue);
	}

	/**
	 * add to the current resistance value
	 * 
	 * @param attackType
	 * @param addedValue
	 */
	public void addResistanceTo(AttackType attackType, Double addedValue) {
		resistances.put(attackType, resistances.get(attackType) + addedValue);
	}

	/**
	 * events the unit sends when it dies
	 */
	protected abstract void sendOnDeathEvents();

	@Override
	protected void setupEventResponses() {
		addResponse(EventType.ADD_BUFF, new UnitAttackAdditiveEventResponse());
		addResponse(EventType.MULT_BUFF,
				new UnitAttackMultiplierEventResponse());
	}

	public UnitAttack getAttack() {
		return new UnitAttack(getAttackValue(), attackType);
	}

	public int getAttackValue() {
		return (getBaseAttack() + getTempAttackAdditive())
				* getTempAttackMultiplicative();
	}

	public abstract void sendAttackEvent();

	protected boolean isDead() {
		return currentHealth <= 0;
	}

	/*
	 * getters and setters
	 */

	public int getBaseAttack() {
		return baseAttack;
	}

	public void setBaseAttack(int baseAttack) {
		this.baseAttack = baseAttack;
	}

	public int getTempAttackAdditive() {
		return tempAttackAdditive;
	}

	public void setTempAttackAdditive(int tempAttackAdditive) {
		this.tempAttackAdditive = tempAttackAdditive;
	}

	public int getTempAttackMultiplicative() {
		return tempAttackMultiplicative;
	}

	public void setTempAttackMultiplicative(int tempAttackMultiplicative) {
		this.tempAttackMultiplicative = tempAttackMultiplicative;
	}

	public float getHealth() {
		return currentHealth;
	}

	public void setHealth(float health) {
		this.currentHealth = health;
	}

	/**
	 * @return the attackType
	 */
	public AttackType getAttackType() {
		return attackType;
	}

	/**
	 * @param attackType
	 *            the attackType to set
	 */
	public void setAttackType(AttackType attackType) {
		this.attackType = attackType;
	}

	public void processAttack(UnitAttack attack) {
		double resistanceValue = getResistanceTo(attack.getType());
		this.currentHealth -= attack.getValue() * resistanceValue;
	}

	protected void drawHealthBar(SpriteBatch batcher, TextureAtlas atlas,
			Rectangle unitBox) {
		float percentHealthFull = currentHealth / maxHealth;
		if (percentHealthFull <= 0) {
			batcher.setColor(Color.RED);
			batcher.draw(healthBar.getAssetFromAtlas(atlas), unitBox.x,
					unitBox.y, unitBox.width, BattlefieldConstants.HEALTHBAR_HEIGHT);
		} else {
			float widthFull = unitBox.width * percentHealthFull;
			batcher.setColor(Color.GREEN);
			batcher.draw(healthBar.getAssetFromAtlas(atlas), unitBox.x,
					unitBox.y, widthFull, BattlefieldConstants.HEALTHBAR_HEIGHT);
			float startXEmpty = unitBox.x + widthFull;
			float widthEmpty = unitBox.width - widthFull;
			batcher.setColor(Color.RED);
			batcher.draw(healthBar.getAssetFromAtlas(atlas), startXEmpty,
					unitBox.y, widthEmpty, BattlefieldConstants.HEALTHBAR_HEIGHT);
		}
		batcher.setColor(Color.WHITE);
	}

	public boolean touched(Vector2 touchPos) {
		return unitBox.contains(touchPos);
	}

}
