package com.example;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
//import javafx.application.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.awt.Desktop;

public class LoginController {

    @FXML
    private TextField username;

    @FXML
    private TextField password;

    @FXML
    private void loginCheck() throws IOException, URISyntaxException{


        if (SQLDatabaseConnection.matchUserLogin(username.getText(), password.getText())) {

            Desktop create = Desktop.getDesktop();
            create.browse(new URI("https://rushilpatel01.github.io/yOUrMoneyDashboard/dashboard_page_yOUr_Account_Checking.html"));
        }
        
    }


}
