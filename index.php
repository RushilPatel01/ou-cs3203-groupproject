<!DOCTYPE html>
<html>

<head>
    <title>yOUr Money Login Page</title>
    <link rel="stylesheet" type="text/css" href="./css/login_page.css">
</head>

<body>
    <div class="login_box">

        <div class="logo">
            <img src="./png/yOUr Money Logo 1.png" alt="yOUr Money Logo" class="yOUr_Money_Logo">
        </div>

        <form id="login" class="user_input" action="./login.php" method="post">
            <?php if (isset($_GET['error'])) { ?>
                <p class="error"><?php echo $_GET['error']; ?></p>
            <?php } ?>
            <input class="email_password" placeholder="Enter 4x4" type="text" maxlength="8" name="uname">

            <input class="email_password" placeholder="Enter Password" required type="password" type="text" name="password">

            <button id="redirect_login" button type="submit" class="login_button">Login</button>
        </form>
    </div>
</body>

</html>

