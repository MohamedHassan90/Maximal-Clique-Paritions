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
	kbuild(input); //Constructing the keys ArrayList
	sVbuild(); //Constructing the SharedVertices
	fcbuild(); //Constructing the fc
	ebuild();
	
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
	
	
	private void ebuild() {
		Object[] ckeys = cliques.keySet().toArray();
		
		for(int i = 0 ; i< ckeys.length ; i++ ) {
			
		}
	}


	public void cbuild(String in) {

		Pattern p = Pattern.compile("(\\w+)\\s*\\[([a-zA-Z-0-9,]+)\\]");
		Matcher mm = p.matcher(in);
		int i = 0;
		
		while(mm.find()) { 
			i++;
			
		    String[] temp = mm.group(2).split(",");
			cliques.put(mm.group(1),new ArrayList<String>());
		    for(int j = 0; j<temp.length;j++){
				cliques.get("c"+i).add(temp[j]);
		    }
		}

	}	
	
	
	private void kbuild(String input) {

		ArrayList<String> a = new ArrayList<String>();
		HashMap<String, Integer> counts = new HashMap<String, Integer>();
		Set<String> k = new HashSet<String>();
		Pattern p = Pattern.compile("(\\w+)\\s*\\[([a-zA-Z-0-9,]+)\\]");
		Matcher m = p.matcher(input);

		while (m.find()) {
			String[] temp = m.group(2).split(",");
			
		    for(int j = 0; j<temp.length;j++){
		    	a.add(temp[j]);
		    }
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
				
				
			//	System.out.println( "svn"+tempN.svN); //printing partitions
			//	System.out.println( "cn"+tempN.cN); //printing partitions
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
	
	// for stage 3 
	public ArrayList<Edge> generateEdges() {
		
		Object[]ckeys = cliques.keySet().toArray();
		ArrayList<Edge>result = new ArrayList<Edge>();
		for(int i = 0 ; i< ckeys.length ; i++ ) {
			for(int x = 0; x< cliques.get(ckeys[i]).size() ; x++) {
				for (int y = x+1; y<cliques.get(ckeys[i]).size() ; y++) {
					HashSet<String> s = new HashSet<String>();
					s.add(cliques.get(ckeys[i]).get(x));
					s.add(cliques.get(ckeys[i]).get(y));
						result.add(new Edge( s.toArray()[0].toString(),s.toArray()[1].toString()));
				}
			}
		}
		removeDuplicates(result);
		return result;
	}
	public void removeDuplicates(ArrayList<Edge>edges) {
		for(int i = 0; i<edges.size(); i++) {
			for(int j = i+1; j<edges.size(); j++) {
				String v11 = edges.get(i).vertex1;
				String v12 = edges.get(i).vertex2;
				String v21 = edges.get(j).vertex1;
				String v22 = edges.get(j).vertex2;
				if((v11.equals(v21)&&v12.equals(v22))||(v11.equals(v22)&&v12.equals(v21))) {
					edges.remove(j);
				}
			}
		}
	}
}
