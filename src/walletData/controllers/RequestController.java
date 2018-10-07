package walletData.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import walletData.Query.Execute;
import walletData.Scenes.LayOut;
import walletData.dbs.DBConnect;

import java.io.IOException;
import java.sql.SQLException;

public class RequestController extends LayOut{

    @FXML
    private TextField amount;

    @FXML
    private Label status;

    @FXML
    public void openHome() throws IOException{
        myhome();
    }

    @FXML
    public void submitReq() throws SQLException{
        DBConnect.getStatement().execute(String.format(Execute.adminInsert,HomeController.getkey(),Integer.parseInt(amount.getText())));
        status.setText("Successful Request");
    }
}
