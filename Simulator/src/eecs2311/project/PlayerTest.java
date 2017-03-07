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
		scan.reset();
	}
	
	@Test
	public void testPlayer() throws Exception{
		exceptionRule.expect(FileNotFoundException.class);
		Player p2 = new Player(new File("nofile.txt"));		
	}
	
	@Test
	public void testPlayAudio() throws Exception {
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
		//Scanner has to be reinitialized because of conflict with testProcessAction

		// tests when the method reads text using speak() method
		scan.findWithinHorizon("<readText1>", 0);
		p1.readText(scan);
		assertTrue("TTS working for letters, sentences, digits, and numbers", p1.testFlag);
		
		// tests when the method does not read text using speak() method
		scan.findWithinHorizon("<readText2>", 0);
		p1.readText(scan);
		assertFalse("TTS does method does not run", p1.testFlag);
		
	}

	@Test
	public void testActionPerformed() {

	}

	@Test
	public void testResetMap() {
		p1.resetMap();	//sets all buttons to false in the button Hashmap
		int i;
		for (i = 0; i < p1.buttonNumber-1; i++) {
			boolean button = p1.buttonMap.get(p1.simulator.getButton(i));
			assertFalse("Button should be false.", button);
		}

	}

	@Test
	public void testProcessAction() throws Exception{
		scan.findWithinHorizon("<case1>", 0);
		
	}

}
