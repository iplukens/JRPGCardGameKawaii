package game.gameobject.card;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import game.gameobject.GameObjectTest;

import org.junit.Test;

import com.cgk.game.event.GameEvent;
import com.cgk.game.gameobject.card.Card;
import com.cgk.game.gameobject.card.FuelTheFire;

public class FuelTheFireTest extends GameObjectTest {

	@Test
	public void playTest() {
		Card ftF = new FuelTheFire(queue);
		ftF.play();
		try {
			verify(queue, times(2)).put((GameEvent) anyObject());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
