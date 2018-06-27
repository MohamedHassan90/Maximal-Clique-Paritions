import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {

	@SuppressWarnings("serial")
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String input = "";

		FileReader fr = null;
		try {
			fr = new FileReader("input.txt");
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		BufferedReader br = new BufferedReader(fr);
		try {
			input = br.readLine();

		} catch (IOException e1) {
			e1.printStackTrace();
		}
		Maximal m = new Maximal(input);
		ArrayList<Edge> mainEdges = m.generateEdges(m.cliques);
		ArrayList<ArrayList<Edge>> parEdges = new ArrayList<ArrayList<Edge>>(); // arraylist containing all edges for
																				// each partition
		for (int i = 0; i < m.partitions.size(); i++) {
			parEdges.add(m.generateEdges(m.partitions.get(i)));
		}
		 //Interaction frame = new Interaction("Maximal Graphs",700);

		 Draw d = new Draw(mainEdges, parEdges, m.partitions);

//		 Interaction.draw( m.partitions.get(2), parEdges);

		Edge e1 = new Edge("4", "6");
		Stage3 s1 = new Stage3(mainEdges, e1);
		Edge e2 = new Edge("4", "5");
		Stage3 s2 = new Stage3(mainEdges, e2);
		Edge e3 = new Edge("6", "7");
		Stage3 s3 = new Stage3(mainEdges, e3);
		 Edge e4 = new Edge("5","7");
		 Stage3 s4 = new Stage3(mainEdges, e4);
		// disabled nodes should not do modifications !!! needs to be fixed
		 //Fixed? ^

		// s.printEdges();
		// s.chooseEdge(s.edges.get(0));
		// ArrayList<String>x = new ArrayList<String>() ;
		// x.add("1");
		// x.add("2");
		// x.add("3");
		//
		// ArrayList<String>y = new ArrayList<String>() ;
		// y.add("1");
		// y.add("2");
		// y.add("6");

		// System.out.println("result : "+s.intersection(x,y));
	}
}
