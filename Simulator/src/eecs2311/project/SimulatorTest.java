package eecs2311.project;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class SimulatorTest {
	
	static private Simulator x;
	static private Simulator y;

	
	//The ExpectedException rule allows you to verify that your code throws a specific exception.
	//We initialize it to expect no exceptions to be thrown yet.
	@Rule 
	public ExpectedException exceptionRule = ExpectedException.none();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		x = new Simulator(1,1);
		y = new Simulator(3,1);

	}

	@Test
	public void testGetButton(){
		int ceilBound = x.buttonList.size();
		int floorBound = -1;
		
		//checks that at index 0 of the button list array it is NOT null. There should be a button there. Otherwise throw an Assertion Error.
		assertNotNull(x.getButton(0));
		
		//Verifies that an IllegalArgumentException is thrown when trying to get a cell that is out of bounds. Else, the test will throw a MatcherAssert Error
		//must be called before the method we want to test
		exceptionRule.expect(IllegalArgumentException.class);
		x.getButton(ceilBound);
		exceptionRule.expect(IllegalArgumentException.class);
		x.getButton(floorBound);
		
	}
	
	@Test
	public void testGetCell(){
		int ceilBound = x.brailleList.size();
		int floorBound = -1;
		
		//checks that at index 0 of the braille list array it is NOT null. There should be a braille cell there. Otherwise throw an Assertion Error.
		assertNotNull(x.getCell(0));
		
		//Verifies that an IllegalArgumentException is thrown when trying to get a cell that is out of bounds. Else, the test will throw a MatcherAssert Error
		//must be called before the method we want to test
		exceptionRule.expect(IllegalArgumentException.class);
		x.getCell(ceilBound);
		exceptionRule.expect(IllegalArgumentException.class);
		x.getCell(floorBound);
		
	}

	@Test
	public void testClearAllCells() {
		//checks whether all the 8 pins for each braille cell are selected/raised or not. If so, throw an Assertion Error.
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
	@Test
	public void testDisplayString() {
		y.displayString("hey");
		
		//check if braille(0) has the right pins selected for letter 'h'
		assertTrue("pin at 1x1 must be true", y.brailleList.get(0).radio1x1.isSelected());
		assertFalse("pin at 1x2 must be false", y.brailleList.get(0).radio1x2.isSelected());
		assertTrue("pin at 2x1 must be true", y.brailleList.get(0).radio2x1.isSelected());
		assertTrue("pin at 2x2 must be true", y.brailleList.get(0).radio2x2.isSelected());
		assertFalse("pin at 3x1 must be false", y.brailleList.get(0).radio3x1.isSelected());
		assertFalse("pin at 3x2 must be false", y.brailleList.get(0).radio3x2.isSelected());
		assertFalse("pin at 4x1 must be false", y.brailleList.get(0).radio4x1.isSelected());
		assertFalse("pin at 4x2 must be false", y.brailleList.get(0).radio4x2.isSelected());
		
		//check if braille(1) has the right pins selected for letter 'e'
		assertTrue("pin at 1x1 must be true", y.brailleList.get(1).radio1x1.isSelected());
		assertFalse("pin at 1x2 must be false", y.brailleList.get(1).radio1x2.isSelected());
		assertFalse("pin at 2x1 must be false", y.brailleList.get(1).radio2x1.isSelected());
		assertTrue("pin at 2x2 must be true", y.brailleList.get(1).radio2x2.isSelected());
		assertFalse("pin at 3x1 must be false", y.brailleList.get(1).radio3x1.isSelected());
		assertFalse("pin at 3x2 must be false", y.brailleList.get(1).radio3x2.isSelected());
		assertFalse("pin at 4x1 must be false", y.brailleList.get(1).radio4x1.isSelected());
		assertFalse("pin at 4x2 must be false", y.brailleList.get(1).radio4x2.isSelected());
		
		//check if braille(2) has the right pins selected for letter 'y'
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
