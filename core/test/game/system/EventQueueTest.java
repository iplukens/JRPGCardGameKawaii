package game.system;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import game.gameobject.GameObjectTest;

import org.junit.Assert;
import org.junit.Test;

import com.cgk.game.event.AttackPlayerEvent;
import com.cgk.game.event.GameEvent;
import com.cgk.game.event.PlayEvent;
import com.cgk.game.gameobject.card.FuelTheFire;
import com.cgk.game.gameobject.units.UnitAttack.AttackType;
import com.cgk.game.gameobject.units.enemy.DeBoOg;
import com.cgk.game.gameobject.units.enemy.Enemy;
import com.cgk.game.gameobject.units.enemy.Wesley;
import com.cgk.game.gameobject.units.hero.Hero;

public class EventQueueTest extends GameObjectTest {

	@Test
	public void registerGameObject() {
		Enemy wesley = new Wesley();
		Assert.assertTrue(queue.containsRegisteredObject(wesley));
	}

	@Test
	public void chainEventTest() throws InterruptedException {
		battlefield.addHero(new Hero(800, AttackType.BLUE));
		new DeBoOg();
		queue.put(new PlayEvent(new FuelTheFire()));
		verify(queue, times(3)).put((GameEvent) anyObject());
	}

	@Test
	public void maximumStackTest() throws InterruptedException {
		Enemy deboOg = new DeBoOg();
		Hero hero = new Hero(queue.getMaxEvents() * 50,
				AttackType.BLUE);
		battlefield.addHero(hero);
		deboOg.setAttackType(AttackType.BLUE);
		deboOg.setBaseAttack(1);
		queue.put(new AttackPlayerEvent(deboOg, hero));
		verify(queue, times(queue.getMaxEvents() + 1)).put(
				(GameEvent) anyObject());
	}

	@Test
	public void chainStopsOnHeroDeath() throws InterruptedException {
		int heroHealth = 50;
		Hero hero = new Hero(heroHealth, AttackType.BLUE);
		battlefield.addHero(hero);
		DeBoOg deboog = new DeBoOg();
		deboog.setBaseAttack(heroHealth);
		queue.put(new AttackPlayerEvent(deboog, hero));
		verify(queue, times(4)).put((GameEvent) anyObject());
	}

}
