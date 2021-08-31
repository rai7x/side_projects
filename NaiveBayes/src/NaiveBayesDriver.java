import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Scanner;

public class NaiveBayesDriver {
	public static void main(String[] args) throws FileNotFoundException {
		
//		System.out.println(new File(".").getAbsolutePath());
		File csvFile = new File("data.csv");
    	Scanner myReader = new Scanner(csvFile);
    	myReader.useDelimiter(",");
    	//skipping headers
    	myReader.nextLine();
    	myReader.nextLine();
    	LinkedHashMap<String, Word> wordMap = new LinkedHashMap<String, Word>();
    	while (myReader.hasNextLine()) {
	    	String data = myReader.nextLine();
	    	String[] tokenized_data = data.split(",");
//	    	for (String s : tokenized_data) {
//	    		System.out.print(s + " ");
//	    	}
//	    	System.out.println();
	    	Word w = new Word(tokenized_data[0], 
	    			Integer.parseInt(tokenized_data[1]), 
	    			Integer.parseInt(tokenized_data[2]), 
	    			Integer.parseInt(tokenized_data[3]), 
	    			Double.parseDouble(tokenized_data[4]), 
	    			Double.parseDouble(tokenized_data[5]), 
	    			Double.parseDouble(tokenized_data[6]));
	    	wordMap.put(tokenized_data[0], w);
    	}
//    	for (Word w: wordMap.values()) {
//    		System.out.println(w);
//    	}
    	myReader.close();
    	
    	File folder = new File("training data");
    	File[] files = folder.listFiles();
    	//read in HTML file
//    	File testFile = new File("training data\\c8.txt");
    	double HTML_count = 0;
    	double correct = 0;
    	for (File testFile : files)
    	{
    		HTML_count++;
	    	myReader = new Scanner((Readable) new BufferedReader(new FileReader(testFile)));
	    	myReader.useDelimiter("\\s|\\W");
	    	//count number of occurrences in file for each special word
	    	HTMLarticle article = new HTMLarticle();
	    	while (myReader.hasNext()) {
	    		String word = myReader.next().toLowerCase();
//	    		System.out.println(word + " ");
	    		if (article.hm.containsKey(word)) {
	    			int new_value = article.hm.get(word)+1;
	    			article.hm.put(word, new_value);
	    		}
	//    		System.out.println(data);
	    	}
//	    	article.displayMap();
	    	//calculate likelihood that the article belongs to each category
	    	double p_cooking = 0.33;
	    	double p_plants = 0.33;
	    	double p_restaurants = 0.33;
	    	double l_cooking = p_cooking, l_restaurants = p_restaurants, l_plants = p_plants;
	    	//iterate through hashMap, and pull out each value > 0
	    	for (String key : article.hm.keySet()) {
	    		int count = article.hm.get(key);
	    		if (count > 0) {
	    			Word w = wordMap.get(key);
	    			l_cooking *= Math.pow(w.freq_cooking, count);
	    			l_plants *= Math.pow(w.freq_plant, count);
	    			l_restaurants *= Math.pow(w.freq_restaurant, count);
	    		}
	    	}
//	    	System.out.println("l_cooking: " + l_cooking);
//	    	System.out.println("l_plants: " + l_plants);
//	    	System.out.println("l_restaurants: " + l_restaurants);
	    	if ((l_cooking > l_plants) && (l_cooking > l_restaurants)) {
	    		System.out.println("The article " + testFile.getName() + " is about cooking.");
	    		if (testFile.getName().charAt(0)=='c') {
	    			System.out.println("This is a correct guess.");
	    			correct++;
	    		}
	    		else {
	    			System.out.println("This is an incorrect guess.");
	    		}
	    	}
	    	else if ((l_plants > l_cooking) && (l_plants > l_restaurants)) {
	    		System.out.println("The article " + testFile.getName() + " is about plants.");
	    		if (testFile.getName().charAt(0)=='p') {
	    			System.out.println("This is a correct guess.");
	    			correct++;
	    		}
	    		else {
	    			System.out.println("This is an incorrect guess.");
	    		}
	    	}
	    	else if ((l_restaurants > l_plants) && (l_restaurants > l_cooking)) {
	    		System.out.println("The article " + testFile.getName() + " is about restaurants.");
	    		if (testFile.getName().charAt(0)=='r') {
	    			System.out.println("This is a correct guess.");
	    			correct++;
	    		}
	    		else {
	    			System.out.println("This is an incorrect guess.");
	    		}
	    	}
	    	else
	    		System.out.println("The article " + testFile.getName() + " is not reading input.");
    	}
    	System.out.println("The classifier accuracy for the data is: " + correct/HTML_count);
	}
}
;