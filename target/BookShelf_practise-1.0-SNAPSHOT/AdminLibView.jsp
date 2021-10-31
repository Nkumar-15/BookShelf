<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 31-05-2021
  Time: 03:04 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="JSON.Jsonwrite" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="JSON.CallJSON" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="com.example.BookShelf_practise.BookShelf" %>
<%@ page import="LibraryShelf.LibraryShelf" %>
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

//    if(CallJSON.count==0)
//    {
//        CallJSON jsc=new CallJSON();
//        CallJSON.count++;
//    }

    ResultSet rs=null;
    LibraryShelf libraryShelf=new LibraryShelf();
    //LibraryShelf.libbookdata.clear();
    libraryShelf.libviewdata();

    if(LibraryShelf.sortsearchflag ==1)
    {

        libraryShelf.display();

        //System.out.println("In display");

    }
    else if(LibraryShelf.sortsearchflag ==2)
    {
        libraryShelf.sortdisplay();
        //System.out.println("In sortdisplay");
    }
    else if(LibraryShelf.sortsearchflag==3)
    {
        libraryShelf.searchdisplay();
        LibraryShelf.sortsearchflag=1;
    }
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
                <form action="libsearch" method="post">
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
                    <form action="libsort" method="POST">
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

        <div class="col-sm-6">
            <div class="card1">
                <h3>Current Library : <span style="color: #22242A"><%= LibraryShelf.category %></span></h3>
            </div>
        </div>
    </div>

    <div class="table-wrapper">
        <table class="fl-table">
            <thead>
            <tr>
                <th>Title</th>
                <th>Author Name</th>
                <th>Publisher Name</th>
                <th>Published Year</th>
                <th>Genre</th>
<%--                <th>Category</th>--%>
                <th>Edit</th>
                <th>Delete</th>
            </tr>
            </thead>
            <tbody>
                <%
                int count=0;
                    for(int i=LibraryShelf.start;i<LibraryShelf.libviewbookdata.size();i++)
                    {
                        if(count==10)
                            {
                                break;
                            }
                        JSONObject bookval=LibraryShelf.libviewbookdata.get(i);
                        System.out.println("Bookval in admil lib = "+bookval);
                        flag=1;
                %>
            <tr>
                <td><%= bookval.get("title")%></td>
                <td><%= bookval.get("authorname") %></td>
                <td><%= bookval.get("publishername") %></td>
                <td><%= bookval.get("publisheddate") %></td>
                <td><%= bookval.get("genre") %></td>
<%--                <td><%= rs.getString(6) %></td>--%>

                <form action="libeditdelcall" method="post">
                    <input name="bookid" value="<%= bookval.get("bookid") %>" hidden>
                    <td><button style="border: 0;" name="editdel" value="Edit"><i class="far fa-edit"></i></button></td>
                    <td><button style="border: 0;" name="editdel" value="Delete"><i class="far fa-trash-alt"></i></button></td>
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
        </table>
    </div>

    <!-- <div class="card">
      <p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged.</p>
    </div> -->
    <div class="button_prevnext">
        <form action="libprevnext" method="POST">
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
