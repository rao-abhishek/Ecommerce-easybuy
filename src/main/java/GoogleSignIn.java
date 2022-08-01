

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

/**
 * Servlet implementation class GoogleSignIn
 */
@WebServlet("/GoogleSignIn")
public class GoogleSignIn extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GoogleSignIn() {
        super();
        // TODO Auto-generated constructor stub
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	
		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		String EmailId = request.getParameter("email");
		String pwd1 = request.getParameter("tid");
		
		try{
			
			Class.forName("com.mysql.jdbc.Driver");
			HttpSession session = request.getSession();
			
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:4000/easybuy","root","admin");
			
			PreparedStatement sql = con.prepareStatement("insert into user values(?,?,?,?,?)");
			PreparedStatement check_uid,sql2;
			ResultSet rs ;
			String uid;
			sql2 = con.prepareStatement("select * from user where email =(?) ;");
			sql2.setString(1, EmailId);
			
			rs = sql2.executeQuery();
			
	
		if(rs.next())
			{
		            session.setAttribute("login","1");  
					session.setAttribute("uid",rs.getString(1));
					session.setAttribute("fname",rs.getString(2));
					session.setAttribute("lname",rs.getString(3));
					session.setAttribute("email",rs.getString(4));
					response.sendRedirect("home.jsp");
					
				
			}
			
		else {
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
			
        	}
        	else
        	{
        		  System.out.println("");
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
