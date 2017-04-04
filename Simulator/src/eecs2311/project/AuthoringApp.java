package eecs2311.project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.mxgraph.layout.mxGraphLayout;
import com.mxgraph.layout.hierarchical.mxHierarchicalLayout;
import com.mxgraph.model.mxCell;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;

import net.codejava.sound.SwingSoundRecorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

public class AuthoringApp {

	static MouseAdapter mouseListener;

	static JFrame frame;
	static String filePath;
	private static File lastpath;
	private static JFileChooser chooser = new JFileChooser();
	private static JButton newScenario, browseAudio, browseScenario, saveScenario, pauseNode, setVoiceNode,
			displayStringNode, branchNode, playAudioNode, clearAllNode, clearCellNode, setPinsNode, displayCharNode,
			ttsNode;
	private static SwingSoundRecorder recordBtn;
	public static ScenarioGraph scenarioGraph;
	public static mxGraph graph;
	public static JPanel graphPanel, btnPanel;
	static mxGraphComponent graphComponent;

	public static void main(String[] args) {

		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 22));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 750);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);

		graphPanel = new JPanel();
		graphPanel.setBounds(50, 75, 366, 600);
		frame.getContentPane().add(graphPanel);
		graphPanel.setLayout(new BorderLayout());
		// graphPanel.setBackground(Color.BLACK);

		btnPanel = new JPanel();
		btnPanel.setBounds(500, 75, 210, 600);
		btnPanel.setLayout(null);
		// btnPanel.setBackground(Color.BLUE);
		frame.getContentPane().add(btnPanel);

		browseAudio = new JButton("Browse Audio");
		browseAudio.setSize(200, 30);

		browseScenario = new JButton("Browse Scenario");
		browseScenario.setLocation(0, 91);
		browseScenario.setSize(200, 30);

		newScenario = new JButton("New Scenario");
		newScenario.setLocation(0, 5);
		newScenario.setSize(200, 30);

		pauseNode = new JButton("Add Pause Node");
		pauseNode.setLocation(0, 177);
		pauseNode.setSize(200, 30);

		setVoiceNode = new JButton("Add Set-Voice Node");
		setVoiceNode.setLocation(0, 220);
		setVoiceNode.setSize(200, 30);

		displayStringNode = new JButton("Add Display-String Node");
		displayStringNode.setLocation(0, 266);
		displayStringNode.setSize(200, 30);

		branchNode = new JButton("Add two branches");
		branchNode.setLocation(0, 309);
		branchNode.setSize(200, 30);

		playAudioNode = new JButton("Add Play-Audio Node");
		playAudioNode.setLocation(0, 352);
		playAudioNode.setSize(200, 30);

		clearAllNode = new JButton("Add Clear-All Node");
		clearAllNode.setLocation(0, 395);
		clearAllNode.setSize(200, 30);

		clearCellNode = new JButton("Add Clear-Cell Node");
		clearCellNode.setLocation(0, 438);
		clearCellNode.setSize(200, 30);

		setPinsNode = new JButton("Add Set-Pins Node");
		setPinsNode.setLocation(0, 481);
		setPinsNode.setSize(200, 30);

		displayCharNode = new JButton("Add Display-Character Node");
		displayCharNode.setLocation(0, 519);
		displayCharNode.setSize(200, 30);

		saveScenario = new JButton("Save Scenario");
		saveScenario.setBounds(0, 48, 200, 30);

		ttsNode = new JButton("Add Text-To-Speech Node");
		ttsNode.setBounds(0, 557, 200, 30);

		setActionListeners();

		// btnPanel.add(browseAudio);
		btnPanel.add(browseScenario);
		btnPanel.add(newScenario);
		btnPanel.add(pauseNode);
		btnPanel.add(setVoiceNode);
		btnPanel.add(displayStringNode);
		btnPanel.add(branchNode);
		btnPanel.add(playAudioNode);
		btnPanel.add(clearAllNode);
		btnPanel.add(clearCellNode);
		btnPanel.add(setPinsNode);
		btnPanel.add(displayCharNode);
		btnPanel.add(ttsNode);
		btnPanel.add(saveScenario);

		btnPanel.revalidate();
		btnPanel.repaint();
		graphPanel.revalidate();
		graphPanel.repaint();

		JLabel lblNodes = new JLabel("Nodes:");
		lblNodes.setBounds(0, 148, 56, 16);
		btnPanel.add(lblNodes);

		mouseListener = new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				mxCell clickedCell = (mxCell) graphComponent.getCellAt(e.getX(), e.getY());
				scenarioGraph.setCurrent(scenarioGraph.graphMap.get(clickedCell));
				System.out.println("mouse clicked on graph\n" + scenarioGraph.graphMap.get(clickedCell).cellNumber + "\n");

			}
		};

		graphComponent.getGraphControl().addMouseListener(mouseListener);

	}

	public static void setActionListeners() {
		// newScenario, browseAudio, browseScenario, saveScenario, pauseNode,
		// setVoiceNode,
		// displayStringNode, branchNode, playAudioNode, clearAllNode,
		// clearCellNode, setPinsNode, displayCharNode,
		// ttsNode;

		newScenario.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String content = JOptionPane.showInputDialog(frame,
						"Please enter the number of Cells, followed by the number of Buttons separated by whitespace",
						"Root Node", JOptionPane.PLAIN_MESSAGE);
				System.out.println(content);
				scenarioGraph = new ScenarioGraph(new ScenarioNode("Root", content));
				graph = scenarioGraph.getGraph();
				graph.refresh();
				graphComponent = new mxGraphComponent(graph);
				graphPanel.removeAll();
				graph.refresh();
				graphPanel.add(graphComponent);
				graphComponent.getGraphControl().addMouseListener(mouseListener);
				graphPanel.repaint();
				graphPanel.revalidate();
				frame.revalidate();

			}

		});
		ttsNode.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (scenarioGraph != null && scenarioGraph.current != null) {
					scenarioGraph.addOneToCurrent(new ScenarioNode("Text-To-Speech", ""));
					graphPanel.removeAll();
					graph.getModel().beginUpdate();
					graph = scenarioGraph.getGraph();
					graph.refresh();
					mxGraphLayout layout = new mxHierarchicalLayout(graph);
					layout.execute(graph.getDefaultParent());
					graph.getModel().endUpdate();
					graph.refresh();
					graphComponent = new mxGraphComponent(graph);
					graphPanel.revalidate();
					graph.refresh();
					graphPanel.add(graphComponent);
					graphComponent.getGraphControl().addMouseListener(mouseListener);
					graphPanel.repaint();
					frame.revalidate();
					scenarioGraph.setCurrent(null);
				}

			}

		});

		pauseNode.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (scenarioGraph != null && scenarioGraph.current != null) {
					scenarioGraph.addOneToCurrent(new ScenarioNode("Pause", ""));
					graphPanel.removeAll();
					graph = scenarioGraph.getGraph();
					graph.refresh();
					graphComponent = new mxGraphComponent(graph);
					mxGraphLayout layout = new mxHierarchicalLayout(graph);
					graph.getModel().beginUpdate();
					layout.execute(graph.getDefaultParent());
					graph.getModel().endUpdate();
					graphPanel.revalidate();
					graph.refresh();
					graphPanel.add(graphComponent);
					graphComponent.getGraphControl().addMouseListener(mouseListener);
					graphPanel.repaint();
					frame.revalidate();
					scenarioGraph.setCurrent(null);
				}

			}

		});

	}
}
