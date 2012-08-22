package wordnet;

import edu.smu.tspell.wordnet.AdjectiveSynset;
import edu.smu.tspell.wordnet.AdverbSynset;
import edu.smu.tspell.wordnet.NounSynset;
import edu.smu.tspell.wordnet.Synset;
import edu.smu.tspell.wordnet.SynsetType;
import edu.smu.tspell.wordnet.VerbSynset;
import edu.smu.tspell.wordnet.WordNetDatabase;

public class Wordnet implements WordNetInterface {
	
	public static class POSType{
		public static int NOUN=1;
		public static int VERB=2;
		public static int ADJECTIVE=3;
		public static int ADVERB=4;	
	}

	
	public Wordnet(){		
		
	}
	@Override
	public String[] getDefinitions(String word) {
		String wnetDir="/usr/share/wordnet-3.0/dict";
		System.setProperty("wordnet.database.dir", wnetDir);		
		WordNetDatabase	database=WordNetDatabase.getFileInstance();		
		Synset[] synsets=database.getSynsets(word);
		StringBuffer str=new StringBuffer();
		for(int i=0;i<synsets.length;i++){			
			str.append(synsets[i].getDefinition());
		}
		return null;
	}
	@Override
	public String[] getDefinition(String word, int posType) {
		String wnetDir="/usr/share/wordnet-3.0/dict";
		System.setProperty("wordnet.database.dir", wnetDir);		
		WordNetDatabase database=WordNetDatabase.getFileInstance();
		Synset[] synsets=null;
		switch(posType){
		case 1:
			NounSynset nounSynset;		
			NounSynset[] hyponyms;
			synsets = database.getSynsets(word, SynsetType.NOUN);
			for (int i = 0; i < synsets.length; i++) {
				nounSynset = (NounSynset)(synsets[i]);
				hyponyms = nounSynset.getHyponyms();				
				System.err.println(nounSynset.getWordForms()[0] +
						": " + nounSynset.getDefinition() + ") has " + hyponyms.length + " hyponyms");
			}
			break;
		case  2:
			VerbSynset[] hypernyms;
			VerbSynset verbSynset;
			synsets=database.getSynsets(word, SynsetType.VERB);
			for (int i = 0; i < synsets.length; i++) {
				verbSynset = (VerbSynset)(synsets[i]);
				hypernyms=verbSynset.getHypernyms();
				System.err.println(verbSynset.getWordForms()[0] +
						": " + verbSynset.getDefinition() + ") has " + hypernyms.length + " hypernyms");
			}
			
	
			
			
	
			break;
		case 3:
			AdjectiveSynset adjSynset;
			AdjectiveSynset[] adj;
			synsets=database.getSynsets(word,SynsetType.ADJECTIVE);
			for(int i=0;i<synsets.length;i++){
				adjSynset=(AdjectiveSynset)(synsets[i]);				
				System.err.println(adjSynset.getWordForms()[0]+":"+adjSynset.getDefinition());
			}			
			break;
		case 4:
			AdverbSynset advSynset;
			AdverbSynset[] adv;
			synsets=database.getSynsets(word,SynsetType.ADVERB);
			for(int i=0;i<synsets.length;i++){
				advSynset=(AdverbSynset)(synsets[i]);
				System.err.println(advSynset.getWordForms()[0]+":"+advSynset.getDefinition());
			}
			break;
		}
		return null;
	}
	
	
}
