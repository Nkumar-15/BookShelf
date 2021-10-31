package com.example.BookShelf_practise;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "sorting", value = "/sort")
public class Sorting extends HttpServlet {
    BookShelf bookShelf=new BookShelf();
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        //extra
        bookShelf.sortval=request.getParameter("sortval");
        //System.out.print(f2.sortval);

        if(!bookShelf.sortval.equals("none"))
        {
            System.out.println(bookShelf.sortval);
            bookShelf.sortsearchflag=2;
        }
        else if (bookShelf.sortval.equals("none"))
        {
            BookShelf.searchdata=null;
            bookShelf.sortsearchflag=1;
        }
        response.sendRedirect("AdminDashboard.jsp");
        //request.setAttribute("sortflag",sortflag);


    }
}

