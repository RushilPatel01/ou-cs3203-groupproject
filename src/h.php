<!DOCTYPE html>
<html>

<head>
    <title>yOUr Money Dashboard Page</title>
    <link rel="stylesheet" href="./css/withdraw_page.css"/>
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
            <h1>Withdraw</h1>
        </div>

        <form id="login" class="user_input">
            <p1>From</p1>
            <input class="input" placeholder="Type 'Checking' or 'Savings'" required type="number">

            <p1>Amount</p1>
            <input class="input" placeholder="0.00" required type="number">

            <div class="button_wrapper">
                <button type="button" class="deposit_button">Withdraw</button>
            </div>
        </form>
    </div>

</body>

</html>