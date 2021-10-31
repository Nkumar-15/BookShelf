package com.example.BookShelf_practise;

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
import java.util.ArrayList;

@WebServlet(name = "editdelcall",value = "/editdelcall")
public class EditDeleteCall extends HttpServlet {

    public static int bookid;
    public static String title;
    public static String authorname;
    public static String publishername;
    public static String publisheddate;
    public static String genre;



    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bookid=request.getParameter("bookid");
        String editdelopt=request.getParameter("adddel");
        if(editdelopt.equals("Edit"))
        {
            for(JSONObject book: Retrivedata.booksdata)
            {
                if(Integer.parseInt(book.get("bookid").toString())==Integer.parseInt(bookid))
                {
                    this.bookid= Integer.parseInt(book.get("bookid").toString());
                    title= (String) book.get("title");
                    authorname= (String) book.get("authorname");
                    publishername= (String) book.get("publishername");
                    publisheddate= (String) book.get("publisheddate");
                    genre= (String) book.get("genre");
                }
            }
//            try
//            {
//                Class.forName("com.mysql.cj.jdbc.Driver");
//                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookshelf", "root", "toor");
//                PreparedStatement stmt=conn.prepareStatement("SELECT * FROM booksdata WHERE bookid=?");
//                stmt.setInt(1,Integer.parseInt(bookid));
//                ResultSet rs=stmt.executeQuery();
//                while(rs.next())
//                {
//                    this.bookid= rs.getInt(1);
//                    title=rs.getString(2);
//                    authorname=rs.getString(3);
//                    publishername=rs.getString(4);
//                    publisheddate=rs.getString(5);
//                    genre=rs.getString(6);
//                }
//            }
//            catch(Exception e)
//            {
//                //System.out.print(e);
//                e.printStackTrace();
//
//            }
            response.sendRedirect("EditDelete.jsp");
        }
//        else if(editdelopt.equals("Delete"))
//        {
//            try
//            {
//                Class.forName("com.mysql.cj.jdbc.Driver");
//                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookshelf", "root", "toor");
//                PreparedStatement stmt=conn.prepareStatement("DELETE FROM booksdata WHERE Title=?");
//                stmt.setString(1,titlename);
//                stmt.executeUpdate();
//            }
//            catch(Exception e)
//            {
//                //System.out.print(e);
//                e.printStackTrace();
//
//            }
//            response.sendRedirect("AdminDashboard.jsp");
//        }
//        else if(editdelopt.equals("Addtolib"))
//        {
//            try
//            {
//                Class.forName("com.mysql.cj.jdbc.Driver");
//                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookshelf", "root", "toor");
//                PreparedStatement stmt=conn.prepareStatement("SELECT * FROM booksdata WHERE Title=?");
//                stmt.setString(1,titlename);
//                ResultSet rs=stmt.executeQuery();
//                while(rs.next())
//                {
//                    title=rs.getString(1);
//                    authorname=rs.getString(2);
//                    publishername=rs.getString(3);
//                    publisheddate=rs.getString(4);
//                    genre=rs.getString(5);
//                }
//            }
//            catch(Exception e)
//            {
//                //System.out.print(e);
//                e.printStackTrace();
//
//            }
//            bookdetails.clear();
//
//
//
//            response.sendRedirect("AddToLib.jsp");
//        }


    }

    public void editcall(int bookid)
    {
        for(JSONObject book: Retrivedata.booksdata)
        {
            if(Integer.parseInt(book.get("bookid").toString())==bookid)
            {
                this.bookid= Integer.parseInt(book.get("bookid").toString());
                title= (String) book.get("title");
                authorname= (String) book.get("authorname");
                publishername= (String) book.get("publishername");
                publisheddate= (String) book.get("publisheddate");
                genre= (String) book.get("genre");
            }
        }

//        try
//        {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookshelf", "root", "toor");
//            PreparedStatement stmt=conn.prepareStatement("SELECT * FROM booksdata WHERE bookid=?");
//            stmt.setInt(1,bookid);
//            ResultSet rs=stmt.executeQuery();
//            while(rs.next())
//            {
//                this.bookid= rs.getInt(1);
//                title=rs.getString(2);
//                authorname=rs.getString(3);
//                publishername=rs.getString(4);
//                publisheddate=rs.getString(5);
//                genre=rs.getString(6);
//            }
//        }
//        catch(Exception e)
//        {
//            //System.out.print(e);
//            e.printStackTrace();
//
//        }
//        response.sendRedirect("EditDelete.jsp");
    }
}
