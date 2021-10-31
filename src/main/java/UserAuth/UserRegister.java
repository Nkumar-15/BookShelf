package UserAuth;

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
import java.sql.ResultSet;

@WebServlet(name = "userreg",value = "/userreg")
public class UserRegister extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uname=request.getParameter("username");
        String name=request.getParameter("name");
        String pass=request.getParameter("userpassword");
        int flag=register(uname,name,pass);
        if(flag>0)
        {
            //response.sendRedirect("index.jsp");
            PrintWriter out = response.getWriter();
            response.setContentType("text/html");
            out.println("<script type=\"text/javascript\">");
            out.println("location='index.jsp';");
            out.println("alert('Registered Successfully');");
            out.println("</script>");
        }
        else
        {
            //response.sendRedirect("index.jsp");
            PrintWriter out = response.getWriter();
            response.setContentType("text/html");
            out.println("<script type=\"text/javascript\">");
            out.println("location='index.jsp';");
            out.println("alert('Registered Unsuccessful');");
            out.println("</script>");
        }





    }


    int register(String uname,String name,String pass)
    {
        int flag=0;
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookshelf", "root", "toor");
            PreparedStatement stmt=conn.prepareStatement("INSERT INTO usertable VALUES (?,?,?);");
            stmt.setString(1,uname);
            stmt.setString(2,name);
            stmt.setString(3,pass);
            flag=stmt.executeUpdate();



            PreparedStatement stmt1=conn.prepareStatement("INSERT INTO userlibaccess (Username) VALUES (?)");
            stmt1.setString(1,uname);
            stmt1.executeUpdate();

            return flag;
        }
        catch(Exception e)
        {
            //System.out.print(e);
            e.printStackTrace();
        }
        return flag;
    }
}
