package auth.user;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServlet
 */

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("in post method");
		
		//getting data from form
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		
		User user  = new User();
		user.setUsername(username);
		user.setPassword(password);
		
		//send form's data to service to check login in database
		LoginService service = new LoginService();
		boolean isValiduser=service.login(user);
		System.out.println(isValiduser);
		
		if(isValiduser)
		{
			response.sendRedirect(request.getContextPath()+"/index.jsp");
		}
		else
		{
			String msg="Invalid Username or password. Please try again.";
			request.setAttribute("error", msg);
			response.sendRedirect(request.getContextPath()+"/Login.jsp");
		}
		
		
	}

}
