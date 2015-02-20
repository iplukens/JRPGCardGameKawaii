package game.gameobject;

import static org.mockito.Mockito.spy;

import org.junit.Before;

import com.cgk.game.system.EventQueue;

public class GameObjectTest {

	protected EventQueue queue;
	protected GameObjectFactory objectFactory;

	@Before
	public void setup() {
		queue = spy(new EventQueue());
		objectFactory = new GameObjectFactory(queue);
	}
}
