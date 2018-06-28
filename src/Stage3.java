import java.util.ArrayList;

/*
 * This Class "Stage3" is used to execute the Algorithm/calculations when each time the User choose an edge in the Original Graph
 */
public class Stage3 {
	ArrayList<Edge> edges; //Main Edges of the original Graph 
	ArrayList<String> ll1 = new ArrayList<String>(); // List 1
	ArrayList<String> ll2 = new ArrayList<String>(); // List 2
	ArrayList<String> intersection = new ArrayList<String>(); //Intersection between the 2 Lists above. 
	String v1; //Vertex 1 of the User Selected Edge
	String v2; //Vertex 2 of the User Selected Edge

	/*
	 * The Constructor where also the steps of the Stage 3 is executed inside it.
	 * It takes the original graph as MainEdges and the User's Chosen Edge. 
	 */
	public Stage3(ArrayList<Edge> e, Edge edge) {
		this.edges = e;
		v1 = edge.vertex1; 
		v2 = edge.vertex2;

		if (isEnabled(edge)) {
			chooseEdge(edge); //Step1
			disable(); //Step2
			printEdges(edges); //Step3
		} else { 
			//Print the current Edges
			System.out.println("Chosen Edge [" + (edge.vertex1) + "," + (edge.vertex2) + "] is disabled");
			printEdges(edges);
		}
	}

	/*
	 * This method checks if the Users Choosn edge is Enabled or Not.  
	 */
	private boolean isEnabled(Edge edge) {
		for (int i = 0; i < edges.size(); i++) {
			Edge current = edges.get(i);

			if ((current.vertex1.equals(edge.vertex1) && current.vertex2.equals(edge.vertex2))
					|| (current.vertex1.equals(edge.vertex2) && current.vertex2.equals(edge.vertex1))) {

				return current.isEnabled;
			}

		}
		return false;
	}

	/*
	 * This method is used to Print all the Enabled edges in the Current Graph.  
	 */
	public void printEdges(ArrayList<Edge> edges) {
		System.out.println("The current edges are : ");
		for (int i = 0; i < edges.size(); i++) {
			if (edges.get(i).isEnabled) {
				System.out.print(" [" + edges.get(i).vertex1 + "," + edges.get(i).vertex2 + "] ");
			}
		}
		System.out.println("");
	}

	/*
	 * ChooseEdge Method is used to Create L1 and L2, then call Intersection method to the intersected vertices, 
	 * then remove the intersected Vertices from each list. 
	 * L1 is a List of Vertices connected to V1 
	 * L2 is a list of Vertices connected to V2  
	 */
	public void chooseEdge(Edge e) {
		//Loop on all existing Edges, Check if it contains v1 or v2, if yes -> Add the Other vertix.  
		for (int i = 0; i < edges.size(); i++) {
			Edge temp = edges.get(i);
			if (temp.isEnabled) {
				if (v1.equals(temp.vertex1)) { 
					ll1.add(temp.vertex2);
				}
				if (v1.equals(temp.vertex2)) {
					ll1.add(temp.vertex1);
				}
				if (v2.equals(temp.vertex1)) {
					ll2.add(temp.vertex2);
				}
				if (v2.equals(temp.vertex2)) {
					ll2.add(temp.vertex1);
				}
			}
		}
		ll1.remove(e.vertex2);
		ll2.remove(e.vertex1);
		intersection = intersection(ll1, ll2); //Then Compute the Intersection between L1 and L2 
		ll1.removeAll(intersection); // Remove the intersected Vs from L1
		ll2.removeAll(intersection); // Remove the intersected Vs from L2
		// System.out.println(ll1);
		// System.out.println(ll2);
	}

	/*
	 * Intersection Method gets 2 Lists as an input and return (arrayList) of the Intersected vertices between them.  
	 */
	public ArrayList<String> intersection(ArrayList<String> a1, ArrayList<String> a2) {
		ArrayList<String> result = new ArrayList<String>();
		for (int i = 0; i < a1.size(); i++) {
			if (a2.contains(a1.get(i))) {
				result.add(a1.get(i));
			}
		}
		return result;
	}

	/*
	 * disable() method Loops 2 times on the edges (one time for L1 , and second time for L2) 
	 * If Customer's chosen Edge was (V1,V2) and L1=[V3,V4], L2=[V5]
	 * 1st Time: The disabled Edges will be (V1, vertices of L1): (V1,V3), (V1,V4).   
	 * 2nd Time: The Disabled Edges will be (V2, vertices of L2): (V2,V5). 
	 */
	public void disable() {
		
		// ll1
		for (int i = 0; i < edges.size(); i++) {
			if (edges.get(i).contains(v1)) {
				if (ll1.contains(edges.get(i).vertex1) || ll1.contains(edges.get(i).vertex2)) {
					edges.get(i).isEnabled = false;
				}
			}
		}

		// ll2
		for (int i = 0; i < edges.size(); i++) {
			if (edges.get(i).contains(v2)) {
				if (ll2.contains(edges.get(i).vertex1) || ll2.contains(edges.get(i).vertex2)) {
					edges.get(i).isEnabled = false;
				}
			}
		}

	}

}
