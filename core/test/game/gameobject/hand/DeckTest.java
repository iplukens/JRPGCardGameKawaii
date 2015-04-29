package game.gameobject.hand;

import game.gameobject.GameObjectTest;

import org.junit.Assert;
import org.junit.Test;

import com.cgk.game.event.DiscardedCardEvent;
import com.cgk.game.gameobject.Deck;
import com.cgk.game.gameobject.card.Card;

public class DeckTest extends GameObjectTest {

	@Test
	public void testDrawCard() {
		Deck deck = objectFactory.getDeckObject();
		int oldSize = deck.getSize();
		deck.drawCard();
		int newSize = deck.getSize();
		Assert.assertEquals(oldSize, newSize + 1);
	}

	@Test
	public void testConstantDeckSizeAfterPlayEvent() throws Exception {
		Card card = objectFactory.getCardObject();
		Deck deck = objectFactory.getDeckObject();
		int oldDeckSize = deck.getSize();
		card.play();
		int newDeckSize = deck.getSize();
		Assert.assertEquals(oldDeckSize, newDeckSize);
	}

	@Test
	public void testConstantDeckSizeAfterDiscard() throws Exception {
		Deck deck = objectFactory.getDeckObject();
		int oldDeckSize = deck.getSize();
		queue.put(new DiscardedCardEvent(objectFactory.getCardObject()));
		int newDeckSize = deck.getSize();
		Assert.assertEquals(oldDeckSize, newDeckSize);
	}
}
