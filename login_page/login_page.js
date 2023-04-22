window.onload=function(){
    let redirect_login = document.getElementById('redirect_login');

    redirect_login.addEventListener('click', function(){
        // Pass through the login page
        window.location.href = "/dashboard_page/dashboard_page_yOUr_Account_Checking.html";

        // get the form data
        let form = document.getElementById("login-form");
        let formData = new FormData(form);

        // create a new XMLHttpRequest object
        let xhr = new XMLHttpRequest();

        // specify the URL of the server endpoint that handles the request
        let url = "http://example.com/login";

        // specify the type of request (e.g., GET, POST)
        let requestType = "POST";

        // open the request
        xhr.open(requestType, url, true);

        // define a callback function to handle the response from the server
        xhr.onreadystatechange = function() {
            if (xhr.readyState === XMLHttpRequest.DONE) {
                if (xhr.status === 200) {
        // handle the response from the server
                    console.log(xhr.responseText);
                } else {
        // handle any errors
        console.error(xhr.statusText);
    }
  }
};

// send the request with the form data
xhr.send(formData);
    })
}