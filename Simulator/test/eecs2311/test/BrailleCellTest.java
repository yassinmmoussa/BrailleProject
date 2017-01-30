package eecs2311.test;

import static org.junit.Assert.*;

import javax.swing.JRadioButton;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import eecs2311.project.BrailleCell;


public class BrailleCellTest {

	static private BrailleCell b;
	static JRadioButton radio1x1 = new JRadioButton();
	static JRadioButton radio1x2 = new JRadioButton();
	static JRadioButton radio2x1 = new JRadioButton();
	static JRadioButton radio2x2 = new JRadioButton();
	static JRadioButton radio3x1 = new JRadioButton();
	static JRadioButton radio3x2 = new JRadioButton();
	static JRadioButton radio4x1 = new JRadioButton();
	static JRadioButton radio4x2 = new JRadioButton();

	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		b = new BrailleCell(radio1x1, radio1x2, radio2x1, radio2x2, radio3x1, radio3x2, radio4x1, radio4x2);
	}

	@Before
	public void setUp() throws Exception {
		
		
	}
	
	@Test
	public void testDisplayChar(){
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
	
	@Test
	public void testRaisePin(){
		b.raisePins("00011100");
		assertFalse("pin at 1x1 must be false", b.radio1x1.isSelected());
		assertFalse("pin at 1x2 must be false", b.radio1x2.isSelected());
		assertFalse("pin at 2x1 must be false", b.radio2x1.isSelected());
		assertTrue("pin at 2x2 must be true", b.radio2x2.isSelected());
		assertTrue("pin at 3x1 must be true", b.radio3x1.isSelected());
		assertTrue("pin at 3x2 must be true", b.radio3x2.isSelected());
		assertFalse("pin at 4x1 must be false", b.radio4x1.isSelected());
		assertFalse("pin at 4x2 must be false", b.radio4x2.isSelected());
		
		b.raisePins("11001100");
		assertTrue("pin at 1x1 must be true", b.radio1x1.isSelected());
		assertTrue("pin at 1x2 must be true", b.radio1x2.isSelected());
		assertFalse("pin at 2x1 must be false", b.radio2x1.isSelected());
		assertFalse("pin at 2x2 must be false", b.radio2x2.isSelected());
		assertTrue("pin at 3x1 must be true", b.radio3x1.isSelected());
		assertTrue("pin at 3x2 must be true", b.radio3x2.isSelected());
		assertFalse("pin at 4x1 must be false", b.radio4x1.isSelected());
		assertFalse("pin at 4x2 must be false", b.radio4x2.isSelected());
		
		b.raisePins("00000000");
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
