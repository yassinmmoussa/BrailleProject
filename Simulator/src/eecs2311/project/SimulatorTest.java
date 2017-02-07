package eecs2311.project;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * This is a JUnit test class for Simulator class.
 * @author Team 4: Yassin Mohamed, Qassim Allauddin, Derek Li, Artem Solovey.
 *
 */
public class SimulatorTest {

	static private Simulator x;
	static private Simulator y;

	/**
	 * The ExpectedException rule allows you to verify that your code throws a
	 * specific exception. We initialize it to expect no exceptions to be thrown
	 * yet.
	 * 
	 */
	@Rule
	public ExpectedException exceptionRule = ExpectedException.none();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * Is executed before every test, sets up the two Simulators to be tested.
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		x = new Simulator(1, 1);
		y = new Simulator(3, 1);

	}

	/**
	 * Test the <code> getButton()</code> method, checking that the one button
	 * that was initialized is returned when the method is called with the
	 * correct index passed, and checking that the method throws the correct
	 * exceptions when an incorrect index is passed.
	 * 
	 */
	@Test
	public void testGetButton() {
		int ceilBound = x.buttonList.size();
		int floorBound = -1;

		assertNotNull(x.getButton(0));

		exceptionRule.expect(IllegalArgumentException.class);
		x.getButton(ceilBound);
		exceptionRule.expect(IllegalArgumentException.class);
		x.getButton(floorBound);

	}

	/**
	 * Tests the <code> getCell()</code> method, checking that the reference to
	 * the cell is returned when the method is called and the correct index is
	 * passed, and checking that the method throws the correct exceptions if the
	 * index that was passed was invalid.
	 * 
	 */
	@Test
	public void testGetCell() {
		int ceilBound = x.brailleList.size();
		int floorBound = -1;

		
		assertNotNull(x.getCell(0));

	
		exceptionRule.expect(IllegalArgumentException.class);
		x.getCell(ceilBound);
		exceptionRule.expect(IllegalArgumentException.class);
		x.getCell(floorBound);

	}

	/**
	 * Tests the <code>clearAllCells()</code> method, calling it then checking
	 * that all the radio buttons in all the cells that are instantiated in the
	 * Simulator are not selected.
	 * 
	 */
	@Test
	public void testClearAllCells() {
		
		x.clearAllCells();
		for (int i = 0; i < x.brailleCellNumber; i++) {
			assertFalse(x.brailleList.get(i).radio1x1.isSelected());
			assertFalse(x.brailleList.get(i).radio1x2.isSelected());
			assertFalse(x.brailleList.get(i).radio2x1.isSelected());
			assertFalse(x.brailleList.get(i).radio2x2.isSelected());
			assertFalse(x.brailleList.get(i).radio3x1.isSelected());
			assertFalse(x.brailleList.get(i).radio3x2.isSelected());
			assertFalse(x.brailleList.get(i).radio4x1.isSelected());
			assertFalse(x.brailleList.get(i).radio4x2.isSelected());
		}
	}

	/**
	 * Tests the <code>displayString()</code> method, passing a string and
	 * checking that the correct braille cells are displaying the correct
	 * character in the string.
	 */
	@Test
	public void testDisplayString() {
		y.displayString("hey");

	
		assertTrue("pin at 1x1 must be true", y.brailleList.get(0).radio1x1.isSelected());
		assertFalse("pin at 1x2 must be false", y.brailleList.get(0).radio1x2.isSelected());
		assertTrue("pin at 2x1 must be true", y.brailleList.get(0).radio2x1.isSelected());
		assertTrue("pin at 2x2 must be true", y.brailleList.get(0).radio2x2.isSelected());
		assertFalse("pin at 3x1 must be false", y.brailleList.get(0).radio3x1.isSelected());
		assertFalse("pin at 3x2 must be false", y.brailleList.get(0).radio3x2.isSelected());
		assertFalse("pin at 4x1 must be false", y.brailleList.get(0).radio4x1.isSelected());
		assertFalse("pin at 4x2 must be false", y.brailleList.get(0).radio4x2.isSelected());

	
		assertTrue("pin at 1x1 must be true", y.brailleList.get(1).radio1x1.isSelected());
		assertFalse("pin at 1x2 must be false", y.brailleList.get(1).radio1x2.isSelected());
		assertFalse("pin at 2x1 must be false", y.brailleList.get(1).radio2x1.isSelected());
		assertTrue("pin at 2x2 must be true", y.brailleList.get(1).radio2x2.isSelected());
		assertFalse("pin at 3x1 must be false", y.brailleList.get(1).radio3x1.isSelected());
		assertFalse("pin at 3x2 must be false", y.brailleList.get(1).radio3x2.isSelected());
		assertFalse("pin at 4x1 must be false", y.brailleList.get(1).radio4x1.isSelected());
		assertFalse("pin at 4x2 must be false", y.brailleList.get(1).radio4x2.isSelected());


		assertTrue("pin at 1x1 must be true", y.brailleList.get(2).radio1x1.isSelected());
		assertTrue("pin at 1x2 must be true", y.brailleList.get(2).radio1x2.isSelected());
		assertFalse("pin at 2x1 must be false", y.brailleList.get(2).radio2x1.isSelected());
		assertTrue("pin at 2x2 must be true", y.brailleList.get(2).radio2x2.isSelected());
		assertTrue("pin at 3x1 must be true", y.brailleList.get(2).radio3x1.isSelected());
		assertTrue("pin at 3x2 must be true", y.brailleList.get(2).radio3x2.isSelected());
		assertFalse("pin at 4x1 must be false", y.brailleList.get(2).radio4x1.isSelected());
		assertFalse("pin at 4x2 must be false", y.brailleList.get(2).radio4x2.isSelected());

	}
}
