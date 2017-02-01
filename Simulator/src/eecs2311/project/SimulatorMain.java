package eecs2311.project;

import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JLabel;

public class SimulatorMain {

	private JFrame frame;
	private JRadioButton radio1x1 = new JRadioButton("");
	private JRadioButton radio1x2 = new JRadioButton("");
	private JRadioButton radio2x1 = new JRadioButton("");
	private JRadioButton radio2x2 = new JRadioButton("");
	private JRadioButton radio3x1 = new JRadioButton("");
	private JRadioButton radio3x2 = new JRadioButton("");
	private JRadioButton radio4x1 = new JRadioButton("");
	private JRadioButton radio4x2 = new JRadioButton("");
	JLabel label1 = new JLabel("Letter");
	static String test = "This is a test sentence";
	static int index = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SimulatorMain window = new SimulatorMain();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SimulatorMain() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
//		BrailleCell cell1 = new BrailleCell(radio1x1, radio1x2, radio2x1, radio2x2, radio3x1, radio3x2, radio4x1,
//				radio4x2);
		frame = new JFrame();
		
//-----Test purposes
		BrailleCell cell1 = new BrailleCell(frame);
		BrailleCell cell2 = new BrailleCell(frame);
		BrailleCell cell3 = new BrailleCell(frame);
		BrailleCell cell4 = new BrailleCell(frame);
		BrailleCell cell5 = new BrailleCell(frame);
		BrailleCell cell6 = new BrailleCell(frame);
		BrailleCell cell12 = new BrailleCell(frame);
		BrailleCell cell22 = new BrailleCell(frame);
		BrailleCell cell32 = new BrailleCell(frame);
		BrailleCell cell42 = new BrailleCell(frame);
		BrailleCell cell52 = new BrailleCell(frame);
		BrailleCell cell62 = new BrailleCell(frame);
		cell1.displayLetter('a');
		cell2.displayLetter('b');
//---------------------------		
		
		//frame.setBounds(100, 100, 627, 459);
		frame.setSize(640, 440);
		frame.setLayout(new FlowLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JButton mainButton = new JButton("O");
		mainButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// cell1.raisePins("11111111");
				if (index >= test.length()) {
//					label1.setText("END");
					index = 0;
				}
				cell1.displayLetter(test.charAt(index));
				if (test.charAt(index) == ' ') {
					label1.setText("<space>");
				} else {
					label1.setText("" + test.charAt(index));
				}

				index++;

			}
		});
//		mainButton.setBounds(268, 364, 68, 35);
//		frame.getContentPane().setLayout(null);
//		frame.getContentPane().add(mainButton);
//
//		// radio1x1 = new JRadioButton("");
//		radio1x1.setEnabled(false);
//		radio1x1.setBounds(264, 158, 25, 22);
//
//		frame.getContentPane().add(radio1x1);
//
//		// radio1x2 = new JRadioButton("");
//		radio1x2.setEnabled(false);
//		radio1x2.setBounds(299, 158, 25, 25);
//		frame.getContentPane().add(radio1x2);
//
//		// radio2x1 = new JRadioButton("");
//		radio2x1.setEnabled(false);
//		radio2x1.setBounds(264, 185, 25, 25);
//		frame.getContentPane().add(radio2x1);
//
//		// radio2x2 = new JRadioButton("");
//		radio2x2.setEnabled(false);
//		radio2x2.setBounds(299, 185, 25, 25);
//		frame.getContentPane().add(radio2x2);
//
//		// radio3x1 = new JRadioButton("");
//		radio3x1.setEnabled(false);
//		radio3x1.setBounds(264, 211, 25, 25);
//		frame.getContentPane().add(radio3x1);
//
//		// radio3x2 = new JRadioButton("");
//		radio3x2.setEnabled(false);
//		radio3x2.setBounds(299, 211, 25, 25);
//		frame.getContentPane().add(radio3x2);
//
//		radio4x1.setEnabled(false);
//		radio4x1.setBounds(264, 236, 25, 25);
//		frame.getContentPane().add(radio4x1);
//
//		radio4x2.setEnabled(false);
//		radio4x2.setBounds(299, 236, 25, 25);
//		frame.getContentPane().add(radio4x2);
//
//		label1.setBounds(158, 164, 56, 16);
//		frame.getContentPane().add(label1);
		

	}
}
