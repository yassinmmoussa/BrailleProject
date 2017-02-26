package eecs2311.project;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Logger;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

public class Player {

	private Simulator simulator;
	private int buttonNumber;
	private int cellNumber;

	public Player(File inputFile) throws FileNotFoundException {

		String nextLine;
		Scanner scanner = new Scanner(inputFile);

		this.buttonNumber = scanner.nextInt();
		this.cellNumber = scanner.nextInt();
		simulator = new Simulator(cellNumber, buttonNumber);

		while (scanner.hasNextLine()) {

			nextLine = scanner.nextLine();
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
		scanner.close();
	}

	/**
	 * Outputs sound based on the input string parameter.
	 * 
	 * @param text
	 *            - The text to be converted to audio.
	 */
	private void tts(String text) {
		String VOICENAME_kevin = "kevin";
		Voice voice;
		VoiceManager vm = VoiceManager.getInstance();
		voice = vm.getVoice(VOICENAME_kevin);
		voice.allocate();
		voice.speak(text);
	}

	public void ttsFile(String fileName) throws Exception {
		try {
			File file = new File(fileName);
			Scanner s = new Scanner(file);

			while (s.hasNextLine()) {
				tts(s.nextLine());
			}
			s.close();
		} catch (IOException e) {
			throw new IOException("File can not be opened.");
		}

	}

	private static void playAudio(Scanner scanner) {

	}

	private static void processAction(Scanner scanner) {

	}

	private static void readText(Scanner scanner) {

	}

}
