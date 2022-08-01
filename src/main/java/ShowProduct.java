

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;


@WebServlet("/ShowProduct")
public class ShowProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:4000/easybuy","root","admin");
			
			ResultSet rs;
			
			String pid = request.getParameter("pid");
			
			PreparedStatement sql = con.prepareStatement("select * from products where pid=(?);");
			
			sql.setString(1, pid);
			
			rs = sql.executeQuery();
			
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
			
			
		}
		catch(Exception e)
		{
			System.out.println(e);
			e.printStackTrace();
			
		}
		
	}

}
