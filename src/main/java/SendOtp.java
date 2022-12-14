


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



/**
 * Servlet implementation class Register
 */
@WebServlet("/SendOtp")
public class SendOtp extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		HttpSession session = request.getSession();
		String otp= Integer.toString(ThreadLocalRandom.current().nextInt(0, 1000 + 1));
		session.setAttribute("otp", otp);
		String EmailId = request.getParameter("email");
		response.setContentType("text/plain"); 
			
		try{
			
			Class.forName("com.mysql.jdbc.Driver");
			
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:4000/easybuy","root","admin");
			
			ResultSet rs ;
			
			PreparedStatement check_email = con.prepareStatement("select count(*),uid from user where email =(?) ;");
			
			check_email.setString(1, EmailId);
			rs = check_email.executeQuery();
			
			if(rs.next())
			{
				if(rs.getInt(1)>0)
				{
					
					 try
				      { 
						 session.setAttribute("uid", rs.getString(2));
					 String sender = "abhishekrao3011@gmail.com";
					 String recipient = EmailId;
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
							    return new PasswordAuthentication("abhishekrao3011@gmail.com", "Rani@bai@2001");
						    
						
							
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
				         message.setSubject("Password Reset OTP"); 
				 
				         // set body of the email.
				         message.setText("OTP: "+otp);
				 
				         // Send email.
				         Transport.send(message);
				         System.out.println("Mail successfully sent");
				      }
				      catch (MessagingException mex) 
				      {
				         mex.printStackTrace();
				      }
					
					
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
