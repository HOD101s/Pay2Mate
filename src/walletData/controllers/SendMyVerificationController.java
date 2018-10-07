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
        DBConnect.getStatement().executeUpdate(String.format(Execute.transTableInsert, Transactions.genTransID(), getsenderpub(),
                Integer.parseInt(publickey.getText()), Integer.parseInt(sendAmount.getText()), Transactions.getTime(), Transactions.getDate()));
    }

    private String getsenderpub() throws SQLException {
        ResultSet sendSet = DBConnect.getStatement().executeQuery(String.format(Execute.getSenderPub, privatekey.getText()));
        if (sendSet.next())
            return sendSet.getString("publickey");
        else
            return "0";
    }
}
