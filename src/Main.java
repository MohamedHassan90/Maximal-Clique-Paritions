import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

/**
 * This is our Main class, where the main method exists.  
 */
class Node {
	
	
	HashMap<String, ArrayList<String>> cN;
	ArrayList<String> svN;
	
	
	public Node (HashMap<String, ArrayList<String>> c , ArrayList<String> s ) {
		this.svN = s;
		this.cN = c ;
	}

}

public class Main {
	
	public  Stack<Node> tree = new Stack<Node>();
	public HashMap<String, ArrayList<String>> cliques;
	public HashMap<String, ArrayList<String>> sharedV= (HashMap<String, ArrayList<String>>) sharedValue().get(0);
	public HashMap<String, ArrayList<String>> fc= forbiddenConfig();
	public ArrayList<String> keys = (ArrayList<String>) sharedValue().get(1);

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	//new HashMap<String, ArrayList<String>>();
	@SuppressWarnings("unchecked")
	public void Maximal () {
	
		//define the root node
		// add it to the tree
		
			while (!tree.isEmpty()) {
				
				if(step2(tree.peek())) {
					
					//node now 
					//svn,cn
					step3(tree.pop());//svn , cn
					step4(); //svn,cn
					//create nodes
				}else {
					//print the cliques partitions p1,p2,..
				}
				
			}
	
	}

	private boolean step2(Node node) { // false if empty
		if(node.svN.isEmpty()) {
			return false;
		}
		return true;
	}

	private void step3(Node node) {
		
		ArrayList<String> svn = node.svN;
		HashMap<String, ArrayList<String>> cn = node.cN;
		
		ArrayList<String> common= sharedV.get(svn.get(0));
		
		for(int i = 0; i<common.size(); i++) {
			
			for(int j = 0; j<common.size();j++) {  // remove part
				cn.get(common.get(j)).remove(svn.get(0));
			}
			cn.get(common.get(i)).add(svn.get(0)); // add 2 to Ci
			svn.remove(0);
			Node tempN = new Node(cn,svn);
			tree.push(tempN);
		}
	}
	
	
	private void step4(Node node) {
		string c = checksubset(node.cN);
		if(c!=null) {
			for(int i = 0; i<node.svN.size();i++) {
				if (sharedV.get(node.svN.get(i)).contains(c)) {
					
				}
			}
		}
		
		
	}
	
	private String checksubset(HashMap<String, ArrayList<String>> cN) {
		// TODO Auto-generated method stub
		return null;
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
