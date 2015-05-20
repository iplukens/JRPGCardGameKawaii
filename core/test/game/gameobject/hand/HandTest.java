package game.gameobject.hand;

import game.gameobject.GameObjectTest;

import org.junit.Assert;
import org.junit.Test;

import com.cgk.game.event.PlayEvent;
import com.cgk.game.event.cardevents.RandomDiscardEvent;
import com.cgk.game.gameobject.Hand;
import com.cgk.game.gameobject.card.Card;
import com.cgk.game.gameobject.card.FuelTheFire;

public class HandTest extends GameObjectTest {

	@Test
	public void testDiscard() {
		Hand hand = objectFactory.getHandObject();
		int oldHandSize = hand.getSize();
		hand.sendEvent(new RandomDiscardEvent());
		int newHandSize = hand.getSize();
		Assert.assertEquals(oldHandSize, newHandSize + 1);
	}

	@Test
	public void testConstantHandSize() throws Exception {
		Hand hand = objectFactory.getHandObject();
		int oldHandSize = hand.getSize();
		objectFactory.getDeckObject();
		queue.put(new RandomDiscardEvent());
		int newHandSize = hand.getSize();
		Assert.assertEquals(oldHandSize, newHandSize);
	}

	@Test
	public void testPlayingACardRemovesItFromHand() throws Exception {
		Hand hand = objectFactory.getHandObject();
		Card card = new FuelTheFire();
		hand.addCard(card);
		objectFactory.getDeckObject();
		queue.put(new PlayEvent(card));
		Assert.assertFalse(hand.getCards().contains(card));
	}
}
