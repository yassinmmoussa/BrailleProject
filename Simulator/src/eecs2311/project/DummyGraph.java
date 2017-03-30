package eecs2311.project;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.mxgraph.layout.mxCompactTreeLayout;
import com.mxgraph.layout.mxGraphLayout;
import com.mxgraph.layout.hierarchical.mxHierarchicalLayout;
import com.mxgraph.model.mxCell;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxRectangle;
import com.mxgraph.view.mxGraph;

import net.codejava.sound.SwingSoundRecorder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DummyGraph {

	private static String filepath = "";

	private static String browseFile() {
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new java.io.File("."));
		chooser.setDialogTitle("choosertitle");
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		chooser.setAcceptAllFileFilterUsed(false);

		if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			// System.out.println("getCurrentDirectory(): " +
			// chooser.getCurrentDirectory());
			System.out.println("getSelectedFile() : " + chooser.getSelectedFile().getName());
		} else {
			System.out.println("No Selection ");
		}
		return chooser.getSelectedFile().getAbsolutePath();
	}

	public static void main(String args[]) {

		JFrame frame = new JFrame();
		frame.setSize(600, 600);
		frame.getContentPane().setLayout(null);

		JPanel mainPanel = new JPanel();
		mainPanel.setBounds(0, 0, frame.getWidth(), frame.getHeight());
		mainPanel.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(50, 50, 135, 800);
		panel.setLayout(new BorderLayout());

		mxGraph graph = new mxGraph();

		mxCell parent = (mxCell) graph.getDefaultParent();

		mxGraphLayout layout = new mxHierarchicalLayout(graph);

		graph.getModel().beginUpdate();

		try {

			Object v1 = graph.insertVertex(parent, null, "v1", 0, 0, 50.0, 50.0);
			Object v2 = graph.insertVertex(parent, null, "v2", 0, 0, 50.0, 50.0);
			Object v3 = graph.insertVertex(parent, null, "v3", 0, 0, 50, 50);
			Object v4 = graph.insertVertex(parent, null, "v4", 0, 0, 50, 50);
			Object v5 = graph.insertVertex(parent, null, "v5", 0, 0, 50, 50);
			Object e1 = graph.insertEdge(parent, null, null, v1, v2);
			Object e2 = graph.insertEdge(parent, null, null, v2, v3);
			Object e3 = graph.insertEdge(parent, null, null, v3, v4);
			Object e4 = graph.insertEdge(parent, null, null, v3, v5);

			layout.execute(graph.getDefaultParent());
		} finally {
			graph.getModel().endUpdate();
		}
		mxGraphComponent idk = new mxGraphComponent(graph);
		panel.revalidate();
		panel.add(idk);

		JPanel btnPanel = new JPanel();
		btnPanel.setBounds(200, 50, 400, 50);
		btnPanel.setLayout(new BorderLayout());

		// add record buttons as panel
		SwingSoundRecorder recordBtn = new SwingSoundRecorder();
		recordBtn.setVisible(true);
		btnPanel.add(recordBtn);

		/*
		 * Browse button will open FileChooser and when you select file name, it
		 * will return the filepath of the selected file
		 */
		JButton browse = new JButton("Browse..");
		browse.setBounds(235, 100, 100, 30);
		browse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// open file chooser and select file path
				filepath = browseFile();
				System.out.println("filepath : " + filepath);

			}
		});

		// add the two panels to the frame
		mainPanel.add(panel);
		mainPanel.add(btnPanel);
		mainPanel.add(browse);
		frame.getContentPane().add(mainPanel);
		frame.setVisible(true);

	}

}
