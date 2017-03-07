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
	private static JButton btn;

	@Rule
	public ExpectedException exceptionRule = ExpectedException.none();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		p1 = new Player(new File("library.txt"));
		scan = new Scanner(new File("test.txt"));
		btn = new JButton("testBtn");
	}

	@Before
	public void setUp() throws Exception {
		// scan.reset(); method does not work for each test case
	}

	@Test
	public void testPlayer() throws Exception {
		exceptionRule.expect(FileNotFoundException.class);
		Player p2 = new Player(new File("nofile.txt"));
	}

	@Test
	public void testPlayAudio() throws Exception {
		scan = new Scanner(new File("test.txt"));
		// Scanner has to be reinitialized because of conflict with
		// testProcessAction

		// test for file not found exception
		scan.findWithinHorizon("<audio1>", 0);
		exceptionRule.expect(FileNotFoundException.class);
		p1.playAudio(scan);

		// test for non-supported audio such as .mp4
		scan.findWithinHorizon("<audio2>", 0);
		exceptionRule.expect(UnsupportedAudioFileException.class);
		p1.playAudio(scan);

	}

	@Test
	public void testReadText() throws FileNotFoundException {
		// Also tests the speak() method.
		scan = new Scanner(new File("test.txt"));
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

	@Test
	public void testActionPerformed() {

	}

	@Test
	public void testResetMap() {
		p1.resetMap(); // sets all buttons to false in the button Hashmap
		int i;
		for (i = 0; i < p1.buttonNumber - 1; i++) {
			boolean button = p1.buttonMap.get(p1.simulator.getButton(i));
			assertFalse("Button should be false.", button);
		}

	}

	@Test
	public void testProcessAction() throws Exception {
		scan = new Scanner(new File("test.txt"));
		// test if the correct button is pressed then continues to play scenario
		// which is indicated by next <case1> tag
		scan.findWithinHorizon("<case1>", 0);
		scan.nextLine();
		p1.processAction(scan);
		assertTrue(p1.buttonMap.get(p1.simulator.getButton(0)));

	}

	@Test
	public void testJump() throws Exception {
		//Testing for correct jump
		scan = new Scanner(new File("test.txt"));
		scan.findWithinHorizon("<jump_test>\n", 0);
		p1.jump(scan);
		// If the scanner didn't jump, the next line would be <incorrect> and
		// the test case would fail
		// Additionnally, if the jump() method throws an exception, which it
		// shouldn't, the test case
		// will also fail.
		assertEquals("<correct>", scan.nextLine());
		
		scan.reset();
		//Testing for exception being thrown in case jump tries to go to a line that doesn't exist
		scan.findWithinHorizon("<jump_test2>\n", 0);
		exceptionRule.expect(IllegalArgumentException.class);
		p1.jump(scan);
		

	}

}
