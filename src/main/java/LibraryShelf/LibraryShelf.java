package LibraryShelf;

import Elasticsearch.Retrivedata;
import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Signature;
import java.sql.*;
import java.util.ArrayList;

@WebServlet(name = "adminlibdisplay",value = "/adminlibdisplay")
public class LibraryShelf extends HttpServlet {
    public static int start=0;
//    public static int end=10;
    public static int sortsearchflag = 1;
    static String  sortval;
    public static String searchdata ;
    public static String category ;

    public static ArrayList<JSONObject> libbookdata=new ArrayList<JSONObject>();

    public static ArrayList<JSONObject> libviewbookdata=new ArrayList<JSONObject>();

    public static ArrayList<Integer> bookids=new ArrayList<Integer>();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String viewdel=request.getParameter("viewtable");
        category=request.getParameter("category");
        if(viewdel.equals("Delete"))
        {
            deletelibaccess();
            response.sendRedirect("LibraryShelf.jsp");
        }
        else if(viewdel.equals("View"))
        {
            libbookdata.clear();
            response.sendRedirect("AdminLibView.jsp");
        }
        else if(viewdel.equals("Viewuser"))
        {
            response.sendRedirect("Userslist.jsp");
        }


    }


    public void display()
    {
        System.out.println("libbookdata size = "+libbookdata.size());
        libviewbookdata.clear();
        System.out.println("libbookdata size = "+libbookdata.size());
        System.out.println("hello in lib dispaly");
        System.out.println("libbookdata size = "+libbookdata.size());
        libviewbookdata= (ArrayList<JSONObject>) libbookdata.clone();
        System.out.println("libviewbookdata size = "+libviewbookdata.size());

    }


    public void sortdisplay() throws IOException {
        libviewbookdata.clear();
        String[] str=sortval.split(" ");
        System.out.println(str[0]);
        System.out.println(str[1]);
        Retrivedata retrivedata=new Retrivedata();
        libviewbookdata=retrivedata.adminlibretrivesortdata(searchdata,str[0],Integer.parseInt(str[1]));
    }

    public void searchdisplay() throws IOException {
        libviewbookdata.clear();
        Retrivedata retrivedata=new Retrivedata();
        libviewbookdata=retrivedata.adminlibretrivesearchdata(searchdata);


    }


    public ArrayList<String> options()
    {
        ArrayList<String> opt=new ArrayList<String>();
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookshelf", "root", "toor");
            PreparedStatement stmt=conn.prepareStatement("SELECT adminlibraries FROM adminlibdata");
            ResultSet rs;
            rs=stmt.executeQuery();
            while(rs.next())
            {
                opt.add(rs.getString(1));
            }
//            while(rs.next()) {
//                PreparedStatement stmt1 = conn.prepareStatement("SELECT "+rs.getString(1)+" FROM userlibaccess where Username=?");
////                stmt1.setString(1, rs.getString(1));
//                System.out.println("Colums=" + rs.getString(1));
//                stmt1.setString(1, username);
//                ResultSet rs1;
//                rs1 = stmt1.executeQuery();
////                rs1.next();
//                while(rs1.next())
//                {
////                    System.out.println(rs1.getBoolean(1));
//
//                    if(rs1.getBoolean(1)==true)
//                    {
//                        opt.add(rs.getString(1));
//                    }
//
//                }
//
//            }
            return opt;

        }
        catch(Exception e)
        {
            //System.out.print(e);
            e.printStackTrace();
        }

        return opt;
    }


    void deletelibaccess()
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookshelf", "root", "toor");
            PreparedStatement stmt=conn.prepareStatement("DELETE FROM adminlibdata Where Adminlibraries=?");
            stmt.setString(1,category);
            stmt.executeUpdate();

            PreparedStatement stmt2=conn.prepareStatement("ALTER TABLE userlibaccess DROP COLUMN "+category);
            stmt2.executeUpdate();

//            PreparedStatement stmt3=conn.prepareStatement("DELETE FROM requesttable WHERE Category=?");
//            stmt3.setString(1,category);
//            stmt3.executeUpdate();
        }
        catch(Exception e)
        {
            //System.out.print(e);
            e.printStackTrace();
        }
    }


    public void libviewdata()
    {
        libbookdata.clear();
        System.out.println(" Viewing Category = "+category);
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/bookshelf", "root", "toor");
            PreparedStatement stmt=conn.prepareStatement("SELECT bookid FROM librarydata JOIN adminlibdata ON adminlibdata.libid=librarydata.libid WHERE Adminlibraries=?");
            stmt.setString(1,category);

            bookids.clear();
            ResultSet rs=stmt.executeQuery();
            while(rs.next())
            {
                bookids.add(rs.getInt(1));
                System.out.println(" Viewing Category bookid = "+rs.getInt(1));
                for(JSONObject book: Retrivedata.booksdata)
                {
                    if(Integer.parseInt(book.get("bookid").toString())==rs.getInt(1))
                    {
                        libbookdata.add(book);
                        System.out.println("Bookdata = "+book);
                        break;

                    }
                }

            }
            System.out.println("libbookdata size in lib viewfunction = "+libbookdata.size());


        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet retriveuser()
    {
        ResultSet rs=null;
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/bookshelf", "root", "toor");

            PreparedStatement stmt=conn.prepareStatement("SELECT Username,Category,Approval FROM requesttable WHERE Category=?");
            stmt.setString(1,category);
             rs=stmt.executeQuery();

            return rs;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return rs;
    }
}



