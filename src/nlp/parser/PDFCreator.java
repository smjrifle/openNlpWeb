package nlp.parser;



import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;


import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Jpeg;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PDFCreator extends HttpServlet {


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
		
		
		
		Document document=new Document();
		 try {
			 	PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("newPdf.pdf"));		       
		        writer.createXmpMetadata();
		        document.open();
		        document.add(new Paragraph(text));
		        document.close();
		    } catch (Exception de) {
		        de.printStackTrace();
		    } 
		    finally{
		        document.close();
		    }
		
		
		
		String data="pdfFile Created";
		req.setAttribute("result", data);
		RequestDispatcher rd = context.getRequestDispatcher("/pdfCreator.jsp");
		rd.forward(req, resp);


	}

	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String text = req.getParameter("text");		
		
		
		
		Document document=new Document();
		 try {
			 	PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("newPdf.pdf"));		       
		        writer.createXmpMetadata();
		        document.open();
		        //Font f=new Font("Preeti",14,);
		        document.add((Element) new Font());
		        document.add(new Paragraph(text));
		        URL u=new URL("http://localhost/test.jpg");
		        Jpeg jpg=new Jpeg(u);
		        jpg.scaleToFit(400, 400);
		        document.add(jpg);
		       
		        document.close();
		    } catch (Exception de) {
		        de.printStackTrace();
		    } 
		    finally{
		        document.close();
		    }
		
		
		
		String data="pdfFile Created";
		req.setAttribute("result", data);
		RequestDispatcher rd = context.getRequestDispatcher("/pdfCreator.jsp");
		rd.forward(req, resp);

	}

	
}
