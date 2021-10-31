package UserBookShelf;

import Elasticsearch.Retrivedata;
import Elasticsearch.UserRetriveData;
import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

@WebServlet(name = "userlibdisplay",value = "/userlibdisplay")
public class UserBookShelf extends HttpServlet {
    public static int start=0;
    //public static int end=10;
    public static int sortsearchflag = 1;
    static String  sortval;
    public static String searchdata ;
    public static String category;

    public static ArrayList<JSONObject> userlibbookdata=new ArrayList<JSONObject>();

    public static ArrayList<JSONObject> userlibviewbookdata=new ArrayList<JSONObject>();

    public static ArrayList<Integer> bookids=new ArrayList<Integer>();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        category=request.getParameter("category");
        response.sendRedirect("UserViewLib.jsp");
    }

    public void display()
    {
        userlibviewbookdata.clear();
        userlibviewbookdata= (ArrayList<JSONObject>) userlibbookdata.clone();

    }

    public ArrayList<String> options(String username)
    {
        ArrayList<String> opt=new ArrayList<String>();
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookshelf", "root", "toor");
            PreparedStatement stmt=conn.prepareStatement("SHOW COLUMNS FROM userlibaccess");
            ResultSet rs;
            rs=stmt.executeQuery();
            rs.next();
            while(rs.next()) {
                PreparedStatement stmt1 = conn.prepareStatement("SELECT "+rs.getString(1)+" FROM userlibaccess where Username=?");
//                stmt1.setString(1, rs.getString(1));
                System.out.println("Colums=" + rs.getString(1));
                stmt1.setString(1, username);
                ResultSet rs1;
                rs1 = stmt1.executeQuery();
//                rs1.next();
                while(rs1.next())
                {
//                    System.out.println(rs1.getBoolean(1));

                    if(rs1.getBoolean(1)==true)
                    {
                        opt.add(rs.getString(1));
                    }

                }

            }
            for(int i=0;i<opt.size();i++)
            {
                System.out.println("opt=+"+opt.get(i));
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


    public void sortdisplay() throws IOException {
        userlibviewbookdata.clear();
        String[] str=sortval.split(" ");
        System.out.println(str[0]);
        System.out.println(str[1]);
        UserRetriveData userRetriveData=new UserRetriveData();
        userlibviewbookdata=userRetriveData.userlibretrivesortdata(searchdata,str[0],Integer.parseInt(str[1]));

    }

    public void searchdisplay() throws IOException {
        userlibviewbookdata.clear();
        UserRetriveData userRetriveData=new UserRetriveData();
        userlibviewbookdata=userRetriveData.userlibretrivesearchdata(searchdata);

    }



    public void userlibviewdata()
    {
        //libbookdata.clear();
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
                        userlibbookdata.add(book);
                        System.out.println("Bookdata = "+book);
                        break;

                    }
                }

            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }




}



