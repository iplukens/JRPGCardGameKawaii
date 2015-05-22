package game.gameobject.card;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import game.gameobject.GameObjectTest;

import org.junit.Assert;
import org.junit.Test;

import com.cgk.game.event.GameEvent;
import com.cgk.game.gameobject.card.Card;
import com.cgk.game.gameobject.card.HealingWind;
import com.cgk.game.gameobject.units.UnitAttack.AttackType;
import com.cgk.game.gameobject.units.hero.Hero;

public class HealingWindTest extends GameObjectTest {

	@Test
	public void playTest() {
		Card healingWind = new HealingWind();
		healingWind.play();
		verify(queue, times(2)).put((GameEvent) anyObject());
	}

	@Test
	public void testEffect() {
		Card healingWind = new HealingWind();
		Hero hero = new Hero(10, AttackType.BLUE);
		hero.setCurrentHealth(5f);
		healingWind.play();
		Assert.assertEquals(hero.getMaxhealth(), hero.getHealth(), 0.00);
	}
}
