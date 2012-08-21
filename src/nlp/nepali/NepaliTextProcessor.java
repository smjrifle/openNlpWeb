package nlp.nepali;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NepaliTextProcessor extends HttpServlet {

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
			doPost(req,resp);
			}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String text = req.getParameter("text");		
		String sent="Input text: ";
		sent+=text;
		req.setAttribute("result", sent);
		RequestDispatcher rd = context.getRequestDispatcher("/index.jsp");
		resp.setCharacterEncoding("UTF-8");
		rd.forward(req, resp);
		// doPost(req, resp);

	}

}
