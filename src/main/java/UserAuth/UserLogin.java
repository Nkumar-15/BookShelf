package UserAuth;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet(name = "userlogin",value = "/userlogin")
public class UserLogin extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uname=request.getParameter("username");
        String pass=request.getParameter("userpassword");
        boolean flag=false;

        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookshelf", "root", "toor");
            PreparedStatement stmt=conn.prepareStatement("SELECT * FROM usertable WHERE Username=? and Password=?");
            stmt.setString(1,uname);
            stmt.setString(2,pass);
            ResultSet rs=stmt.executeQuery();
            flag=rs.next();


        }
        catch(Exception e)
        {
            //System.out.print(e);
            e.printStackTrace();
        }

        if (flag)
        {
            HttpSession session=request.getSession();
            session.setAttribute("username",uname);
            response.sendRedirect("UserDashboard.jsp");
        }
        else
        {
            response.sendRedirect("index.jsp");
        }

    }
}
