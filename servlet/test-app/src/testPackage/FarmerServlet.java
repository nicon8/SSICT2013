package testPackage;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

import com.google.gson.Gson;

/** Simple servlet for testing. Generates HTML instead of plain
 *  text as with the HelloWorld servlet.
 */

@WebServlet("/farmer")
public class FarmerServlet extends HttpServlet {
  
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException {
	    doGetOrPost(req, resp);
	}
	
	@Override
	// This method is called by the servlet container to process a POST request.
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException {
	    doGetOrPost(req, resp);
	}
	
  public void doGetOrPost(HttpServletRequest request,
                    HttpServletResponse response)
      throws ServletException, IOException {
	  
	Gson gson = new Gson();
	String locality = request.getParameter("locality");
	
	
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();
    
    //Fill the farmer object
    FarmerResponse res = new FarmerResponse();
    res.suggestion = new String[]{"potato", "tomato","onion"};
    
    
    String output=(gson.toJson(res));
    out.append(output);
    out.close();
  }
}
