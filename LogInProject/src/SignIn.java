

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SignIn
 */
@WebServlet("/SignIn")
public class SignIn extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignIn() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sessionId = "place holder";
		Connect stream = new Connect();
		response.setContentType("text/plain");
		if (stream.SameUser(request.getParameter("oldUserName"),request.getParameter("oldPassword"))){
			response.getWriter().println("permission granted");
			Cookie cookie = new Cookie(request.getParameter("oldUserName"),"placeholder");
			cookie.setMaxAge(60*60*6); // store for 6 hours
			response.addCookie(cookie);
		}
		else{
			response.getWriter().println(request.getParameter("permission denied"));
		}
			
	}

}
