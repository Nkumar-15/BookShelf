package LibraryShelf;

import com.example.BookShelf_practise.BookShelf;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;




@WebServlet(name = "libsearch", value = "/libsearch")
public class LibSearch extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String sdata=request.getParameter("searchdata");
        LibraryShelf.searchdata=sdata;
        LibraryShelf.sortsearchflag=3;

        response.sendRedirect("AdminLibView.jsp");

    }
}
