package com.example.BookShelf_practise;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "searching", value = "/search")
public class Searching extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String sdata=request.getParameter("searchdata");
        BookShelf.searchdata=sdata;
        BookShelf.sortsearchflag=3;

        response.sendRedirect("AdminDashboard.jsp");

    }
}
