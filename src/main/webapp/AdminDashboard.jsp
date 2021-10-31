<%@ page import="JSON.Jsonwrite" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="JSON.CallJSON" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="com.example.BookShelf_practise.BookShelf" %>
<%@ page import="Elasticsearch.Retrivedata" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="org.json.simple.JSONObject" %>
<!DOCTYPE html>
<html lang="en" dir="ltr">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Book Shelf</title>
    <link rel="icon" href="img/book.png"size="64x64">
    <link rel="stylesheet" href="./css/style.css">
    <link rel="stylesheet" href="./css/table.css">

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.12.1/css/all.min.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js" charset="utf-8"></script>
</head>
<body>
    <%
        response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");
        if(session.getAttribute("adminusername")==null)
        {
            response.sendRedirect("index.jsp");
        }

        if(CallJSON.count==0)
        {
            CallJSON jsc=new CallJSON();
            CallJSON.count++;
        }

        ResultSet rs=null;
        BookShelf bookShelf=new BookShelf();
        //Retrivedata.booksdata.clear();
        Retrivedata retriveData=new Retrivedata();
        retriveData.retrivedata();
//        if(BookShelf.end>Retrivedata.booksdata.size())
//        {
//            BookShelf.end=Retrivedata.booksdata.size();
//        }
        System.out.println(BookShelf.start);
//        System.out.println(BookShelf.end);
        if(BookShelf.sortsearchflag ==1)
        {
            bookShelf.display();

            //System.out.println("In display");

        }
        else if(BookShelf.sortsearchflag ==2)
        {
            bookShelf.sortdisplay();
            //System.out.println("In sortdisplay");
        }
        else if(BookShelf.sortsearchflag==3)
        {
            bookShelf.searchdisplay();
            BookShelf.sortsearchflag=1;
            //System.out.println("In searchdisplay");
        }
//        if(BookShelf.sortsearchflag ==1)
//        {
//
//            rs=bookShelf.display(BookShelf.start,BookShelf.end);
//            //System.out.println("In display");
//
//        }
//        else if(BookShelf.sortsearchflag ==2)
//        {
//            rs=bookShelf.sortdisplay(BookShelf.start,BookShelf.end);
//            //System.out.println("In sortdisplay");
//        }
//        else if(BookShelf.sortsearchflag==3)
//        {
//            rs=bookShelf.searchdisplay(BookShelf.start,BookShelf.end);
//            BookShelf.sortsearchflag=1;
//        }
        int flag=0;


    %>

    <input type="checkbox" id="check1">
    <!--header area start-->
    <header>
        <!-- <label for="check1">
          <i class="fas fa-bars" id="sidebar_btn"></i>
        </label> -->
        <div class="left_area">
            <h3><span>Book</span> Shelf</h3>
        </div>
        <div class="right_area">
            <form action="adminlogout" method="post">
                <button style="border: 0" class="logout_btn">Logout</button>
<%--                <a href="#" class="logout_btn">Logout</a>--%>
            </form>

        </div>
    </header>
    <!--header area end-->

    <!--sidebar start-->
    <div class="sidebar">
        <!-- <div class="profile_info">
          <img src="1.png" class="profile_image" alt="">
          <h4>Jessica</h4>
        </div> -->
        <a href="AdminDashboard.jsp"><i class="fas fa-boxes"></i><span>Shelf</span></a>
        <a href="LibraryShelf.jsp"><i class="fas fa-book-open"></i><span>Library</span></a>
        <a href="AdminRequest.jsp"><i class="far fa-bell"></i><span>Requests</span></a>
        <a href="AddBook.jsp"><i class="fas fa-swatchbook"></i><span>Add Book</span></a>
<%--        <a href="#"><i class="fas fa-sliders-h"></i><span>Settings</span></a>--%>
    </div>
    <!--sidebar end-->

    <div class="content">
        <div class="row">
            <div class="col-sm-6">
                <div class="card1">
                    <form action="search" method="post">
                        <input class="search" type="text" id="myInput" name="searchdata"  placeholder="Search for Knowledge..." title="Type in a name">
                        <button  type="submit" class="btn  btn-outline-dark search_button"  >Search</button>
                    </form>
                </div>
            </div>

            <div class="col-sm-6">
                <div class="card1">
                    <div class="dropdown">
                        <%--                            <button onclick="dropFunction()" class="dropbtn">choose</button>--%>
                        <%--                            <div id="myDropdown" class="dropdown-content">--%>
                        <%--                                <a href="#">Link 1</a>--%>
                        <%--                                <a href="#">Link 2</a>--%>
                        <%--                                <a href="#">Link 3</a>--%>
                        <%--                            </div>--%>
                        <form action="sort" method="POST">
                            <select class="selectsort" name="sortval"  onchange="this.form.submit()">
                                <option class="selectsortopt" hidden value="choose a value">Choose..</option>
                                <option class="selectsortopt" value="none">None</option>

                                <optgroup class="selectsortopt" label="Title">
                                    <option class="selectsortopt" value="title 1">Ascending Order</option>
                                    <option class="selectsortopt" value="title 2">Descending Order</option>
                                </optgroup>
                                <optgroup class="selectsortopt" label="Author Name">
                                    <option class="selectsortopt" value="authorname 1">Ascending Order</option>
                                    <option class="selectsortopt" value="authorname 2">Descending Order</option>
                                </optgroup>
                                <optgroup class="selectsortopt" label="Publisher Name">
                                    <option class="selectsortopt" value="publishername 1">Ascending Order</option>
                                    <option class="selectsortopt" value="publishername 2">Descending Order</option>
                                </optgroup>
                                <optgroup class="selectsortopt" label="Published Year">
                                    <option class="selectsortopt" value="publisheddate 1">Ascending Order</option>
                                    <option class="selectsortopt" value="publisheddate 2">Descending Order</option>
                                </optgroup>
                                <optgroup class="selectsortopt" label="Genre">
                                    <option class="selectsortopt" value="genre 1">Ascending Order</option>
                                    <option class="selectsortopt" value="genre 2">Descending Order</option>
                                </optgroup>
                            </select>

                        </form>
                    </div>
                </div>
            </div>
        </div>



        <form action="addtolibcall" method="post">

            <div class="row">
                <div class="col-sm-6">
                    <div class="card1">
                        <%--                    <p class="pathoffile" style="font-size: 20px"><%= f.ppath %></p>--%>

                    </div>

                </div>
                <div class="col-sm-6">
                    <div class="button_prevnextdir">
<%--                        <form action="" method="POST">--%>
                            <button type="submit" class="btn  btn-outline-info pathbutton1" name="adddel"  value="AddToLib">Add</button>
                            <button type="submit" class="btn  btn-outline-info pathbutton1" name="adddel"  value="Delete">Delete</button>
<%--                        </form>--%>

                    </div>
                </div>
            </div>

            <div class="table-wrapper">
                <table class="fl-table">
                    <thead>
                    <tr>
                        <th></th>
                        <th>Title</th>
                        <th>Author Name</th>
                        <th>Publisher Name</th>
                        <th>Published Year</th>
                        <th>Genre</th>
<%--                        <th>Add</th>--%>
                        <th>Edit</th>
<%--                        <th>Delete</th>--%>
                    </tr>
                    </thead>
                    <tbody>
                        <%
                           //retriveData.retrivedata();
//                        for(int i=BookShelf.start;i<BookShelf.end;i++)
                            int count=0;
                            for(int i=BookShelf.start;i<BookShelf.bookshelfbookdata.size();i++)
                            {
                                if(count==10)
                                    {
                                        break;
                                    }

                                JSONObject bookval=BookShelf.bookshelfbookdata.get(i);

                                System.out.println(bookval);
                                flag=1;
                    %>
                    <tr>
                        <td><input type="checkbox" name="booktitles" value="<%= bookval.get("bookid") %>"></td>
                        <td><%= bookval.get("title") %></td>
                        <td><%= bookval.get("authorname") %></td>
                        <td><%= bookval.get("publishername") %></td>
                        <td><%= bookval.get("publisheddate") %></td>
                        <td><%= bookval.get("genre") %></td>
                        <form action="editdelcall" method="post">
                            <input name="bookid" value="<%= bookval.get("bookid") %>" hidden>
                            <%--                            <td><button style="border: 0;" name="addeditdel" value="Addtolib"><i class="fas fa-cart-plus"></i></button></td>--%>
                            <td><button style="border: 0;" name="adddel" value="Edit"><i class="far fa-edit"></i></button></td>
                            <%--                            <td><button style="border: 0;" name="addeditdel" value="Delete"><i class="far fa-trash-alt"></i></button></td>--%>
                        </form>

                        <%--                    <td>Delete</td>--%>
                    </tr>
                        <%
                            count++;
                        }
                        if(flag==0)
                        {
                    %>
                    <tr>
                        <td>No Records Found</td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr>
                        <% } %>
                    <tbody>
<%--                    <tbody>--%>
<%--                    <%--%>
<%--                        while(rs.next()){--%>
<%--                            flag=1;--%>
<%--                    %>--%>
<%--                    <tr>--%>
<%--                        <td><input type="checkbox" name="booktitles" value="<%= rs.getInt(1) %>"></td>--%>
<%--                        <td><%= rs.getString(2) %></td>--%>
<%--                        <td><%= rs.getString(3) %></td>--%>
<%--                        <td><%= rs.getString(4) %></td>--%>
<%--                        <td><%= rs.getString(5) %></td>--%>
<%--                        <td><%= rs.getString(6) %></td>--%>
<%--                        <form action="editdelcall" method="post">--%>
<%--                            <input name="bookid" value="<%= rs.getString(1) %>" hidden>--%>
<%--&lt;%&ndash;                            <td><button style="border: 0;" name="addeditdel" value="Addtolib"><i class="fas fa-cart-plus"></i></button></td>&ndash;%&gt;--%>
<%--                            <td><button style="border: 0;" name="adddel" value="Edit"><i class="far fa-edit"></i></button></td>--%>
<%--&lt;%&ndash;                            <td><button style="border: 0;" name="addeditdel" value="Delete"><i class="far fa-trash-alt"></i></button></td>&ndash;%&gt;--%>
<%--                        </form>--%>

<%--    &lt;%&ndash;                    <td>Delete</td>&ndash;%&gt;--%>
<%--                    </tr>--%>
<%--                    <%--%>
<%--                        }--%>
<%--                        if(flag==0)--%>
<%--                        {--%>
<%--                    %>--%>
<%--                    <tr>--%>
<%--                        <td>No Records Found</td>--%>
<%--                        <td></td>--%>
<%--                        <td></td>--%>
<%--                        <td></td>--%>
<%--                        <td></td>--%>
<%--                        <td></td>--%>
<%--                        <td></td>--%>
<%--                    </tr>--%>
<%--                    <% } %>--%>
<%--                    <tbody>--%>
                </table>
            </div>
        </form>

        <!-- <div class="card">
          <p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged.</p>
        </div> -->
        <div class="button_prevnext">
            <form action="prev-next" method="POST">
                <button type="submit" class="btn btn-primary btn-sm btn1" name="prevnext"  value="prev">&#8592; Prev</button>
                <button type="submit" class="btn btn-secondary btn-sm btn2" name="prevnext"  value="next">Next &#8594;</button>
            </form>

        </div>

    </div>


<script type="text/javascript">
    $(document).ready(function(){
        $('.nav_btn').click(function(){
            $('.mobile_nav_items').toggleClass('active');
        });
    });
</script>

</body>
</html>
