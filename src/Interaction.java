import java.util.*;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.geom.Line2D;
public class Interaction extends JFrame implements ActionListener {
	//static JFrame frame;
	JFrame frame = new JFrame();
	
    int width;
    int height;
    ArrayList<Vertex> vertices;
    ArrayList<edge> edges;
    
    public Interaction() { //Constructor
        vertices = new ArrayList<Vertex>();
        edges = new ArrayList<edge>();
        width = 30;
        height = 30;
    }
  //Constructor with label
    public Interaction(String name) { 
	this.setTitle(name);
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
	//adds an edge between vertices i and j
	edges.add(new edge(i,j));
	this.repaint();
    }
    
    public void paint(Graphics g) { // draw the nodes and edges
	FontMetrics f = g.getFontMetrics();
	Graphics2D g2 = (Graphics2D) g;

	//vertex height
	int vertexHeight = Math.max(height, f.getHeight());
	//Drawing edges
	g2.setStroke(new BasicStroke(4));
	g2.setColor(Color.black);
	for (edge e : edges) {
	    g2.drawLine(vertices.get(e.i).x, vertices.get(e.i).y,
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
    
    
  
	public static void draw (Map<String, ArrayList<String>> map , ArrayList<Edge> edges) {
    	HashMap<String,Integer> verKeys = new HashMap<String,Integer>();
    	ArrayList<String> Ver= new ArrayList<String>();
    	Object[] pKeys = map.keySet().toArray();
    	for(int i =0 ; i<map.keySet().size();i++) {
    		Ver.addAll(map.get(pKeys[i]));
    	}
    	
    	int windowSize = 700;

    	Interaction frame = new Interaction("Maximal Clique Partioning");
    	frame.setSize(windowSize,windowSize);	
    	
    	//Buttons
    	
    	//Next
  
    	JButton next = new JButton("Next");	
    	next.setBounds(590, 560, 80, 60);   
    	frame.add(next);   	
    	next.addActionListener((ActionListener) next); 	     	
    	
    	//Back
    	JButton back = new JButton("Previous");
    back.setBounds(60, 560, 80, 60);  	
    frame.add(back); 	
    back.addActionListener((ActionListener) back);  
    
    	
   
    	
    int 	verNums = Ver.size(); //number of vertices
    	double theta = 360/verNums ;
    	double degree = Math.toRadians(theta);
    	int radius = (windowSize-100)/2;
    	int cx = windowSize/2;
    	int cy = windowSize/2;
    	

    	//adding the vertices 
    	for (int i =0; i< verNums; i++) {
    		int x = (int) (cx+(radius*Math.cos(degree*i)));
    		int y = (int) (cy+(radius*Math.sin(degree*i)));
    		frame.addVertex(""+Ver.get(i), x,y);
    		verKeys.put(Ver.get(i),i);
    	
    	}
    	
    	//adding the edges
    	for (int i =0; i< edges.size(); i++) {
    		Edge e = edges.get(i);
    		String v1= e.vertex1;
    		String v2= e.vertex2;
    		
        	frame.addEdge(verKeys.get(v1),verKeys.get(v2));
        	System.out.println(verKeys.get(v1)+" : "+verKeys.get(v2));

    	}

    	System.out.println(verKeys);
    frame.setLayout(null); 	  
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setVisible(true);
    	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// Code that will need to react to the buttons
		
	}
   

}