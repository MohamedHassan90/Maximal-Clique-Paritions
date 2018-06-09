import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class Main {

	

	@SuppressWarnings("serial")
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String input = "";
		
        FileReader fr = null;
        try {
            fr = new FileReader ("input.txt");
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
        
        BufferedReader br = new BufferedReader(fr);
        try {
           input=br.readLine();                

        } catch (IOException e1) {
            e1.printStackTrace();
        }
		Maximal m = new Maximal(input);


		//String in = "c1[1,2],c2[1,3],c3[3,4],c4[2,5]";
		
		
	//  fc example 1
	//	m.fc.add(new ArrayList<String>() {{add("c1");add("1");}});
	//	m.fc.add(new ArrayList<String>() {{add("c1");add("2");}});
	//	m.fc.add(new ArrayList<String>() {{add("c2");add("3");}});
		
	//  sharedV example 1 
	//	m.sharedV.put("1",new ArrayList<String>() {{add("c1");add("c2");}});
	//	m.sharedV.put("2",new ArrayList<String>() {{add("c1");add("c4");}});
	//	m.sharedV.put("3",new ArrayList<String>() {{add("c2");add("c3");}});

		//cliques

	//	m.cliques.put("c1",new ArrayList<String>() {{add("1");add("2");}});
	//	m.cliques.put("c2",new ArrayList<String>() {{add("1");add("3");}});
	//	m.cliques.put("c3",new ArrayList<String>() {{add("3");add("4");}});
	//	m.cliques.put("c4",new ArrayList<String>() {{add("2");add("5");}});
		
		//keys
	//	m.keys.add("1");
	//	m.keys.add("2");
	//	m.keys.add("3");
		
	}

}
