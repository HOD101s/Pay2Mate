package walletData.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import walletData.Query.Execute;
import walletData.Scenes.LayOut;
import walletData.dbs.DBConnect;

import java.io.IOException;
import java.sql.SQLException;

public class AdminController extends LayOut{

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
        mylogin();
    }
}
