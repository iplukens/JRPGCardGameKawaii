package game.gameobject.card;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import game.gameobject.GameObjectTest;

import org.junit.Assert;
import org.junit.Test;

import com.cgk.game.event.EventType;
import com.cgk.game.event.GameEvent;
import com.cgk.game.gameobject.Hand;
import com.cgk.game.gameobject.card.Card;
import com.cgk.game.gameobject.card.FuelTheFire;
import com.cgk.game.gameobject.card.RecklessAbandon;
import com.cgk.game.gameobject.card.ValueCard;
import com.cgk.game.gameobject.card.ValueType;
import com.cgk.game.gameobject.units.UnitAttack.AttackType;
import com.cgk.game.gameobject.units.hero.Hero;

public class RecklessAbandonTest extends GameObjectTest {

	@Test
	public void playTest() {
		Card recklessAbandon = new RecklessAbandon();
		recklessAbandon.play();
		verify(queue, times(recklessAbandon.getPlayEventsSize() + 1)).put(
				(GameEvent) anyObject());
	}

	@Test
	public void testEffect() {
		Card recklessAbandon = new RecklessAbandon();
		Hero hero = new Hero(10, AttackType.BLUE);
		int oldAttack = hero.getBaseAttack();
		Hand hand = new Hand();
		hand.addCard(new FuelTheFire());
		hand.addCard(recklessAbandon);
		int oldSize = hand.getSize();
		recklessAbandon.play();
		Assert.assertEquals(
				oldAttack
						+ ((ValueCard) recklessAbandon).getValue(
								EventType.ADD_BUFF, ValueType.STRENGTH),
				hero.getAttackValue(), 0.00);
		Assert.assertEquals(oldSize - 2, hand.getSize());
	}
}
