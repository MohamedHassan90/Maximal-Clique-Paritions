import java.util.*;
import javax.swing.JButton;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.geom.Line2D;

/*The interaction class creates the GUI frame, and all the requirements for the GUI frame, including	 
   * methods to generate and calculate the vertices, and edges. Mouse click events to determine 
   * when a user wishes to interact with the GUI.It also includes methods to 
   * change the color of the edge and pass information of which edge was chosen
   *  once it is clicked by the user.
 */
public class Interaction extends JFrame implements MouseListener, ActionListener {
	int width;
	int height;
	int windowSize;
	int m,n;
	ArrayList<Vertex> vertices;
	ArrayList<edge> edges;
	Graphics2D g2;
	
	
	/* This method adds a mouse listener, sets the foundation for the GUI window, and other
	 * parameters crucial to begin creating a GUI. 
	 */
	public Interaction(String name, int ws) { // Constructor
		addMouseListener(this);
		this.setTitle(name);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		vertices = new ArrayList<Vertex>();
		edges = new ArrayList<edge>();
		width = 30;
		height = 30;
		this.windowSize = ws;
		this.setSize(windowSize, windowSize);
		this.setVisible(true);
		//Makes sure everything is within the window
		this.pack();
	}
	/*This method creates integer values for the vertices and 
	 * initializes a string for the vertices
	 */
	class Vertex {
		int x, y;
		String name;

		public Vertex(String myName, int myX, int myY) {
			x = myX;
			y = myY;
			name = myName;
		}
	}
	
	/*This method creates integer values for the edges, the number of the vertex,
	 * as well as sets the unclicked color to black.
	 */
	class edge {
		int i, j;
		boolean isRed = false;

		public edge(int ii, int jj) {
			i = ii;
			j = jj;
		}
	}
	
	/*This method clears the frame once the next or previous button is clicked.
	 */
	public void clearFrame() {
		this.vertices.clear();
		this.edges.clear();
	}
	
/*This method adds the vertices at specific coordinates based on the draw method
 * found in Draw.java
 */
	public void addVertex(String name, int x, int y) {
		// add a vertex at pixel (x,y)
		vertices.add(new Vertex(name, x, y));
		this.repaint();
	}

	/*This method connects the edges to the vertices at specific coordinates based on the draw method
	 * found in Draw.java
	 */
	public void addEdge(int i, int j) {
		// adds an edge between vertices i and j
		edges.add(new edge(i, j));
		this.repaint();
	}

	/*This method draws the vertices and edges, the for loop will determine
	 * whether the edge drawn should be red or black based on whether the user has clicked
	 * on it. The vertices are drawn based on the formula.
	 */
	public void paint(Graphics g) { // draw the nodes and edges
		FontMetrics f = g.getFontMetrics();
		g2 = (Graphics2D) g;
		int vertexHeight = Math.max(height, f.getHeight());
		g2.setStroke(new BasicStroke(4));
		g2.setColor(Color.black);
		for (edge e: edges) {
			if(onLine(vertices.get(e.i).x, vertices.get(e.i).y, vertices.get(e.j).x, vertices.get(e.j).y, m, n))
				e.isRed = true;
			if(e.isRed)
				g2.setColor(Color.red);
			else
				g2.setColor(Color.black);
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
	/*This method determines whether or not the user has clicked on a certain edge.
	 * The if statement parameter allows for the user to click within a certain range
	 * of the line so that the click doesn't have to be exactly on the line.
	 */
	private boolean onLine(double x1, double y1, double x2, double y2, double x, double y) {
		double m = (y2-y1)/(x2-x1);
		double b = y1-m*x1;
		double vary = m*x+b;
		if(Math.abs(x2-x1) > 5) {
			if((Math.abs(y-vary) < 20) && ((x1<=x && x<=x2) || (x2<=x && x<=x2))) {
				return true;
			}
		}
		else {
			if((Math.abs(x-x1) <10) && ((y1>=y && y>=y2) || (y2>=y && y>=y1)))
					return true;
		}
		return false;
	}
	@Override
	/*The following empty methods had to be drawn in order for it not to throw an error.
	 */
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
	
		}

	@Override
	/*This method obtains the click location of the user.
	 */
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		m = e.getX();
		n = e.getY();
		repaint();
	}
		

	}

