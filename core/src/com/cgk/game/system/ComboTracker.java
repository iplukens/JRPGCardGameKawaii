package com.cgk.game.system;

import com.cgk.game.event.DroppedComobEvent;
import com.cgk.game.event.EventType;
import com.cgk.game.event.ResourceComboIncrementEvent;
import com.cgk.game.event.SuperComboEvent;
import com.cgk.game.event.TypeComboEvent;
import com.cgk.game.gameobject.GameObject;
import com.cgk.game.gameobject.card.Card;
import com.cgk.game.gameobject.units.UnitAttack.AttackType;
import com.cgk.game.system.eventresponses.ComboTrackerPlayResponse;

public class ComboTracker extends GameObject {

	private int currentComboSize;
	private int resourceValue;
	private AttackType attackType;

	@Override
	protected void setUpEventResponses() {
		addResponse(EventType.PLAY, new ComboTrackerPlayResponse());
	}

	@Override
	public void draw() {
		// TODO Auto-generated method stub

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
		currentComboSize++;
		if (startingCardType == attackType
				&& cardResourceNumber == resourceValue + 1) {
			sendEvent(new SuperComboEvent());
		} else if (startingCardType == attackType) {
			sendEvent(new TypeComboEvent(startingCardType));
		} else if (cardResourceNumber == resourceValue + 1) {
			sendEvent(new ResourceComboIncrementEvent(cardResourceNumber));
		} else {
			currentComboSize = 0;
			sendEvent(new DroppedComobEvent());
		}
		attackType = endCardType;
		resourceValue = cardResourceNumber;
	}

	public int getComboSize() {
		return currentComboSize;
	}

}
