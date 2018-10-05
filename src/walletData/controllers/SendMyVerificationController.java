package walletData.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import walletData.dbs.DBConnect;
import walletData.Main;
import walletData.dbs.Transactions;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;
import java.sql.SQLException;

public class SendMyVerificationController {
    @FXML
    private AnchorPane root;

    @FXML
    private TextField publickey;

    @FXML
    private TextField privatekey;

    @FXML
    private TextField sendAmount;

    @FXML
    private Label verifyLabel;

    @FXML
    void onPay(ActionEvent event){
        int vreturn = 0;

        try {       //verifies pubkey prikey and amount
            vreturn = Transactions.verification(Integer.parseInt(privatekey.getText()), LoginController.loggeduser,Integer.parseInt(publickey.getText()));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println(Integer.parseInt(publickey.getText()));
        if(vreturn == 1) {
            try {
                if(Transactions.amountInBalance(Integer.parseInt(sendAmount.getText()) , LoginController.loggeduser)){
                    Transactions.removeMoney();         //deducts money from sender wallet
                    Transactions.addMoney();            //adds money to receiver wallet
                    TransactionTableInsert();           //Inserts Transaction Id
                    verifyLabel.setText("Successful Transaction");
                }else{
                    verifyLabel.setText("Insufficient Balance");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else if(vreturn == 0){
            verifyLabel.setText("Private Key Incorrect");
        }else if(vreturn == 2){
            verifyLabel.setText("Receiver Public Key Incorrect");
        }
    }

    @FXML
    void backHome(ActionEvent event) throws IOException{        //Stages Home
        Stage homeStage = Main.stage;
        root = FXMLLoader.load(getClass().getResource("/walletData/fxml/home.fxml"));
        homeStage.setTitle(LoginController.loggeduser);
        homeStage.setScene(new Scene(root));
        homeStage.setResizable(false);
        homeStage.show();
    }

    private void TransactionTableInsert() throws SQLException{
            String TransID = Transactions.genTransID();
            String insert = String.format("INSERT INTO %s (TransactionIDs) VALUES (%s)",LoginController.loggeduser,TransID);
            DBConnect.getStatement().executeUpdate(insert);
    }
}
