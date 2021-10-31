package AdminRequest;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet(name = "adminrequestcall",value = "/adminrequestcall")
public class AdminRequestcall extends HttpServlet {


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username=request.getParameter("username");
        String category=request.getParameter("category");
        String decision=request.getParameter("decision");
        if(decision.equals("accept"))
        {
            try
            {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookshelf", "root", "toor");
                PreparedStatement stmt=conn.prepareStatement("UPDATE requesttable SET Approval=? WHERE Username=? and category=? and Approval=?");
                stmt.setInt(1,2);
                stmt.setString(2,username);;
                stmt.setString(3,category);
                stmt.setInt(4,1);
                stmt.executeUpdate();

                PreparedStatement stmt2=conn.prepareStatement("UPDATE userlibaccess SET "+category+"=? WHERE Username=?");
                //stmt2.setString(1,category);
                stmt2.setBoolean(1,true);
                stmt2.setString(2,username);
                stmt2.executeUpdate();


            }
            catch(Exception e)
            {
                //System.out.print(e);
                e.printStackTrace();

            }
            response.sendRedirect("AdminRequest.jsp");
        }
        else  if(decision.equals("reject"))
        {
            try
            {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookshelf", "root", "toor");
                PreparedStatement stmt=conn.prepareStatement("UPDATE requesttable SET Approval=? WHERE Username=? and category=? and Approval=?");
                stmt.setInt(1,3);
                stmt.setString(2,username);;
                stmt.setString(3,category);
                stmt.setInt(4,1);
                stmt.executeUpdate();



            }
            catch(Exception e)
            {
                //System.out.print(e);
                e.printStackTrace();

            }
            response.sendRedirect("AdminRequest.jsp");
        }
    }

    public ResultSet showrequest()
    {
        ResultSet rs=null;
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookshelf", "root", "toor");
            PreparedStatement stmt=conn.prepareStatement("SELECT DISTINCT * FROM requesttable where Approval=?");
            stmt.setInt(1,1);
            rs=stmt.executeQuery();

            return rs;


        }
        catch(Exception e)
        {
            //System.out.print(e);
            e.printStackTrace();

        }
        return rs;
    }
}
