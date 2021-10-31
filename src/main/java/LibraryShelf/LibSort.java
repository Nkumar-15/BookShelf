package LibraryShelf;

import com.example.BookShelf_practise.BookShelf;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "libsort", value = "/libsort")
public class LibSort extends HttpServlet {
    LibraryShelf libraryShelf=new LibraryShelf();
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        //extra
        libraryShelf.sortval=request.getParameter("sortval");
        //System.out.print(f2.sortval);

        if(!libraryShelf.sortval.equals("none"))
        {
            System.out.println(libraryShelf.sortval);
            libraryShelf.sortsearchflag=2;
        }
        else if (libraryShelf.sortval.equals("none"))
        {
            LibraryShelf.searchdata=null;
            libraryShelf.sortsearchflag=1;
        }
        response.sendRedirect("AdminLibView.jsp");
        //request.setAttribute("sortflag",sortflag);


    }
}

