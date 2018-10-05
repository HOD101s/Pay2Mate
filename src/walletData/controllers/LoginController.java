package walletData.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import walletData.dbs.DBConnect;
import walletData.Main;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;


import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {

    @FXML
    static GridPane root;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private Label status;

    static String loggeduser;

    @FXML
    void onLogin(ActionEvent event) {
        System.out.println("OnLogin called");
        String query = "SELECT * FROM `userdata` WHERE `username` = '%s' && `password` = '%s'";
        query = String.format(query, username.getText(), password.getText());

        if(username.getText().isEmpty() || password.getText().isEmpty()){
            status.setText("username or password cannot be empty");
        }else{
            try{
                login(query);           //check login data
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    @FXML
    void onRegister(ActionEvent event) throws IOException {     //Stages register
        Stage registerStage = Main.stage;
        registerStage.setTitle("Register");
        Parent root = FXMLLoader.load(getClass().getResource("/walletData/fxml/register.fxml"));
        registerStage.setScene(new Scene(root));
        registerStage.setResizable(false);
        registerStage.show();
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
        Stage registerStage = Main.stage;
        registerStage.setTitle(loggeduser);
        Parent root = FXMLLoader.load(getClass().getResource("/walletData/fxml/home.fxml"));
        registerStage.setScene(new Scene(root));
        registerStage.setResizable(false);
        registerStage.show();
    }
}
