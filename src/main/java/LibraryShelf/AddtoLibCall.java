package LibraryShelf;

import Elasticsearch.Booksdata;
import Elasticsearch.ESConnect;
import Elasticsearch.Retrivedata;
import com.example.BookShelf_practise.EditDeleteCall;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHost;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.RescaleOp;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;

@WebServlet(name = "addtolibcall",value = "/addtolibcall")
public class AddtoLibCall extends HttpServlet {
    public static ArrayList<Integer> bookdetails=new ArrayList<Integer>();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String adddel=request.getParameter("adddel");
        System.out.println(adddel);
        if(adddel.equals("AddToLib"))
        {
            bookdetails.clear();
            if(request.getParameterValues("booktitles")==null)
            {
                PrintWriter out = response.getWriter();
                response.setContentType("text/html");
                out.println("<script type=\"text/javascript\">");
                out.println("location='AdminDashboard.jsp';");
                out.println("alert('Please Select the value');");
                out.println("</script>");
            }
            else
            {
                for(String bookid : request.getParameterValues("booktitles"))
                {
                    System.out.println(bookid);
                    bookdetails.add(Integer.parseInt(bookid));
                }
                response.sendRedirect("AddToLib.jsp");

            }
        }
        else if(adddel.equals("Delete"))
        {

            if(request.getParameterValues("booktitles")==null)
            {
                PrintWriter out = response.getWriter();
                response.setContentType("text/html");
                out.println("<script type=\"text/javascript\">");
                out.println("location='AdminDashboard.jsp';");
                out.println("alert('Please Select the value');");
                out.println("</script>");
            }
            else
            {
                int flag = 0;
                ESConnect esConnect=new ESConnect();
                for(String bookid : request.getParameterValues("booktitles")) {
                    flag = esConnect.deletedata(bookid);
                    try{
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookshelf", "root", "toor");
                        PreparedStatement stmt=conn.prepareStatement("DELETE FROM libdata WHERE bookid=?");
                        stmt.setInt(1,Integer.parseInt(bookid));
                        stmt.executeUpdate();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                esConnect.refreshdata();
                if (flag > 0) {
                    response.sendRedirect("AdminDashboard.jsp");
                }

            }
        }
        else if(adddel.equals("Edit"))
        {
            int bookid=Integer.parseInt(request.getParameter("bookid"));
            System.out.println(bookid);
            EditDeleteCall editDeleteCall=new EditDeleteCall();
            editDeleteCall.editcall(bookid);
            response.sendRedirect("EditDelete.jsp");

        }



    }

    public JSONObject bdetails(int bookid)
    {
        System.out.println("Hello in addtolib bdetails ");
        JSONObject bookval = null;
        for(JSONObject book:Retrivedata.booksdata)
        {
            System.out.println(book.get("bookid"));
            System.out.println(bookid);
            if(Integer.parseInt(book.get("bookid").toString())==bookid)
            {
                bookval=book;
                System.out.println(book);
                System.out.println(bookval);
                break;

            }
        }


        return bookval;
    }

    public ArrayList<String> options()
    {
        ArrayList<String> opt=new ArrayList<String>();
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookshelf", "root", "toor");
            PreparedStatement stmt=conn.prepareStatement("SELECT Adminlibraries from adminlibdata");
            ResultSet rs;
            rs=stmt.executeQuery();
            while(rs.next()) {
                opt.add(rs.getString(1));
            }
            return opt;

        }
        catch(Exception e)
        {
            //System.out.print(e);
            e.printStackTrace();
        }

        return opt;
    }





}
