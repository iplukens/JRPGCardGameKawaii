package game.system;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import game.gameobject.GameObjectTest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.cgk.game.event.GameEvent;
import com.cgk.game.gameobject.units.UnitAttack.AttackType;
import com.cgk.game.gameobject.units.enemy.Wesley;
import com.cgk.game.gameobject.units.hero.Hero;
import com.cgk.game.system.PlayerTurnTimer;

/**
 * @author ianlukens May 20, 2015
 *
 */
public class PlayerTimerTest extends GameObjectTest {

	private PlayerTurnTimer timer;

	@Before
	public void setupTimer() {
		timer = new PlayerTurnTimer(5, 5, 5);
	}

	@Test
	public void testTimerUpdateWorks() {
		timer.startTimer();
		Assert.assertEquals(timer.getBaseTurnTime(), timer.getTimeRemaining(),
				0.00);
		float timePassed = 1.00f;
		timer.update(timePassed);
		Assert.assertEquals(timer.getBaseTurnTime() - timePassed,
				timer.getTimeRemaining(), 0.00);
	}

	@Test
	public void testEndTurnEventSent() {
		timer.startTimer();
		float timePassed = timer.getTimeRemaining();
		timer.update(timePassed);
		verify(queue, times(1)).put((GameEvent) anyObject());
	}

	@Test
	public void testHeroAndEnemyAttack() {
		timer.startTimer();
		new Hero(500, AttackType.BLUE);
		new Wesley();
		float timePassed = timer.getTimeRemaining();
		timer.update(timePassed);
		verify(queue, times(3)).put((GameEvent) anyObject());
	}

	@Test
	public void testHeroAttackBeforeEnemy() {
		timer.startTimer();
		Hero hero = new Hero(5, AttackType.BLUE);
		Wesley wesley = new Wesley();
		wesley.setBaseAttack((int) hero.getHealth());
		hero.setBaseAttack((int) wesley.getHealth());
		float timePassed = timer.getTimeRemaining();
		timer.update(timePassed);
		verify(queue, times(4)).put((GameEvent) anyObject());
	}

}
