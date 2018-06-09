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
	}

}
