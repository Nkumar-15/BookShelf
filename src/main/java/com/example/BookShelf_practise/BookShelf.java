package com.example.BookShelf_practise;

import Elasticsearch.Retrivedata;
import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class BookShelf extends HttpServlet {
    public static int start=0;
    //public static int end=10;
    public static int sortsearchflag = 1;
    static String  sortval;
    public static String searchdata ;

    public static ArrayList<JSONObject> bookshelfbookdata=new ArrayList<JSONObject>();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    public void display() throws IOException {
        bookshelfbookdata.clear();
        Retrivedata retrivedata=new Retrivedata();
        bookshelfbookdata=retrivedata.adminretrivedata();
    }


    public void sortdisplay() throws IOException {
        bookshelfbookdata.clear();
        String[] str=sortval.split(" ");
        System.out.println(str[0]);
        System.out.println(str[1]);
        Retrivedata retrivedata=new Retrivedata();
        bookshelfbookdata=retrivedata.adminretrivesortdata(searchdata,str[0],Integer.parseInt(str[1]));
    }

    public void searchdisplay() throws IOException {
        bookshelfbookdata.clear();
        Retrivedata retrivedata=new Retrivedata();
        bookshelfbookdata=retrivedata.adminretrivesearchdata(searchdata);
        //searchdata="";

    }





}



