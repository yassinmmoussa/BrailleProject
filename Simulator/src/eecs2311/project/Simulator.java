package eecs2311.project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class Simulator implements ActionListener {

	int brailleCellNumber;
	int jButtonNumber;
	private JFrame frame;
	private GridLayout cellGrid = new GridLayout(4, 2);
	LinkedList<JPanel> panelList = new LinkedList<JPanel>();
	ArrayList<JRadioButton> pins = new ArrayList<JRadioButton>(8);
	LinkedList<ArrayList<JRadioButton>> pinList = new LinkedList<ArrayList<JRadioButton>>();
	LinkedList<JButton> buttonList = new LinkedList<JButton>();
	LinkedList<BrailleCell> brailleList = new LinkedList<BrailleCell>();
	JPanel southPanel = new JPanel();
	JPanel centerPanel = new JPanel();

	public Simulator(int brailleCellNumber, int jButtonNumber) {

		this.brailleCellNumber = brailleCellNumber;
		this.jButtonNumber = jButtonNumber;
		frame = new JFrame();
		frame.setTitle("Simulator");
		frame.setBounds(100, 100, 627, 459);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout());

		for (int i = 0; i < brailleCellNumber; i++) {

			JPanel panel = new JPanel(cellGrid);

			for (int j = 0; j < 8; j++) {
				JRadioButton radioButton = new JRadioButton();
				radioButton.setEnabled(false);
				radioButton.setSize(25, 25);
				pins.add(radioButton);
				panel.add(radioButton);
				panel.repaint();
			}

			panel.setVisible(true);
			pinList.add(pins);

			BrailleCell cell = new BrailleCell(pins.get(0), pins.get(1), pins.get(2), pins.get(3), pins.get(4),
					pins.get(5), pins.get(6), pins.get(7));
			panelList.add(panel);
			brailleList.add(cell);
			panel.setSize(150, 200);
			panel.setBorder(BorderFactory.createLineBorder(Color.black));
			centerPanel.add(panel);

			pins.clear();
			if (i == (brailleCellNumber - 1))
				frame.getContentPane().add(centerPanel, BorderLayout.CENTER);

		}

		for (int i = 0; i < jButtonNumber; i++) {
			JButton button = new JButton("" + i);
			button.addActionListener(this); // added an action listener event on
											// the button
			buttonList.add(button);

			southPanel.add(button);
		}
		frame.getContentPane().add(southPanel, BorderLayout.SOUTH);

		frame.repaint();
		frame.setVisible(true);

	}

	public void actionPerformed(ActionEvent e) {
		JOptionPane.showMessageDialog(null,
				"Button number " + buttonList.indexOf(e.getSource()) + " has been pressed.");

	}

	public JButton getButton(int index) {
		if (index >= this.jButtonNumber || index < 0) {
			throw new IllegalArgumentException("Invalid button index.");
		}
		return this.buttonList.get(index);
	}

	public BrailleCell getCell(int index) {
		if (index >= this.brailleCellNumber || index < 0) {
			throw new IllegalArgumentException("Invalid cell index.");
		}
		return this.brailleList.get(index);
	}

	public void clearAllCells() {

		for (int i = 0; i < this.brailleCellNumber; i++) {
			this.brailleList.get(i).clear();

		}
	}

	public void displayString(String aString) {
		this.clearAllCells();
		for (int i = 0; i < this.brailleCellNumber && i < aString.length(); i++) {
			this.brailleList.get(i).displayLetter(aString.charAt(i));
		}
	}

}
