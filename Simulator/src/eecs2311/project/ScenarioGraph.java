package eecs2311.project;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.mxgraph.layout.mxGraphLayout;
import com.mxgraph.layout.hierarchical.mxHierarchicalLayout;
import com.mxgraph.model.mxCell;
import com.mxgraph.view.mxGraph;

public class ScenarioGraph {

	ScenarioNode root;
	ScenarioNode current;

	HashMap<mxCell, ScenarioNode> graphMap = new HashMap<mxCell, ScenarioNode>();

	public ScenarioGraph(ScenarioNode node) {
		root = node;
	}

	public ScenarioGraph(File file) throws FileNotFoundException {

		Scanner scanner = new Scanner(file);
		String[] cell = scanner.nextLine().split(" ");
		String[] button = scanner.nextLine().split(" ");
		String line = "";

		//System.out.println(cell[1] + " " + button[1]);

		this.root = new ScenarioNode("Root", cell[1] + " " + button[1]);
		current = root;
		root.setCellNumber(Integer.parseInt("" + cell[1]));
		root.setButtonNumber(Integer.parseInt("" + button[1]));

		while (scanner.hasNextLine()) {

			if (current.getOnlyParent().nodeType != "Text-To-Speech") {
				line = scanner.nextLine();
			}

			if (line == "") {
				line = scanner.nextLine();

			} else if (line.substring(0, 8) == "/~pause:") {
				ScenarioNode node = new ScenarioNode("Pause", ("" + line.charAt(8)));
				addOneToCurrent(node);
				current = current.getOnlyChild();

			} else if (line.substring(0, 13) == "/~set-voice:") {
				ScenarioNode node = new ScenarioNode("Set Voice", "" + line.charAt(13));
				addOneToCurrent(node);
				current = current.getOnlyChild();

			} else if (line.substring(0, 15) == "/~disp-string:") {
				ScenarioNode node = new ScenarioNode("Display String", line.substring(15, line.length()));
				addOneToCurrent(node);
				current = current.getOnlyChild();

			} else if (line.substring(0, 15) == "/~skip-button:") {

				ScenarioNode node = new ScenarioNode("Branch on User Input", "");
				addOneToCurrent(node);
				ScenarioNode leftNode = new ScenarioNode("Branch 1", "");
				ScenarioNode rightNode = new ScenarioNode("Branch 2", "");
				current.setLeft(leftNode);
				current.setRight(rightNode);
				current = leftNode;
				parseBranch(scanner);
				ScenarioNode resumeNode = new ScenarioNode("Resume", "");
				addOneToCurrent(resumeNode);
				current = resumeNode;
				ScenarioNode mainBranch = new ScenarioNode("Main Branch", "");
				current.setOnlyChild(mainBranch);
				current = rightNode;
				parseBranch(scanner);
				ScenarioNode resumeNode2 = new ScenarioNode("Resume", "");
				addOneToCurrent(resumeNode2);
				current = resumeNode2;
				resumeNode2.setOnlyChild(mainBranch);
				mainBranch.setLeftParent(resumeNode);
				mainBranch.setRightParent(resumeNode2);
				current = mainBranch;

			} else if (line.substring(0, 9) == "/~sound:") {
				ScenarioNode node = new ScenarioNode("Play Audio", line.substring(9, line.length()));
				addOneToCurrent(node);
				current = current.getOnlyChild();

			} else if (line.substring(0, 16) == "/~disp-clearAll") {
				ScenarioNode node = new ScenarioNode("Clear All", "");
				addOneToCurrent(node);
				current = current.getOnlyChild();

			} else if (line.substring(0, 19) == "/~disp-clear-cell:") {
				ScenarioNode node = new ScenarioNode("Clear Cell", "" + line.charAt(19));
				addOneToCurrent(node);
				current = current.getOnlyChild();

			} else if (line.substring(0, 18) == "/~disp-cell-pins:") {
				ScenarioNode node = new ScenarioNode("Set Pins", line.substring(18, line.length()));
				addOneToCurrent(node);
				current = current.getOnlyChild();

			} else if (line.substring(0, 18) == "/~disp-cell-char:") {
				ScenarioNode node = new ScenarioNode("Display Character", line.substring(18, line.length()));
				addOneToCurrent(node);
				current = current.getOnlyChild();

			} else if (line.charAt(0) != '/') {
				StringBuilder audio = new StringBuilder();
				while (line.charAt(0) != '/') {
					audio.append(line + "\n");
					line = scanner.nextLine();
				}
				ScenarioNode node = new ScenarioNode("Text-To-Speech", audio.toString());
				addOneToCurrent(node);
				current = current.getOnlyChild();
			}

		}

		current = null;
	}

	private void parseBranch(Scanner scanner) {
		String line = scanner.nextLine();
		while (line.substring(0, 8) != "/~skip:mainBranch") {

			if (current.getOnlyParent().nodeType != "Text-To-Speech") {
				line = scanner.nextLine();
			}

			if (line == "") {
				line = scanner.nextLine();

			} else if (line.substring(0, 8) == "/~pause:") {
				ScenarioNode node = new ScenarioNode("Pause", ("" + line.charAt(8)));
				addOneToCurrent(node);
				current = current.getOnlyChild();

			} else if (line.substring(0, 13) == "/~set-voice:") {
				ScenarioNode node = new ScenarioNode("Set Voice", "" + line.charAt(13));
				addOneToCurrent(node);
				current = current.getOnlyChild();

			} else if (line.substring(0, 15) == "/~disp-string:") {
				ScenarioNode node = new ScenarioNode("Display String", line.substring(15, line.length()));
				addOneToCurrent(node);
				current = current.getOnlyChild();

			} else if (line.substring(0, 15) == "/~skip-button:") {
				ScenarioNode node = new ScenarioNode("Branch on User Input", "");
				addOneToCurrent(node);
				ScenarioNode leftNode = new ScenarioNode("Branch 1", "");
				ScenarioNode rightNode = new ScenarioNode("Branch 2", "");
				current.setLeft(leftNode);
				current.setRight(rightNode);
				current = leftNode;
				parseBranch(scanner);
				ScenarioNode resumeNode = new ScenarioNode("Resume", "");
				addOneToCurrent(resumeNode);
				current = resumeNode;
				ScenarioNode mainBranch = new ScenarioNode("Main Branch", "");
				current.setOnlyChild(mainBranch);
				current = rightNode;
				parseBranch(scanner);
				ScenarioNode resumeNode2 = new ScenarioNode("Resume", "");
				addOneToCurrent(resumeNode2);
				current = resumeNode2;
				resumeNode2.setOnlyChild(mainBranch);
				mainBranch.setLeftParent(resumeNode);
				mainBranch.setRightParent(resumeNode2);
				current = mainBranch;

			} else if (line.substring(0, 9) == "/~sound:") {
				ScenarioNode node = new ScenarioNode("Play Audio", line.substring(9, line.length()));
				addOneToCurrent(node);
				current = current.getOnlyChild();

			} else if (line.substring(0, 16) == "/~disp-clearAll") {
				ScenarioNode node = new ScenarioNode("Clear All", "");
				addOneToCurrent(node);
				current = current.getOnlyChild();

			} else if (line.substring(0, 19) == "/~disp-clear-cell:") {
				ScenarioNode node = new ScenarioNode("Clear Cell", "" + line.charAt(19));
				addOneToCurrent(node);
				current = current.getOnlyChild();

			} else if (line.substring(0, 18) == "/~disp-cell-pins:") {
				ScenarioNode node = new ScenarioNode("Set Pins", line.substring(18, line.length()));
				addOneToCurrent(node);
				current = current.getOnlyChild();

			} else if (line.substring(0, 18) == "/~disp-cell-char:") {
				ScenarioNode node = new ScenarioNode("Display Character", line.substring(18, line.length()));
				addOneToCurrent(node);
				current = current.getOnlyChild();

			} else if (line.charAt(0) != '/') {
				StringBuilder audio = new StringBuilder();
				while (line.charAt(0) != '/') {
					audio.append(line + "\n");
					line = scanner.nextLine();
				}
				ScenarioNode node = new ScenarioNode("Text-To-Speech", audio.toString());
				addOneToCurrent(node);
				current = current.getOnlyChild();
			}

		}
	}

	public String getScenario() {

		ScenarioNode current2 = root;
		StringBuilder scenario = new StringBuilder();

		while (!current2.hasNoChildren()) {

			if (current2.hasOneChild()) {

				scenario.append(current2.toString());
				current2 = current2.getOnlyChild();

			} else if (current2.hasTwoChildren()) {
				ScenarioNode leftChild = current2.getTwoChildren().get(0);
				ScenarioNode rightChild = current2.getTwoChildren().get(1);
				scenario.append(leftChild.toString());
				current2 = getBranchScenario(leftChild, scenario);
				scenario.append(rightChild.toString());
				getBranchScenario(rightChild, scenario);
				scenario.append("/~mainBranch\n");

			}
		}

		return scenario.toString();
	}

	// Private because its only purpose is to assist the getScenario() method

	private ScenarioNode getBranchScenario(ScenarioNode current2, StringBuilder scenario) {

		while (current2.hasOneParent()) {

			if (current2.hasOneChild()) {

				scenario.append(current2.toString());
				current2 = current2.getOnlyChild();

			} else if (current2.hasTwoChildren()) {

				ScenarioNode leftChild = current2.getTwoChildren().get(0);
				ScenarioNode rightChild = current2.getTwoChildren().get(1);
				scenario.append(leftChild.toString());
				current2 = getBranchScenario(leftChild, scenario);
				scenario.append(rightChild.toString());
				getBranchScenario(rightChild, scenario);
				scenario.append("/~mainBranch\n");

			}
		}
		return null;
	}

	public void addOneToCurrent(ScenarioNode node) {
		if (current == null) {
			return;
		}
		// if it has no children
		if (current.hasNoChildren()) {
			current.setOnlyChild(node);
			node.setParent(current);
		} else if (current.hasOneChild()) // if it has one child
		{
			ScenarioNode temp = current.getOnlyChild();
			current.setOnlyChild(node);
			temp.setParent(node);
			node.setParent(current);
			node.setOnlyChild(temp);
		} else if (current.hasTwoChildren()) // if it has two children
		{
			ArrayList<ScenarioNode> nodes = current.getTwoChildren();
			current.setOnlyChild(node);
			current.setLeft(null);
			current.setRight(null);
			nodes.get(0).setParent(node);
			nodes.get(1).setParent(node);
			node.setLeft(nodes.get(0));
			node.setRight(nodes.get(1));
		}

	}

	public void addTwoToCurrent(ScenarioNode leftNode, ScenarioNode rightNode) {
		if (current == null) {
			return;
		}
		// if it has no children
		if (current.hasNoChildren()) {
			current.setLeft(leftNode);
			current.setRight(rightNode);
			leftNode.setParent(current);
			rightNode.setParent(current);

		} else if (current.hasOneChild()) // if it has one child
		{
			ScenarioNode temp = current.getOnlyChild();
			current.setOnlyChild(null);
			current.setLeft(leftNode);
			current.setRight(rightNode);
			leftNode.setOnlyChild(temp);
			leftNode.setParent(current);
			rightNode.setOnlyChild(temp);
			rightNode.setParent(current);
			temp.setLeftParent(leftNode);
			temp.setRightParent(rightNode);

		} else if (current.hasTwoChildren()) // if it has two children
		{
			ArrayList<ScenarioNode> tempChildren = new ArrayList<ScenarioNode>(2);
			tempChildren.add(current.getTwoChildren().get(0));
			tempChildren.add(current.getTwoChildren().get(1));
			current.getTwoChildren().set(0, leftNode);
			current.getTwoChildren().set(1, rightNode);
			leftNode.setOnlyChild(tempChildren.get(0));
			rightNode.setOnlyChild(tempChildren.get(1));
			tempChildren.get(0).setParent(leftNode);
			tempChildren.get(1).setParent(rightNode);

		}

	}

	public mxGraph getGraph() {
		mxGraph graph = new mxGraph();
		mxCell parent = (mxCell) graph.getDefaultParent();
		mxGraphLayout layout = new mxHierarchicalLayout(graph);

		ScenarioNode current2;
		current2 = root;
		Object mxRoot;
		graph.getModel().beginUpdate();
		try {
			mxRoot = graph.insertVertex(parent, null, root.nodeType, 0, 0, 50, 50);
			layout.execute(graph.getDefaultParent());
		} finally {
			graph.getModel().endUpdate();
		}

		graphMap.put((mxCell) mxRoot, root);
		mxCell current2Cell = null;
		while (!current2.hasNoChildren()) {

			for (Map.Entry<mxCell, ScenarioNode> i : graphMap.entrySet()) {
				if (i.getValue() == current2) {
					current2Cell = i.getKey();
				}
			}

			if (current2.hasOneChild()) {

				graph.getModel().beginUpdate();
				try {
					Object v1 = graph.insertVertex(parent, null, current2.getOnlyChild().nodeType, 0, 0, 50, 50);
					graphMap.put((mxCell) v1, current2.getOnlyChild());
					Object e1 = graph.insertEdge(parent, null, null, current2Cell, v1);
					// DEBUG LINE BELOW
					System.out.println(graphMap.get(current2Cell).nodeType + "->" + graphMap.get(v1).nodeType);
					layout.execute(graph.getDefaultParent());
				} finally {
					graph.getModel().endUpdate();

				}
				current2 = current2.getOnlyChild();

			} else if (current2.hasTwoChildren()) {

				graph.getModel().beginUpdate();
				try {
					Object v1 = graph.insertVertex(parent, null, current2.getTwoChildren().get(0).nodeType, 0, 0, 50,
							50);
					Object v2 = graph.insertVertex(parent, null, current2.getTwoChildren().get(1).nodeType, 0, 0, 50,
							50);
					graphMap.put((mxCell) v1, current2.getTwoChildren().get(0));
					graphMap.put((mxCell) v2, current2.getTwoChildren().get(1));
					Object e1 = graph.insertEdge(parent, null, null, current2Cell, v1);
					Object e2 = graph.insertEdge(parent, null, null, current2Cell, v2);
					layout.execute(graph.getDefaultParent());

				} finally {
					graph.getModel().endUpdate();

				}
				ScenarioNode leftChild, rightChild;
				leftChild = current2.getTwoChildren().get(0);
				rightChild = current2.getTwoChildren().get(1);
				current2 = processBranchGraph(graph, leftChild);
				processBranchGraph(graph, rightChild);

			}
		}

		return graph;

	}

	// Private because it's only purpose is to assist the getGraph() method;

	private ScenarioNode processBranchGraph(mxGraph graph, ScenarioNode current2) {

		mxCell current2Cell = null;
		mxCell parent = (mxCell) graph.getDefaultParent();
		mxGraphLayout layout = new mxHierarchicalLayout(graph);

		for (Map.Entry<mxCell, ScenarioNode> i : graphMap.entrySet()) {
			if (i.getValue() == current) {
				current2Cell = i.getKey();
			}
		}
		while (current2.hasOneParent()) {

			if (current2.hasOneChild()) {
				Object v1 = null;

				graph.getModel().beginUpdate();
				try {
					if (!graphMap.containsValue(current2.getOnlyChild())) {
						v1 = graph.insertVertex(parent, null, current2.getOnlyChild().nodeType, 0, 0, 50, 50);
						graphMap.put((mxCell) v1, current2.getOnlyChild());
					} else {

						for (Map.Entry<mxCell, ScenarioNode> i : graphMap.entrySet()) {
							if (i.getValue() == current2.getOnlyChild()) {
								v1 = i.getKey();
							}
						}

					}
					Object e1 = graph.insertEdge(parent, null, null, current2Cell, v1);
					layout.execute(graph.getDefaultParent());

				} finally {
					graph.getModel().endUpdate();

				}
				current2 = current2.getOnlyChild();

			} else if (current2.hasTwoChildren()) {

				graph.getModel().beginUpdate();
				try {
					Object v1 = graph.insertVertex(parent, null, current2.getTwoChildren().get(0).nodeType, 0, 0, 50,
							50);
					Object v2 = graph.insertVertex(parent, null, current2.getTwoChildren().get(1).nodeType, 0, 0, 50,
							50);
					graphMap.put((mxCell) v1, current2.getTwoChildren().get(0));
					graphMap.put((mxCell) v2, current2.getTwoChildren().get(1));
					Object e1 = graph.insertEdge(parent, null, null, current2Cell, v1);
					Object e2 = graph.insertEdge(parent, null, null, current2Cell, v2);
					layout.execute(graph.getDefaultParent());

				} finally {
					graph.getModel().endUpdate();

				}

				ScenarioNode leftChild, rightChild;
				leftChild = current2.getTwoChildren().get(0);
				rightChild = current2.getTwoChildren().get(1);
				current2 = processBranchGraph(graph, leftChild);
				processBranchGraph(graph, rightChild);
			}
		}

		return current2;

	}

	public void setCurrent(ScenarioNode node) {
		this.current = node;
	}

	public void removeCurrent() {
		if (current != null && current.nodeType != "Main Branch") {

			if (current.nodeType == "Branch on User Input") {
				ScenarioNode parent = current.getOnlyParent();
				ScenarioNode child = current.getTwoChildren().get(0);
				while (child.nodeType != "Main Branch") {
					child = child.getOnlyChild();
				}
				child = child.getOnlyChild();
				parent.setOnlyChild(child);
				child.setParent(parent);
				current = null;
			} else {
				ScenarioNode parent = current.getOnlyParent();
				ScenarioNode child = current.getOnlyChild();
				parent.setOnlyChild(child);
				child.setParent(parent);
				current = null;

			}

		}
	}

}
