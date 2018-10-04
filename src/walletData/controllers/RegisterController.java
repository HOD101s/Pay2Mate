package walletData.controllers;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;
import walletData.DBConnect;
import walletData.Main;

import java.io.IOException;
import java.sql.SQLException;

public class RegisterController {

    @FXML
    public void newRegister() {

//        String username = newusername.getText();
//        String password = newpassword.getText();
//        String confpassword = newconfpassword.getText();
//
//        if (username.isEmpty() || password.isEmpty() || confpassword.isEmpty()) {
//            regstatus.setText("Fields cannot be empty");
//        } else if (!password.equals(confpassword)) {
//            regstatus.setText("Passwords do not match");
//        } else if (doesUserExist(username)) {
//            regstatus.setText("User already exists");
//        } else {
//            registerUser(username, password);
//        }
    }

    void registerUser(String username, String password) {
//        try {
//            String insert = String.format("INSERT INTO `users`(`username`, `password`) VALUES ('%s','%s')", username, password); //change table name
//            DBConnect.getStatement().executeUpdate(insert);
//            regstatus.setText("Registration successful");
//
//            PauseTransition pause = new PauseTransition(Duration.seconds(1));
//            pause.setOnFinished(event -> backToLogin());
//            pause.play();
//
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }
    public static void transactiontable(){
        try {
            String tablequery = String.format("INSERT INTO pay2mate.sessionTable SELECT ,  FROM pay2mate.users;"); //add fields
            DBConnect.getStatement().executeUpdate(tablequery);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @FXML
    public void backToLogin() {
//        try {
//            Stage loginStage = Main.stage;
//            Parent root = FXMLLoader.load(getClass().getResource("/walletData/fxml/login.fxml"));
//            loginStage.setTitle("Login");
//            loginStage.setScene(new Scene(root));
//            loginStage.setResizable(false);
//            loginStage.show();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
