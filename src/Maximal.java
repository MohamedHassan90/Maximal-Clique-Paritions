import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;

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


public class Maximal {
	
	public Stack<Node> tree = new Stack<Node>();
	public HashMap<String, ArrayList<String>> cliques = new HashMap<String, ArrayList<String>>() ;
	public HashMap<String, ArrayList<String>> sharedV= new  HashMap<String, ArrayList<String>> ();
	public ArrayList<ArrayList<String>> fc = new ArrayList<ArrayList<String>>() ;
	public ArrayList<String> keys = new ArrayList<String>() ;
	public int partition = 0; 
	public int nodes = 0; 
	boolean delete = false;

	@SuppressWarnings("unchecked")
	
	
	public Maximal (String input) {
	
	cbuild(input);  //Constructing the Cliques	
	kbuild(); //Constructing the keys ArrayList
	sVbuild(); //Constructing the SharedVertices
	fcbuild(); //Constructing the fc
	System.out.print(keys);
	
	Node root = new Node(cliques,sharedV,keys);
	tree.push(root);
			while (!tree.isEmpty()) {
				if(step2(tree.peek())) {
					Node node = tree.pop();
					step3(node);//step 3 and 4 and 5 are combined	//nodes are created inside step 3
				}else {
					if(delete) {
						delete = false;
					}else {
						partition ++;
						Map<String, ArrayList<String>> par = new TreeMap<>(tree.peek().cN);
						System.out.println( "P"+partition+" : "+par); //printing partitions
					}
					tree.pop();
				}
				
			}
	}
	
	
	

	private void kbuild() {

		ArrayList<String> a = new ArrayList<String>();
		HashMap<String, Integer> counts = new HashMap<String, Integer>();
		Set<String> k = new HashSet<String>();
		Collection c = cliques.values();
		Pattern p = Pattern.compile("\\d+");
		Matcher m = p.matcher(c.toString());
		while (m.find()) {
			a.add(m.group());
		}
		
		for(int i = 0; i<a.size();i++) {
			 if (counts.containsKey(a.get(i))) {
			        counts.put(a.get(i), counts.get(a.get(i)) + 1);
			    } else {
			        counts.put(a.get(i), 1);
			    }
			 }
		Object[] ckeys = counts.keySet().toArray();
		for(int i = 0; i< counts.size(); i++) {
			if(counts.get(ckeys[i])>1) {
				k.add((String) ckeys[i]);
			}
		}
		keys.addAll(k);
	}



	private void sVbuild() {
		ArrayList<String>ckeys = new ArrayList<String>();
		Map<String, ArrayList<String>> map = new TreeMap<>(cliques);
		ckeys.addAll(map.keySet());
		for(int i=0; i<keys.size(); i++) {
			for(int j = 0; j<map.size(); j++) {
				if(  map.get(ckeys.get(j)).contains(keys.get(i))) {
					if(sharedV.containsKey(keys.get(i))) {
						sharedV.get(keys.get(i)).add(ckeys.get(j));
					}else {
						String s = ckeys.get(j);
						sharedV.put(keys.get(i),new ArrayList<String>() {{add(s);}});
					}
				}
			}
		}	
	}



	private void fcbuild() {
		for(int i = 0 ; i<keys.size(); i++) {
			String s = keys.get(i);
			fc.add(new ArrayList<String>() {{add(sharedV.get(s).get(0));add(s);}});
		}
		
		
		for(int i = 0 ; i < sharedV.size(); i++) {
			for(int j = i+1 ; j< sharedV.size(); j++) {
				if(sharedV.get(keys.get(i)).containsAll(sharedV.get(keys.get(j))) && sharedV.get(keys.get(j)).containsAll(sharedV.get(keys.get(i)))) {
					
					String s = sharedV.get(keys.get(i)).get(0); //c1
					int ii = i;
					int jj = j;
					fc.add(new ArrayList<String>() {{add(s);add(keys.get(ii));add(keys.get(jj));}}); //c1 ; 2(i) ; 3(j)
					
				}
			}
				
		}

		optimizefc(); //removing duplicated and nonshared vertices from fc

	}


	private void optimizefc() {
	
		for( int i = 0 ; i< fc.size() ; i++) {
			String c = fc.get(i).get(0);
			if(!keys.containsAll(cliques.get(c))) {
				fc.remove(i);
				optimizefc();
			}	
		}
	}




	public void cbuild(String in) {

		Pattern p = Pattern.compile("\\[(.*?)\\]");
		Matcher mm = p.matcher(in);
		int i = 0;
		while(mm.find()) {
			i++;
				
		    String[] temp = mm.group(1).split(",");
			cliques.put("c"+i,new ArrayList<String>());
		    for(int j = 0; j<temp.length;j++){
				cliques.get("c"+i).add(temp[j]);
		    }
		}
	
	}	
	

	private  boolean step2(Node node) { // false if empty
		if(node.keysN.isEmpty()) {
			return false;
		}
		return true;
	}

	private void step3(Node node) { 
		
		
		ArrayList<String> common= sharedV.get(node.keysN.get(0));
		
		for(int i = 0; i<sharedV.get(node.keysN.get(0)).size(); i++) {
			ArrayList<String> keys= copy(node.keysN);
			HashMap<String, ArrayList<String>> cn = copy(node.cN);
			HashMap<String, ArrayList<String>> svn = copy(node.svN);
			

			for(int j = 0; j<common.size();j++) {  
				if(cn.get(common.get(j))!= null) {
					cn.get(common.get(j)).remove(keys.get(0));
				}
			}
			
			if(cn.get(common.get(i))!= null) {
				cn.get(common.get(i)).add(keys.get(0)); 
			}else {
				delete = true;
				cn.get(common.get(i+1)).add(keys.get(0)); 
		
			}
			keys.remove(0);

			Node tempN = new Node(cn,svn,keys);
			step4(tempN);

			if(!step5(tempN)) {
					tree.push(tempN);	
			}
			else {
				break;
			}
			nodes++;
		}
	}
	
	public static HashMap<String, ArrayList<String>> copy(HashMap<String, ArrayList<String>> original)
		{
		    HashMap<String, ArrayList<String>> copy = new HashMap<String, ArrayList<String>>();
		    for (Entry<String, ArrayList<String>> entry : original.entrySet())
		    {
		        copy.put(entry.getKey(),
		           new ArrayList<String>(entry.getValue()));
		    }
		    return copy;
		}

	public static ArrayList<String> copy( ArrayList<String> original)
	{
	    ArrayList<String> copy = new ArrayList<String>();
	    for(int i = 0 ; i< original.size();i++) {
	    	copy.add(original.get(i));
	    }
	    return copy;
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
	
	
	private boolean step5(Node node) { // returns true means fail 
		
		HashMap<String, ArrayList<String>> cn = copy(node.cN);
		Object[]cnkeys =  cn.keySet().toArray();
		
		//checking for forbidden config
		
		for(int i = 0; i<cn.keySet().size(); i++) {
			for(int j = 0 ; j<fc.size() ; j++) {
				if(fc.get(j).containsAll(cn.get(cnkeys[i]))&&(fc.get(j).get(0).equals(cnkeys[i]))) {
					return true;
				}
			}
		}
		
		// checking for unions
		for(int i = 0 ; i < cn.keySet().size() ; i++) {
			for(int  j=i+1 ; j< cn.keySet().size(); j++) {
				Set<String> set = new HashSet<String>();
				set.addAll(cn.get(cnkeys[i]));
				set.addAll(cn.get(cnkeys[j]));
				if(checksetunions(set,cliques)) {
					return true;
				}
			}
		}
			return false;
	}
	


	private boolean checksetunions(Set<String> set, HashMap<String, ArrayList<String>> c) { // returns true if contains union
		Object[]ckeys =  c.keySet().toArray();
		for(int i = 0; i< c.keySet().size();i++) {
			if(c.get(ckeys[i]).containsAll(set)) {
				return true;
			}
		}
		return false;
	}

	private String checksubset(HashMap<String, ArrayList<String>> cn) {
		Object[]cnkeys =  cn.keySet().toArray();
		for(int i = 0 ; i< cnkeys.length ; i++) {
			for(int j = cnkeys.length-1; j >i ; j--) {
				if(cn.get(cnkeys[i]).size()<cn.get(cnkeys[j]).size()) {
					if(cn.get(cnkeys[j]).containsAll(cn.get(cnkeys[i]))) {
						return (String) cnkeys[i];
					}
				}
				if(cn.get(cnkeys[i]).containsAll(cn.get(cnkeys[j]))) {
					return (String) cnkeys[j];
				}
			}
		}
		return null;	
	}
}
