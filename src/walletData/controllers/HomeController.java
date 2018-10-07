package walletData.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;
import walletData.Query.Execute;
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
    public void initialize() {

        homeUser.setText(LoginController.loggeduser);

        int mykey;
        String bal;
        String[] ids = new String[5];
        String[] recpubs = new String[5];
        String[] time = new String[5];
        String[] amts = new String[5];

        try {
            mykey = getkey();                                                 //gets public key
            String key = Integer.toString(mykey);
            publicKey.setText(key);
            bal = getbal(mykey);                                                 //gets user balance
            balance.setText(bal);
            setmyIds(ids, recpubs, time, amts, mykey);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            setTable(ids, recpubs, time, amts);                                                  //sets UI table values
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void goToSend(ActionEvent event) throws IOException {             //Stages send
        Stage sendMoneyStage = Main.stage;
        Parent root = FXMLLoader.load(getClass().getResource("/walletData/fxml/sendVerification.fxml"));
        sendMoneyStage.setTitle("Hello World");
        sendMoneyStage.setScene(new Scene(root));
        sendMoneyStage.show();
    }

    @FXML
    public void openLogin(ActionEvent event) throws IOException {            //Stages Login
            Stage loginStage = Main.stage;
            Parent root = FXMLLoader.load(getClass().getResource("/walletData/fxml/login.fxml"));
            loginStage.setTitle("Login");
            loginStage.setScene(new Scene(root));
            loginStage.setResizable(false);
            loginStage.show();
    }

    private int getkey() throws SQLException {
        ResultSet set = DBConnect.getStatement().executeQuery(String.format(Execute.getKey, LoginController.loggeduser));
        if (set.next())
            return set.getInt("publickey");
        else return 0;
    }

    private String getbal(int mykey) throws SQLException {
        ResultSet mysetbal = DBConnect.getStatement().executeQuery(String.format(Execute.getBal, mykey));
        if (mysetbal.next())
            return mysetbal.getString("balance");
        else
            return "00";
    }

    private void setmyIds(String[] ids, String[] recpubs, String[] time, String[] amts, int mykey) throws SQLException {
        int count = 1;
        ResultSet mySet = DBConnect.getStatement().executeQuery(String.format(Execute.setTable, mykey));
        mySet.last();
        ids[0] = mySet.getString("transid");
        recpubs[0] = mySet.getString("receiverpub");
        time[0] = mySet.getString("time");
        amts[0] = mySet.getString("amount");
        while (mySet.previous() && count < 5) {
            ids[count] = mySet.getString("transid");
            recpubs[count] = mySet.getString("receiverpub");
            time[count] = mySet.getString("time");
            amts[count] = mySet.getString("amount");
            count++;
        }
    }

    private void setTable(String[] ids, String[] recpubs, String[] time, String[] amts) throws NullPointerException {

        trans1.setText(ids[0]);
        rec1.setText(recpubs[0]);
        amt1.setText(amts[0]);
        time1.setText(time[0]);

        trans2.setText(ids[1]);
        rec2.setText(recpubs[1]);
        amt2.setText(amts[1]);
        time2.setText(time[1]);

        trans3.setText(ids[2]);
        rec3.setText(recpubs[3]);
        amt3.setText(amts[3]);
        time3.setText(time[3]);

        trans4.setText(ids[3]);
        rec4.setText(recpubs[3]);
        amt4.setText(amts[3]);
        time4.setText(time[3]);

        trans5.setText(ids[4]);
        rec5.setText(recpubs[4]);
        amt5.setText(amts[4]);
        time5.setText(time[4]);
    }
}
