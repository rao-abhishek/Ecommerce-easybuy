


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;


/**
 * Servlet implementation class Register
 */
@WebServlet("/CheckEmail")
public class CheckEmail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		
		String EmailId = request.getParameter("email");
		response.setContentType("text/plain"); 
			
		try{
			
			Class.forName("com.mysql.jdbc.Driver");
			
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:4000/easybuy","root","admin");
			
			ResultSet rs ;
			
			PreparedStatement check_email = con.prepareStatement("select count(*) from user where email =(?) ;");
			
			check_email.setString(1, EmailId);
			rs = check_email.executeQuery();
			
			if(rs.next())
			{
				if(rs.getInt(1)>0)
				{
				response.getWriter().write("1");
				System.out.print("1");
				}
				else
				{
				//System.out.print("0");
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
