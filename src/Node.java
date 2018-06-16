import java.util.ArrayList;
import java.util.HashMap;

class Node {
	
	
	HashMap<String, ArrayList<String>> cN;
	HashMap<String, ArrayList<String>> svN;
	ArrayList<String> keysN;
	
	
	public Node (HashMap<String, ArrayList<String>> c , HashMap<String, ArrayList<String>> s, ArrayList<String>k ) {
		this.svN = s;
		this.cN = c ;
		this.keysN = k;
	}

}