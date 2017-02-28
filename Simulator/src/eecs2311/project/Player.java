package eecs2311.project;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Logger;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

/*
 * All buttons will be added to a Map<JButton, Boolean>, all booleans will be initialized to false.
 * The boolean indicates whether that button was pressed or not. When a choice is required,
 * another tag (after the choice tag) indicates which button should be pressed for the scenario to continue.
 * The actionPerformed method checks which button was pressed and sets its corresponding flag in the map
 * to true. The processAction method then compares which flag was set to true with which flag was SUPPOSED to
 * be set to true. If the correct button was pressed it continues, if the incorrect one is pressed it loops back.
 */

public class Player {

	private Simulator simulator;
	private int buttonNumber;
	private int cellNumber;
	private static final String VOICENAME_kevin = "kevin";

	public Player(File inputFile) throws FileNotFoundException {

		String nextLine;
		Scanner scanner = new Scanner(inputFile);

		this.cellNumber = scanner.nextInt();
		this.buttonNumber = scanner.nextInt();
		simulator = new Simulator(cellNumber, buttonNumber);

		while (scanner.hasNextLine()) {

			nextLine = scanner.nextLine();
			setPath(nextLine, scanner);

		}
		scanner.close();
	}

	private void setPath(String nextLine, Scanner scanner) {
		switch (nextLine) {
		case "<t>":
			Player.readText(scanner);
			break;

		case "<a>":
			Player.playAudio(scanner);
			break;

		case "<c>":
			Player.processAction(scanner);
			break;
		default:
			break;

		}
	}

	/**
	 * Outputs sound based on the input string parameter.
	 * 
	 * @param text
	 *            - The text to be converted to audio.
	 */
	public static void speak(String text) {
		Voice voice;
		VoiceManager vm = VoiceManager.getInstance();
		voice = vm.getVoice(VOICENAME_kevin);
		voice.allocate();
		voice.speak(text);
	}

	private static void playAudio(Scanner scanner) {

	}

	private static void processAction(Scanner scanner) {

	}

	private static void readText(Scanner scanner) {
		String line = scanner.nextLine();
		while (line != "<end>") {

			speak(line);
			line = scanner.nextLine();
		}
	}

}
