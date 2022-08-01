
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;






	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		    
		    String email = request.getParameter("email");
	        String pwd = request.getParameter("pwd");
	        
	        // To verify whether entered data is printing correctly or not
	        System.out.println("emailId.." + email);
	        System.out.println("password.." + pwd);
	        
	        try {
	            
	            // loading drivers for mysql
	            Class.forName("com.mysql.jdbc.Driver");
	             
	            //creating connection with the database
	            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:4000/google_auth","root","admin");
	 
	            PreparedStatement ps = con.prepareStatement("insert into test_info values(?,?);");
                PreparedStatement checkuser = con.prepareStatement("select count(*) from test_info where email = ? ;");
                
            System.out.println(ps);
            ps.setString(1, email);
            ps.setString(2, pwd);
            checkuser.setString(1,email);
            //int i = ps.executeUpdate();
            ResultSet rs = checkuser.executeQuery();
            
            int n;
            
            while(!rs.next());
            { n = rs.getInt(1);
              
            }

            if(n == 0) {
            	int i = ps.executeUpdate();
            	
            	if(i>0)
            	{
            		  System.out.println("You are successfully registered");
            		  response.sendRedirect("signout.html");
            	}
            	
               
            }
            
            else
            {
            	System.out.println("User Logged in");
            	  response.sendRedirect("signout.html");
            }
         
        }
        catch(Exception se) {
        	System.out.println("Error "+se);
            se.printStackTrace();
        }
	        
	
	}

}
