package nlp;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import opennlp.tools.chunker.ChunkerME;
import opennlp.tools.chunker.ChunkerModel;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;

public class ProcessText extends HttpServlet {

	ServletContext context;

	public void init(ServletConfig config) throws ServletException {
		context = config.getServletContext();
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String text = req.getParameter("text");		
		
		//input streams
		InputStream modelSentDetect = null;
		InputStream modelTokenizer = null;
		InputStream modelPOSTagger = null;
		InputStream modelChunker=null;
		
		//models
		SentenceModel modelSentence = null;
		TokenizerModel modelToken = null;
		POSModel modelPOS=null;
		ChunkerModel modelChunk=null;
		
		String sent = "Input Text: "+text;

		//loading the SentenceDetect Model
		try{
			modelSentDetect = new FileInputStream(
					"/home/opnchaudhary/androidapps/openNlpWeb/WebContent/mod/en-sent.bin");
			modelSentence = new SentenceModel(modelSentDetect);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if (modelSentDetect != null) {
				try{
					modelSentDetect.close();
				}catch(Exception e1){
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
		
		//loading the  POSTagger Model
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
		
		//loading the chunker model
		try{
			modelChunker=new FileInputStream("/home/opnchaudhary/androidapps/openNlpWeb/WebContent/mod/en-chunker.bin");
			modelChunk=new ChunkerModel(modelChunker);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(modelChunker!=null){
				try{
					modelChunker.close();
				}catch(Exception e1){
					e1.printStackTrace();
				}
			}
		}
		
		
		//Sentence Detection
		SentenceDetectorME sentenceDetector=new SentenceDetectorME(modelSentence);
		String[] sentences=sentenceDetector.sentDetect(text);
		sent+="<p>Sentence  Detection<br />.....................................<br />";
		for(int tempVar=0;tempVar<sentences.length;tempVar++){
			sent+=sentences[tempVar]+"<br />";
		}
		sent+="</p>";
		
		//Tokenization
		Tokenizer tokenizer=new TokenizerME(modelToken);
		String[] tokens=tokenizer.tokenize(text);
		sent+="<p>Tokenization<br />.....................................<br />";
		for(int tempVar=0;tempVar<tokens.length;tempVar++){
			sent+=tokens[tempVar]+" ";
		}
		sent+="</p>";
		
		//POS Tagging
		POSTaggerME posTagger=new POSTaggerME(modelPOS);
		sent+="<p>POS Tagging<br />.......................................<br />";
		String pos=null;
		List<String> poses=new ArrayList<String>();
		for(int tempVar=0;tempVar<tokens.length;tempVar++){
			sent+=posTagger.tag(tokens[tempVar])+" ";
			pos=posTagger.tag(tokens[tempVar]);
			int t=pos.indexOf('/');
			pos=pos.substring(t+1);			
			poses.add(pos);
		}	
		sent+="</p>";
		
		//Chunking
		/*
		ChunkerME chunker=new ChunkerME(modelChunk);
		String[] sentToChunk=tokens;
		String[]  posOfSentToChunk=(String[]) poses.toArray();
		sent+="<p>Chunking<br />...........................................<br />";
		String[] tag=chunker.chunk(sentToChunk, posOfSentToChunk);
		//chunking area
		sent+="</p>";
		*/
		
		
		
		req.setAttribute("result", sent);
		RequestDispatcher rd = context.getRequestDispatcher("/index.jsp");
		rd.forward(req, resp);
		// doPost(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		/*
		 * System.out.println("We are un Post method");
		 * 
		 * String number1=req.getParameter("num1"); String
		 * number2=req.getParameter("num2"); int
		 * sum=Integer.parseInt(number1)+Integer.parseInt(number2);
		 * req.setAttribute("data",sum+"");
		 * 
		 * RequestDispatcher rd = context.getRequestDispatcher("/page3.jsp");
		 * rd.forward(req, resp);
		 */
		// resp.sendRedirect(req.getContextPath()+"/page3.jsp");

	}

}
