<?php 
include "db_conn.php";
function verifyInfo($name, $word, $checkName, $checkWord){
    if($name == $checkName){
        if($word == $checkWord){
            return true;
        }
        else{
            return false;
        }
    }
    else{
        return false;
    }
}

function filledIn($user, $userp){
    if(isset($user)){
        if(isset($userp)){
            return true;
        }
        else{
            return false;
        }
    }
    else{
        return false;
    }
}

function emp($unam, $passs){
    if (empty($unam)) {
        echo "user empty";
        exit();
    }else if(empty($passs)){
        echo "password empty";
        exit();
    }else{
        return true;
    }
}

function deposit($old, $current, $link, $acc){
    if (emp($old, $current)){
        $add = $old + $current;
        if($acc=="Checking"){
            $sql = "UPDATE bank_info SET Checking = '$add'";
            $result = mysqli_query($link, $sql);
            succQuery($result);
        }
        elseif($acc=="Savings"){
            $sql = "UPDATE bank_info SET Savings = '$add'";
            $result = mysqli_query($link, $sql);
            succQuery($result);
            return;
        }
        else{
            echo "Wtf happened";
        }
    }
}

function withdraw($old, $current, $link, $acc){
    if (emp($old, $current)){
        $sub = $current - $old;
        if($acc=="Checking"){
            $sql = "UPDATE bank_info SET Checking = '$sub'";
            $result = mysqli_query($link, $sql);
            succQuery($result);
        }
        elseif($acc=="Savings"){
            $sql = "UPDATE bank_info SET Savings = '$sub'";
            $result = mysqli_query($link, $sql);
            succQuery($result);
            return;
        }
        else{
            echo "Wtf happened";
        }
    }
}

function succQuery($info){
    if($info){
        return;
    }
    else{
        echo "Query failed";
        exit;
    }
}

function validate($data){
    $data = trim($data);
    $data = stripslashes($data);
    $data = htmlspecialchars($data);
    return $data;
 }

if (filledIn($_POST['uname'], $_POST['password'])) {
    $uname = validate($_POST['uname']);
    $pass = validate($_POST['password']);
    if (emp($uname, $pass)){
        $sql = "SELECT * FROM bank_info WHERE UName='$uname' AND Pword='$pass'";
        $result = mysqli_query($conn, $sql);
        succQuery($result);
        if (mysqli_num_rows($result) == 1) {
            $row = mysqli_fetch_assoc($result);
            $ucheck = $row['UName'];
            $pcheck = $row['Pword'];
            if (verifyInfo($uname, $pass, $ucheck, $pcheck)) {
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
    <script src="./js/dash.js"></script>
</head>

<body>

    <div class="side_menu">
        <div class="logo">
            <img src="./png/yOUr_Money_Logo_1.png" alt="yOUr Money Logo" class="yOUr_Money_Logo">
        </div>
    </div>

    <div class = "checking_balance_box">
        <div class = "checking_balance_header">
            <h1>Checking Balance</h1>
        </div>

        <div class = "checking_balance">
            <h1 id="checking_balance_amount">100</h1>
        </div>
    </div>

    <div class = "savings_balance_box">
        <div class = "savings_balance_header">
            <h1>Savings Balance</h1>
        </div>

        <div class = "savings_balance">
            <h1 id="saving_balance_amount">100</h1>
        </div>
    </div>

    <div class = "deposit_box">
        <div class = "deposit_header">
            <h1>Deposit</h1>
        </div>

        <form id="login" class="user_input">
            <p1>To</p1>
            <input id="deposit_from_account" class="input" placeholder="Type 'Checking' or 'Savings'" name="accdepo">

            <p1>Amount</p1>
            <input id="deposit_amount" class="input" placeholder="0.00" required type="number" name="amtdepo">

            <div class="for_button">
                <button onclick="deposit()" id="deposit_button" type="button" class="deposit_withdraw_button">Deposit</button>
            </div>
        </form>
    </div>

    <div class = "withdraw_box">
        <div class = "withdraw_header">
            <h1>Withdraw</h1>
        </div>

        <form id="login" class="user_input">
            <p1>From</p1>
            <input id="withdraw_from_account" class="input" placeholder="Type 'Checking' or 'Savings'" name="accdepo">

            <p1>Amount</p1>
            <input id="withdraw_amount" class="input" placeholder="0.00" required type="number" name="amtdepo">

            <div class="for_button">
                <button onclick="withdraw()" id="the_withdraw_button" type="button" class="deposit_withdraw_button">Withdraw</button>
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
