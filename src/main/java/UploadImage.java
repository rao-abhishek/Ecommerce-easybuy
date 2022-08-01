import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.*;


import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;
 
@MultipartConfig

public class UploadImage extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try  {
            //fetch form data
            
            Part part = request.getPart("p_img");
            String ext = request.getParameter("p_img_ext");
            String pname = request.getParameter("pname");
            String price = request.getParameter("price");
            String pdesc = request.getParameter("pdesc");
            String sid = "s1234";
            String pid = "pid123";
            
            try {
            Class.forName("com.mysql.jdbc.Driver");
			
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:4000/easybuy","root","admin");
			
			PreparedStatement sql = con.prepareStatement("insert into products values(?,?,?,?,?,?)");
			PreparedStatement check_pid;
			ResultSet rs;
			
			SimpleDateFormat formatter = new SimpleDateFormat("yyyymmdd");
			Date date = new Date();
			do{
			String randomNum = Integer.toString(ThreadLocalRandom.current().nextInt(0, 1000 + 1));
			pid = "p"+formatter.format(date)+randomNum;
			
			String uids = "select count(*) from products where pid='"+pid+"';";
		
			check_pid = con.prepareStatement(uids);
			rs = check_pid.executeQuery();
			rs.next();
			}while(rs.getInt(1)>0);         
            
			sql.setString(1, pid);
			sql.setString(2, pname);
			sql.setString(3, price);
			sql.setString(4, sid);
			sql.setString(5, pdesc);
			sql.setString(6, ext);
			
			int i = sql.executeUpdate();
			
			if(i>0)
			{
				System.out.println("Product Uploaded to Database");
			}

            }
            catch(Exception e)
            {
            	   e.printStackTrace();
            }
            
            
            String des_path = "C:\\Users\\Abhishek Rao\\eclipse-workspace\\fulltest\\src\\main\\webapp\\product_img\\"+pid+"."+ext;
            
            //String fileName = part.getName();
            //String path = getServletContext().getRealPath("/"+File.separator+fileName);
            
           //System.out.println(path);
           //System.out.println(Paths.get(path));
            
            System.out.println(part);
            
            InputStream is = part.getInputStream();
            Files.copy(is,Paths.get(des_path));
           // boolean test = uploadFile(is,des_path);
          
                System.out.println("Product Image uploaded");
         
                response.sendRedirect("product.jsp?pid="+pid);
           
            
           
        }
        catch(Exception e)
        {
        	   e.printStackTrace();
        }
    }
    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        
        
    }
 
    // </editor-fold>
 
}