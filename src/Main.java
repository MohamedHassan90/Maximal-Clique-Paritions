import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

	@SuppressWarnings("serial")
	public static void main(String[] args) {
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
		Maximal m = new Maximal(input); // Maximal
		ArrayList<Edge> mainEdges = m.generateEdges(m.cliques);
		ArrayList<ArrayList<Edge>> parEdges = new ArrayList<ArrayList<Edge>>(); // arraylist containing all edges for
																				// each partition
		for (int i = 0; i < m.partitions.size(); i++) {
			parEdges.add(m.generateEdges(m.partitions.get(i)));
		}

		Draw d = new Draw(mainEdges, parEdges, m.partitions); // GUI draw all maximal partitions

		// User Interaction
		boolean exit = false;
		System.out.println("The current edges are : ");
		for (int i = 0; i < mainEdges.size(); i++) {
			if (mainEdges.get(i).isEnabled) {
				System.out.print(" [" + mainEdges.get(i).vertex1 + "," + mainEdges.get(i).vertex2 + "] ");
			}
		}

		System.out.println("");
		System.out.println("write '-1' to exit");
		Scanner scan = new Scanner(System.in); // getting input from user to select edges
		while (!exit) {
			System.out.println("Enter vertex 1 : ");
			String v1 = scan.nextLine();
			if (v1.equals("-1")) {
				exit = true;
				break;
			}
			System.out.println("Enter vertex 2 : ");
			String v2 = scan.nextLine();
			if (v2.equals("-1")) {
				exit = true;
				break;
			}
			Edge e1 = new Edge(v1, v2);
			System.out.print("Edge [" + v1 + "," + v2 + "] is selected , ");
			Stage3 s1 = new Stage3(mainEdges, e1);
		}
		if (exit) {
			System.out.println("System terminated");
		}

	}
}
