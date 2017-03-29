package eecs2311.project;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.mxgraph.layout.mxCompactTreeLayout;
import com.mxgraph.layout.mxGraphLayout;
import com.mxgraph.layout.hierarchical.mxHierarchicalLayout;
import com.mxgraph.model.mxCell;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxRectangle;
import com.mxgraph.view.mxGraph;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;

public class DummyGraph {

	public static void main(String args[]) {
		JFrame frame = new JFrame();
		frame.setSize(600, 600);
		frame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(70, 28, 134, 800);
		panel.setLayout(new BorderLayout());
		frame.getContentPane().add(panel);
		frame.setVisible(true);

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
		frame.repaint();
		

	}
}
