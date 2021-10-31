package AdminAuth;

import com.example.BookShelf_practise.HelloServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet(name = "adminlogin",value = "/adminlogin")
public class AdminLogin extends HelloServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uname=request.getParameter("adminusername");
        String pass=request.getParameter("adminpass");
        boolean flag=false;

        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookshelf", "root", "toor");
            PreparedStatement stmt=conn.prepareStatement("SELECT * FROM admin WHERE Username=? and Password=?");
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
            session.setAttribute("adminusername",uname);
            response.sendRedirect("AdminDashboard.jsp");
        }
        else
        {
//            response.sendRedirect("index.jsp");
            PrintWriter out = response.getWriter();
            response.setContentType("text/html");
            out.println("<script type=\"text/javascript\">");
            out.println("location='index.jsp';");
            out.println("alert('Invalid login');");
            out.println("</script>");
        }


    }
}
