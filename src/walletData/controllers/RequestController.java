package walletData.controllers;


import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import walletData.Query.Execute;
import walletData.Scenes.LayOut;
import walletData.dbs.DBConnect;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RequestController extends LayOut{

    @FXML
    private JFXTextField amount;

    @FXML
    private Label status;

    @FXML
    public void openHome() throws IOException{
        myhome();
    }

    @FXML
    public void submitReq() throws SQLException{
        PreparedStatement adminInsert = DBConnect.getConn().prepareStatement(Execute.adminInsert);
        adminInsert.setInt(1,HomeController.getkey());
        adminInsert.setInt(2,Integer.parseInt(amount.getText()));
        adminInsert.executeUpdate();
        status.setText("Successful Request");
    }


}
