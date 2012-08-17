package test;

import java.util.Scanner;

import edu.smu.tspell.wordnet.NounSynset;
import edu.smu.tspell.wordnet.Synset;
import edu.smu.tspell.wordnet.SynsetType;
import edu.smu.tspell.wordnet.VerbSynset;
import edu.smu.tspell.wordnet.WordNetDatabase;

public class myTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		System.out.println("It starts here...");
		//set the directory for the  wordnet database
		String wnetDir="/usr/share/wordnet-3.0/dict";
		System.setProperty("wordnet.database.dir", wnetDir);
		
		NounSynset nounSynset;
		VerbSynset verbSynset;
		NounSynset[] hyponyms;
		VerbSynset[] hypernyms;
		
		WordNetDatabase database=WordNetDatabase.getFileInstance();
		
		Scanner s=new Scanner(System.in);
		while(true){
			System.out.println("Enter a word: ");
			String wordToSearch=s.next();
			
			
			Synset[] synsets = database.getSynsets(wordToSearch, SynsetType.NOUN);
			for (int i = 0; i < synsets.length; i++) {
				nounSynset = (NounSynset)(synsets[i]);
				hyponyms = nounSynset.getHyponyms();
				System.err.println(nounSynset.getWordForms()[0] +
						": " + nounSynset.getDefinition() + ") has " + hyponyms.length + " hyponyms");
			}
			
			
			synsets=database.getSynsets(wordToSearch, SynsetType.VERB);
			for (int i = 0; i < synsets.length; i++) {
				verbSynset = (VerbSynset)(synsets[i]);
				hypernyms=verbSynset.getHypernyms();
				System.err.println(verbSynset.getWordForms()[0] +
						": " + verbSynset.getDefinition() + ") has " + hypernyms.length + " hypernyms");
			}
		}
		
	}

}
