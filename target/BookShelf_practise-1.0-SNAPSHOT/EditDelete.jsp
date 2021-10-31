<%@ page import="com.example.BookShelf_practise.EditDeleteCall" %><%--
  Created by IntelliJ IDEA.
  User: user
  Date: 29-05-2021
  Time: 05:46 PM
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
    <link rel="stylesheet" href="./css/addbook.css">

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.12.1/css/all.min.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js" charset="utf-8"></script>
    <style>

    </style>
</head>
<body>
<%
    response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");
    if(session.getAttribute("adminusername")==null)
    {
        response.sendRedirect("index.jsp");
    }
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
            <button style="border: 0;    width: 70px;" class="logout_btn">Logout</button>
            <%--    <input type="submit" class="logout_btn" value="Logout">--%>
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
<%--    <a href="#"><i class="fas fa-sliders-h"></i><span>Settings</span></a>--%>
</div>
<!--sidebar end-->

<div class="content">
    <div class="testbox">
        <form action="editbook" method="post">
            <div class="banner">
                <h1>New Member To The Shelf</h1>
            </div>
            <div class="colums">
                <div class="item">
                    <label for="">Book's Title<span>*</span></label>
                    <input id="" type="text" name="title" value="<%= EditDeleteCall.title %>" required/>
                </div>
                <div class="item">
                    <label for="">Author Name<span>*</span></label>
                    <input  type="text" name="authorname" value="<%= EditDeleteCall.authorname %>" required/>
                </div>
                <div class="item">
                    <label for="">Publisher Name<span>*</span></label>
                    <input  type="text"   name="publishername" value="<%= EditDeleteCall.publishername %>" required/>
                </div>
                <div class="item">
                    <label for="">Published Year<span>*</span></label>
                    <input type="text"   name="publisherdate" value="<%= EditDeleteCall.publisheddate %>" required/>
                </div>
                <div class="item">
                    <label for="">Genre<span>*</span></label>
                    <input i type="text"   name="genre" value="<%= EditDeleteCall.genre %>" required/>
                </div>

            </div>

            <div class="btn-block">
                <button type="submit" >Update</button>
<%--                <button type="submit" >Delete Book</button>--%>

            </div>
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
