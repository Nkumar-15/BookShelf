<%@ page import="LibraryShelf.LibEditDeleteCall" %>
<%@ page import="LibraryShelf.AddtoLibCall" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: user
  Date: 31-05-2021
  Time: 03:24 PM
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
<%--    <link rel="stylesheet" href="./css/addbook.css">--%>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.12.1/css/all.min.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js" charset="utf-8"></script>
    <style>
        html, body {
            min-height: 100%;
        }
        body, div, form, input, select, textarea, label, p {
            padding: 0;
            margin: 0;
            outline: none;
            font-family: var(--bs-font-sans-serif);    /*font-size: 14px;*/
            color: #666;
            line-height: 22px;
        }
        h1 {
            position: absolute;
            margin: 0;
            font-size: 40px;
            color: #fff;
            z-index: 2;
            line-height: 83px;
        }
        textarea {
            width: calc(100% - 12px);
            padding: 5px;
        }
        .testbox {
            display: flex;
            justify-content: center;
            align-items: center;
            height: inherit;
            padding: 20px;
        }
        .testbox form {
            width: 60%;
            padding: 20px;
            border-radius: 6px;
            background: #fff;
            box-shadow: 0 0 8px  #669999;
        }
        .banner {
            position: relative;
            height: 300px;
            background-image: url("https://images.unsplash.com/photo-1522211988038-6fcbb8c12c7e?ixid=MnwxMjA3fDB8MHxzZWFyY2h8MzB8fGxpYnJhcnl8ZW58MHx8MHx8&ixlib=rb-1.2.1&w=1000&q=80");
            background-size: cover;
            display: flex;
            justify-content: center;
            align-items: center;
            text-align: center;
        }
        .banner::after {
            content: "";
            background-color: rgba(0, 0, 0, 0.2);
            position: absolute;
            width: 100%;
            height: 100%;
        }
        input, select, textarea {
            margin-bottom: 10px;
            border: 1px solid #ccc;
            border-radius: 3px;
        }
        input {
            width: calc(100% - 10px);
            padding: 5px;
        }
        input[type="date"] {
            padding: 4px 5px;
        }
        textarea {
            width: calc(100% - 12px);
            padding: 5px;
        }
        .item:hover p, .item:hover i, .question:hover p, .question label:hover, input:hover::placeholder {
            color:  #669999;
        }
        .item input:hover, .item select:hover, .item textarea:hover {
            border: 1px solid transparent;
            box-shadow: 0 0 3px 0  #669999;
            color: #669999;
        }
        .item {
            position: relative;
            margin: 10px 0;
        }
        .item span {
            color: red;
        }
        .week {
            display:flex;
            justfiy-content:space-between;
        }
        .colums {
            display:flex;
            justify-content:space-between;
            flex-direction:row;
            flex-wrap:wrap;
        }
        .colums div {
            width:48%;
        }
        input[type="date"]::-webkit-inner-spin-button {
            display: none;
        }
        .item i, input[type="date"]::-webkit-calendar-picker-indicator {
            position: absolute;
            font-size: 20px;
            color:  #a3c2c2;
        }
        .item i {
            right: 1%;
            top: 30px;
            z-index: 1;
        }
        input[type=radio], input[type=checkbox]  {
            display: none;
        }
        label.radio {
            position: relative;
            display: inline-block;
            margin: 5px 20px 15px 0;
            cursor: pointer;
        }
        .question span {
            margin-left: 30px;
        }
        .question-answer label {
            display: block;
        }
        label.radio:before {
            content: "";
            position: absolute;
            left: 0;
            width: 17px;
            height: 17px;
            border-radius: 50%;
            border: 2px solid #ccc;
        }
        input[type=radio]:checked + label:before, label.radio:hover:before {
            border: 2px solid  #669999;
        }
        label.radio:after {
            content: "";
            position: absolute;
            top: 6px;
            left: 5px;
            width: 8px;
            height: 4px;
            border: 3px solid  #669999;
            border-top: none;
            border-right: none;
            transform: rotate(-45deg);
            opacity: 0;
        }
        input[type=radio]:checked + label:after {
            opacity: 1;
        }
        .flax {
            display:flex;
            justify-content:space-around;
        }
        .btn-block {
            margin-top: 10px;
            text-align: center;
        }
        button {
            width: 150px;
            padding: 10px;
            border: none;
            border-radius: 5px;
            background:  #669999;
            font-size: 16px;
            color: #fff;
            cursor: pointer;
        }
        button:hover {
            background:  #a3c2c2;
        }
        @media (min-width: 568px) {
            .name-item, .city-item {
                display: flex;
                flex-wrap: wrap;
                justify-content: space-between;
            }
            .name-item input, .name-item div {
                width: calc(50% - 20px);
            }
            .name-item div input {
                width:97%;}
            .name-item div label {
                display:block;
                padding-bottom:5px;
            }
        }
        .logout_btn{
            padding: 5px;
            background: #19B3D3;
            text-decoration: none;
            float: right;
            margin-top: -30px;
            margin-right: 40px;
            border-radius: 2px;
            font-size: 15px;
            font-weight: 600;
            color: #fff;
            transition: 0.5s;
            transition-property: background;
        }

        .logout_btn:hover{
            background: #0B87A6;
        }
        .chooselib{
            width: 29%;
            height: 50%;
            border-width: 5px;
            margin-top: 10px;

        }
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
        <form action="libeditbook" method="post">
            <div class="banner">
                <h1>New Member To The Shelf</h1>
            </div>
            <div class="colums">
                <div class="item">
                    <label for="">Book's Title</label>
                    <input id="" type="text" name="title" value="<%= LibEditDeleteCall.title %>" readonly/>
                </div>
                <div class="item">
                    <label for="">Author Name</label>
                    <input  type="text" name="authorname" value="<%= LibEditDeleteCall.authorname %>" readonly/>
                </div>
                <div class="item">
                    <label for="">Publisher Name</label>
                    <input  type="text"   name="publishername" value="<%= LibEditDeleteCall.publishername %>" readonly/>
                </div>
                <div class="item">
                    <label for="">Published Year</label>
                    <input type="text"   name="publisherdate" value="<%= LibEditDeleteCall.publisheddate %>" readonly/>
                </div>
                <div class="item">
                    <label for="">Genre</label>
                    <input i type="text"   name="genre" value="<%= LibEditDeleteCall.genre %>" readonly/>
                </div>

                <div class="item">
                    <label for=""> View Existing Libraries</label>
                    <select class="chooselib"><br>
                        <option class="chooselib" value="null" hidden>View</option>
                        <option class="chooselib" value="none">Libraries</option>
                        <%
                            AddtoLibCall addtoLibCall=new AddtoLibCall();

                            int dropflag=0;
                            ArrayList<String> opt=addtoLibCall.options();
                            for(int i=0;i<opt.size();i++)
                            {
                                dropflag=1;
                        %>
                        <optgroup class="chooselib" >
                            <option class="chooselib" value="<%=opt.get(i)%>"><%=opt.get(i)%></option>
                        </optgroup>
                        <%}%>
                        <%if(dropflag==0){
                        %>
                        <option class="chooselib" value="null">Not Available</option>
                        <%}%>


                    </select>
                </div>

                <div class="item">
                    <label for="">Category<span>*</span></label>
                    <input i type="text"   name="category" value="<%= LibEditDeleteCall.category %>" required/>
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
