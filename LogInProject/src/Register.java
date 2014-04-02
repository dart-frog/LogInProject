

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connect stream = new Connect();
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		 pw.println("<html>");
		  pw.println("<head><title>Nates Website</title></head>");
		  pw.println("<body>");
		  pw.println("<h1>Welcome to Nate's Website</h1>");
		  if (stream.checkSessionId(request.getCookies())){
			  pw.println("<p>logged in</p>");
		  }
		  pw.println("<button type='button' onclick='myFunction()'> Java Script</button>");
		  pw.println("<script>");
		  pw.println("function short(){");
		  pw.println("if(document.getElementById('password') > 6){");
		  pw.println("confirm('your password was to short please make it greater then six letters')");
		  pw.println("}");
		  pw.println("}");
		  pw.println("</script>");
		  pw.println("<form action='LogOut' method = 'Post'>");
		  pw.println("<input type = 'submit' value ='Log Out'>");
		  pw.println("<p> New Users </p>");
		  pw.println("<form action='Register' method='Post'>");
		  pw.println("User Name: <input type='text' name='name'><br>");
		  pw.println("Password: <input type='password' name='password'><br>");
		  pw.println("<input type='submit' value='Submit'>");
		  pw.println("</form>");
		  pw.println("<p> Returning users</p>");
		  pw.println("<form action='SignIn' method = 'Post'>");
		  pw.println("User Name: <input type='text' name = 'oldUserName'><br>");
		  pw.println("Password: <input type='password' name='oldPassword'><br>");
		  pw.println("<input type='submit' value='Submit'>");
		  pw.println("</form>");
		  pw.println("</body></html>");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/plain");
		response.getWriter().println("works");
		response.getWriter().println(request.getParameter("name"));
		Connect stream = new Connect();
		stream.addNewUser(request.getParameter("name"), request.getParameter("password"));
		
	}

}
