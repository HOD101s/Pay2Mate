package walletData.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import walletData.Query.Execute;
import walletData.Scenes.LayOut;
import walletData.dbs.DBConnect;
import javafx.scene.control.Label;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HomeController extends LayOut {

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
        mySend();
    }

    @FXML
    public void openLogin(ActionEvent event) throws IOException {            //Stages Login
        mylogin();
    }

    @FXML
    private void openRequest() throws IOException{
        myrequest();
    }

    public static int getkey() throws SQLException {
        PreparedStatement getkey = DBConnect.getConn().prepareStatement(Execute.getKey);
        getkey.setString(1,LoginController.loggeduser);
        ResultSet set = getkey.executeQuery();
        if (set.next())
            return set.getInt("publickey");
        else return 0;
    }

    private String getbal(int mykey) throws SQLException {
        PreparedStatement getkey = DBConnect.getConn().prepareStatement(Execute.getBal);
        getkey.setInt(1,mykey);
        ResultSet mysetbal = getkey.executeQuery();
        if (mysetbal.next())
            return mysetbal.getString("balance");
        else
            return "00";
    }

    private void setmyIds(String[] ids, String[] recpubs, String[] time, String[] amts, int mykey) throws SQLException {
        int count = 1;
        PreparedStatement getkey = DBConnect.getConn().prepareStatement(Execute.setTable);
        getkey.setInt(1,mykey);
        ResultSet mySet = getkey.executeQuery();
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
