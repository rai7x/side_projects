import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedHashMap;
import java.util.Scanner;

public class HTMLarticle {
	LinkedHashMap<String, Integer> hm = new LinkedHashMap<String, Integer>();
	File sourceFile = new File("special words.txt");
	
	public HTMLarticle() throws FileNotFoundException {
		Scanner sc = new Scanner(sourceFile);
		while (sc.hasNext()) {
			String word = sc.next();
			hm.put(word, 0);
		}
//		displayMap();
		sc.close();
	}
	
	public void displayMap() {
		System.out.println("HashMap contains: " + hm);
	}
	
}
