package walletData.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import walletData.DBConnect;
import walletData.Main;
import walletData.Transactions;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HomeController {

    @FXML
    private Label homeUser;

    @FXML
    private Label publicKey;

    @FXML
    private Label balance;

    @FXML
    private Label trans1;

    @FXML
    private Label rec1;

    @FXML
    private Label amt1;

    @FXML
    private Label trans2;

    @FXML
    private Label rec2;

    @FXML
    private Label amt2;

    @FXML
    private Label trans3;

    @FXML
    private Label rec3;

    @FXML
    private Label amt3;

    @FXML
    private Label rec4;

    @FXML
    private Label amt4;

    @FXML
    private Label trans4;

    @FXML
    private Label rec5;

    @FXML
    private Label amt5;

    @FXML
    private Label trans5;

    @FXML
    public void  initialize(){
        String bal;
        homeUser.setText(LoginController.loggeduser);
        int mykey;
        try{
            String myquery = String.format("SELECT `publickey` FROM `users` WHERE `username` = '%s' ",LoginController.loggeduser);
            ResultSet set =
                    DBConnect.getStatement()
                            .executeQuery(myquery);
            set.next();
            mykey = set.getInt("publickey");


        }catch(SQLException e){
            e.printStackTrace();
            mykey =0;
        }
        String key = Integer.toString(mykey);
        publicKey.setText(key);

        try{
            String querybal = String.format("SELECT `balance` FROM `users` WHERE `username` = '%s'",LoginController.loggeduser);
            ResultSet mysetbal = DBConnect.getStatement().executeQuery(querybal);
            mysetbal.next();
            bal = mysetbal.getString("balance");
        }catch(SQLException e){
            e.printStackTrace();
            bal = "00";
        }

        balance.setText(bal);
        int count =1;
        String[] arr = new String[5];
        String search = String.format("SELECT * FROM %s WHERE 1",LoginController.loggeduser);
        try{
            ResultSet mySet = DBConnect.getStatement().executeQuery(search);
            mySet.last();
            arr[0] = mySet.getString("TransactionIDs");
            while(mySet.previous() && count<5){
                arr[count] = mySet.getString("TransactionIDs");
                count++;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        //0-11  12-17 18-23
        try {
            trans1.setText(arr[0]);
            rec1.setText(arr[0].substring(12,18));
            amt1.setText(arr[0].substring(24));

            trans2.setText(arr[1]);
            rec2.setText(arr[1].substring(12,18));
            amt2.setText(arr[1].substring(24));

            trans3.setText(arr[2]);
            rec3.setText(arr[2].substring(12,18));
            amt3.setText(arr[2].substring(24));

            trans4.setText(arr[3]);
            rec4.setText(arr[3].substring(12,18));
            amt4.setText(arr[3].substring(24));

            trans5.setText(arr[4]);
            rec5.setText(arr[4].substring(12,18));
            amt5.setText(arr[4].substring(24));
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void goToSend(ActionEvent event) throws IOException{
        Stage sendMoneyStage  = Main.stage;
        Parent root = FXMLLoader.load(getClass().getResource("/walletData/fxml/sendVerification.fxml"));
        sendMoneyStage.setTitle("Hello World");
        sendMoneyStage.setScene(new Scene(root));
        sendMoneyStage.show();
    }

    @FXML
    public void openLogin(ActionEvent event) throws IOException{
        try {
            Stage loginStage = Main.stage;
            Parent root = FXMLLoader.load(getClass().getResource("/walletData/fxml/login.fxml"));
            loginStage.setTitle("Login");
            loginStage.setScene(new Scene(root));
            loginStage.setResizable(false);
            loginStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
