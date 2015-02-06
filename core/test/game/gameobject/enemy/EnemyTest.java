package game.gameobject.enemy;

import game.gameobject.GameObjectTest;

import org.junit.Assert;
import org.junit.Test;

import com.cgk.game.event.AttackAdditiveEvent;
import com.cgk.game.gameobject.units.enemy.Enemy;
import com.cgk.game.gameobject.units.enemy.Wesley;

public class EnemyTest extends GameObjectTest {

	private static final AttackAdditiveEvent buffEvent = new AttackAdditiveEvent(50);

	@Test
	public void enemyResponseToBuffAttackTest() {
		Enemy wesley = new Wesley(queue);
		int baseAttack = wesley.getAttack();
		wesley.respondToEvent(buffEvent);
		Assert.assertEquals(baseAttack + buffEvent.getValue(),
				wesley.getAttack());
	}

}
