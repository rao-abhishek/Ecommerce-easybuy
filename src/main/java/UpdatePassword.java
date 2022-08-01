



import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.*;



/**
 * Servlet implementation class UpdatePassword
 */
@WebServlet("/UpdatePassword")
public class UpdatePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			
			HttpSession session = request.getSession();
			
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:4000/easybuy","root","admin");
		PreparedStatement sql = con.prepareStatement("UPDATE user SET password = (?) WHERE (uid = (?));");
		
		String pwd = request.getParameter("pwd");
		String uid = (String) session.getAttribute("uid");
		
		sql.setString(1, pwd);
		sql.setString(2, uid);
		
		int i = sql.executeUpdate();
		
		if(i>0)
		{
			System.out.println("Password Updated Successfully");
			response.getWriter().write("1");
			System.out.print("1");
		}
		else
		{
			System.out.println("Error in SQL/Or Somewhere");
			
		}
		
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

}
