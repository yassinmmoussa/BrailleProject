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

import javax.swing.JButton;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

/*
 * All buttons will be added to a Map<JButton, Boolean>, all booleans will be initialized to false.
 * And all brailleCels will be added to a DT.
 * Use displayString to set the braille Cells.
 * set a flag in actionperformed and make the program sleep while the flag is false.
 * 
 * The boolean indicates whether that button was pressed or not. When a choice is required,
 * another tag (after the choice tag) indicates which button should be pressed for the scenario to continue.
 * The actionPerformed method checks which button was pressed and sets its corresponding flag in the map
 * to true. The processAction method then compares which flag was set to true with which flag was SUPPOSED to
 * be set to true. If the correct button was pressed it continues, if the incorrect one is pressed it loops back.
 * 
 * Should the audio file paths be absoulute. Yes, or you can make them relative to the input file.
 * 
 * 
 */

public class Player implements ActionListener {

	private Simulator simulator;
	private int buttonNumber;
	private int cellNumber;
	private static final String VOICENAME_kevin = "kevin";
	private HashMap<JButton, Boolean> buttonMap;
	String filePath;
	boolean buttonFlag = true; // I explain why it's initiialized to true in the
								// processAction method.

	public Player(File inputFile) throws FileNotFoundException, InterruptedException {

		String nextLine;
		Scanner scanner = new Scanner(inputFile);
		filePath = inputFile.getAbsolutePath();

		this.cellNumber = scanner.nextInt();
		this.buttonNumber = scanner.nextInt();
		simulator = new Simulator(cellNumber, buttonNumber);

		buttonMap = new HashMap<JButton, Boolean>();

		for (int i = 0; i < this.buttonNumber; i++) {
			simulator.getButton(i).addActionListener(this);
			buttonMap.put(simulator.getButton(i), false);
		}

		while (scanner.hasNextLine()) {

			nextLine = scanner.nextLine();
			setPath(nextLine, scanner);

		}
		scanner.close();
	}

	private void setPath(String nextLine, Scanner scanner) throws InterruptedException {
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
		voice.speak(text);
	}

	private void playAudio(Scanner scanner) {

	}

	private void processAction(Scanner scanner) throws InterruptedException {

		// The reason buttonFlag is originally initialized to true is this:
		// Assume I had, as intuition decrees, set it initially to false, what
		// if the user presses a button without being prompted? In that case the
		// actionPerformed method would run, and the flag would be set to true.
		// In that case, when an actual choice is required the flag would
		// already be true, meaning the method won't wait for an input and will
		// just consider the last button pressed as the answer. If I
		// initialize the flag to true, I can then tell the actionPerformed method not to do
		// anything unless the flag is false, and I then set the flag to = false ONLY in the processAction
		// method. After that, the actionPerformed method (provided the flag is false) sets it to true
		// if a button is pressed and the processAction method can continue normally.
		buttonFlag = false;
		simulator.displayString(scanner.nextLine());
		int correctAnswer = Integer.parseInt(scanner.nextLine());

		if (!buttonMap.containsKey(simulator.getButton(correctAnswer - 1))) {

			throw new IllegalArgumentException("Invalid index for button in file\n");

		}
		while (!buttonFlag) {
			Thread.sleep(500);
		}
		
		if(buttonMap.get(simulator.getButton(correctAnswer-1)))
		{
			scanner.findWithinHorizon("<cs>", 0);						
		}
		else
		{
			scanner.findWithinHorizon("<cf>", 0);
		}

		resetMap(); // Resets all boolean values associated with buttons to
					// false.
	}

	private void readText(Scanner scanner) {
		String line = scanner.nextLine();
		while (!line.contentEquals("<end>")) {
			
			
			speak(line);
			line = scanner.nextLine();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (buttonFlag == false) {
			buttonFlag = true;
			buttonMap.put((JButton) e.getSource(), true);
		}

	}

	public void resetMap() {
		for (int i = 0; i < buttonNumber; i++) {
			buttonMap.put(simulator.getButton(i), false);
		}
	}

}
