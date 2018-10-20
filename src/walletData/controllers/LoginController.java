package walletData.controllers;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import walletData.Query.Execute;
import walletData.Scenes.LayOut;
import walletData.dbs.DBConnect;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;


import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static walletData.encryption.HashString.hashString;

public class LoginController extends LayOut {

    @FXML
    static GridPane root;

    @FXML
    private JFXTextField username;

    @FXML
    private JFXPasswordField password;

    @FXML
    private Label status;

    public static String loggeduser;

    @FXML
    void onLogin(ActionEvent event) {
        if (username.getText().isEmpty() || password.getText().isEmpty()) {
            status.setText("Username or Password cannot be empty");
        } else {
            try {
                login();           //check login data
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void onRegister(ActionEvent event) throws IOException {     //Stages register
        myregister();
    }

    private void openAdmin() {
        try {
            myadmin();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void login() throws SQLException {
        PreparedStatement login = DBConnect.getConn().prepareStatement(Execute.loginQuery);
        login.setString(1, username.getText());
        login.setString(2, hashString(hashString(password.getText())));
        ResultSet set = login.executeQuery();
        if (set.next()) {
            loggeduser = username.getText();
            if ((set.getInt("accType")) == 1) {
                openAdmin();
            } else {
                status.setText("Logged in !");
                try {
                    openHome();                             //openHome on login
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            status.setText("Incorrect username or password");
        }
        set.close();
    }

    private void openHome() throws IOException {             //Stages Home
        myhome();
    }
}
