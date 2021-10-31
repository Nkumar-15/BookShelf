<%@ page import="java.sql.ResultSet" %>
<%@ page import="UserRequest.Requestcall" %><%--
  Created by IntelliJ IDEA.
  User: user
  Date: 01-06-2021
  Time: 01:59 AM
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
    if(session.getAttribute("username")==null)
    {
        response.sendRedirect("index.jsp");
    }
    String username= (String) session.getAttribute("username");
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
    <a href="UserDashboard.jsp"><i class="fas fa-boxes"></i><span>Shelf</span></a>
    <a href="UserRequest.jsp"><i class="fas fa-tasks"></i><span>Request</span></a>
    <a href="UserProfile.jsp"><i class="fas fa-swatchbook"></i><span>Profile</span></a>
    <%--    <a href="#"><i class="fas fa-sliders-h"></i><span>Settings</span></a>--%>
</div>
<!--sidebar end-->

    <div class="content">
        <div  class="row">
            <%
                ResultSet rs=null;
                Requestcall requestcall=new Requestcall();
                rs= requestcall.showlib();
                int flag=0;
                while(rs.next())
                { flag=1;
            %>
            <div style="margin-top: 20px;" class="col-sm-6">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title"><%= rs.getString(1) %></h5>
<%--                        <p class="card-text">User can request here...</p>--%>
                        <p class="card-text">Status :<% int status=requestcall.requeststatus(username,rs.getString(1)); if(status==1){%>Waiting For Approval<%} else if(status==2){%>Permission Granted<%}else if(status==3){%> Permission Denied <%}else {%> <%}%></p>
                        <form action="requestcall" method="post">
                            <input type="text" name="category" value="<%= rs.getString(1) %>" hidden>
                            <input type="text" name="username" value="<%=username%>" hidden>
                            <%
                                if(status==1){%>
                                        <a href="#" class="btn btn-success" >Request sent</a>
                                <%} else if(status==2){%>
                                        <button class="btn btn-primary" hidden>Request</button>
                                <%}
                                else if(status==3){%>
                                        <button class="btn btn-primary">Request Again</button>
                                         <%}
                                else {%>
                                        <button class="btn btn-primary">Request</button>
                                <%}%>
                        </form>
                    </div>
                </div>
            </div>

            <%
                }
                if(flag==0){

            %>
            <h1 style="color: #22242A;margin: 50px;">No libraries found...</h1>
            <%
                }
            %>

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
