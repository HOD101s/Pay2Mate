package walletData.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import walletData.Main;
import walletData.Query.Execute;
import walletData.dbs.DBConnect;

import java.io.IOException;
import java.sql.SQLException;

public class AdminController {

    @FXML
    private TextField amount;

    @FXML
    private TextField publicKey;

    @FXML
    private Label status;

    public void adminMoney(){
        try {
            DBConnect.getStatement().executeUpdate(String.format(Execute.addMoney,Integer.parseInt(amount.getText()),Integer.parseInt(publicKey.getText())));
            status.setText("Successful Transaction");
        } catch (SQLException e) {
            e.printStackTrace();
            status.setText("Could Not Complete Transaction");
        }
    }



    public void openLogin() throws IOException{
        Stage loginStage = Main.stage;
        Parent root = FXMLLoader.load(getClass().getResource("/walletData/fxml/login.fxml"));
        loginStage.setTitle("Login");
        loginStage.setScene(new Scene(root));
        loginStage.setResizable(false);
        loginStage.show();
    }
}
