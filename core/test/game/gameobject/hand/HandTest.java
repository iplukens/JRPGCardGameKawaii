package game.gameobject.hand;

import game.gameobject.GameObjectTest;

import org.junit.Assert;
import org.junit.Test;

import com.cgk.game.event.cardevents.DiscardEvent;
import com.cgk.game.gameobject.Hand;

public class HandTest extends GameObjectTest {

	@Test
	public void testDiscard() {
		Hand hand = objectFactory.getHandObject();
		int oldHandSize = hand.getSize();
		hand.sendEvent(new DiscardEvent());
		int newHandSize = hand.getSize();
		Assert.assertEquals(oldHandSize, newHandSize + 1);
	}

	@Test
	public void testConstantHandSize() throws Exception {
		Hand hand = objectFactory.getHandObject();
		int oldHandSize = hand.getSize();
		objectFactory.getDeckObject();
		queue.put(new DiscardEvent());
		int newHandSize = hand.getSize();
		Assert.assertEquals(oldHandSize, newHandSize);
	}
}
