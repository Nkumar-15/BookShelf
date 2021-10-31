package com.example.BookShelf_practise;

import Elasticsearch.Booksdata;
import Elasticsearch.ESConnect;
import Elasticsearch.Retrivedata;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHost;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
//import org.elasticsearch.common.collect.Map;
import org.elasticsearch.common.xcontent.XContentType;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

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
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "editbook",value = "/editbook")
public class EditDeleteBook extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bookname=request.getParameter("title");
        String authorname=request.getParameter("authorname");
        String publishername=request.getParameter("publishername");
        String publishedyear=request.getParameter("publisherdate");
        String genre=request.getParameter("genre");
        int flag=0;

        System.out.println(bookname);
        System.out.println(authorname);
        System.out.println(publishername);
        System.out.println(publishedyear);
        System.out.println(genre);
        System.out.println(EditDeleteCall.title);


        Booksdata bsd = new Booksdata(EditDeleteCall.bookid,bookname,authorname,publishername,publishedyear,genre);
        ESConnect esConnect=new ESConnect();
        flag=esConnect.updatedata(bsd);

        if(flag>0)
        {

            //response.sendRedirect("AdminDashboard.jsp");
            PrintWriter out = response.getWriter();
            response.setContentType("text/html");
            out.println("<script type=\"text/javascript\">");
            out.println("location='AdminDashboard.jsp';");
            out.println("alert('Successful Updated');");
            out.println("</script>");

        }
        else
        {
            PrintWriter out = response.getWriter();
            response.setContentType("text/html");
            out.println("<script type=\"text/javascript\">");
            out.println("location='EditDelete.jsp';");
            out.println("alert('Edit Unsuccessful');");
            out.println("</script>");
        }
    }
}
