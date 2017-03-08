package eecs2311.project;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Logger;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

/**
 * This class reads through and executes a scenario made to teach children
 * Braille.
 * <p>
 * The class has methods that process all the different tags that can be present
 * in the scenario text file.
 * <p>
 * The player class supports reading test out loud, through a Text-To-Speech
 * library called FreeTTS, playing .mp3 and .wav files, and branching paths in the scenario.
 * 
 * @author Team 4: Qassim Allaudin, Derek Li, Yassin Mohamed, Artem Solovey
 */
public class Player implements ActionListener {

	// Variables pertaining to the Simulator
	public Simulator simulator;
	public int buttonNumber;
	public int cellNumber;

	// Flag used for testing purposes. (speak() method)
	boolean testFlag;

	private static final String VOICENAME_kevin = "kevin";
	// size of the byte buffer used to read/write the audio stream
	private static final int BUFFER_SIZE = 4096;

	// Contains the references for all the buttons that are declared, and maps
	// all the buttons to flags.
	// Each flag represents whether the button was pressed or not.
	public HashMap<JButton, Boolean> buttonMap;
	boolean buttonFlag = true; // Flag that represents whether the user has made
								// a choice or not (in the case of a question)

	public Player(File inputFile) throws Exception {

		String nextLine;
		Scanner scanner = new Scanner(inputFile);

		this.cellNumber = scanner.nextInt();
		this.buttonNumber = scanner.nextInt();
		simulator = new Simulator(cellNumber, buttonNumber);
		// Construct the simulator with the number of cells and buttons read
		// from the file

		buttonMap = new HashMap<JButton, Boolean>();

		for (int i = 0; i < this.buttonNumber; i++) {
			simulator.getButton(i).addActionListener(this);
			buttonMap.put(simulator.getButton(i), false);
			// The flags in the HashMap indicate whether this specific button
			// was pressed or not. Thus they're all initialized to false.
		}

		while (scanner.hasNextLine()) {

			nextLine = scanner.nextLine();
			setPath(nextLine, scanner);

		}
		scanner.close();
	}

	private void setPath(String nextLine, Scanner scanner) throws Exception {
		switch (nextLine) {
		case "<t>":
			readText(scanner);
			break;

		case "<a>":
			playAudio(scanner);
			break;

		case "<c>":
			processAction(scanner);
			break;
		case "<j>":
			jump(scanner);
			break;

		case "<break>":
			scanner.findWithinHorizon("<end_c>", 0);
			break;
		case "<cs>":
		case "<cf>":
			scanner.findWithinHorizon("<end>", 0);
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
	public void speak(String text) {
		Voice voice;
		VoiceManager vm = VoiceManager.getInstance();
		voice = vm.getVoice(VOICENAME_kevin);
		voice.allocate();
		testFlag = voice.speak(text); // speak method in freetts returns
										// true if text is spoken; otherwise
										// false
	}

	/**
	 * 
	 * Plays audio that follows the scanner in its current position in the file.
	 * <p>
	 * Audio files must be in the same directory as the project OR absolute file
	 * paths.
	 * 
	 * @param scanner
	 *            The current scanner going through the scenario text file
	 * @throws Exception
	 *             if: The file was not found, or if the audio file was not
	 *             supported, or if the thread playing the audio is interrupted.
	 */
	public void playAudio(Scanner scanner) throws Exception {
		String line = scanner.nextLine();
		while (!line.contentEquals("<end>")) {

			File audioFile;
			audioFile = new File(line);

			AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
			AudioFormat format = audioStream.getFormat();
			DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
			SourceDataLine audioLine = (SourceDataLine) AudioSystem.getLine(info);
			audioLine.open(format);
			audioLine.start();
			System.out.println("Playback started.");
			byte[] bytesBuffer = new byte[BUFFER_SIZE];
			int bytesRead = -1;
			while ((bytesRead = audioStream.read(bytesBuffer)) != -1) {
				audioLine.write(bytesBuffer, 0, bytesRead);
			}
			testFlag = audioLine.isActive(); // For testing: checks if the audio
												// is being played
			audioLine.drain();
			audioLine.close();
			audioStream.close();

			System.out.println("Playback completed.");

			line = scanner.nextLine(); // go to next line to read
		}
	}

	/**
	 * 
	 * Decides which location in the file to jump to based on which button the user was prompted to press.
	 * 
	 * 
	 * @param scanner The current scanner going through the scenario text file
	 * 
	 * @throws Exception
	 */
	public void processAction(Scanner scanner) throws Exception {
		resetMap();
		// Resets all the flags in the buttonMap to 0, this is to avoid bugs
		// caused by old values
		// from previous calls to this method.
		buttonFlag = false;
		simulator.displayString(scanner.nextLine());
		int correctAnswer = Integer.parseInt(scanner.nextLine());

		if (!buttonMap.containsKey(simulator.getButton(correctAnswer - 1))) {

			throw new IllegalArgumentException("Invalid index for button in file\n");

		}
		while (!buttonFlag) {
			Thread.sleep(500);
		}

		if (buttonMap.get(simulator.getButton(correctAnswer - 1))) {
			scanner.findWithinHorizon("<cs>", 0);
		} else {
			scanner.findWithinHorizon("<cf>", 0);
		}
	}

	/**
	 * Calls on the speak() method to read text through the TTS engine.
	 * @param scanner
	 */
	public void readText(Scanner scanner) {
		String line = scanner.nextLine();
		testFlag = false; // For testing purposes
		while (!line.contentEquals("<end>")) {
			if (line.length() == 0) {
				line = scanner.nextLine();
			} else {
				speak(line);
				line = scanner.nextLine();
			}
		}
	}

	/**
	 * Looks for a specific string in the file, then positions the scanner right
	 * after that string. The string is the next line after whatever position
	 * the scanner was in when it was passed to the method. Typically, this
	 * method will only be called after the correct tag, which in this case is
	 * "<j>".
	 * 
	 * 
	 * @param scanner
	 *            the scanner going through the file currently being read.
	 *
	 */
	public void jump(Scanner scanner) {
		String line = scanner.nextLine();
		String k = scanner.findWithinHorizon(line + "\n", 0);
		// The first if statement is just in case the user wants to jump to a
		// tag that is at the very end of the file.
		// In that case the tag wouldn't have a \n after it, meaning the
		// findWithinHorizon method wouldn't spot it.
		// Just for that case, if it is not found the scanner looks over the
		// file one more time for the same
		// line without a '\n' character, if it's not found a second time then
		// an exception is thrown.
		if (k == null) {
			k = scanner.findWithinHorizon(line, 0);
		}
		if (k == null) {
			throw new IllegalArgumentException("Cannot jump to line, does not exist within the file");
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (buttonFlag == false) {
			buttonFlag = true;
			buttonMap.put((JButton) e.getSource(), true);
		}

	}

	/**
	 * resets the buttonMap hashmap so that all the flags are false.
	 */
	public void resetMap() {
		for (int i = 0; i < buttonNumber; i++) {
			buttonMap.put(simulator.getButton(i), false);
		}
	}

}
