<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 01-06-2021
  Time: 02:15 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="JSON.Jsonwrite" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="JSON.CallJSON" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="com.example.BookShelf_practise.BookShelf" %>
<%@ page import="UserBookShelf.UserBookShelf" %>
<%@ page import="java.util.ArrayList" %>
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
        if(session.getAttribute("username")==null)
        {
            response.sendRedirect("index.jsp");
        }


//        if(CallJSON.count==0)
//        {
//            CallJSON jsc=new CallJSON();
//            CallJSON.count++;
//        }

        ResultSet rs=null;
        UserBookShelf bookShelf=new UserBookShelf();

        String username=(String) session.getAttribute("username");


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
            <form action="userlogout" method="post">
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
        <a href="UserDashboard.jsp"><i class="fas fa-boxes"></i><span>Shelf</span></a>
        <a href="UserRequest.jsp"><i class="fas fa-tasks"></i><span>Request</span></a>
        <a href="UserProfile.jsp"><i class="fas fa-swatchbook"></i><span>Profile</span></a>
        <%--        <a href="#"><i class="fas fa-sliders-h"></i><span>Settings</span></a>--%>
    </div>
    <!--sidebar end-->

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
                options=bookShelf.options(username);
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
                        <form action="userlibdisplay" method="post">
                            <input type="text" name="category" value="<%= options.get(i) %>" hidden>
    <%--                        <input type="text" name="username" value="<%=rs.getString(1)%>" hidden>--%>
                            <button class="btn btn-outline-info" name="viewtable" value="accept">View</button>
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
