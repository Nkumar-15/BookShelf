<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="LibraryShelf.LibraryShelf" %><%--
  Created by IntelliJ IDEA.
  User: user
  Date: 02-06-2021
  Time: 02:38 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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

    LibraryShelf libraryShelf=new LibraryShelf();



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

<div class="content">
    <div class="row">
        <div class="col-sm-6">
            <div class="card1">
                <%--                <form action="usersearch" method="post">--%>
                <%--                    <input class="search" type="text" id="myInput" name="searchdata"  placeholder="Search for Knowledge.." title="Type in a name">--%>
                <%--                    <button  type="submit" class="btn  btn-outline-dark search_button"  >Search</button>--%>
                <%--                </form>--%>
                <p style="color: #19B3D3;margin-top: 20px;font-size: 25px;">Libraries Available:</p>
            </div>
        </div>
    </div>
    <div  class="row">
        <%
            int flag=0;
            ArrayList<String> options=new ArrayList<String>();
            options=libraryShelf.options();
            for(int i=0;i<options.size();i++)
            {
                flag=1;
        %>
        <div style="margin-top: 20px;" class="col-sm-6">
            <div class="card">
                <div class="card-body">
                    <h5 class="card-title"><%= options.get(i) %></h5>
                    <%--                        <p class="card-text">User can request here...</p>--%>
                    <p class="card-text">View Library Here...</p>
                    <form action="adminlibdisplay" method="post">
                        <input type="text" name="category" value="<%= options.get(i) %>" hidden>
                        <%--                        <input type="text" name="username" value="<%=rs.getString(1)%>" hidden>--%>
                        <button class="btn btn-outline-info" name="viewtable" value="View">View</button>
                        <button class="btn btn-outline-danger" name="viewtable" value="Delete">Delete</button>
                        <button class="btn btn-outline-info" name="viewtable" value="Viewuser">View User</button>
                    </form>
                </div>
            </div>
        </div>

        <%
            }
        %>


    </div>
        <%if(flag==0){

        %>
    <h1 style="color: #22242A;margin: 50px;">No Libraries Available...</h1>
        <%
            }
        %>


</body>
</html>
