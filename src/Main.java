import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

/**
 * This is our Main class, where the main method exists.  
 */
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
					
				
					Node node = tree.pop();
					step3(node);//step 3 and 4 are combined
					//nodes are created inside step 3
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

	private void step3(Node node) { // the svn map is not updated !!! , all nodes will contain the original SharedV hashmap
		
		HashMap<String, ArrayList<String>> svn = node.svN;
		HashMap<String, ArrayList<String>> cn = node.cN;
		ArrayList<String> keys= node.keysN;
		
		ArrayList<String> common= sharedV.get(keys.get(0));
		
		for(int i = 0; i<common.size(); i++) {
			
			for(int j = 0; j<common.size();j++) {  // remove part
				cn.get(common.get(j)).remove(keys.get(0));
			}
			cn.get(common.get(i)).add(keys.get(0)); // add 2 to Ci
			keys.remove(0);
			Node tempN = new Node(cn,svn,keys);
			step4(tempN);
			if(step5()) {
				tree.push(tempN);
			}
			else {
				break;
			}
		}
	}
	
	
	private void step4(Node node) {
		HashMap<String, ArrayList<String>> svn = node.svN;
		HashMap<String, ArrayList<String>> cn = node.cN;
		ArrayList<String> keys= node.keysN;
		String c = checksubset(cn);
		
		if(c!=null) {
			for(int i = 0; i<keys.size();i++) {
				if (svn.get(keys.get(i)).contains(c)) {
					svn.get(keys.get(i)).remove(c);
					if(svn.get(keys.get(i)).size()<=1) {
						svn.remove(keys.get(i));
						keys.remove(i);
					}
				}
			}
			
			cn.remove(c);
			
		}

	}
	
	private String checksubset(HashMap<String, ArrayList<String>> cn) {
		// TODO Auto-generated method stub
		String[]cnkeys = (String[]) cn.keySet().toArray();
		for(int i = 0 ; i< cnkeys.length ; i++) {
			for(int j = cnkeys.length-1; j >i ; j--) {
				if(cn.get(cnkeys[i]).containsAll(cn.get(cnkeys[j]))) {
					return cnkeys[j];
				}
			}
		}
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
