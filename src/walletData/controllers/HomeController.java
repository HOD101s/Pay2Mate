package walletData.controllers;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import walletData.Query.Execute;
import walletData.Scenes.LayOut;
import walletData.dbs.DBConnect;

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
    private TableView<HomeTable> hometable;

    @FXML
    private TableColumn<HomeTable, String> transID;

    @FXML
    private TableColumn<HomeTable, String> date;

    @FXML
    private TableColumn<HomeTable, String> time;

    @FXML
    private TableColumn<HomeTable, Integer> sender;

    @FXML
    private TableColumn<HomeTable, Integer> receiver;

    @FXML
    private TableColumn<HomeTable, Integer> amount;

    ObservableList<HomeTable> data;

    @FXML
    private void initialize() {
        try {
            homeUser.setText(LoginController.loggeduser);
            int mykey;
            String bal;
            mykey = getkey();                                                 //gets public key
            String key = Integer.toString(mykey);
            publicKey.setText(key);
            bal = getbal(mykey);                                                 //gets user balance
            balance.setText(bal);
            transID.setCellValueFactory(new PropertyValueFactory<HomeTable, String>("transID"));
            date.setCellValueFactory(new PropertyValueFactory<HomeTable, String>("date"));
            time.setCellValueFactory(new PropertyValueFactory<HomeTable, String>("time"));
            sender.setCellValueFactory(new PropertyValueFactory<HomeTable, Integer>("sender"));
//            new PropertyValueFactory<HomeTable, Integer>("sender")
            receiver.setCellValueFactory(new PropertyValueFactory<HomeTable, Integer>("receiver"));
            amount.setCellValueFactory(new PropertyValueFactory<HomeTable, Integer>("amount"));
            buildData(mykey);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void buildData(int key) throws SQLException {
        data = FXCollections.observableArrayList();
        PreparedStatement build = DBConnect.getConn().prepareStatement(Execute.homeTable);
        build.setInt(1,key);
        build.setInt(2,key);
        ResultSet rs = build.executeQuery();
        while (rs.next()) {
            HomeTable ht = new HomeTable();
            ht.transID.set(rs.getString("transID"));
            ht.date.set(rs.getString("date"));
            ht.time.set(rs.getString("time"));
            ht.sender.set(rs.getInt("senderpub"));
            ht.receiver.set(rs.getInt("receiverpub"));
            ht.amount.set(rs.getInt("amount"));
            data.add(ht);
        }
        hometable.setItems(data);
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
}
