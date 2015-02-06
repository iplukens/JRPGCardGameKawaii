package game.system;

import game.gameobject.GameObjectTest;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.*;

import org.junit.Assert;
import org.junit.Test;

import com.cgk.game.event.AttackPlayerEvent;
import com.cgk.game.event.GameEvent;
import com.cgk.game.event.PlayEvent;
import com.cgk.game.gameobject.card.FuelTheFire;
import com.cgk.game.gameobject.units.enemy.DeBoOg;
import com.cgk.game.gameobject.units.enemy.Enemy;
import com.cgk.game.gameobject.units.enemy.Wesley;

public class EventQueueTest extends GameObjectTest {

	@Test
	public void registerGameObject() {
		Enemy wesley = new Wesley(queue);
		Assert.assertTrue(queue.containsRegisteredObject(wesley));
	}

	@Test
	public void chainEventTest() throws InterruptedException {
		new DeBoOg(queue);
		queue.put(new PlayEvent(new FuelTheFire(queue)));
		verify(queue, times(3)).put((GameEvent) anyObject());
	}

	@Test
	public void maximumStackTest() throws InterruptedException {
		new DeBoOg(queue);
		queue.put(new AttackPlayerEvent(50));
	}

}
