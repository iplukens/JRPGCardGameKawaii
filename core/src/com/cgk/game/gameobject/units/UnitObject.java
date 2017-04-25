package com.cgk.game.gameobject.units;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.cgk.game.event.EventType;
import com.cgk.game.event.GameEvent;
import com.cgk.game.gameobject.GameObject;
import com.cgk.game.gameobject.eventresponses.UpdateModifiersResponse;
import com.cgk.game.gameobject.strategy.AttackTypeStrategyModifier;
import com.cgk.game.gameobject.strategy.IModifiable;
import com.cgk.game.gameobject.units.UnitAttack.AttackType;
import com.cgk.game.gameobject.units.eventresponses.UnitAddResistanceResponse;
import com.cgk.game.gameobject.units.eventresponses.UnitAttackAdditiveEventResponse;
import com.cgk.game.gameobject.units.eventresponses.UnitAttackMultiplierEventResponse;
import com.cgk.game.gameobject.units.strategy.AttackStatsStrategy;
import com.cgk.game.gameobject.units.strategy.ResistanceStrategyModifier;
import com.cgk.game.gameobject.units.strategy.ResistancesStrategy;
import com.cgk.game.system.Asset;
import com.cgk.game.util.BattlefieldConstants;

/**
 * generic class representing the on-board units in the game
 * 
 * @author ianlukens
 *
 */
public abstract class UnitObject extends GameObject implements IModifiable {
	protected float maxHealth = 100f;
	protected float currentHealth = 1f;
	protected AttackStatsStrategy attackStats;
	protected ResistancesStrategy resistanceStats = new ResistancesStrategy();
	protected Rectangle unitBox;
	protected Asset<Texture> currentGraphic;
	protected static Asset<Texture> healthBar = new Asset<Texture>(
			"assets/healthBar.png", Texture.class);

	public UnitObject() {
		super();
	}

	public static List<Asset<Texture>> getBaseTextureAssets() {
		List<Asset<Texture>> textureAssets = new ArrayList<Asset<Texture>>();
		textureAssets.add(healthBar);
		return textureAssets;
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
		return resistanceStats.get(attackType);
	}

	/**
	 * 
	 * @param attackType
	 * @param newValue
	 */
	public void setBaseResistanceTo(AttackType attackType, Double newValue) {
		resistanceStats.setBaseResistanceTo(attackType, newValue);
	}

	/**
	 * events the unit sends when it dies
	 */
	protected abstract void sendOnDeathEvents();

	@Override
	protected void setupEventResponses() {
		addEventResponse(EventType.ADD_BUFF,
				new UnitAttackAdditiveEventResponse());
		addEventResponse(EventType.MULT_BUFF,
				new UnitAttackMultiplierEventResponse());
		addEventResponse(EventType.END_ENEMY_TURN,
				new UpdateModifiersResponse());
		addEventResponse(EventType.ADD_RESISTANCE_MOD,
				new UnitAddResistanceResponse());
	}

	public UnitAttack getAttack() {
		return attackStats.getAttack();
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
		return attackStats.getBaseAttack();
	}

	public void setBaseAttack(int baseAttack) {
		attackStats.setBaseAttack(baseAttack);
	}

	public int getTempAttackAdditive() {
		return attackStats.getTempAttackAdditive();
	}

	public void setTempAttackAdditive(int tempAttackAdditive) {
		attackStats.setTempAttackAdditive(tempAttackAdditive);
	}

	public int getTempAttackMultiplicative() {
		return attackStats.getTempAttackMultiplicative();
	}

	public void setTempAttackMultiplicative(int tempAttackMultiplicative) {
		attackStats.setTempAttackMultiplicative(tempAttackMultiplicative);
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
		return attackStats.getAttackType();
	}

	/**
	 * @param attackType
	 *            the attackType to set
	 */
	public void setAttackType(AttackType attackType) {
		attackStats.setAttackType(attackType);
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
					unitBox.y, unitBox.width,
					BattlefieldConstants.HEALTHBAR_HEIGHT);
		} else {
			float widthFull = unitBox.width * percentHealthFull;
			batcher.setColor(Color.GREEN);
			batcher.draw(healthBar.getAssetFromAtlas(atlas), unitBox.x,
					unitBox.y, widthFull, BattlefieldConstants.HEALTHBAR_HEIGHT);
			float startXEmpty = unitBox.x + widthFull;
			float widthEmpty = unitBox.width - widthFull;
			batcher.setColor(Color.RED);
			batcher.draw(healthBar.getAssetFromAtlas(atlas), startXEmpty,
					unitBox.y, widthEmpty,
					BattlefieldConstants.HEALTHBAR_HEIGHT);
		}
		batcher.setColor(Color.WHITE);
	}

	public boolean touched(Vector2 touchPos) {
		return unitBox.contains(touchPos);
	}

	public void heal(int value) {
		float newHealth = currentHealth + value;
		if (newHealth > maxHealth) {
			currentHealth = maxHealth;
		} else {
			currentHealth = newHealth;
		}
	}

	public void updateModifiers() {
		attackStats.updateTemporaryModifiers();
		resistanceStats.updateTemporaryModifiers();
	}

	public void addAttackModifier(AttackTypeStrategyModifier modifier) {
		attackStats.addModifier(modifier);
	}

	public void addResistanceModifier(ResistanceStrategyModifier modifier) {
		resistanceStats.addModifier(modifier);
	}

}
