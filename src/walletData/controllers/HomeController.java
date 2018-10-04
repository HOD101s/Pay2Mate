package walletData.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import walletData.DBConnect;
import walletData.Main;
import walletData.Transactions;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HomeController {

    @FXML
    public void  initialize(){
        homeUser.setText(LoginController.loggeduser);
        publicKey.setText();
        balance.setText(Transactions.userBalance);
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

    }

    @FXML
    public void goToSend(){
        Stage sendMoneyStage  = Main.stage;
        Parent root = FXMLLoader.load(getClass().getResource("/walletData/fxml/sendVerification.fxml"));
        sendMoneyStage.setTitle("Hello World");
        sendMoneyStage.setScene(new Scene(root));
        sendMoneyStage.show();
    }

    @FXML
    public void openLogin() {
        try {
            Stage loginStage = Main.stage;
            root = FXMLLoader.load(getClass().getResource("/walletData/fxml/login.fxml"));
            loginStage.setTitle("Login");
            loginStage.setScene(new Scene(root));
            loginStage.setResizable(false);
            loginStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
