package eecs2311.project;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class DummyApp {

	public static void main(String[] args) {
		Simulator x = new Simulator(48, 7);

		x.displayString("Wow these are so many braille cells I dont even know how we fit them in there");
		x.getCell(4).clear();
		x.getCell(12).displayLetter('z');
		x.getButton(0).setText("My Button");

		x.getButton(0).addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "The action event you set is triggered");

			}

		});

	}
}
