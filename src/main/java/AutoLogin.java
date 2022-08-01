
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Servlet implementation class Login
 */
@WebServlet("/AutoLogin")
public class AutoLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;






	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		    
		    String uid;
		    String email = request.getParameter("email");
	        String pwd = request.getParameter("uid");
	        String fname = request.getParameter("fname");
	        String lname = request.getParameter("lname");
	        String url = request.getParameter("url");
	        ResultSet rs;
	        PreparedStatement ps, check_uid ,sql;
	    	HttpSession session = request.getSession();
	        // To verify whether entered data is printing correctly or not
	        System.out.println("emailId.." + email);
	        System.out.println("password.." + pwd);
	        
	        
	        try {
	            
	            // loading drivers for mysql
	            Class.forName("com.mysql.jdbc.Driver");
	             
	            //creating connection with the database
	            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:4000/easybuy","root","admin");
	 
	            ps = con.prepareStatement("insert into user values(?,?,?,?,?);");
                PreparedStatement checkuser = con.prepareStatement("select count(*) from user where email = ? ;");
                
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
                
            System.out.println(ps);
            ps.setString(1, uid);
            ps.setString(2, email);
            ps.setString(3, fname);
            ps.setString(4, lname);
            ps.setString(5, pwd);
            checkuser.setString(1,email);
            //int i = ps.executeUpdate();
            rs = checkuser.executeQuery();
            
            int n;
            
            while(!rs.next());
            { 
            	n = rs.getInt(1);
              
            }

            if(n == 0) {
            	int i = ps.executeUpdate();
            	
            	if(i>0)
            	{
            		  System.out.println("You are successfully registered");
            		    session.setAttribute("login","1");  
    					session.setAttribute("uid",rs.getString(1));
    					session.setAttribute("fname",rs.getString(2));
    					session.setAttribute("lname",rs.getString(3));
    					session.setAttribute("email",rs.getString(4));
    			
    			
            		  
            	}
            	
               
            }
            
            else
            {
            	System.out.println("User Logged in");
            	  //response.sendRedirect("signout.html");
            	sql = con.prepareStatement("select * from user where email =(?) ;");
				sql.setString(1, email);
			
				
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
         
            response.sendRedirect(url);
            
        }
        catch(Exception se) {
        	System.out.println("Error "+se);
            se.printStackTrace();
        }
	        
	
	}

}
