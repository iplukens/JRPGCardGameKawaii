package com.cgk.game.gameobject.card;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.graphics.Texture;
import com.cgk.game.event.EventType;
import com.cgk.game.event.GameEvent;
import com.cgk.game.event.cardevents.ValueEvent;
import com.cgk.game.system.Asset;

/**
 * @author ianlukens May 20, 2015
 *
 */
public abstract class ValueCard extends Card {

	protected ValueCard(String cardName, Asset<Texture> cardGraphic) {
		super(cardName, cardGraphic);
		this.values = new HashMap<EventType, Map<ValueType, Integer>>();
	}

	protected Map<EventType, Map<ValueType, Integer>> values;

	public void addValue(GameEvent event, ValueType valueKey, int value) {
		addValue(event.getType(), valueKey, value);
	}

	public void addValue(EventType eventKey, ValueType valueKey, int value) {
		Map<ValueType, Integer> valueMap = values.get(eventKey);
		if (valueMap == null) {
			valueMap = new HashMap<ValueType, Integer>();
			values.put(eventKey, valueMap);
		}
		valueMap.put(valueKey, value);
	}

	public int getValue(EventType eventKey, ValueType valueKey) {
		return values.get(eventKey).get(valueKey);
	}

	public boolean addPlayValueEvent(ValueEvent event, ValueType valueKey,
			int value) {
		addValue(event, valueKey, value);
		return addPlayEvent(event);
	}

	public boolean addPlayValueEvent(ValueEvent event,
			Map<ValueType, Integer> values) {
		setValues(event, values);
		return addPlayEvent(event);
	}

	public void setValues(ValueEvent event, Map<ValueType, Integer> valueMap) {
		values.put(event.getType(), valueMap);
	}

}
