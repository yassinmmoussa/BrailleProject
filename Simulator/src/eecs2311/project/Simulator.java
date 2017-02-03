package eecs2311.project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class Simulator {

	int brailleCellNumber;
	int jButtonNumber;
	private JFrame frame;
	GridLayout cellGrid = new GridLayout(4, 2);
	GridLayout frameGrid = new GridLayout(3, 1);
	LinkedList<JPanel> panelList = new LinkedList<JPanel>();
	ArrayList<JRadioButton> pins = new ArrayList<JRadioButton>(8);
	LinkedList<ArrayList<JRadioButton>> pinList = new LinkedList<ArrayList<JRadioButton>>();
	LinkedList<JButton> buttonList = new LinkedList<JButton>();
	LinkedList<BrailleCell> brailleList = new LinkedList<BrailleCell>();

	public Simulator(int brailleCellNumber, int jButtonNumber) {

		int bigger = brailleCellNumber > jButtonNumber ? brailleCellNumber : jButtonNumber;
		frame = new JFrame();
		frame.setBounds(100, 100, 627, 459);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(3, 2));

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
			panel.setSize(100, 150);
			panel.setBorder(BorderFactory.createLineBorder(Color.black));
			frame.getContentPane().add(panel);
			pins.clear();

		}

		for (int i = 0; i < jButtonNumber; i++) {
			JButton button = new JButton("" + i);
			// button.setSize(70, 35);
			buttonList.add(button);
			button.setSize(70, 35);
			frame.getContentPane().add(button);
		}

		for (int i = 0; i < brailleCellNumber; i++) {
			brailleList.get(i).displayLetter('a');
		}
		frame.repaint();
		frame.setVisible(true);

	}

}
