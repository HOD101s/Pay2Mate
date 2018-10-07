package walletData.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import walletData.Query.Execute;
import walletData.Scenes.LayOut;
import walletData.dbs.DBConnect;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;


import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController extends LayOut{

    @FXML
    static GridPane root;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private Label status;

    public static String loggeduser;

    @FXML
    void onLogin(ActionEvent event) {
        if(username.getText().isEmpty() || password.getText().isEmpty()){
            status.setText("username or password cannot be empty");
        }else{
            if(username.getText().equals("admin") && password.getText().equals("admin")){
                openAdmin();
            }else {
                try {
                    login(String.format(Execute.loginQuery, username.getText(), password.getText()));           //check login data
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @FXML
    void onRegister(ActionEvent event) throws IOException {     //Stages register
        myregister();
    }

    private void openAdmin(){
        try {
            myadmin();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void login(String query) throws SQLException{
        ResultSet set =
                DBConnect.getStatement()
                        .executeQuery(query);
        if(set.next()){
            loggeduser = username.getText();
            status.setText("Logged in !");
            try {
                openHome();                             //openHome on login
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            status.setText("Incorrect username or password");
        }
        set.close();
    }

    private void openHome() throws IOException{             //Stages Home
        myhome();
    }
}
