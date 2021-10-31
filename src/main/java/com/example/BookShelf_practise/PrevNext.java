package com.example.BookShelf_practise;



import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "prevnext", value = "/prev-next")
public class PrevNext extends HttpServlet {

    BookShelf bs=new BookShelf();
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {


        String val=request.getParameter("prevnext");
        //System.out.print(val);
        if(val.equals("next"))
        {
            next();
            if(bs.start>=0)
            {

                response.sendRedirect("AdminDashboard.jsp");
            }


        }
        if(val.equals("prev"))
        {
            prev();
            response.sendRedirect("AdminDashboard.jsp");
        }



    }

    void next()  {
        bs.start=bs.start+10;

    }

    void prev()  {

        bs.start=bs.start-10;
//        bs.end=bs.end-10;
        if(bs.start<0)
        {
            next();
        }
    }


}
