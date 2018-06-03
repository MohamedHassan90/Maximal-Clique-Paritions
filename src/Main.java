import java.util.ArrayList;


public class Main {

	public Main() {
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("serial")
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Maximal m = new Maximal();
		
		//fc
		m.fc.add(new ArrayList<String>() {{add("c1");add("1");}});
		m.fc.add(new ArrayList<String>() {{add("c1");add("2");}});
		m.fc.add(new ArrayList<String>() {{add("c2");add("3");}});
		
		//sharedV
		m.sharedV.put("1",new ArrayList<String>() {{add("c1");add("c2");}});
		m.sharedV.put("2",new ArrayList<String>() {{add("c1");add("c4");}});
		m.sharedV.put("3",new ArrayList<String>() {{add("c2");add("c3");}});

		//cliques
		m.cliques.put("c1",new ArrayList<String>() {{add("1");add("2");}});
		m.cliques.put("c2",new ArrayList<String>() {{add("1");add("3");}});
		m.cliques.put("c3",new ArrayList<String>() {{add("3");add("4");}});
		m.cliques.put("c4",new ArrayList<String>() {{add("2");add("5");}});
		
		//keys
		m.keys.add("1");
		m.keys.add("2");
		m.keys.add("3");

		

		m.Maximal();
		
		System.out.println(m.fc.toString());
	}

}
