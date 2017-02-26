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
	private static final String VOICENAME_kevin = "kevin";	

	public Player(File inputFile) throws FileNotFoundException {

		String nextLine;
		Scanner scanner = new Scanner(inputFile);

		//this.buttonNumber = scanner.nextInt();
		//this.cellNumber = scanner.nextInt();
		//simulator = new Simulator(cellNumber, buttonNumber);

		while (scanner.hasNextLine()) {

			nextLine = scanner.nextLine();
			switch (nextLine) {
			case "<t>":
				Player.readText(scanner);
				
			case "<a>":
				Player.playAudio(scanner);
				
			case "<c>":
				Player.processAction(scanner);
				
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
	public static void speak(String text) {
		Voice voice;
		VoiceManager vm = VoiceManager.getInstance();
		voice = vm.getVoice(VOICENAME_kevin);
		voice.allocate();
		voice.speak(text);
	}

	// Do we need this block of code?
	/*public void ttsFile(String fileName) throws Exception {
		try {
			File file = new File(fileName);
			Scanner s = new Scanner(file);

			while (s.hasNextLine()) {
				speak(s.nextLine());
			}
			s.close();
		} catch (IOException e) {
			throw new IOException("File can not be opened.");
		}

	}*/

	private static void playAudio(Scanner scanner) {
		
	}

	private static void processAction(Scanner scanner) {

	}

	private static void readText(Scanner scanner) {
		String line = scanner.nextLine();
		while(scanner.hasNextLine()){
			if(line.contentEquals("<end>")){
				break;
			}
			speak(line);
			line = scanner.nextLine();
		}
	}

}
