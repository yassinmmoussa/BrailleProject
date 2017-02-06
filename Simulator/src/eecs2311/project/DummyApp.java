package eecs2311.project;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class DummyApp {

	public static void main(String[] args) {
		Simulator x = new Simulator(20, 7);

		x.displayString("Hello my friend");

		x.getButton(0).addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "The action event you set is triggered");

			}

		});

	}
}
