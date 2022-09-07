

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


@WebServlet("/BuyFromCart")
public class BuyFromCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	 

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String uid = (String) session.getAttribute("uid");
		String email = null;
		String oids[] = request.getParameterValues("oids[]");
		ResultSet rs;
		int order_placed = 1;
		String all_id ="\n ";
		System.out.println(oids.length);
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:4000/easybuy","root","admin");
			PreparedStatement sql = con.prepareStatement("UPDATE order_history SET order_time = (?), order_placed = 1 WHERE oid = (?);;");
           
			SimpleDateFormat formatter = new SimpleDateFormat("yyyymmdd");
			Date date = new Date();
			
            PreparedStatement check_oid, get_email_id;
			
		    get_email_id = con.prepareStatement("select * from user where uid=(?);");
		    
		    get_email_id.setString(1, uid);
		    
		    rs = get_email_id.executeQuery();
		    
		    if(rs.next())
		    {
		    	email = rs.getString(4);
		    }
			
		
			for( int i = 0; i<oids.length;i++)
			{
				
				
			sql.setString(1,LocalDateTime.now().toString()); 
			sql.setString(2, oids[i]);
			
			sql.executeUpdate();
			
			all_id = all_id + oids[i] + "\n";
			
			}

		     try
		      { 
			 String sender = "abhishekrao3011@gmail.com";
			 String recipient = email;
			   Properties props = System.getProperties();
		        String host = "smtp.gmail.com";
		        props.put("mail.smtp.starttls.enable", "true");
		        props.put("mail.smtp.host", host);
		        props.put("mail.smtp.user", "abhishekrao3011");
		        props.put("mail.smtp.password", "Rani@bai@2001");
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
		         message.setText("Order Placed Successfully \n your order-Id:"+all_id);
		 
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
