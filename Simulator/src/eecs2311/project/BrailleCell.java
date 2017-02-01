package eecs2311.project;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/*
 * The simulator is mainly a platform for the next step in the project, the actual software, to use.
 * The more flexible the simulator, the better it is. But I'd thought at first that flexible meant that
 * it was just able to show any letter, and that you had to shield that data away from the user. The
 * professor explained however that the main purpose is to create a platform that would give absolute
 * control of the pins to the software. And so instead of having 26 specific methods, we'd have a method
 * to which you (for example) pass a string of 1s and 0s, and it manually raises the pins. This is to give
 * absolute access to the software in terms of what pins to raise.
 * 
 * Use a HashMap to map every character of the alphabet to an 8 character string, implement a method that would
 * take an 8 character string of 1s and 0s, and raise the corresponding pins in the cell object. That way,
 * if the client wants it could manually call that method and pass it  string of 1s and 0s to manually raise
 * the pins it wants to raise. Additionally, implementing the simulator this way would allow for very fast and
 * easy addition of new characters or letters to the already available ones.
 * 
 * 
 * verify input
 * extended letters - 8 pin
 * Throw exceptions
 * Should the client have access to the Hash Map?
 * 8 pin different?
 */

public class BrailleCell {

	JRadioButton radio1x1;
	JRadioButton radio1x2;
	JRadioButton radio2x1;
	JRadioButton radio2x2;
	JRadioButton radio3x1;
	JRadioButton radio3x2;
	JRadioButton radio4x1;
	JRadioButton radio4x2;
	public JPanel panel;
	private static HashMap<Character, String> alphabet = new HashMap<Character, String>();

	private void initializeAlphabet() {
		alphabet.put('a', "10000000");
		alphabet.put('b', "10100000");
		alphabet.put('c', "11000000");
		alphabet.put('d', "11010000");
		alphabet.put('e', "10010000");
		alphabet.put('f', "11100000");
		alphabet.put('g', "11110000");
		alphabet.put('h', "10110000");
		alphabet.put('i', "01100000");
		alphabet.put('j', "01110000");
		alphabet.put('k', "10001000");
		alphabet.put('l', "10101000");
		alphabet.put('m', "11001000");
		alphabet.put('n', "11011000");
		alphabet.put('o', "10011000");
		alphabet.put('p', "11101000");
		alphabet.put('q', "11111000");
		alphabet.put('r', "10111000");
		alphabet.put('s', "01101000");
		alphabet.put('t', "01111000");
		alphabet.put('u', "10001100");
		alphabet.put('v', "10101100");
		alphabet.put('w', "01110100");
		alphabet.put('x', "11001100");
		alphabet.put('y', "11011100");
		alphabet.put('z', "10011100");
		alphabet.put(' ', "00000000");

	}
	
	public BrailleCell(JFrame frame)
	{
		this.initializeAlphabet();
		
		this.radio1x1 = new JRadioButton();
		radio1x1.setEnabled(true);
		
		this.radio1x2 = new JRadioButton();
		radio1x2.setEnabled(true);
		
		this.radio2x1 = new JRadioButton();
		radio2x1.setEnabled(true);
		
		this.radio2x2 = new JRadioButton();
		radio2x2.setEnabled(true);
		
		this.radio3x1 = new JRadioButton();
		radio3x1.setEnabled(true);
		
		this.radio3x2 = new JRadioButton();
		radio3x2.setEnabled(true);
		
		this.radio4x1 = new JRadioButton();
		radio4x1.setEnabled(true);
		
		this.radio4x2 = new JRadioButton();
		radio4x2.setEnabled(true);
		
		GridLayout grid = new GridLayout();
		panel = new JPanel(grid);
		grid.setRows(4);
		grid.setColumns(2);
		panel.setSize(100, 50);
		panel.add(radio1x1);
		panel.add(radio1x2);
		panel.add(radio2x1);
		panel.add(radio2x2);
		panel.add(radio3x1);
		panel.add(radio3x2);
		panel.add(radio4x1);
		panel.add(radio4x2);
		panel.setBorder(BorderFactory.createLineBorder(Color.black));
		panel.setVisible(true);
		frame.add(panel);
	}

	public BrailleCell(JRadioButton radio1x1, JRadioButton radio1x2, JRadioButton radio2x1, JRadioButton radio2x2,
			JRadioButton radio3x1, JRadioButton radio3x2, JRadioButton radio4x1, JRadioButton radio4x2) {

		this.initializeAlphabet();
		this.radio1x1 = radio1x1;
		this.radio1x2 = radio1x2;
		this.radio2x1 = radio2x1;
		this.radio2x2 = radio2x2;
		this.radio3x1 = radio3x1;
		this.radio3x2 = radio3x2;
		this.radio4x1 = radio4x1;
		this.radio4x2 = radio4x2;

	}

	/**
	 * The letter to be displayed.
	 * @param a - the letter to be displayed
	 */
	public void displayLetter(char a) {

		a = Character.toLowerCase(a);
		if (!alphabet.containsKey(a)) {
			throw new IllegalArgumentException("Non standard character");
		}
		this.raisePins(alphabet.get(a));
		// switch (a) {
		// case 'a':
		// // Fully implemented
		// this.displayA();
		// break;
		// case 'b':
		// // Implementation missing
		// break;
		// // Rest of the case statements go after here
		// default:
		// break;
		// }
	}

	/**
	 * TODO
	 * 
	 * @param pins - the binary string that represents the status of each pin on the braille cell
	 */
	public void raisePins(String pins) {
		if (pins.length() != 8) {
			throw new IllegalArgumentException("Illegal string passed, length > or < 8.");
		}
		for (int i = 0; i <= 7; i++) {
			if (pins.charAt(i) != '0' && pins.charAt(i) != '1') {
				throw new IllegalArgumentException(
						"Invalid string passed, non-binary character detected at index:" + i + ".");
			}
		}

		this.clear();

		for (int i = 0; i <= 7; i++) {
			if (pins.charAt(i) == '1') {
				raiseOnePin(i + 1);
			}
		}
	}

	private void raiseOnePin(int i) {
		if (i < 1 || i > 8) {
			throw new IllegalArgumentException("Invalid index");
		}
		switch (i) {
		case 1:
			radio1x1.setSelected(true);
			break;
		case 2:
			radio1x2.setSelected(true);
			break;
		case 3:
			radio2x1.setSelected(true);
			break;
		case 4:
			radio2x2.setSelected(true);
			break;
		case 5:
			radio3x1.setSelected(true);
			break;
		case 6:
			radio3x2.setSelected(true);
			break;
		case 7:
			radio4x1.setSelected(true);
			break;
		case 8:
			radio4x2.setSelected(true);
			break;
		default:
			break;

		}
	}

	/**
	 * Clears the braille cell by setting the status of all the braille cells to false.
	 */
	public void clear() {
		// Fully implemented
		radio1x1.setSelected(false);
		radio1x2.setSelected(false);
		radio2x1.setSelected(false);
		radio2x2.setSelected(false);
		radio3x1.setSelected(false);
		radio3x2.setSelected(false);
		radio4x1.setSelected(false);
		radio4x2.setSelected(false);
	}

	// private void displayA() {
	// // Fully implemented
	// this.clear();
	// radio1x1.setSelected(true);
	// }
	//
}
