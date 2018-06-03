import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

/**
 * This is our Main class, where the main method exists.  
 */
class node {
	
	
	HashMap<String, ArrayList<String>> cN;
	ArrayList<String> svN;
	
	
	public node (HashMap<String, ArrayList<String>> c , ArrayList<String> s ) {
		this.svN = s;
		this.cliques = c ;
	}

}

public class Main {
	
	public  Stack<node> tree = new Stack<node>();
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	//new HashMap<String, ArrayList<String>>();
	@SuppressWarnings("unchecked")
	public void Maximal () {
		HashMap<String, ArrayList<String>> cliques;
		HashMap<String, ArrayList<String>> sharedV= (HashMap<String, ArrayList<String>>) sharedValue().get(0);
		HashMap<String, ArrayList<String>> fc= forbiddenConfig();
		ArrayList<String> keys = (ArrayList<String>) sharedValue().get(1);
		
				
		keys.add("");
		//step 2
		if(!isSharedVempty(sharedV)) { 
			
			//step3
			
			for(int i = 0 ; i< sharedV.get(keys.get(0)).size() ;i++) {
				
				tree.push(new Node());

			}
			
		}
	
	}



	private boolean isSharedVempty(HashMap<String, ArrayList<String>> sv) {
		// TODO Auto-generated method stub
		if(sv.isEmpty()) {
			return true;
		}
		//print the cliques 
		return false;
	}

	private HashMap<String, ArrayList<String>> forbiddenConfig() {
		// TODO Auto-generated method stub
		return null;
	}

	private ArrayList<Object> sharedValue() {
		// TODO Auto-generated method stub
		
		return null;
	}

}
