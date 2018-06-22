import java.awt.Frame;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;

public class Draw{

	int currentPar = 0;
	Interaction frame;
	ArrayList<Edge> mainEdges;
	ArrayList<ArrayList<Edge>> parEdges = new ArrayList<ArrayList<Edge>>();
	ArrayList<Map<String, ArrayList<String>>> partitions = new ArrayList<Map<String, ArrayList<String>>> ();


	public Draw(Interaction f,ArrayList<Edge> me,ArrayList<ArrayList<Edge>> pr, ArrayList<Map<String,ArrayList<String>>> p) {
		this.frame = f;
		this.partitions = p;
		this.mainEdges = me;
		this.parEdges = pr;

		initFrame();
	}

	public void initFrame() { 

		draw(partitions.get(0), mainEdges); // draw original graph

	}

	public void nextButton() {
		if (currentPar < partitions.size()) {
			frame.edges.clear();
			frame.vertices.clear();
			frame.repaint();
			System.out.println("inside");
			// draw(partitions.get(currentPar), parEdges.get(currentPar));
			currentPar++;
		}
	}

	public void prevButton() {
		if (currentPar >= 0) {
			draw(partitions.get(currentPar), parEdges.get(currentPar));
			currentPar--;
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

    	Interaction frame = new Interaction("Maximal Clique Partioning", windowSize);
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

	}
}
