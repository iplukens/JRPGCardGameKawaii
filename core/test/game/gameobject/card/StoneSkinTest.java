package game.gameobject.card;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import game.gameobject.GameObjectTest;

import org.junit.Assert;
import org.junit.Test;

import com.cgk.game.event.EndEnemyTurnEvent;
import com.cgk.game.event.EventType;
import com.cgk.game.event.GameEvent;
import com.cgk.game.gameobject.card.Card;
import com.cgk.game.gameobject.card.StoneSkin;
import com.cgk.game.gameobject.card.ValueCard;
import com.cgk.game.gameobject.card.ValueType;
import com.cgk.game.gameobject.units.UnitAttack.AttackType;
import com.cgk.game.gameobject.units.hero.Hero;

public class StoneSkinTest extends GameObjectTest {

	@Test
	public void playTest() {
		Card stoneSkin = new StoneSkin();
		stoneSkin.play();
		verify(queue, times(stoneSkin.getPlayEventsSize() + 1)).put(
				(GameEvent) anyObject());
	}

	@Test
	public void testEffect() {
		ValueCard stoneSkin = new StoneSkin();
		Hero hero = new Hero(20, AttackType.BLUE);
		double oldRes = hero.getResistanceTo(AttackType.GREEN);
		stoneSkin.play();
		double newRes = hero.getResistanceTo(AttackType.GREEN);
		Assert.assertEquals(
				oldRes
						+ stoneSkin.getValue(EventType.ADD_RESISTANCE_MOD,
								ValueType.STRENGTH), newRes, 0.0);
		int duration = stoneSkin.getValue(EventType.ADD_RESISTANCE_MOD,
				ValueType.DURATION);
		for (int i = 0; i < duration; i++) {
			queue.put(new EndEnemyTurnEvent());
		}
		Assert.assertEquals(oldRes, hero.getResistanceTo(AttackType.GREEN), 0.0);

	}
}
