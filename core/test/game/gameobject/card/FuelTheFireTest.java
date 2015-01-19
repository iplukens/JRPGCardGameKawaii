package game.gameobject.card;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;
import com.cgk.game.event.EventQueue;
import com.cgk.game.event.PlayEvent;
import com.cgk.game.gameobject.card.Card;
import com.cgk.game.gameobject.card.FuelTheFire;

public class FuelTheFireTest {

	private EventQueue queue;

	@Before
	public void setup() {
		queue = mock(EventQueue.class);
	}

	@Test
	public void playTest() {
		Card ftF = new FuelTheFire(queue);
		ftF.play();
		try {
			verify(queue).put((PlayEvent) anyObject());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
