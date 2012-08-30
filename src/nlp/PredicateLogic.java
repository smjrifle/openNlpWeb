package nlp;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;

public class PredicateLogic {

	public static String getPredicates(String s) {
		InputStream modelPOSTagger = null;
		InputStream modelTokenizer = null;
		
		POSModel modelPOS = null;
		TokenizerModel modelToken = null;
		
		StringBuffer sent=new StringBuffer();		
		// loading the POSTagger Model
		try {
			modelPOSTagger = new FileInputStream(
					"/home/opnchaudhary/androidapps/openNlpWeb/WebContent/mod/en-pos-maxent.bin");
			modelPOS = new POSModel(modelPOSTagger);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (modelPOSTagger != null) {
				try {
					modelPOSTagger.close();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}
		//loading the Tokenizer Model		
				try{			
					modelTokenizer = new FileInputStream(
							"/home/opnchaudhary/androidapps/openNlpWeb/WebContent/mod/en-token.bin");
					modelToken=new TokenizerModel(modelTokenizer);
				}catch(Exception e){
					e.printStackTrace();		
				}finally{
					if(modelTokenizer!=null){
						try{
							modelTokenizer.close();
						}catch(Exception e1){
							e1.printStackTrace();
						}
					}
				}
		// POS Tagging
		// Tokenization
		Tokenizer tokenizer = new TokenizerME(modelToken);
		String[] tokens = tokenizer.tokenize(s);

		POSTaggerME posTagger = new POSTaggerME(modelPOS);
			
		/*
		 * This works to some extent.
		 */
		String consts=null;
		String attr=null;
		String consts1=null;
		int count=0;
		for (int tempVar = 0; tempVar < tokens.length; tempVar++) {			
			String temp=posTagger.tag(tokens[tempVar]);
			String[] temps=temp.split(" ");			
			for(int i=0;i<temps.length;i++){
				String[] t=temps[i].split("/");
				if(t[1].equals("NN")||t[1].equals("NNS")||t[1].equals("NNP")||t[1].equals("NNPS")||t[1].equals("FW")){					
					count++;
					if(count==1){
						consts=t[0];
						break;
					}
				}
			}
			
			for(int i=0;i<temps.length;i++){
				String[] t=temps[i].split("/");
				if(t[1].equals("VB")||t[1].equals("VBD")||t[1].equals("VBN")||t[1].equals("VBZ")){
					attr=t[0];
				}
			}
			for(int i=0;i<temps.length;i++){
				String[] t=temps[i].split("/");
				if(t[1].equals("NN")||t[1].equals("NNS")||t[1].equals("NNP")||t[1].equals("NNPS")||t[1].equals("FW")||t[1].equals("JJ")){					
					consts1=t[0];
				}
			}
			
			
		}
		sent.append(attr+"("+consts+","+consts1+")");		
		
		return sent.toString();		
	}
}