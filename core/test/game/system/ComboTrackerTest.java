package game.system;

import game.gameobject.GameObjectTest;

import org.junit.Assert;
import org.junit.Test;

import com.cgk.game.gameobject.card.Card;
import com.cgk.game.gameobject.card.FuelTheFire;
import com.cgk.game.gameobject.units.UnitAttack.AttackType;
import com.cgk.game.system.ComboTracker;

/**
 * @author ianlukens Apr 30, 2015
 *
 */
public class ComboTrackerTest extends GameObjectTest {

	@Test
	public void comboLengthTestMatchingColor() throws Exception {
		ComboTracker comboTracker = new ComboTracker(queue, 0, 0,
				AttackType.GREY);
		Card blueGreenCard = new FuelTheFire(queue);
		blueGreenCard.setStartingCardType(AttackType.BLUE);
		blueGreenCard.setEndingCardType(AttackType.GREEN);
		blueGreenCard.play();
		Assert.assertEquals(blueGreenCard.getEndingCardType(),
				comboTracker.getAttackType());
		Assert.assertEquals(blueGreenCard.getResourceNumber(),
				comboTracker.getResourceValue());
		Assert.assertEquals(1, comboTracker.getSize());
		Card greenRedCard = new FuelTheFire(queue);
		greenRedCard.setStartingCardType(AttackType.GREEN);
		greenRedCard.setEndingCardType(AttackType.RED);
		greenRedCard.play();
		Assert.assertEquals(greenRedCard.getEndingCardType(),
				comboTracker.getAttackType());
		Assert.assertEquals(greenRedCard.getResourceNumber(),
				comboTracker.getResourceValue());
		Assert.assertEquals(2, comboTracker.getSize());
	}

	@Test
	public void comboLengthTestValue() throws Exception {
		ComboTracker comboTracker = new ComboTracker(queue, 0, 0,
				AttackType.GREY);
		Card oneValueCard = new FuelTheFire(queue);
		oneValueCard.setResourceNumber(1);
		oneValueCard.play();
		Assert.assertEquals(oneValueCard.getResourceNumber(),
				comboTracker.getResourceValue());
		Assert.assertEquals(1, comboTracker.getSize());
		Card twoValueCard = new FuelTheFire(queue);
		twoValueCard.setResourceNumber(2);
		twoValueCard.play();
		Assert.assertEquals(twoValueCard.getResourceNumber(),
				comboTracker.getResourceValue());
		Assert.assertEquals(2, comboTracker.getSize());
	}

	@Test
	public void breakComboValueTest() throws Exception {
		ComboTracker comboTracker = new ComboTracker(queue, 0, 0,
				AttackType.GREY);
		Card oneValueCard = new FuelTheFire(queue);
		oneValueCard.setResourceNumber(1);
		oneValueCard.play();
		Assert.assertEquals(oneValueCard.getResourceNumber(),
				comboTracker.getResourceValue());
		Assert.assertEquals(1, comboTracker.getSize());
		Card threeValueCard = new FuelTheFire(queue);
		threeValueCard.setStartingCardType(AttackType.GREEN);
		threeValueCard.setResourceNumber(1);
		threeValueCard.play();
		Assert.assertEquals(threeValueCard.getResourceNumber(),
				comboTracker.getResourceValue());
		Assert.assertEquals(1, comboTracker.getSize());
	}

	@Test
	public void breakColorTest() throws Exception {
		ComboTracker comboTracker = new ComboTracker(queue, 0, 0,
				AttackType.GREY);
		Card blueRedCard = new FuelTheFire(queue);
		blueRedCard.setStartingCardType(AttackType.BLUE);
		blueRedCard.setEndingCardType(AttackType.RED);
		blueRedCard.play();
		Assert.assertEquals(blueRedCard.getEndingCardType(),
				comboTracker.getAttackType());
		Assert.assertEquals(blueRedCard.getResourceNumber(),
				comboTracker.getResourceValue());
		Assert.assertEquals(1, comboTracker.getSize());
		Card greenRedCard = new FuelTheFire(queue);
		greenRedCard.setStartingCardType(AttackType.GREEN);
		greenRedCard.setEndingCardType(AttackType.RED);
		greenRedCard.play();
		Assert.assertEquals(greenRedCard.getEndingCardType(),
				comboTracker.getAttackType());
		Assert.assertEquals(greenRedCard.getResourceNumber(),
				comboTracker.getResourceValue());
		Assert.assertEquals(1, comboTracker.getSize());
	}

	@Test
	public void greyDoesNotComboTest() throws Exception {
		ComboTracker comboTracker = new ComboTracker(queue, 0, 0,
				AttackType.GREY);
		Card oneValueCard = new FuelTheFire(queue);
		oneValueCard.setResourceNumber(1);
		oneValueCard.play();
		Assert.assertEquals(oneValueCard.getResourceNumber(),
				comboTracker.getResourceValue());
		Assert.assertEquals(1, comboTracker.getSize());
		oneValueCard.play();
		Assert.assertEquals(1, comboTracker.getSize());
	}
}
