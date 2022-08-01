

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		String EmailId = request.getParameter("email");
		String pwd1 = request.getParameter("pwd1");
		HttpSession session = request.getSession();
		try{
			
			Class.forName("com.mysql.jdbc.Driver");
			
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:4000/easybuy","root","admin");
			
			PreparedStatement sql = con.prepareStatement("insert into user values(?,?,?,?,?)");
			PreparedStatement check_uid;
			ResultSet rs ;
			String uid;
			
			PreparedStatement check_email = con.prepareStatement("select count(*) from user where email =(?) ;");
			
			check_email.setString(1, EmailId);
			rs = check_email.executeQuery();
			
			while(rs.next())
			{
				System.out.print("1");
			}
			
			SimpleDateFormat formatter = new SimpleDateFormat("yyyymmdd");
			Date date = new Date();
			do{
			String randomNum = Integer.toString(ThreadLocalRandom.current().nextInt(0, 1000 + 1));
			uid = "u"+formatter.format(date)+randomNum;
			
			String uids = "select count(*) from user where uid='"+uid+"';";
			
			check_uid = con.prepareStatement(uids);
			rs = check_uid.executeQuery();
			rs.next();
			}while(rs.getInt(1)>0);
			
			sql.setString(1, uid);
			sql.setString(2,fname);
			sql.setString(3, lname);
			sql.setString(4,EmailId);
			sql.setString(5, pwd1);
			
			int i = sql.executeUpdate();
        	
        	if(i>0)
        	{
        		  System.out.println("You are successfully registered");
        		   session.setAttribute("login","1");  
					session.setAttribute("uid",uid);
					session.setAttribute("fname",fname);
					session.setAttribute("lname",lname);
					session.setAttribute("email",EmailId);
					response.sendRedirect("home.jsp");
       		  response.sendRedirect("home.jsp");
  
        	}
        	else
        	{
        		  System.out.println("");
        	}
		
			
			
		}
		catch(Exception e)
		{
			System.out.println(e);
			e.printStackTrace();
		}
		
		
		
		
		
		
		
	}

}
