package walletData.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import walletData.Query.Execute;
import walletData.Scenes.LayOut;
import walletData.dbs.DBConnect;
import walletData.dbs.Transactions;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SendMyVerificationController extends LayOut{

    @FXML
    private TextField publickey;

    @FXML
    private TextField privatekey;

    @FXML
    private TextField sendAmount;

    @FXML
    private Label verifyLabel;

    @FXML
    void onPay(ActionEvent event) {


        int vreturn = 0;

        try {       //verifies pubkey prikey and amount
            vreturn = Transactions.verification(Integer.parseInt(privatekey.getText()), LoginController.loggeduser, Integer.parseInt(publickey.getText()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (vreturn == 1) {
            try {
                if (Transactions.amountInBalance(Integer.parseInt(sendAmount.getText()), Integer.parseInt(privatekey.getText()))) {
                    Transactions.removeMoney();         //deducts money from sender wallet
                    Transactions.addMoney();            //adds money to receiver wallet
                    TransactionTableInsert();           //Inserts Transaction Id
                    verifyLabel.setText("Successful Transaction");
                } else {
                    verifyLabel.setText("Insufficient Balance");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (vreturn == 0) {
            verifyLabel.setText("Private Key Incorrect");
        } else if (vreturn == 2) {
            verifyLabel.setText("Receiver Public Key Incorrect");
        }
    }

    @FXML
    void backHome(ActionEvent event) throws IOException {        //Stages Home
        myhome();
    }

    private void TransactionTableInsert() throws SQLException {
        PreparedStatement transTable = DBConnect.getConn().prepareStatement(Execute.transTableInsert);
        transTable.setString(1,Transactions.genTransID());
        transTable.setString(2,getsenderpub());
        transTable.setInt(3,Integer.parseInt(publickey.getText()));
        transTable.setInt(4,Integer.parseInt(sendAmount.getText()));
        transTable.setString(5,Transactions.getTime());
        transTable.setString(6,Transactions.getDate());
        transTable.executeUpdate();
    }

    private String getsenderpub() throws SQLException {
        PreparedStatement getSenderPub = DBConnect.getConn().prepareStatement(Execute.getSenderPub);
        getSenderPub.setString(1,privatekey.getText());
        ResultSet sendSet = getSenderPub.executeQuery();
        if (sendSet.next())
            return sendSet.getString("publickey");
        else
            return "0";
    }
}
