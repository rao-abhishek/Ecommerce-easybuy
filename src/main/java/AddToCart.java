

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

import java.time.LocalDateTime;


@WebServlet("/AddToCart")
public class AddToCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String uid =(String)session.getAttribute("uid");
		String sid = "1234";
		String price = request.getParameter("price");
		String addr = " Chennai, India";
		String mop = "card";
		String pid = request.getParameter("pid");
		String oid;
		ResultSet rs;
		int order_placed = 0;
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:4000/easybuy","root","admin");
			PreparedStatement sql = con.prepareStatement("insert into order_history values(?,?,?,?,?,?,?,?,?);");
			PreparedStatement check_oid;
           
			SimpleDateFormat formatter = new SimpleDateFormat("yyyymmdd");
			Date date = new Date();
			do{
			String randomNum = Integer.toString(ThreadLocalRandom.current().nextInt(0, 1000 + 1));
			oid = "o"+formatter.format(date)+randomNum;
			
			String oids = "select count(*) from user where uid='"+oid+"';";
			
			check_oid = con.prepareStatement(oids);
			rs = check_oid.executeQuery();
			rs.next();
			}while(rs.getInt(1)>0);
			
			sql.setString(1, oid);
			sql.setString(2, uid);
			sql.setString(3, sid);
			sql.setString(4,LocalDateTime.now().toString()); 
			sql.setString(5, price);
			sql.setString(6, addr);
			sql.setString(7, mop);
			sql.setString(8, pid);
			sql.setInt(9, order_placed);
			
           int i = sql.executeUpdate();
        	
        	if(i>0)
        	{
        		  System.out.println("Order Placed Successfully");
        
        	}
        	else
        	{
        		  System.out.println("couldn't place order");
        	}
			
			
			
			
			
		}
		catch( Exception e) {
			
			e.printStackTrace();
			
		}
		
		
				
	
	}

}
