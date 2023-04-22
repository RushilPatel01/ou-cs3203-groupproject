package main.resources;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class Login_Servlet extends HttpServlet {
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        // Retrieve the data sent in the AJAX request
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        // Perform some processing or validation on the data
        // ...
        
        // Connect to the database using JDBC
        String jdbcUrl = "jdbc:sqlserver://bankmainproject.database.windows.net:1433";
        String DataBaseUsername = "clar0126@bankmainproject";
        String DataBasePassword = "Hoverboard1456!";
        Connection conn = DriverManager.getConnection(jdbcUrl, DataBaseUsername, DataBasePassword);
        
        // Perform some database operations using JDBC
        // ...
        
        // Send a response back to the client
        PrintWriter out = response.getWriter();
        out.println("success");
        out.close();
    }
}
