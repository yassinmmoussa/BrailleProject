package eecs2311.project;

import static org.junit.Assert.*;

import javax.swing.JRadioButton;

import org.junit.Before;
import org.junit.Test;

import eecs2311.project.BrailleCell;

/**
 * 
 * This is a JUnit test class for the BrailleCell class.
 * 
 * @author Team 4: Yassin Mohamed, Qassim Allauddin, Derek Li, Artem Solovey.
 *
 */
public class BrailleCellTest {


	static private BrailleCell b;
	JRadioButton radio1x1 = new JRadioButton();
	JRadioButton radio1x2 = new JRadioButton();
	JRadioButton radio2x1 = new JRadioButton();
	JRadioButton radio2x2 = new JRadioButton();
	JRadioButton radio3x1 = new JRadioButton();
	JRadioButton radio3x2 = new JRadioButton();
	JRadioButton radio4x1 = new JRadioButton();
	JRadioButton radio4x2 = new JRadioButton();

	/**
	 * This method runs before every test, initializing the object on which the
	 * tests will be conducted.
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		
		b = new BrailleCell(radio1x1, radio1x2, radio2x1, radio2x2, radio3x1, radio3x2, radio4x1, radio4x2);
	}

	/**
	 * 
	 * Tests the displayChar() method, passing three different characters and
	 * testing that they raise the correct pins.
	 * 
	 */
	@Test
	public void testDisplayChar() {
		
		b.displayLetter('a');

		assertTrue("pin at 1x1 must be true", b.radio1x1.isSelected());
		assertFalse("pin at 1x2 must be false", b.radio1x2.isSelected());
		assertFalse("pin at 2x1 must be false", b.radio2x1.isSelected());
		assertFalse("pin at 2x2 must be false", b.radio2x2.isSelected());
		assertFalse("pin at 3x1 must be false", b.radio3x1.isSelected());
		assertFalse("pin at 3x2 must be false", b.radio3x2.isSelected());
		assertFalse("pin at 4x1 must be false", b.radio4x1.isSelected());
		assertFalse("pin at 4x2 must be false", b.radio4x2.isSelected());

	
		b.displayLetter('z');
		assertTrue("pin at 1x1 must be true", b.radio1x1.isSelected());
		assertFalse("pin at 1x2 must be false", b.radio1x2.isSelected());
		assertFalse("pin at 2x1 must be false", b.radio2x1.isSelected());
		assertTrue("pin at 2x2 must be true", b.radio2x2.isSelected());
		assertTrue("pin at 3x1 must be true", b.radio3x1.isSelected());
		assertTrue("pin at 3x2 must be true", b.radio3x2.isSelected());
		assertFalse("pin at 4x1 must be false", b.radio4x1.isSelected());
		assertFalse("pin at 4x2 must be false", b.radio4x2.isSelected());


		b.displayLetter(' ');
		assertFalse("pin at 1x1 must be false", b.radio1x1.isSelected());
		assertFalse("pin at 1x2 must be false", b.radio1x2.isSelected());
		assertFalse("pin at 2x1 must be false", b.radio2x1.isSelected());
		assertFalse("pin at 2x2 must be false", b.radio2x2.isSelected());
		assertFalse("pin at 3x1 must be false", b.radio3x1.isSelected());
		assertFalse("pin at 3x2 must be false", b.radio3x2.isSelected());
		assertFalse("pin at 4x1 must be false", b.radio4x1.isSelected());
		assertFalse("pin at 4x2 must be false", b.radio4x2.isSelected());
	}

	/**
	 * Tests the setPins() method, passing three different strings and checking
	 * that the correct pins are raised.
	 * 
	 */
	@Test
	public void testSetPin() {

		
		b.setPins("00011100");
		assertFalse("pin at 1x1 must be false", b.radio1x1.isSelected());
		assertFalse("pin at 1x2 must be false", b.radio1x2.isSelected());
		assertFalse("pin at 2x1 must be false", b.radio2x1.isSelected());
		assertTrue("pin at 2x2 must be true", b.radio2x2.isSelected());
		assertTrue("pin at 3x1 must be true", b.radio3x1.isSelected());
		assertTrue("pin at 3x2 must be true", b.radio3x2.isSelected());
		assertFalse("pin at 4x1 must be false", b.radio4x1.isSelected());
		assertFalse("pin at 4x2 must be false", b.radio4x2.isSelected());

		b.setPins("11001100");
		assertTrue("pin at 1x1 must be true", b.radio1x1.isSelected());
		assertTrue("pin at 1x2 must be true", b.radio1x2.isSelected());
		assertFalse("pin at 2x1 must be false", b.radio2x1.isSelected());
		assertFalse("pin at 2x2 must be false", b.radio2x2.isSelected());
		assertTrue("pin at 3x1 must be true", b.radio3x1.isSelected());
		assertTrue("pin at 3x2 must be true", b.radio3x2.isSelected());
		assertFalse("pin at 4x1 must be false", b.radio4x1.isSelected());
		assertFalse("pin at 4x2 must be false", b.radio4x2.isSelected());

		b.setPins("00000000");
		assertFalse("pin at 1x1 must be false", b.radio1x1.isSelected());
		assertFalse("pin at 1x2 must be false", b.radio1x2.isSelected());
		assertFalse("pin at 2x1 must be false", b.radio2x1.isSelected());
		assertFalse("pin at 2x2 must be false", b.radio2x2.isSelected());
		assertFalse("pin at 3x1 must be false", b.radio3x1.isSelected());
		assertFalse("pin at 3x2 must be false", b.radio3x2.isSelected());
		assertFalse("pin at 4x1 must be false", b.radio4x1.isSelected());
		assertFalse("pin at 4x2 must be false", b.radio4x2.isSelected());
	}

	
	/**
	 * Tests the clear() method, calling it and verifying that none of the radio buttons are selected.
	 */
	@Test
	public void testClear() {

		
		b.clear();
		assertFalse("pin at 1x1 must be false", b.radio1x1.isSelected());
		assertFalse("pin at 1x2 must be false", b.radio1x2.isSelected());
		assertFalse("pin at 2x1 must be false", b.radio2x1.isSelected());
		assertFalse("pin at 2x2 must be false", b.radio2x2.isSelected());
		assertFalse("pin at 3x1 must be false", b.radio3x1.isSelected());
		assertFalse("pin at 3x2 must be false", b.radio3x2.isSelected());
		assertFalse("pin at 4x1 must be false", b.radio4x1.isSelected());
		assertFalse("pin at 4x2 must be false", b.radio4x2.isSelected());
	}
}
