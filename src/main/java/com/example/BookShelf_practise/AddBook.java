package com.example.BookShelf_practise;

import Elasticsearch.Booksdata;
import Elasticsearch.ESConnect;
import Elasticsearch.Retrivedata;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

//import org.elasticsearch.index.mapper.ObjectMapper;

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
import java.util.ArrayList;

@WebServlet(name = "addbook",value = "/addbook")
public class AddBook extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String bookname=request.getParameter("title");
        String authorname=request.getParameter("authorname");
        String publishername=request.getParameter("publishername");
        String publishedyear=request.getParameter("publisherdate");
        String genre=request.getParameter("genre");
        ArrayList<Booksdata> booksdata=new ArrayList<Booksdata>();
        int flag=0;
        Booksdata bsd = new Booksdata(Retrivedata.totalvalues+1,bookname,authorname,publishername,publishedyear,genre);

        ESConnect esConnect=new ESConnect();
        booksdata.add(bsd);
        flag=esConnect.adddata(booksdata);
        esConnect.refreshdata();
        booksdata.clear();
        if(flag>0)
        {
            response.sendRedirect("AdminDashboard.jsp");
        }
        else
        {
            PrintWriter out = response.getWriter();
            response.setContentType("text/html");
            out.println("<script type=\"text/javascript\">");
            out.println("location='AddBook.jsp';");
            out.println("alert('Title Already exist');");
            out.println("</script>");
        }



    }
}
