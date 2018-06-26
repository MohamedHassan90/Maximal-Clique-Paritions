import java.util.*;

import javax.swing.JButton;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.geom.Line2D;

public class Interaction extends JFrame implements MouseListener, ActionListener {
	int width;
	int height;
	int windowSize;
	ArrayList<Vertex> vertices;
	ArrayList<edge> edges;

	public Interaction(String name, int ws) { // Constructor

		this.setTitle(name);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		vertices = new ArrayList<Vertex>();
		edges = new ArrayList<edge>();
		width = 30;
		height = 30;
		this.windowSize = ws;
		this.setSize(windowSize, windowSize);
		this.setVisible(true);

	}

	class Vertex {
		int x, y;
		String name;

		public Vertex(String myName, int myX, int myY) {
			x = myX;
			y = myY;
			name = myName;
		}
	}

	class edge {
		int i, j;

		public edge(int ii, int jj) {
			i = ii;
			j = jj;
		}
	}
	
	public void clearFrame() {
		this.vertices.clear();
		this.edges.clear();
	}

	public void addVertex(String name, int x, int y) {
		// add a vertex at pixel (x,y)
		vertices.add(new Vertex(name, x, y));
		this.repaint();
	}

	public void addEdge(int i, int j) {
		// adds an edge between vertices i and j
		edges.add(new edge(i, j));
		this.repaint();
	}

	public void paint(Graphics g) { // draw the nodes and edges
		FontMetrics f = g.getFontMetrics();
		Graphics2D g2 = (Graphics2D) g;
		int vertexHeight = Math.max(height, f.getHeight());
		addMouseListener(this);
		g2.setStroke(new BasicStroke(4));
		g2.setColor(Color.black);
		for (edge e : edges) {
			g2.drawLine(vertices.get(e.i).x, vertices.get(e.i).y, vertices.get(e.j).x, vertices.get(e.j).y);
		}

		for (Vertex n : vertices) {
			int vertexWidth = Math.max(width, f.stringWidth(n.name) + width / 2);
			g.setColor(Color.white);
			g.fillOval(n.x - vertexWidth / 2, n.y - vertexHeight / 2, vertexWidth, vertexHeight);
			g.setColor(Color.black);
			g.drawOval(n.x - vertexWidth / 2, n.y - vertexHeight / 2, vertexWidth, vertexHeight);

			g.drawString(n.name, n.x - f.stringWidth(n.name) / 2, n.y + f.getHeight() / 2);
		}
	}

	// The actual graph part

	@Override
	public void mouseClicked(MouseEvent e) {
		int m = e.getX();
		int n = e.getY();
	}

	private void setEdgeColor(Color red) {

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	}

}