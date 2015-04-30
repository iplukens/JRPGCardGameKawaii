package game.system;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import game.gameobject.GameObjectTest;

import org.junit.Test;

import com.cgk.game.event.GameEvent;
import com.cgk.game.event.PlayEvent;
import com.cgk.game.gameobject.card.FuelTheFire;
import com.cgk.game.gameobject.units.UnitAttack.AttackType;
import com.cgk.game.system.ComboTracker;

/**
 * @author ianlukens
 * Apr 30, 2015
 *
 */
public class ComboTrackerTest extends GameObjectTest {

	@Test
	public void chainEventTest() throws InterruptedException {
		new ComboTracker(queue, 0, 0, AttackType.GREY);
		queue.put(new PlayEvent(new FuelTheFire(queue)));
		verify(queue, times(3)).put((GameEvent) anyObject());
	}
}
