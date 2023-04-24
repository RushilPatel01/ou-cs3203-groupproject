<?php 
include "db_conn.php";
if (isset($_POST['uname']) && isset($_POST['password'])) {
    function validate($data){
       $data = trim($data);
       $data = stripslashes($data);
       $data = htmlspecialchars($data);
       return $data;
    }
    $uname = validate($_POST['uname']);
    $pass = validate($_POST['password']);
    if (empty($uname)) {
        echo "user empty\n";
        exit();
    }else if(empty($pass)){
        echo "password empty\n";
        exit();
    }else{
        $sql = "SELECT * FROM bank_info WHERE UName='$uname' AND Pword='$pass'";
        $result = mysqli_query($conn, $sql);
        if (mysqli_num_rows($result) == 1) {
            $row = mysqli_fetch_assoc($result);
            if ($row['UName'] == $uname && $row['Pword'] == $pass) {
#                echo "Logged in!\n";
                $FName = $row['FName'];
                $LName = $row['LName'];
                $Checking = $row['Checking'];
                $Savings = $row['Savings'];
?>
<!DOCTYPE html>
<html>

<head>
    <title>yOUr Money Dashboard Page</title>
    <link rel="stylesheet" href="./css/deposit_page.css"/>
</head>

<body>

    <div class="side_menu">
        <div class="logo">
            <img src="./png/yOUr Money Logo 1.png" alt="yOUr Money Logo" class="yOUr_Money_Logo">
        </div>
        <ul>
            <li><a href="./dashboard_page_yOUr_Account_Checking.html" class="hover_effect">Accounts</a></li>
            <li><a href="./deposit_page.html" class="hover_effect">Deposit</a></li>
            <li><a href="./withdraw_page.html" class="hover_effect">Withdraw</a></li>
            <li><a href="./index.html" class="hover_effect">Logout</a></li>
        </ul>
    </div>

    <div class = "deposit_box">
        <div class = "deposit_header">
            <h1>Deposit</h1>
        </div>

        <form id="login" class="user_input">
            <p1>From</p1>
            <input id="deposit_from" class="input" placeholder="Type 'Checking' or 'Savings'">

            <p1>Amount</p1>
            <input id="deposit_amount" class="input" placeholder="0.00" required type="number">

            <div class="button_wrapper">
                <button id="deposit_button" type="button" class="deposit_button">Deposit</button>
            </div>
        </form>
    </div>

</body>

</html>
<?php 
            }else{
                echo "Incorrect Login\n";
                exit();
            }
        }else{
            echo "multiple rows or no rows\n";
            exit();
        }
    }
}else{
    echo "showing null in login\n";
    exit();
}
?>