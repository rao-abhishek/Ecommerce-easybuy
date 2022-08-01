

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

/**
 * Servlet implementation class UserLogin
 */
@WebServlet("/UserLogin")
public class UserLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		String EmailId = request.getParameter("email");
		String pwd = request.getParameter("pwd");
		
		
		
		response.setContentType("text/plain"); 
			
		try{
			
			Class.forName("com.mysql.jdbc.Driver");
			
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:4000/easybuy","root","admin");
			
			PreparedStatement sql = con.prepareStatement("select count(*) from user where email =(?) and password=(?) ;");

			ResultSet rs ;
		    HttpSession session = request.getSession();
			
		//	PreparedStatement check_email = con.prepareStatement("select count(*) from user where email =(?) and password=(?) ;");
			
			sql.setString(1, EmailId);
			sql.setString(2, pwd);
			
			rs = sql.executeQuery();
			
			if(rs.next())
			{
				if(rs.getInt(1)>0)
				{
				response.getWriter().write("1");
				System.out.print("1");
				sql = con.prepareStatement("select * from user where email =(?) and password=(?) ;");
				sql.setString(1, EmailId);
				sql.setString(2, pwd);
				
				rs = sql.executeQuery();
				if(rs.next())
				{
		            session.setAttribute("login","1");  
					session.setAttribute("uid",rs.getString(1));
					session.setAttribute("fname",rs.getString(2));
					session.setAttribute("lname",rs.getString(3));
					session.setAttribute("email",rs.getString(4));
					
				}
				}
				else
				{
					response.getWriter().write("Username of password doesnt match");
					System.out.println("Username of password doesnt match");
				}
				
			}
			
		
			
			
			
			
			
		}
		catch(Exception e)
		{
			System.out.println(e);
			e.printStackTrace();
		}
		
		
		
		
		
	}

}
