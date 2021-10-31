package UserBookShelf;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserProfile extends HttpServlet {
    public static String username;
    public ResultSet userpro(String uname)  {


        username=uname;
        ResultSet rs = null;
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookshelf", "root", "toor");
            PreparedStatement stmt=conn.prepareStatement("SELECT * FROM usertable WHERE Username=?");
            stmt.setString(1,username);
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

