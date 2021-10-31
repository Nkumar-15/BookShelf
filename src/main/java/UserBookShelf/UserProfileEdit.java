package UserBookShelf;

import com.example.BookShelf_practise.EditDeleteCall;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;


@WebServlet(name = "userprofileedit",value = "/userprofileedit")
public class UserProfileEdit extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uname=request.getParameter("Username");
        String name=request.getParameter("name");
        String upassword=request.getParameter("password");

        int flag=0;

        System.out.println(uname);
        System.out.println(name);
        System.out.println(upassword);

        System.out.println(UserProfile.username);


        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookshelf", "root", "toor");
            PreparedStatement stmt=conn.prepareStatement("UPDATE usertable SET Username=?, Name=?, Password=? WHERE Username=?");
            stmt.setString(1,uname);
            stmt.setString(2,name);
            stmt.setString(3,upassword);
            stmt.setString(4,UserProfile.username);
            flag=stmt.executeUpdate();

        }
        catch(Exception e)
        {
            //System.out.print(e);
            e.printStackTrace();

        }
        if(flag>0)
        {
            //response.sendRedirect("AdminDashboard.jsp");
            PrintWriter out = response.getWriter();
            response.setContentType("text/html");
            out.println("<script type=\"text/javascript\">");
            out.println("location='UserProfile.jsp';");
            out.println("alert('Successful Updated');");
            out.println("</script>");
        }
        else
        {
            PrintWriter out = response.getWriter();
            response.setContentType("text/html");
            out.println("<script type=\"text/javascript\">");
            out.println("location='UserProfile.jsp';");
            out.println("alert('Edit Unsuccessful');");
            out.println("</script>");
        }
    }
}