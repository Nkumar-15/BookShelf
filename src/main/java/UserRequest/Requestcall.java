package UserRequest;

import UserBookShelf.UserProfile;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

@WebServlet(name = "requestcall",value = "/requestcall")
public class Requestcall extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username=request.getParameter("username");
        String category=request.getParameter("category");
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookshelf", "root", "toor");
            PreparedStatement stmt1=conn.prepareStatement("SELECT libid FROM adminlibdata WHERE Adminlibraries=?");
            stmt1.setString(1,category);
            ResultSet rs=stmt1.executeQuery();
            while (rs.next())
            {
                PreparedStatement stmt=conn.prepareStatement("INSERT INTO requesttable (lib_id,Username,Category,Approval) VALUES (?,?,?,?)");
                stmt.setInt(1,rs.getInt(1));
                stmt.setString(2,username);
                stmt.setString(3,category);
                stmt.setInt(4,1);
                stmt.executeUpdate();
            }



        }
        catch(Exception e)
        {
            //System.out.print(e);
            e.printStackTrace();

        }

        response.sendRedirect("UserRequest.jsp");

    }

    public ResultSet showlib()
    {
        ResultSet rs=null;
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookshelf", "root", "toor");
            PreparedStatement stmt=conn.prepareStatement("SELECT Adminlibraries FROM adminlibdata");
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

    public int requeststatus(String username,String category)
    {
        int statusflag=0;

        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookshelf", "root", "toor");
            PreparedStatement stmt=conn.prepareStatement("SELECT * FROM requesttable WHERE Username=? and Category=?");
            stmt.setString(1,username);
            stmt.setString(2,category);
            ResultSet rs=stmt.executeQuery();
            while(rs.next())
            {
                statusflag=rs.getInt(4);
            }
            System.out.println("Statusflag= "+statusflag);
            return statusflag;



        }
        catch(Exception e)
        {
            //System.out.print(e);
            e.printStackTrace();

        }
        return statusflag;
    }
}
