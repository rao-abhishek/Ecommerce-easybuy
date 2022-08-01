

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.*;


@WebServlet("/AllProducts")
public class AllProducts extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:4000/easybuy","root","admin");
			
			ResultSet rs;
			
			PreparedStatement sql = con.prepareStatement("select * from products;");
			
			rs = sql.executeQuery();
			
			//response.getWriter().println(rs);
			
			String in_html = "";
			
			/*while(rs.next())
			{
			in_html  = in_html + "  <a href=\"product.jsp?pid="+rs.getString(1)+"\"><div class=\"col-3\">  \r\n"
						+ "        <img src=\"product_img/"+rs.getString(1)+"."+rs.getString(6)+"\">\r\n"
						+ "           <div class=\"product-name\">"+rs.getString(2)+"</div>\r\n"
						+ "           <div class=\"product-price\">$ "+rs.getString(3)+"</div>\r\n"
						+ "        </div> </a>  ";
			}*/
			
			
			JSONArray json = new JSONArray();
			ResultSetMetaData rsmd = rs.getMetaData();
			while(rs.next()) {
			  int numColumns = rsmd.getColumnCount();
			  JSONObject obj = new JSONObject();
			  for (int i=1; i<=numColumns; i++) {
			    String column_name = rsmd.getColumnName(i);
			    obj.put(column_name, rs.getObject(column_name));
			  }
			  json.put(obj);
			}
			
			
			
			response.getWriter().println(json);
			
			//response.getWriter().println(in_html);
			
			
		}
		catch(Exception e)
		{
			System.out.println(e);
			e.printStackTrace();
			
		}
		
	}

}
