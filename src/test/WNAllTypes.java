package test;

import java.util.Scanner;

import edu.smu.tspell.wordnet.Synset;
import edu.smu.tspell.wordnet.WordNetDatabase;

public class WNAllTypes {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("It starts here...");
		//set the directory for the  wordnet database
		String wnetDir="/usr/share/wordnet-3.0/dict";
		System.setProperty("wordnet.database.dir", wnetDir);
		
		WordNetDatabase database=WordNetDatabase.getFileInstance();
		Scanner s=new Scanner(System.in);
		while(true){
			System.out.println("Enter a word:");
			String inputWord=s.next();
			Synset[] synsets=database.getSynsets(inputWord);
			for(int i=0;i<synsets.length;i++){
				System.out.println(" : "+synsets[i].getDefinition());
			}
		}		
	}
}
