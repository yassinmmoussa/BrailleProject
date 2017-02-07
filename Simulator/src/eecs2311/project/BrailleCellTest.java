package eecs2311.project;

import static org.junit.Assert.*;

import javax.swing.JRadioButton;

import org.junit.Before;
import org.junit.Test;

import eecs2311.project.BrailleCell;


public class BrailleCellTest {
	
	//initialize the braille cell radio buttons
	static private BrailleCell b;
	JRadioButton radio1x1 = new JRadioButton();
	JRadioButton radio1x2 = new JRadioButton();
	JRadioButton radio2x1 = new JRadioButton();
	JRadioButton radio2x2 = new JRadioButton();
	JRadioButton radio3x1 = new JRadioButton();
	JRadioButton radio3x2 = new JRadioButton();
	JRadioButton radio4x1 = new JRadioButton();
	JRadioButton radio4x2 = new JRadioButton();
	
	@Before
	public void setUp() throws Exception {
		//create the braille cell class that we will be testing
		b = new BrailleCell(radio1x1, radio1x2, radio2x1, radio2x2, radio3x1, radio3x2, radio4x1, radio4x2);
	}
	
	@Test
	public void testDisplayChar(){
		//check if braille cell for letter 'a' has the right pins selected.
		b.displayLetter('a');

		assertTrue("pin at 1x1 must be true", b.radio1x1.isSelected());
		assertFalse("pin at 1x2 must be false", b.radio1x2.isSelected());
		assertFalse("pin at 2x1 must be false", b.radio2x1.isSelected());
		assertFalse("pin at 2x2 must be false", b.radio2x2.isSelected());
		assertFalse("pin at 3x1 must be false", b.radio3x1.isSelected());
		assertFalse("pin at 3x2 must be false", b.radio3x2.isSelected());
		assertFalse("pin at 4x1 must be false", b.radio4x1.isSelected());
		assertFalse("pin at 4x2 must be false", b.radio4x2.isSelected());
		
		//check if braille cell for letter 'z' has the right pins selected.
		b.displayLetter('z');
		assertTrue("pin at 1x1 must be true", b.radio1x1.isSelected());
		assertFalse("pin at 1x2 must be false", b.radio1x2.isSelected());
		assertFalse("pin at 2x1 must be false", b.radio2x1.isSelected());
		assertTrue("pin at 2x2 must be true", b.radio2x2.isSelected());
		assertTrue("pin at 3x1 must be true", b.radio3x1.isSelected());
		assertTrue("pin at 3x2 must be true", b.radio3x2.isSelected());
		assertFalse("pin at 4x1 must be false", b.radio4x1.isSelected());
		assertFalse("pin at 4x2 must be false", b.radio4x2.isSelected());
		
		//check if braille cell for letter ' ' has the right pins selected.
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
	
	@Test
	public void testSetPin(){
		
		//check if the correct pins get selected
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
	
//	@Test
//	public void testRaiseOnePin(){
//		b.raiseOnePin(4);
//		assertTrue(b.radio2x1.isSelected());
//	}
	
	@Test
	public void testClear(){
		
		//check if all the pins are not selected
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
