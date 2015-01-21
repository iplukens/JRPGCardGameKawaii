package game.gameobject;

import static org.mockito.Mockito.spy;

import org.junit.Before;

import com.cgk.game.event.EventQueue;

public class GameObjectTest {
	
	protected EventQueue queue;

	@Before
	public void setup() {
		queue = spy(new EventQueue());
	}
}
