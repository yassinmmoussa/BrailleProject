package eecs2311.project;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/*
 * NOTES:
 * 
 * Remember to reset buttons at the very beginning of any branch you go to
 * AND to use the /~skip: tag to make it go back to the main storyline
 * 
 * You can't set the Resume nodes to current, because you can't add anything after
 * The Main Branch node is the only one that is allowed to have two parents
 * 
 * MAYBE implement switch nodes by switching their values and not actually switching the nodes themselves
 * 
 * 
 */

public class ScenarioNode {

	public int cellNumber; // only if node is root
	public int buttonNumber; // only if node is root
	ScenarioNode onlyParent, onlyChild, leftChild, rightChild;
	ArrayList<ScenarioNode> twoParents = new ArrayList<ScenarioNode>(2);

	String nodeType;
	// The nodeType variable must correspond to one of the keys in the labelMap
	// HashMap.
	// It specifies if there is content in the first place, and if so how it is
	// to be formatted.
	String content;
	// The content of the node, this can either be the parameters the node
	// needs, or the text in the case of Text-To-Speech
	private HashMap<String, String> labelMap = new HashMap<String, String>();

	public ScenarioNode(String nodeType, String content) {
		initializeMap();
		this.nodeType = nodeType;
		if (nodeType != "Root")
			{this.content = content;}
		else
		{
			Scanner scanner = new Scanner(content);
			this.cellNumber = scanner.nextInt();
			this.buttonNumber = scanner.nextInt();
			
		}
		this.onlyParent = null;
		this.leftChild = null;
		this.rightChild = null;
		this.onlyChild = null;
		twoParents.add(0, null);
		twoParents.add(1, null);

	}

	// public ScenarioNode(String nodeType, String content, ScenarioNode parent,
	// ScenarioNode left, ScenarioNode right,
	// ScenarioNode onlyChild) {
	// this(nodeType, content);
	// this.onlyParent = parent;
	// this.leftChild = left;
	// this.rightChild = right;
	// this.onlyChild = onlyChild;
	//
	// }

	private void initializeMap() {
		labelMap.put("Pause", "/~pause:");
		labelMap.put("Set Voice", "/~set-voice:");
		labelMap.put("Display String", "/~disp-string:");
		labelMap.put("Branch on User Input", "/~skip-button:0 Branch1\n/~skip-button:1 Branch2\n/~user-input\n");
		labelMap.put("Play Audio", "/~sound:");
		labelMap.put("Resume", "/~skip:mainBranch\n");
		labelMap.put("Branch 1", "/~Branch1\n");
		labelMap.put("Branch 2", "/~Branch2\n");
		labelMap.put("Main Branch", "");
		labelMap.put("Clear All", "/~disp-clearALL\n");
		labelMap.put("Clear Cell", "/~disp-clear-cell:");
		labelMap.put("Set Pins", "/~disp-cell-pins:");
		labelMap.put("Display Character", "/~disp-cell-char:");
		labelMap.put("Text-To-Speech", "");
		labelMap.put("Root", "Cells " + cellNumber + "\nButton " + buttonNumber + "\n");

	}

	public String toString() {
		return labelMap.get(nodeType) + content + "\n";

	}

	public boolean hasOneChild() {
		return (onlyChild != null && leftChild == null && rightChild == null);
	}

	public boolean hasNoChildren() {
		return (onlyChild == null && leftChild == null && rightChild == null);
	}

	public boolean hasTwoChildren() {
		return (onlyChild == null && leftChild != null && rightChild != null);
	}

	public String getContent() {
		return content;

	}

	public void setParent(ScenarioNode node) {
		this.onlyParent = node;
		this.twoParents.set(0,  null);
		this.twoParents.set(1, null);
	}

	public void setRight(ScenarioNode node) {
		this.rightChild = node;
	}

	public void setLeft(ScenarioNode node) {
		this.leftChild = node;
	}

	public void setOnlyChild(ScenarioNode node) {
		this.onlyChild = node;
	}

	public ScenarioNode getOnlyChild() {
		return this.onlyChild;

	}

	public ArrayList<ScenarioNode> getTwoChildren() {
		ArrayList<ScenarioNode> nodes = new ArrayList<ScenarioNode>(2);
		nodes.add(this.leftChild);
		nodes.add(this.rightChild);
		return nodes;

	}

	public ArrayList<ScenarioNode> getTwoParents() {

		if (twoParents.get(0) != null && twoParents.get(1) != null) {
			return twoParents;
		} else {
			throw new IllegalArgumentException("Node does not have two parents");
		}
	}

	public void setLeftParent(ScenarioNode node) {
		this.twoParents.set(0, node);
		this.onlyParent = null;
	}

	public void setRightParent(ScenarioNode node) {
		this.twoParents.set(1, node);
		this.onlyParent = null;
	}

	public void switchNodes(ScenarioNode node) {
		String tempType = this.nodeType;
		String tempContent = this.content;
		this.content = node.content;
		this.nodeType = node.nodeType;
		node.content = tempContent;
		node.nodeType = tempType;
	}

	public boolean hasOneParent() {
		return (twoParents.get(0) == null && twoParents.get(1) == null && this.onlyParent != null);
	}

	public void setCellNumber(int cellNumber) {
		if (this.nodeType == "Root") {
			this.cellNumber = cellNumber;
		} else {
			throw new IllegalStateException("Node not a Root node");
		}
	}

	public void setButtonNumber(int buttonNumber) {
		if (this.nodeType == "Root") {
			this.buttonNumber = buttonNumber;
		} else {
			throw new IllegalStateException("Node not a Root node");
		}
	}

	public ScenarioNode getOnlyParent() {
		return this.onlyParent;
	}

}
