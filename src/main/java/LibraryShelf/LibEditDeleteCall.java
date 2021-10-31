package LibraryShelf;

import Elasticsearch.Retrivedata;
import org.json.simple.JSONObject;

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


@WebServlet(name = "libeditdelcall",value = "/libeditdelcall")
public class LibEditDeleteCall extends HttpServlet {

    public static int Bookid;
    public static String title;
    public static String authorname;
    public static String publishername;
    public static String publisheddate;
    public static String genre;
    public static String category;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bookid=request.getParameter("bookid");
        String editdelopt=request.getParameter("editdel");
        if(editdelopt.equals("Edit"))
        {
            for(JSONObject book: Retrivedata.booksdata)
            {
                if(Integer.parseInt(bookid)==Integer.parseInt(book.get("bookid").toString()))
                {

                    Bookid= Integer.parseInt(book.get("bookid").toString());
                    title= (String) book.get("title");
                    authorname= (String) book.get("authorname");
                    publishername= (String) book.get("publishername");
                    publisheddate= (String) book.get("publisheddate");
                    genre= (String) book.get("genre");
                    try
                    {
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookshelf", "root", "toor");
                        PreparedStatement stmt=conn.prepareStatement("SELECT librarydata.Category FROM librarydata WHERE librarydata.bookid=?");
                        stmt.setInt(1,Integer.parseInt(bookid));
                        ResultSet rs=stmt.executeQuery();
                        while(rs.next())
                        {
                            category=rs.getString(1);
                        }
                    }
                    catch(Exception e)
                    {
                        //System.out.print(e);
                        e.printStackTrace();

                    }

                }
            }

//            try
//            {
//                Class.forName("com.mysql.cj.jdbc.Driver");
//                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookshelf", "root", "toor");
//                PreparedStatement stmt=conn.prepareStatement("SELECT *,librarydata.Category FROM booksdata JOIN librarydata ON librarydata.bookid=booksdata.bookid WHERE librarydata.bookid=?");
//                stmt.setInt(1,Integer.parseInt(bookid));
//                ResultSet rs=stmt.executeQuery();
//                while(rs.next())
//                {
//                    Bookid=rs.getInt(1);
//                    title=rs.getString(2);
//                    authorname=rs.getString(3);
//                    publishername=rs.getString(4);
//                    publisheddate=rs.getString(5);
//                    genre=rs.getString(6);
//                    category=rs.getString(9);
//                }
//            }
//            catch(Exception e)
//            {
//                //System.out.print(e);
//                e.printStackTrace();
//
//            }
            response.sendRedirect("LibEditDelete.jsp");
        }
        else if(editdelopt.equals("Delete"))
        {
            try
            {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookshelf", "root", "toor");
                PreparedStatement stmt=conn.prepareStatement("DELETE FROM librarydata WHERE bookid=?");
                stmt.setInt(1,Integer.parseInt(bookid));
                stmt.executeUpdate();
            }
            catch(Exception e)
            {
                //System.out.print(e);
                e.printStackTrace();

            }
            response.sendRedirect("AdminLibView.jsp");
        }



    }
}
