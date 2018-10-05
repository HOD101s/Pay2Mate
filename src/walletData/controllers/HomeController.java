package walletData.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;
import walletData.dbs.DBConnect;
import walletData.Main;
import javafx.scene.control.Label;

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
    private Label time1;

    @FXML
    private Label time2;

    @FXML
    private Label time3;

    @FXML
    private Label time4;

    @FXML
    private Label time5;

    @FXML
    private Tooltip tt1;

    @FXML
    private Tooltip tt2;

    @FXML
    private Tooltip tt3;

    @FXML
    private Tooltip tt4;

    @FXML
    private Tooltip tt5;


    @FXML
    public void  initialize(){

        homeUser.setText(LoginController.loggeduser);

        int mykey;
        String bal;
        String[] arr = new String[5];

        try{
            mykey=getkey();                                                 //gets public key
            String key = Integer.toString(mykey);
            publicKey.setText(key);
            bal = getbal();                                                 //gets user balance
            balance.setText(bal);
            setmyIds(arr);
        }catch(SQLException e){
            e.printStackTrace();
        }

        try {
            setTable(arr);                                                  //sets UI table values
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void goToSend(ActionEvent event) throws IOException{             //Stages send
        Stage sendMoneyStage  = Main.stage;
        Parent root = FXMLLoader.load(getClass().getResource("/walletData/fxml/sendVerification.fxml"));
        sendMoneyStage.setTitle("Hello World");
        sendMoneyStage.setScene(new Scene(root));
        sendMoneyStage.show();
    }

    @FXML
    public void openLogin(ActionEvent event) throws IOException{            //Stages Login
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

    private int getkey() throws SQLException{
        String myquery = String.format("SELECT `publickey` FROM `users` WHERE `username` = '%s' ",LoginController.loggeduser);
        ResultSet set =
                DBConnect.getStatement()
                        .executeQuery(myquery);
        if(set.next())
            return set.getInt("publickey");
        else return 0;
    }

    private String getbal() throws SQLException{
        String querybal = String.format("SELECT `balance` FROM `users` WHERE `username` = '%s'",LoginController.loggeduser);
        ResultSet mysetbal = DBConnect.getStatement().executeQuery(querybal);
        if(mysetbal.next())
            return mysetbal.getString("balance");
        else
            return "00";
    }

    private void setmyIds(String[] myarr) throws SQLException {
        int count = 1;
        String search = String.format("SELECT * FROM %s WHERE 1", LoginController.loggeduser);
        ResultSet mySet = DBConnect.getStatement().executeQuery(search);
        mySet.last();
        myarr[0] = mySet.getString("TransactionIDs");
        while (mySet.previous() && count < 5) {
            myarr[count] = mySet.getString("TransactionIDs");
            count++;
        }
    }

    private String setRec(String s){return s.substring(12,18);}
    private String setAmt(String s){return s.substring(24);}
    private String setTime(String s){
        return s.substring(6,8)+":"+s.substring(8,10)+":"+s.substring(10,12);
    }

    private void setTable(String[] arr) throws NullPointerException{

        trans1.setText(arr[0]);
        tt1.setText(arr[0]);
        trans1.setTooltip(tt1);
        rec1.setText(setRec(arr[0]));
        amt1.setText(setAmt(arr[0]));
        time1.setText(setTime(arr[0]));

        trans2.setText(arr[1]);
        tt2.setText(arr[1]);
        trans2.setTooltip(tt2);
        rec2.setText(setRec(arr[1]));
        amt2.setText(setAmt(arr[1]));
        time2.setText(setTime(arr[1]));

        trans3.setText(arr[2]);
        tt3.setText(arr[2]);
        trans3.setTooltip(tt3);
        rec3.setText(setRec(arr[2]));
        amt3.setText(setAmt(arr[2]));
        time3.setText(setTime(arr[2]));

        trans4.setText(arr[3]);
        tt4.setText(arr[3]);
        trans4.setTooltip(tt4);
        rec4.setText(setRec(arr[3]));
        amt4.setText(setAmt(arr[3]));
        time4.setText(setTime(arr[3]));

        trans5.setText(arr[4]);
        tt5.setText(arr[4]);
        trans5.setTooltip(tt5);
        rec5.setText(setRec(arr[4]));
        amt5.setText(setAmt(arr[4]));
        time5.setText(setTime(arr[4]));
    }
}
