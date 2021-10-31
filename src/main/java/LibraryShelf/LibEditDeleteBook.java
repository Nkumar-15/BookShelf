package LibraryShelf;

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
import java.sql.ResultSet;

@WebServlet(name = "libeditbook",value = "/libeditbook")
public class LibEditDeleteBook extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String bookname=request.getParameter("title");
//        String authorname=request.getParameter("authorname");
//        String publishername=request.getParameter("publishername");
//        String publishedyear=request.getParameter("publisherdate");
//        String genre=request.getParameter("genre");
        String category=request.getParameter("category");
        int flag=0;

//        System.out.println(bookname);
//        System.out.println(authorname);
//        System.out.println(publishername);
//        System.out.println(publishedyear);
//        System.out.println(genre);
        System.out.println(LibEditDeleteCall.Bookid);
        ResultSet existflag=null;
        boolean eflag=false;


        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookshelf", "root", "toor");

            PreparedStatement stmt1=conn.prepareStatement("SELECT * FROM librarydata where Category=?");
            stmt1.setString(1,category);
            existflag=stmt1.executeQuery();
            eflag=existflag.next();
//            System.out.println("existflag= "+existflag);
            if(eflag==false)            //Checking whether it is in Library data or not if not we can add columns in user and admin lib access tables
            {
                System.out.println("hello in userlibaccess");
                PreparedStatement stmt2=conn.prepareStatement("ALTER TABLE userlibaccess ADD "+category+" BOOLEAN DEFAULT 0");
//                stmt2.setString(1,category);
                stmt2.executeUpdate();

                PreparedStatement stmt3=conn.prepareStatement("INSERT INTO adminlibdata VALUES(null,?)");
                stmt3.setString(1,category);
                stmt3.executeUpdate();

            }

            PreparedStatement stmt4=conn.prepareStatement("SELECT libid FROM adminlibdata WHERE Adminlibraries=?");
            stmt4.setString(1,category);
            ResultSet rs=stmt4.executeQuery();

            while (rs.next())
            {
                PreparedStatement stmt=conn.prepareStatement("UPDATE librarydata SET Category=?,libid=? WHERE bookid=?");
                stmt.setString(1,category);
                stmt.setInt(2,rs.getInt(1));
                stmt.setInt(3,LibEditDeleteCall.Bookid);
                flag=stmt.executeUpdate();
            }


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
            out.println("location='AdminLibView.jsp';");
            out.println("alert('Successful Updated');");
            out.println("</script>");
        }
        else
        {
            PrintWriter out = response.getWriter();
            response.setContentType("text/html");
            out.println("<script type=\"text/javascript\">");
            out.println("location='LibEditDelete.jsp';");
            out.println("alert('Edit Unsuccessful');");
            out.println("</script>");
        }
    }
}
