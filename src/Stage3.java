import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Stage3 {

	HashMap<String, ArrayList<String>> cliques;
	ArrayList<Edge> edges;
	ArrayList<String> intersection = new ArrayList<String>();
	String v1;
	String v2;
	ArrayList<String> ll1 = new ArrayList<String>();
	ArrayList<String> ll2 = new ArrayList<String>();

	public Stage3(ArrayList<Edge> e, Edge edge) {
		this.edges = e;
		v1 = edge.vertex1;
		v2 = edge.vertex2;

		chooseEdge(edge);
		disable();

		printEdges();
	}

	public void printEdges() {
		for (int i = 0; i < edges.size(); i++) {
			if (edges.get(i).isEnabled) {
				System.out.print(" [" + edges.get(i).vertex1 + "," + edges.get(i).vertex2 + "] ");
			}
		}
		System.out.println("");
	}

	public void chooseEdge(Edge e) {

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
		intersection = intersection(ll1, ll2);
		ll1.removeAll(intersection);
		ll2.removeAll(intersection);
		// System.out.println(ll1);
		// System.out.println(ll2);
	}

	public ArrayList<String> intersection(ArrayList<String> a1, ArrayList<String> a2) {
		ArrayList<String> result = new ArrayList<String>();
		for (int i = 0; i < a1.size(); i++) {
			if (a2.contains(a1.get(i))) {
				result.add(a1.get(i));
			}
		}
		return result;
	}

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
