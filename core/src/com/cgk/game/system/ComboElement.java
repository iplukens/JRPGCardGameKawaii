package com.cgk.game.system;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.cgk.game.gameobject.units.UnitAttack.AttackType;
import com.cgk.game.system.ComboTracker.ComboType;
import com.cgk.game.util.BattlefieldConstants;

/**
 * @author ianlukens Apr 30, 2015
 *
 */
public class ComboElement {

	private AttackType startColor;
	private AttackType endColor;
	private int value;
	private ComboType type;

	public ComboElement(AttackType startColor, AttackType endColor, int value,
			ComboType type) {
		this.startColor = startColor;
		this.endColor = endColor;
		this.value = value;
		this.type = type;
	}

	/**
	 * @return the startColor
	 */
	public AttackType getStartColor() {
		return startColor;
	}

	/**
	 * @return the endColor
	 */
	public AttackType getEndColor() {
		return endColor;
	}

	/**
	 * @return the value
	 */
	public int getValue() {
		return value;
	}

	/**
	 * @return the type
	 */
	public ComboType getType() {
		return type;
	}

	/**
	 * @param startColor
	 *            the startColor to set
	 */
	public void setStartColor(AttackType startColor) {
		this.startColor = startColor;
	}

	/**
	 * @param endColor
	 *            the endColor to set
	 */
	public void setEndColor(AttackType endColor) {
		this.endColor = endColor;
	}

	/**
	 * @param value
	 *            the value to set
	 */
	public void setValue(int value) {
		this.value = value;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(ComboType type) {
		this.type = type;
	}

	public void draw(SpriteBatch batcher, AtlasRegion comboTop,
			AtlasRegion comboBottom, int comboNumber) {
		float x = 0 + comboNumber * BattlefieldConstants.COMBO_AREA_BETWEEN;
		float y = BattlefieldConstants.COMBO_Y;
		batcher.setColor(startColor.getColorTint());
		batcher.draw(comboTop, x, y, BattlefieldConstants.COMBO_WIDTH,
				BattlefieldConstants.COMBO_HEIGHT);
		batcher.setColor(endColor.getColorTint());
		batcher.draw(comboBottom, x, y, BattlefieldConstants.COMBO_WIDTH,
				BattlefieldConstants.COMBO_HEIGHT);
		batcher.setColor(Color.WHITE);
		BattlefieldConstants.COMBO_BUBBLE_FONT.draw(batcher, "" + value, x
				+ (BattlefieldConstants.COMBO_WIDTH / 2), y + BattlefieldConstants.COMBO_HEIGHT);
	}
}
