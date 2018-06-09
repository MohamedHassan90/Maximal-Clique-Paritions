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
	public ArrayList<ArrayList<String>> fc = new ArrayList<ArrayList<String>>() ;//= forbiddenConfig();
	public ArrayList<String> keys = new ArrayList<String>() ; //1,2,3
	public int partition = 0; 
	public int nodes = 0; 
	boolean delete = false;

	@SuppressWarnings("unchecked")
	
	
	public Maximal (String input) {
	
	cbuild(input);  //Constructing the Cliques	
	fcbuild(); //Constructing the fc
	kbuild(); //Constructing the keys ArrayList
	sVbuild(); //Constructing the SharedVertices
	
	Node root = new Node(cliques,sharedV,keys);
	tree.push(root);
	//System.out.println( "root :"+ root.cN);
			while (!tree.isEmpty()) {
			//	System.out.println( tree.peek().cN);

				if(step2(tree.peek())) {
					

					Node node = tree.pop();
					step3(node);//step 3 and 4 and 5 are combined
					//nodes are created inside step 3
				}else {
					if(delete) {
						delete = false;
					}else {
						partition ++;
						Map<String, ArrayList<String>> par = new TreeMap<>(tree.peek().cN);
						System.out.println( "P"+partition+" : "+par);
					}
					tree.pop();
				    //print the cliques partitions p1,p2,..
				
				}
				
			}
	
	}
	
	
	

	private void kbuild() {
		
	//	keys.add("1");
	//	keys.add("2");
	//	keys.add("3");
		ArrayList<String> a = new ArrayList<String>();
		HashMap<String, Integer> counts = new HashMap<String, Integer>();
		Set<String> k = new HashSet<String>();
	//	String k =  sharedV.keySet();
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
	//	System.out.println(k);
	//	System.out.println(cliques.values());

	//	keys.addAll(sharedV.keySet());
		
	}



	private void sVbuild() {
	//	sharedV.put("1",new ArrayList<String>() {{add("c1");add("c2");}});
	//	sharedV.put("2",new ArrayList<String>() {{add("c1");add("c4");}});
	//	sharedV.put("3",new ArrayList<String>() {{add("c2");add("c3");}});
	
		ArrayList<String>ckeys = new ArrayList<String>();
		Map<String, ArrayList<String>> map = new TreeMap<>(cliques);
		ckeys.addAll(map.keySet());
		for(int i=0; i<keys.size(); i++) {
			for(int j = 0; j<map.size(); j++) {
				if(  map.get(ckeys.get(j)).contains(keys.get(i))) {
					if(sharedV.containsKey(keys.get(i))) {
						sharedV.get(keys.get(i)).add((String) ckeys.get(j));
					}else {
						String s = (String) ckeys.get(j);
						sharedV.put(keys.get(i),new ArrayList<String>() {{add(s);}});
					}
				}
			}
		}	
	}



	private void fcbuild() {
		fc.add(new ArrayList<String>() {{add("c1");add("1");}});
		fc.add(new ArrayList<String>() {{add("c1");add("2");}});
		fc.add(new ArrayList<String>() {{add("c2");add("3");}});
		
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

	private void step3(Node node) { // the svn map is not updated !!! , all nodes will contain the original SharedV hashmap
		
		
		ArrayList<String> common= sharedV.get(node.keysN.get(0));
		
		for(int i = 0; i<sharedV.get(node.keysN.get(0)).size(); i++) {
			ArrayList<String> keys= copy(node.keysN);
			HashMap<String, ArrayList<String>> cn = copy(node.cN);
			HashMap<String, ArrayList<String>> svn = copy(node.svN);
			

			for(int j = 0; j<common.size();j++) {  // remove part
				if(cn.get(common.get(j))!= null) {
				//	System.out.println(keys.get(0)+":"+common.get(j));
					cn.get(common.get(j)).remove(keys.get(0));

				}
			}
			
//			System.out.println(common.get(i));
			if(cn.get(common.get(i))!= null) {
				cn.get(common.get(i)).add(keys.get(0)); // add 2 to Ci
			}else {
				delete = true;
				cn.get(common.get(i+1)).add(keys.get(0)); // add 2 to Ci
		
			}
			keys.remove(0);

			Node tempN = new Node(cn,svn,keys);
			//System.out.println( "cliques : "+(i+1)+": "+ tempN.cN);
			step4(tempN);
			//System.out.println( "cliques : "+(i+1)+": "+ tempN.cN);

			if(!step5(tempN)) {
			//	System.out.println( "keys : "+(i+1)+": "+ tempN.keysN);
			//	System.out.println( "cliques : "+(i+1)+": "+ tempN.cN);
				
					tree.push(tempN);
				
			}
			else {
				//System.out.println( "cliques : "+(i+1)+": "+ tempN.cN);
			//	System.out.println("Step 5 : Failed");
				break;
			}
			nodes++;
		//	System.out.println( "N"+nodes);

		}
	}
	
	public static HashMap<String, ArrayList<String>> copy(HashMap<String, ArrayList<String>> original)
		{
		    HashMap<String, ArrayList<String>> copy = new HashMap<String, ArrayList<String>>();
		    for (Entry<String, ArrayList<String>> entry : original.entrySet())
		    {
		        copy.put(entry.getKey(),
		           // Or whatever List implementation you'd like here.
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
		// TODO Auto-generated method stub
		Object[]ckeys =  c.keySet().toArray();
		for(int i = 0; i< c.keySet().size();i++) {
			if(c.get(ckeys[i]).containsAll(set)) {
				return true;
			}
		}
		return false;
	}

	private String checksubset(HashMap<String, ArrayList<String>> cn) {
		// TODO Auto-generated method stub
		Object[]cnkeys =  cn.keySet().toArray();
		for(int i = 0 ; i< cnkeys.length ; i++) {
			for(int j = cnkeys.length-1; j >i ; j--) {
		//	for(int j = 0; j <cnkeys.length ; j++) {
			//	System.out.println("subset S: " + cn.get(cnkeys[j]));
			//	System.out.println("subset L: " + cn.get(cnkeys[i]));
				if(cn.get(cnkeys[i]).size()<cn.get(cnkeys[j]).size()) {
					if(cn.get(cnkeys[j]).containsAll(cn.get(cnkeys[i]))) {
					
				//		System.out.println("trxxxxxxx");
				//		System.out.println(cnkeys[i]);


						return (String) cnkeys[i];

					}
				}
				if(cn.get(cnkeys[i]).containsAll(cn.get(cnkeys[j]))) {
					
				//	System.out.println("treeeeeeeeeee");

					return (String) cnkeys[j];

				}
			}
		}
		return null;	
	}


	

}
