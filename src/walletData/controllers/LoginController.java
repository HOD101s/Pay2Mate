package walletData.controllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import walletData.DBConnect;
import walletData.Main;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {

    //Test Commit

    @FXML
    void onLogin(ActionEvent event) {
        String query = "SELECT * FROM `users` WHERE `username` = '%s' && `password` = '%s'";            //edit in our table name
        query = String.format(query, username.getText(), password.getText());

        if(username.getText().isEmpty() || password.getText().isEmpty()){
            status.setText("username or password cannot be empty");
        }else{
            try{
                ResultSet set =
                        DBConnect.getStatement()
                                .executeQuery(query);
                if(set.next()){
                    sessionTable();
                    status.setText("Logged in successfully");
                }else{
                    status.setText("Incorrect username or password");
                }
                set.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    @FXML
    void onRegister(ActionEvent event) throws IOException {
        Stage registerStage = Main.stage;
        registerStage.setTitle("Register");
        root = FXMLLoader.load(getClass().getResource("/fxmls/register.fxml"));
        registerStage.setScene(new Scene(root));
        registerStage.show();
    }

    public static void sessionTable(){
        try {
            String tablequery = String.format("INSERT INTO pay2mate.sessionTable SELECT ,  FROM pay2mate.users;"); //add fields
            DBConnect.getStatement().executeUpdate(tablequery);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

}
