import java.util.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.geom.Line2D;
public class Interaction extends JFrame implements MouseListener {
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
	//adds an edge between vertices i and j
	edges.add(new edge(i,j));
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
    	frame.setVisible(true);
    	
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
    	
    }
    //The actual graph part

	@Override
	public void mouseClicked(MouseEvent e) {
		  int m = e.getX();
		  int n = e.getY();	  
	}
	
	
//	//Partion one
//	
//	frame.addVertex("1", 330,70);//7
//	frame.addVertex("2", 380,40); //8
//	frame.addVertex("3", 380,100); //9
//	frame.addVertex("4", 430,70); //10
//	frame.addVertex("5", 480,40);//11
//	frame.addVertex("6", 480,100);//12
//	frame.addVertex("7", 530,70);//13
//	frame.addEdge(7, 8);
//	frame.addEdge(7, 9);
//	frame.addEdge(8, 9);
//	frame.addEdge(10, 11);
//	frame.addEdge(10, 12);
//	frame.addEdge(11, 12);
//	
//	//Partion two
//	frame.addVertex("1", 630,70);//14
//	frame.addVertex("2", 680,40); //15
//	frame.addVertex("3", 680,100); //16
//	frame.addVertex("4", 730,70); //17
//	frame.addVertex("5", 780,40);//18
//	frame.addVertex("6", 780,100);//19
//	frame.addVertex("7", 830,70);//20
//	frame.addEdge(14, 15);
//	frame.addEdge(14, 16);
//	frame.addEdge(15, 16);
//	frame.addEdge(17, 18);
//	frame.addEdge(19, 20);
//	
//	//Partion three
//	frame.addVertex("1", 30,270);//21
//	frame.addVertex("2", 80,240); //22
//	frame.addVertex("3", 80,300); //23
//	frame.addVertex("4", 130,270); //24
//	frame.addVertex("5", 180,240);//25
//	frame.addVertex("6", 180,300);//26
//	frame.addVertex("7", 230,270);//27
//	frame.addEdge(21,22);
//	frame.addEdge(21,23);
//	frame.addEdge(22,23);
//	frame.addEdge(25, 27);
//	frame.addEdge(24, 26);
//
//	//Partion four
//	frame.addVertex("1", 30,470);//28
//	frame.addVertex("2", 80,440); //29
//	frame.addVertex("3", 80,500); //30
//	frame.addVertex("4", 130,470); //31
//	frame.addVertex("5", 180,500);//32
//	frame.addVertex("6", 180,440);//33
//	frame.addVertex("7", 230,470);//34
//	frame.addEdge(28,29);
//	frame.addEdge(28,30);
//	frame.addEdge(29,30);
//	frame.addEdge(32, 33);
//	frame.addEdge(33, 34);
//	frame.addEdge(32, 33);
//	frame.addEdge(32, 34);
//	
//	//Partion five
//	frame.addVertex("1", 330,270);//35
//	frame.addVertex("2", 380,240); //36
//	frame.addVertex("3", 380,300); //37
//	frame.addVertex("4", 430,270); //38
//	frame.addVertex("5", 480,240);//39
//	frame.addVertex("6", 480,300);//40
//	frame.addVertex("7", 530,270);//41
//	frame.addEdge(35, 36);
//	frame.addEdge(37, 38);
//	frame.addEdge(39, 40);
//	frame.addEdge(39, 41);
//	frame.addEdge(40, 41);	
//	
//	//Partion six
//	frame.addVertex("1", 630,270);//42
//	frame.addVertex("2", 680,240); //43
//	frame.addVertex("3", 680,300); //44
//	frame.addVertex("4", 730,270); //45
//	frame.addVertex("5", 780,240);//46
//	frame.addVertex("6", 780,300);//47
//	frame.addVertex("7", 830,270);//48
//	frame.addEdge(42, 44);
//	frame.addEdge(43, 45);
//	frame.addEdge(46, 47);
//	frame.addEdge(47, 48);
//	frame.addEdge(46, 48);
//	
//	//Partion seven
//	frame.addVertex("1", 430,470);//49
//	frame.addVertex("2", 480,440); //50
//	frame.addVertex("3", 480,500); //51
//	frame.addVertex("4", 530,470); //52
//	frame.addVertex("5", 580,440);//53
//	frame.addVertex("6", 580,500);//54
//	frame.addVertex("7", 630,470);//55
//	frame.addEdge(50, 51);
//	frame.addEdge(50, 52);
//	frame.addEdge(51, 52);
//	frame.addEdge(53, 55);
//	frame.addEdge(53, 54);
//	frame.addEdge(54, 55);
//    }


	private void setEdgeColor(Color red) {
	
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
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
}