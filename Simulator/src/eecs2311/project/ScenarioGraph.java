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

	int size;
	HashMap<mxCell, ScenarioNode> graphMap = new HashMap<mxCell, ScenarioNode>();

	public ScenarioGraph() {
		root = null;
		current = null;
		size = 0;
	}

	public ScenarioGraph(ScenarioNode node) {
		root = node;
		current = root;
		size = 1;

	}

	public ScenarioGraph(File file) throws FileNotFoundException {

		Scanner scanner = new Scanner(file);
		String line = scanner.nextLine();
		this.root = new ScenarioNode("Root", "");
		current = root;
		root.setCellNumber(Integer.parseInt("" + line.charAt(7)));
		line = scanner.nextLine();
		root.setButtonNumber(Integer.parseInt("" + line.charAt(8)));
		while (scanner.hasNextLine()) {
			line = scanner.nextLine();
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
				// IMPLEMTENATION DONE AT THE END, PL0X DON'T FORGET BABE
			}
//			else if (line.substring(0,  ))
		}

		// labelMap.put("Pause", "/~pause:");
		// labelMap.put("Set Voice", "/~set-voice:");
		// labelMap.put("Display String", "/~disp-string:");
		// labelMap.put("Branch on User Input", "/~skip-button:0
		// Branch1\n/~skip-button:1 Branch2\n/~user-input\n");
		// labelMap.put("Play Audio", "/~sound:");
		// labelMap.put("Resume", "/~skip:mainBranch\n");
		// labelMap.put("Branch 1", "/~Branch1\n");
		// labelMap.put("Branch 2", "/~Branch2\n");
		// labelMap.put("Main Branch", "");
		// labelMap.put("Clear All", "/~disp-clearALL\n");
		// labelMap.put("Clear Cell", "/~disp-clear-cell:");
		// labelMap.put("Set Pins", "/~disp-cell-pins:");
		// labelMap.put("Display Character", "/~disp-cell-char:");
		// labelMap.put("Text-To-Speech", "");

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
		size++;

	}

	public void addTwoToCurrent(ScenarioNode leftNode, ScenarioNode rightNode) {
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
		Object mxRoot = graph.insertVertex(parent, null, root.nodeType, 0, 0, 50, 50);

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

}
