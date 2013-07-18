package testPackage;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

import com.google.gson.Gson;

import coreservlets.*;

/** Simple servlet for testing. Makes use of a helper class. */

@WebServlet("/fisherman")
public class FishermanServlet extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		Gson gson = new Gson();
		String locality = request.getParameter("locality");

		// Get Data from DB
		Connection conn = null;
		Statement stmt = null;
		try {
		    System.out.println("Loading driver...");
		    Class.forName("com.mysql.jdbc.Driver");
		    System.out.println("Driver loaded!");
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/<dbname>", "<mysql-user>", "<password>");
			stmt = conn.createStatement();
			String sqlStr = "SELECT * FROM  <query>";
			ResultSet rset = stmt.executeQuery(sqlStr);
			conn.close();
		} catch (ClassNotFoundException e) {
		    throw new RuntimeException("Cannot find the driver in the classpath!", e);
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
		}
		// Fill object
		// Transform the object to json and add to the response
		PrintWriter out = response.getWriter();

		out.close();
	}
}
