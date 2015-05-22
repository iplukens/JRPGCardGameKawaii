package com.cgk.game.gameobject.strategy;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ianlukens May 22, 2015
 *
 */
public abstract class Strategy {

	protected List<Modifier> modifiers;

	public Strategy() {
		modifiers = new ArrayList<>();
	}

	public void addModifier(Modifier modifier) {
		modifiers.add(modifier);
	}

	public void updateTemporaryModifiers() {
		List<Modifier> expiredMods = new ArrayList<>();
		for (Modifier modifier : modifiers) {
			modifier.updateDuration();
			if (modifier.isExpired()) {
				expiredMods.add(modifier);
			}
		}
		modifiers.removeAll(expiredMods);
	}
}
