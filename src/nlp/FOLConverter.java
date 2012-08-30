package nlp;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FOLConverter extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ServletContext context;
	
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		context = config.getServletContext();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String text = req.getParameter("text");
		StringBuffer sent=new StringBuffer();
		
		sent.append("Input String: <br />");
		sent.append(text);	
		sent.append("<p>FOL:<br />");
		
		
		//The FOL conversion logic	
		sent.append(PredicateLogic.getPredicates(text));
		
		
		sent.append("</p>");
		
		String dataToBeSent = sent.toString();
		req.setAttribute("result", dataToBeSent);
		RequestDispatcher rd = context.getRequestDispatcher("/FOLTest.jsp");
		rd.forward(req, resp);
		// doPost(req, resp);
	}

}
