<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Book Shelf</title>
    <link rel="icon" href="img/book.png"size="64x64">
    <link rel="stylesheet" type="text/css" href="./css/login.css">


</head>
<body>


    <h2>Book Shelf</h2>
    <div class="container" id="container">
        <div class="form-container sign-up-container">
            <form action="userlogin" method="post">
                <h1>User Login</h1>

                <input type="text" placeholder="Email" name="username"/>
                <input type="password" placeholder="Password" name="userpassword" />
                <button >Login</button>


                <h3 class="newuser">A New User ??</h3><a href="#" class="show-popup"><u>Register here</u></a>
            </form>
            <div class="popup" >
                <div class="close-btn" class="close" data-dismiss="modal" >&times;</div>
                <form action="userreg" method="POST">
                    <div class="form">
                        <h2>Registration</h2>
                        <div class="form-element">
                            <label >Username</label>
                            <input type="text" name="username"  placeholder="Enter Username" required>
                        </div>
                        <div class="form-element">
                            <label >Name</label>
                            <input type="text" name="name"  placeholder="Enter Name" required>
                        </div>
                        <div class="form-element">
                            <label >Password</label>
                            <input type="password"  name="userpassword" placeholder="Enter password" required>
                        </div>
                        <div class="form-element">
                            <label >Confirm Password</label>
                            <input type="password"  name="confirmpass" placeholder="Enter password" required>
                        </div>
                        <div class="form-element">
                            <button type="submit">Register</button>
                        </div>
                    </div>
                </form>

            </div>
        </div>
        <div class="form-container sign-in-container">
            <form action="adminlogin" method="post">
                <h1>Admin Login</h1>

                <input type="email" name="adminusername" placeholder="Email" id="admin_email"/>
                <input type="password" name="adminpass" placeholder="Password" id="admin_password"/>
                <!-- <a href="#">Forgot your password?</a> -->
                <button >Login</button>
            </form>
        </div>
        <div class="overlay-container">
            <div class="overlay">
                <div class="overlay-panel overlay-left">
                    <h1>Hello, User!</h1>
                    <!-- <p>To keep connected with us please login with your personal info</p> -->
                    <button class="ghost" id="signIn">&larr; Admin Login</button>
                </div>
                <div class="overlay-panel overlay-right">
                    <h1>Hello, Admin!</h1>
                    <!-- <p>Enter your personal details and start journey with us</p> -->
                    <button class="ghost" id="signUp">&rarr; User Login</button>
                </div>
            </div>
        </div>
    </div>


<script type="text/javascript" src="slider.js"></script>
<script>
    document.querySelector(".show-popup").addEventListener("click",function(e){
        document.querySelector(".popup").classList.add("active");
        console.log(e.target);
    });
    document.querySelector(".popup .close-btn").addEventListener("click",function(){
        document.querySelector(".popup").classList.remove("active");
    });
</script>


</body>
</html>