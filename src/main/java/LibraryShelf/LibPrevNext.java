package LibraryShelf;

import com.example.BookShelf_practise.BookShelf;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "libprevnext", value = "/libprevnext")
public class LibPrevNext extends HttpServlet {

    LibraryShelf ls=new LibraryShelf();
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {


        String val=request.getParameter("prevnext");
        //System.out.print(val);
        if(val.equals("next"))
        {
            next();
            if(ls.start>=0)
            {

                response.sendRedirect("AdminLibView.jsp");
            }


        }
        if(val.equals("prev"))
        {
            prev();
            response.sendRedirect("AdminLibView.jsp");
        }



    }

    void next()  {
        ls.start=ls.start+10;

    }

    void prev()  {

        ls.start=ls.start-10;
//        ls.end=ls.end-10;
        if(ls.start<0)
        {
            next();
        }

    }


}
