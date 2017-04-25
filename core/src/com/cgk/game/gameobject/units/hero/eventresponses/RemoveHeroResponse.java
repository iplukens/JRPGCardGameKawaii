package com.cgk.game.gameobject.units.hero.eventresponses;

import com.cgk.game.event.GameEvent;
import com.cgk.game.gameobject.GameObject;
import com.cgk.game.gameobject.eventresponses.EventResponse;
import com.cgk.game.gameobject.units.hero.Hero;

/**
 * @author ianlukens
 * May 20, 2015
 *
 */
public class RemoveHeroResponse extends EventResponse<Hero, GameEvent> {

	@Override
	protected void handleEvent(Hero gameObject, GameEvent event) {
		GameObject.getBattlefield().removeHero(gameObject);
	}

}
