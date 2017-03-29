package eecs2311.project;

import java.io.File;
import java.util.ArrayList;

public class ScenarioGraph {

	ScenarioNode root;
	ScenarioNode current;
	StringBuilder scenario;
	int size;

	public ScenarioGraph() {
		root = null;
		scenario = null;
		size = 0;
	}

	public ScenarioGraph(ScenarioNode node) {
		root = node;
		size = 1;
		scenario = null;

	}

	public ScenarioGraph(File file) {
		// not implemented
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

	}
	
	public void addTwoToCurrent(ScenarioNode leftNode, ScenarioNode rightNode)
	{
		//if it has no children
		if (current.hasNoChildren())
		{
			current.setLeft(leftNode);
			current.setRight(rightNode);
			leftNode.setParent(current);
			rightNode.setParent(current);
			
		}else if (current.hasOneChild())
		{
			ScenarioNode temp = current.getOnlyChild();
			current.setOnlyChild(null);
			current.setLeft(leftNode);
			current.setRight(rightNode);
			leftNode.setOnlyChild(temp);
			rightNode.setOnlyChild(temp);
			//HERE
			
		}
		//if it has one child
		//if it has two children
	}
	
	

}
