package UserBookShelf;

import com.example.BookShelf_practise.BookShelf;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;




@WebServlet(name = "userprevnext", value = "/userprevnext")
public class UserPrevNext extends HttpServlet {

    UserBookShelf bs=new UserBookShelf();
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {


        String val=request.getParameter("prevnext");
        //System.out.print(val);
        if(val.equals("next"))
        {
            next();
            if(bs.start>=0)
            {

                response.sendRedirect("UserViewLib.jsp");
            }


        }
        if(val.equals("prev"))
        {
            prev();
            response.sendRedirect("UserViewLib.jsp");
        }



    }

    void next()  {
        bs.start=bs.start+10;


    }

    void prev()  {

        bs.start=bs.start-10;

        if(bs.start<0)
        {
            next();
        }

    }


}
