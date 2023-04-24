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
    <link rel="stylesheet" href="./css/dashboard_page.css"/>
</head>

<body>

    <div class="side_menu">
        <div class="logo">
            <img src="./png/yOUr Money Logo 1.png" alt="yOUr Money Logo" class="yOUr_Money_Logo">
        </div>
        <ul>
            <li><a href="" class="hover_effect">Accounts</a></li>
            <li><a href="./deposit_page.php" class="hover_effect">Deposit</a></li>
            <li><a href="./withdraw_page.php" class="hover_effect">Withdraw</a></li>
            <li><a href="./index.php" class="hover_effect">Logout</a></li>
        </ul>
    </div>

    <div class = "accounts_box">
        <div class = "accounts_header">
            <h1>Accounts</h1>
        </div>

        <div class = "accounts">
            <li><a href="./dashboard_page_yOUr_Account_Checking.php" class="accounts_hover">yOUr Account Checking</a></li>
            <li><a href="./dashboard_page_yOUr_Account_Saving.php" class="accounts_hover">yOUr Account Savings</a></li>
        </div>
    </div>

    <div class = "balance_box">
        <div class = "balance_header">
            <h1>Balance</h1>
        </div>

        <div class = "balance">
            <h2>Checking</h2>
            <h3>$<?php echo $Checking;?></h3>
        </div>
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