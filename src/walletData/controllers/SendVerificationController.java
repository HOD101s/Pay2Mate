package walletData.controllers;

import javafx.fxml.FXML;
import walletData.DBConnect;
import walletData.Transactions;

import java.sql.SQLException;


public class SendVerificationController {
    @FXML
    public void onPay(){
        int vreturn = Transactions.verification(privatekey.getText(),LoginController.loggeduser,publickey.getText());
        if(vreturn == 1) {
            if(Transactions.amountInBalance(sendAmount.getInt() , LoginController.loggeduser)){
                Transactions.removeMoney();
                Transactions.addMoney();
                TransactionTableInsert();
                verifyLabel.setText("Successful Transaction");
            }else{
                verifyLabel.setText("Insufficient Balance");
            }
        }else if(vreturn == 0){
            verifyLabel.setText("Private Key Incorrect");
        }else if(vreturn == 2){
            verifyLabel.setText("Receiver Public Key Incorrect");
        }
    }

    @FXML
    public void backHome(){
        LoginController.openHome();
    }

    public void TransactionTableInsert(){
        try {
            String TransID = Transactions.genTransID();
            String insert = String.format("INSERT INTO %s (TransactionIDs) VALUES (%s)",LoginController.loggeduser,TransID);
            DBConnect.getStatement().executeUpdate(insert);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
