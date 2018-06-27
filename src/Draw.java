import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
/*The class Draw draws the specific partitions based on what is read in from the file.
 * It contains methods for a next and previous button, border layout, and the algorithim in order to draw
 * the graph into a circle given any set of vertices added. 
 */
public class Draw {

	static int currentPar = 0;
	static int windowSize = 700;
	static Interaction frame = new Interaction("Maximal Clique Partioning", windowSize);
	JPanel bottom = new JPanel(new BorderLayout());
	JButton next = new JButton("Next");
	JButton back = new JButton("Previous");
	ArrayList<Edge> mainEdges;
	static ArrayList<ArrayList<Edge>> parEdges = new ArrayList<ArrayList<Edge>>();
	static ArrayList<Map<String, ArrayList<String>>> partitions = new ArrayList<Map<String, ArrayList<String>>>();

	/*This method pulls array lists necessary to draw the partions and edges from
	 * Edge.java, and also calls on the initializing frame.
	 */
	public Draw(ArrayList<Edge> me, ArrayList<ArrayList<Edge>> pr,
			ArrayList<Map<String, ArrayList<String>>> p) {
		this.partitions = p;
		this.mainEdges = me;
		this.parEdges = pr;

		initFrame();
	}

	/*This method adds the border layout to the page in order to align the buttons at the bottom,
	 * which in turn will send information regarding which button is clicked to the other classes to determine
	 * the next partion to be drawn.
	 * 
	 */
	public void initFrame() {

		frame.setSize(windowSize, windowSize);
		frame.setLayout(new BorderLayout());
		frame.add(bottom, BorderLayout.PAGE_END);
		next.setBounds(590, 560, 80, 60);
		next.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.repaint();
				System.out.println("GET ME NEXT");
				nextButton();
			}
		});
		bottom.add(next, BorderLayout.LINE_END);
		back.setBounds(60, 560, 80, 60);
		bottom.add(back, BorderLayout.LINE_START);
		back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.print("GET ME BACK");
				prevButton();
			}
		});
		
		draw(partitions.get(0), mainEdges); // draw original graph

	}
/*This method determines the next partion to be drawn by going through the partions
 * array list
 */
	public static void nextButton() {
		if (currentPar < partitions.size()-1) {
			currentPar++;
		}
		frame.clearFrame();
		frame.repaint();
		draw(partitions.get(currentPar), parEdges.get(currentPar));
	}
	/*This method determines the previous partion to be drawn by going through the partions
	 * array list
	 */
	public static void prevButton() {
		if (currentPar > 0) {
			currentPar--;
		}
		draw(partitions.get(currentPar), parEdges.get(currentPar));

	}
/*This method determines the amount of vertices to be drawn based on the input file.
 * It positions the vertices based on a unit circle, and draws the edges to attach
 * to the appropriate vertices.
 */
	public static void draw(Map<String, ArrayList<String>> map, ArrayList<Edge> edges) {

		HashMap<String, Integer> verKeys = new HashMap<String, Integer>();
		ArrayList<String> Ver = new ArrayList<String>();
		Object[] pKeys = map.keySet().toArray();
		for (int i = 0; i < map.keySet().size(); i++) {
			Ver.addAll(map.get(pKeys[i]));
		}	

		int verNums = Ver.size(); // number of vertices
		double theta = 360 / verNums;
		double degree = Math.toRadians(theta);
		int radius = (windowSize - 100) / 2;
		int cx = windowSize / 2;
		int cy = windowSize / 2;

		// adding the vertices
		for (int i = 0; i < verNums; i++) {
			int x = (int) (cx + (radius * Math.cos(degree * i)));
			int y = (int) (cy + (radius * Math.sin(degree * i)));
			frame.addVertex("" + Ver.get(i), x, y);
			verKeys.put(Ver.get(i), i);

		}

		// adding the edges
		for (int i = 0; i < edges.size(); i++) {
			Edge e = edges.get(i);
			String v1 = e.vertex1;
			String v2 = e.vertex2;

			frame.addEdge(verKeys.get(v1), verKeys.get(v2));
			System.out.println(verKeys.get(v1) + " : " + verKeys.get(v2));

		}
	}
}
