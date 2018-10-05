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
import java.sql.ResultSet;
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

        if(vreturn == 1) {
            try {
                if(Transactions.amountInBalance(Integer.parseInt(sendAmount.getText()) , Integer.parseInt(privatekey.getText()))){
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
            String insert = String.format("INSERT INTO `transaction` (`transid`,`senderpub`,`receiverpub`,`amount`,"
                    + "`time`) VALUES ('%s','%s','%d','%d','%s')",Transactions.genTransID(),getsenderpub(),Integer.parseInt(publickey.getText()),Integer.parseInt(sendAmount.getText()),Transactions.getTime());
            DBConnect.getStatement().executeUpdate(insert);
    }

    private String getsenderpub() throws SQLException{
        String query = String.format("SELECT * FROM `userwallet` WHERE `privatekey` = '%s'",privatekey.getText());
        ResultSet sendSet = DBConnect.getStatement().executeQuery(query);
        if(sendSet.next())
            return sendSet.getString("publickey");
        else
            return "0";
    }
}
