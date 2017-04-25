package game.gameobject.card;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import game.gameobject.GameObjectTest;

import org.junit.Assert;
import org.junit.Test;

import com.cgk.game.event.EndEnemyTurnEvent;
import com.cgk.game.event.GameEvent;
import com.cgk.game.gameobject.Hand;
import com.cgk.game.gameobject.card.Card;
import com.cgk.game.gameobject.card.FuelTheFire;
import com.cgk.game.gameobject.card.RoseColorGlass;
import com.cgk.game.gameobject.units.UnitAttack.AttackType;

public class RoseColorGlassTest extends GameObjectTest {

	@Test
	public void playTest() {
		Card roseColorGlass = new RoseColorGlass();
		roseColorGlass.play();
		verify(queue, times(roseColorGlass.getPlayEventsSize() + 1)).put(
				(GameEvent) anyObject());
	}

	@Test
	public void testEffect() {
		Card roseColorGlass = new RoseColorGlass();
		Hand hand = new Hand();
		FuelTheFire blueFtF = new FuelTheFire();
		blueFtF.setStartingCardType(AttackType.BLUE);
		blueFtF.setEndingCardType(AttackType.BLUE);
		hand.addCard(blueFtF);
		hand.addCard(blueFtF);
		hand.addCard(blueFtF);
		hand.addCard(roseColorGlass);
		roseColorGlass.play();
		for (Card card : hand.getCards()) {
			Assert.assertEquals(AttackType.RED, card.getStartingCardType());
			Assert.assertEquals(AttackType.RED, card.getEndingCardType());
		}
		queue.put(new EndEnemyTurnEvent());
		for (Card card : hand.getCards()) {
			Assert.assertEquals(AttackType.BLUE, card.getStartingCardType());
			Assert.assertEquals(AttackType.BLUE, card.getEndingCardType());
		}
	}
}
