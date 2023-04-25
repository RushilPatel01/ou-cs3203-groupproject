window.onload=function(){
    let deposit_button = document.getElementById('redirect_login');

    redirect_login.addEventListener('click', function(){
        // Pass through the login page
        // window.location.href = "dashboard_page_yOUr_Account_Checking.cshtml";
        window.location.href = "/deposit_page"
    }
)};