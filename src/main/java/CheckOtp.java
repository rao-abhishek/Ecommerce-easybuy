

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class CheckOtp
 */
@WebServlet("/CheckOtp")
public class CheckOtp extends HttpServlet {
	private static final long serialVersionUID = 1L;


	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		String otp1 = request.getParameter("otp");
		String otp2 = (String) session.getAttribute("otp");
		System.out.println(otp2);
		System.out.println(otp1);
		if(otp1.equals(otp2))
		{
			response.getWriter().write("1");
			System.out.print("1");
		}
		
	}

}
