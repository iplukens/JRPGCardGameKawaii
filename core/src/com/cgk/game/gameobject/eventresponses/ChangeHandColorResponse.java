package com.cgk.game.gameobject.eventresponses;

import com.cgk.game.event.cardevents.ChangeHandColorEvent;
import com.cgk.game.gameobject.Hand;
import com.cgk.game.gameobject.card.Card;
import com.cgk.game.gameobject.card.ValueType;
import com.cgk.game.gameobject.strategy.AttackTypeStrategyModifier;

/**
 * @author ianlukens May 21, 2015
 *
 */
public class ChangeHandColorResponse extends
		EventResponse<Hand, ChangeHandColorEvent> {

	@Override
	protected void handleEvent(Hand gameObject, ChangeHandColorEvent event) {
		for (Card card : gameObject.getCards()) {
			card.addModifier(new AttackTypeStrategyModifier(event.getColor(),
					event.getColor(), event.getValue(ValueType.DURATION)));
		}
	}

}
