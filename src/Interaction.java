import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Interaction extends JFrame {
    int width;
    int height;

    ArrayList<Vertex> vertices;
    ArrayList<edge> edges;

    public Interaction() { //Constructor
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        vertices = new ArrayList<Vertex>();
        edges = new ArrayList<edge>();
        width = 30;
        height = 30;
    }

    public Interaction(String name) { //Construct with label
	this.setTitle(name);
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	vertices = new ArrayList<Vertex>();
	edges = new ArrayList<edge>();
	width = 30;
	height = 30;
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
	int i,j;
	
	public edge(int ii, int jj) {
	    i = ii;
	    j = jj;	    
	}
    }
    
    public void addVertex(String name, int x, int y) { 
	//add a vertex at pixel (x,y)
	vertices.add(new Vertex(name,x,y));
	this.repaint();
    }
    public void addEdge(int i, int j) {
	//adds an edge between nodes i and j
	edges.add(new edge(i,j));
	this.repaint();
    }
    
    public void paint(Graphics g) { // draw the nodes and edges
	FontMetrics f = g.getFontMetrics();
	int vertexHeight = Math.max(height, f.getHeight());

	g.setColor(Color.black);
	for (edge e : edges) {
	    g.drawLine(vertices.get(e.i).x, vertices.get(e.i).y,
		     vertices.get(e.j).x, vertices.get(e.j).y);
	}

	for (Vertex n : vertices) {
	    int vertexWidth = Math.max(width, f.stringWidth(n.name)+width/2);
	    g.setColor(Color.white);
	    g.fillOval(n.x-vertexWidth/2, n.y-vertexHeight/2, 
		       vertexWidth, vertexHeight);
	    g.setColor(Color.black);
	    g.drawOval(n.x-vertexWidth/2, n.y-vertexHeight/2, 
		       vertexWidth, vertexHeight);
	    
	    g.drawString(n.name, n.x-f.stringWidth(n.name)/2,
			 n.y+f.getHeight()/2);
	}
    }


    //Here is the test for the points
    public static void main(String[] args) {
	Interaction frame = new Interaction("Maximal Clique Partioning");

	frame.setSize(400,300);
	
	frame.setVisible(true);

	frame.addVertex("a", 20,20);
	frame.addVertex("b", 70,70);
	frame.addVertex("c", 200,200);
	frame.addEdge(0,1);
	frame.addEdge(0,2);
    }}
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Interaction extends JFrame {
    int width;
    int height;
    ArrayList<Vertex> vertices;
    ArrayList<edge> edges;

    public Interaction() { //Constructor
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        vertices = new ArrayList<Vertex>();
        edges = new ArrayList<edge>();
        width = 30;
        height = 30;
    }

    public Interaction(String name) { //Construct with label
    	this.setTitle(name);
    	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	vertices = new ArrayList<Vertex>();
    	edges = new ArrayList<edge>();
    	width = 30;
    	height = 30;
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
    	int i,j;
    	public edge(int ii, int jj) {
	    i = ii;
	    j = jj;	    
    	}
    }
    
    public void addVertex(String name, int x, int y) { 
    	//add a vertex at pixel (x,y)
    	vertices.add(new Vertex(name,x,y));
    	this.repaint();
    }
    
    public void addEdge(int i, int j) {
    	//adds an edge between nodes i and j
    	edges.add(new edge(i,j));
    	this.repaint();
    }
    
   
    public void paint(Graphics g) { // draw the nodes and edges
    	FontMetrics f = g.getFontMetrics();
    	int vertexHeight = Math.max(height, f.getHeight());
    	g.setColor(Color.black);
    	for (edge e : edges) {
    		g.drawLine(vertices.get(e.i).x, vertices.get(e.i).y,
    		vertices.get(e.j).x, vertices.get(e.j).y);
    	}

    	for (Vertex n : vertices) {
    		int vertexWidth = Math.max(width, f.stringWidth(n.name)+width/2);
    		g.setColor(Color.white);
    		g.fillOval(n.x-vertexWidth/2, n.y-vertexHeight/2, vertexWidth, vertexHeight);
    		g.setColor(Color.black);
    		g.drawOval(n.x-vertexWidth/2, n.y-vertexHeight/2, vertexWidth, vertexHeight);    
    		g.drawString(n.name, n.x-f.stringWidth(n.name)/2,
			n.y+f.getHeight()/2);
    	}
    }


    //Here is the test for the points
    public static void main(String[] args) {
    	Interaction frame = new Interaction("Maximal Clique Partioning");
    	frame.setSize(1000,1000);
    	frame.setVisible(true);
    	frame.addVertex("a", 200,200);
    	frame.addVertex("b", 300,300);
    	frame.addVertex("c", 100,100);
    	frame.addEdge(0,1);
    	frame.addEdge(0,2);
    }
}