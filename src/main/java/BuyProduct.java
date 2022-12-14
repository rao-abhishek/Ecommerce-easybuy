

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

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.Session;
import javax.mail.Transport;


@WebServlet("/BuyProduct")
public class BuyProduct extends HttpServlet {
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
		String email = null;
		ResultSet rs;
		int order_placed = 1;
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:4000/easybuy","root","admin");
			PreparedStatement sql = con.prepareStatement("insert into order_history values(?,?,?,?,?,?,?,?,?);");
			PreparedStatement check_oid, get_email_id;
			
		    get_email_id = con.prepareStatement("select * from user where uid=(?);");
		    
		    get_email_id.setString(1, uid);
		    
		    rs = get_email_id.executeQuery();
		    
		    if(rs.next())
		    {
		    	email = rs.getString(4);
		    }
           
			SimpleDateFormat formatter = new SimpleDateFormat("yyyymmdd");
			Date date = new Date();
			do{
			String randomNum = Integer.toString(ThreadLocalRandom.current().nextInt(0, 1000 + 1));
			oid = "o"+formatter.format(date)+randomNum;
			
			String oids = "select count(*) from order_history where oid='"+oid+"';";
			
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
			
		     try
		      { 
			 String sender = "abhishekrao3011@gmail.com";
			 String recipient = email;
			   Properties props = System.getProperties();
		        String host = "smtp.gmail.com";
		        props.put("mail.smtp.starttls.enable", "true");
		        props.put("mail.smtp.host", host);
		        props.put("mail.smtp.user", "abhishekrao3011");
		        props.put("mail.smtp.password", "---");
		        props.put("mail.smtp.port", "587");
		        props.put("mail.smtp.auth", "true");
		        props.put("mail.smtp.ssl.protocols", "TLSv1.2");

 
			    
			    Session session2 = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
				    protected PasswordAuthentication getPasswordAuthentication() {
					    return new PasswordAuthentication("INVALID@INVALID.COM", "INVALID@INVALID.COM");
				    
				
					
				}
			
			});

		      // creating session object to get properties
		    
			  //  System.out.print(session2);
		 
		 
		         // MimeMessage object.
		         MimeMessage message = new MimeMessage(session2);
		 
		         // Set From Field: adding senders email to from field.
		         message.setFrom(new InternetAddress(sender));
		 
		         // Set To Field: adding recipient's email to from field.
		         message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
		 
		         // Set Subject: subject of the email 
		         message.setSubject("Order Placed Successfull"); 
		 
		         // set body of the email.
		         message.setText("Order Placed Successfully \n your order-Id:"+oid);
		 
		         // Send email.
		         Transport.send(message);
		         System.out.println("Mail successfully sent");
		      }
		      catch (MessagingException mex) 
		      {
		         mex.printStackTrace();
		      }
			
			
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
