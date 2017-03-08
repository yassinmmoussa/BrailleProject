package eecs2311.project;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;


public class PlayerTest {

	private static Player p1;
	private static Scanner scan;

	/*
	 * Use this rule to test for exceptions
	 */
	@Rule
	public ExpectedException exceptionRule = ExpectedException.none();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		p1 = new Player(new File("library.txt"));

	}

	@Before
	public void setUp() throws Exception {
		scan = new Scanner(new File("test.txt"));
		// scan.reset(); method does not work for each test case
	}

	
	 @Test
	 public void testPlayer() throws Exception {
	 exceptionRule.expect(FileNotFoundException.class);
	 Player p2 = new Player(new File("nofile.txt"));
	 }
	
	 /*
	 * Test two different exceptions as well as if the play audio method is
	 * working
	 */
	 @Test
	 public void testPlayAudio() throws Exception {
	 //scan = new Scanner(new File("test.txt"));
	 // Scanner has to be reinitialized because of conflict with
	 // testProcessAction
	
	 // test for when audio plays
	 // Note: You must have the file in the project and it must be either
	 // .mp3 or .wav
	 p1.testFlag = false;
	 scan.findWithinHorizon("<audio3>", 0);
	 scan.nextLine();
	 p1.playAudio(scan);
	 assertTrue(p1.testFlag);
	
	 // test for file not found exception
	 // Note: the file must not exist in your project
	 scan.findWithinHorizon("<audio1>", 0);
	 scan.nextLine();
	 exceptionRule.expect(FileNotFoundException.class);
	 p1.playAudio(scan);
	
	 // test for non-supported audio such as .mp4 or .txt
	 // Note: you must have the unsupported file in your project folder
	 scan.findWithinHorizon("<audio2>", 0);
	 scan.nextLine();
	 exceptionRule.expect(UnsupportedAudioFileException.class);
	 p1.playAudio(scan);
	
	 }
	
	 /*
	 * Tests the available format in the scenario txt file that the tts is
	 able to read
	 * Also tests the speak() method.
	 */
	 @Test
	 public void testReadText() throws FileNotFoundException {
	
	 //scan = new Scanner(new File("test.txt"));
	 // Scanner has to be reinitialized because of conflict with
	 // testProcessAction
	
	 // tests when the method reads text using speak() method
	 scan.findWithinHorizon("<readText1>", 0);
	 p1.readText(scan);
	 assertTrue("TTS method should run for letters, sentences, digits, and numbers", p1.testFlag);
	
	 // tests when the method does not read text using speak() method
	 scan.findWithinHorizon("<readText2>", 0);
	 p1.readText(scan);
	 assertFalse("TTS method should not run", p1.testFlag);
	
	 }
	
	 /*
	 * This method does not need to be tested as it is tested as part of
	 * processAction method
	 */
	 @Ignore
	 public void testActionPerformed() {
	
	 }
	
	 /*
	 * Tests if the button hashmap is reset back to false
	 */
	 @Test
	 public void testResetMap() {
	 p1.resetMap(); // sets all buttons to false in the button Hashmap
	 int i;
	 for (i = 0; i < p1.buttonNumber - 1; i++) {
	 boolean button = p1.buttonMap.get(p1.simulator.getButton(i));
	 assertFalse("Button should be false.", button);
	 }
	
	 }
	
	 /*
	 * Tests if the direction of branching is correct depending on what button
	 * the user presses
	 */
	
	@Test
	public void testProcessAction() throws Exception {
		// <case1> tests if the correct button is pressed.
		// If correct button is pressed it will take us inside the <cs> tag
		// shown in the scenario txt file
		// Else it will go to cf tag
		// Also ActionPerformed method is tested on the button when it is
		// clicked
		scan.findWithinHorizon("<case1>\n", 0);
		p1.readText(scan); // based on scenario txt file
		p1.processAction(scan);
		scan.nextLine(); // to skip the '\n' that was left in the scanner from
							// the last call
		// button 1 is the correct answer in this scenario txt file
		boolean correctBtn = p1.buttonMap.get(p1.simulator.getButton(0));
		// if correct button was pressed the processAction method sends the
		// Scanner to the <cs> tag
		if (correctBtn) {
			assertTrue(correctBtn); // correct button should be true - tests if
									// ActionPerformed works
			assertEquals("Correct", scan.nextLine()); // in the scenario txt
														// file the next line
														// should be "Correct"
			p1.readText(scan);
		}
		// else it takes Scanner to the <cf> tag
		else {
			assertFalse(correctBtn); // correct button should be false
			assertEquals("Fail", scan.nextLine()); // after the <cf> the next
													// line should be "Fail"
			p1.readText(scan);
		}

		// <case2> tests for indexOutOfBound exception to be thrown
		scan.findWithinHorizon("<case2>\n", 0);
		// in the scenario txt file, the correct button is set to '100' which is
		// out of bounds and should then throw an exception
		exceptionRule.expect(IllegalArgumentException.class);
		p1.processAction(scan);

	}

	@Test
	public void testJump() throws Exception {
		// Testing for correct jump
		// scan = new Scanner(new File("test.txt"));
		scan.findWithinHorizon("<jump_test>\n", 0);
		p1.jump(scan);
		// If the scanner didn't jump, the next line would be <incorrect> and
		// the test case would fail
		// Additionnally, if the jump() method throws an exception, which it
		// shouldn't, the test case
		// will also fail.
		assertEquals("<correct>", scan.nextLine());

		scan.reset();
		// Testing for exception being thrown in case jump tries to go to a line
		// that doesn't exist
		scan.findWithinHorizon("<jump_test2>\n", 0);
		exceptionRule.expect(IllegalArgumentException.class);
		p1.jump(scan);

	}

}
