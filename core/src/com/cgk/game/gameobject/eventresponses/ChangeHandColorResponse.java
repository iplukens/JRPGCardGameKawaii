package com.cgk.game.gameobject.eventresponses;

import com.cgk.game.event.cardevents.ChangeHandColorEvent;
import com.cgk.game.gameobject.Hand;
import com.cgk.game.gameobject.card.Card;

/**
 * @author ianlukens May 21, 2015
 *
 */
public class ChangeHandColorResponse extends
		EventResponse<Hand, ChangeHandColorEvent> {

	@Override
	protected void handleEvent(Hand gameObject, ChangeHandColorEvent event) {
		for (Card card : gameObject.getCards()) {
			card.addModifier(event.getModifier());
		}
	}

}
