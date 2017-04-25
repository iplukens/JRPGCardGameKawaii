package game.gameobject;

import static org.mockito.Mockito.spy;

import org.junit.Before;

import com.cgk.game.gameobject.GameObject;
import com.cgk.game.system.Battlefield;
import com.cgk.game.system.EventQueue;

public class GameObjectTest {

	protected Battlefield battlefield;
	protected EventQueue queue;
	protected GameObjectFactory objectFactory;

	@Before
	public void setup() {
		queue = spy(new EventQueue());
		objectFactory = new GameObjectFactory();
		GameObject.setQueue(queue);
		battlefield = new Battlefield();
		GameObject.setBattlefield(battlefield);
		System.out.println("STARTING TEST METHOD");
	}
}
