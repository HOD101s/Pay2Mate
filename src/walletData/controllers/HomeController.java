package walletData.controllers;

import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import walletData.Query.Execute;
import walletData.Scenes.LayOut;
import walletData.dbs.DBConnect;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.function.Predicate;

public class HomeController extends LayOut {

    @FXML
    private Label homeUser;

    @FXML
    private Label publicKey;

    @FXML
    private Label balance;

    @FXML
    private Label currency;

    @FXML
    private JFXTextField input;

    @FXML
    private JFXTreeTableView<HomeTable> reciverTable;

    @FXML
    private JFXToggleButton usdtoggle;

    @FXML
    private JFXTreeTableView<HomeTableSend> senderTable;

    @FXML
    private JFXTextField sendSearch;

    ObservableList<HomeTable> data;
    ObservableList<HomeTableSend> sendData;

    private int bal;

    @FXML
    private void initialize() {
        try {
            homeUser.setText(LoginController.loggeduser);
            int mykey = getkey();                                                 //gets public key
            String key = Integer.toString(mykey);
            publicKey.setText(key);                                                //gets user balance
            bal = Integer.parseInt(getbal(mykey));
            balance.setText(String.valueOf(bal));

            JFXTreeTableColumn transID = new JFXTreeTableColumn("Transaction ID");
            JFXTreeTableColumn date = new JFXTreeTableColumn("Date");
            JFXTreeTableColumn time = new JFXTreeTableColumn("Time");
            JFXTreeTableColumn sender = new JFXTreeTableColumn("Sender");
            JFXTreeTableColumn receiver = new JFXTreeTableColumn("Receiver");
            JFXTreeTableColumn amount = new JFXTreeTableColumn("Amount");
            reciverTable.getColumns().addAll(transID, date, time, sender, receiver, amount);

            JFXTreeTableColumn transIDsend = new JFXTreeTableColumn("Transaction ID");
            JFXTreeTableColumn datesend = new JFXTreeTableColumn("Date");
            JFXTreeTableColumn timesend = new JFXTreeTableColumn("Time");
            JFXTreeTableColumn sendersend = new JFXTreeTableColumn("Sender");
            JFXTreeTableColumn receiversend = new JFXTreeTableColumn("Receiver");
            JFXTreeTableColumn amountsend = new JFXTreeTableColumn("Amount");
            senderTable.getColumns().addAll(transIDsend, datesend, timesend, sendersend, receiversend, amountsend);

            transID.setPrefWidth(140.878);
            sender.setPrefWidth(95.0);
            receiver.setPrefWidth(95.0);

            transIDsend.setPrefWidth(140.878);
            sendersend.setPrefWidth(95.0);
            receiversend.setPrefWidth(95.0);

            transIDsend.setCellValueFactory(new TreeItemPropertyValueFactory<HomeTableSend, String>("transIDsend"));
            datesend.setCellValueFactory(new TreeItemPropertyValueFactory<HomeTableSend, String>("datesend"));
            timesend.setCellValueFactory(new TreeItemPropertyValueFactory<HomeTableSend, String>("timesend"));
            sendersend.setCellValueFactory(new TreeItemPropertyValueFactory<HomeTableSend, Integer>("sendersend"));
            receiversend.setCellValueFactory(new TreeItemPropertyValueFactory<HomeTableSend, Integer>("receiversend"));
            amountsend.setCellValueFactory(new TreeItemPropertyValueFactory<HomeTableSend, String>("amountsend"));

            transID.setCellValueFactory(new TreeItemPropertyValueFactory<HomeTable, String>("transID"));
            date.setCellValueFactory(new TreeItemPropertyValueFactory<HomeTable, String>("date"));
            time.setCellValueFactory(new TreeItemPropertyValueFactory<HomeTable, String>("time"));
            sender.setCellValueFactory(new TreeItemPropertyValueFactory<HomeTable, Integer>("sender"));
            receiver.setCellValueFactory(new TreeItemPropertyValueFactory<HomeTable, Integer>("receiver"));
            amount.setCellValueFactory(new TreeItemPropertyValueFactory<HomeTable, String>("amount"));

            buildDataReciver(mykey);
            buildDataSender(mykey);

        } catch (SQLException e) {
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
    private void openRequest() throws IOException {
        myrequest();
    }

    @FXML
    void usdConvert(ActionEvent event) throws SQLException {
        if (usdtoggle.isSelected()) {
            DecimalFormat df = new DecimalFormat("###.##");
            balance.setText(String.valueOf(df.format(bal / 73.6)));
            currency.setText("$");
        } else {
            balance.setText(String.valueOf(bal));
            currency.setText("₹");
        }
    }

    private void buildDataReciver(int mykey) throws SQLException {
        data = FXCollections.observableArrayList();
        PreparedStatement build = DBConnect.getConn().prepareStatement(Execute.homeTableReceive);
        build.setInt(1, mykey);
        ResultSet rs = build.executeQuery();
        while (rs.next()) {
            HomeTable ht = new HomeTable();
            ht.transID.set(rs.getString("transID"));
            ht.date.set(rs.getString("date"));
            ht.time.set(rs.getString("time"));
            ht.sender.set(rs.getInt("senderpub"));
            ht.receiver.set(rs.getInt("receiverpub"));
            if (rs.getInt("receiverpub") == mykey)
                ht.amount.set(" +" + " ₹ " + rs.getInt("amount"));     //Add a plus sign before amount
            else
                ht.amount.set(" -" + " ₹ " + rs.getInt("amount"));     //Add a negative sign before amount
            data.add(ht);
        }
        TreeItem root = new RecursiveTreeItem<>(data, RecursiveTreeObject::getChildren);
        reciverTable.setRoot(root);
        reciverTable.setShowRoot(false);
        addSearchFieldReceiver(input, reciverTable);
    }

    private void buildDataSender(int mykey) throws SQLException {
        sendData = FXCollections.observableArrayList();
        PreparedStatement build = DBConnect.getConn().prepareStatement(Execute.homeTableSend) ;
        build.setInt(1, mykey);
        ResultSet rs = build.executeQuery();
        while (rs.next()) {
            HomeTableSend hts = new HomeTableSend();
            hts.transIDsend.set(rs.getString("transID"));
            hts.datesend.set(rs.getString("date"));
            hts.timesend.set(rs.getString("time"));
            hts.sendersend.set(rs.getInt("senderpub"));
            hts.receiversend.set(rs.getInt("receiverpub"));
            if (rs.getInt("receiverpub") == mykey)
                hts.amountsend.set(" +" + " ₹ " + rs.getInt("amount"));     //Add a plus sign before amount
            else
                hts.amountsend.set(" -" + " ₹ " + rs.getInt("amount"));     //Add a negative sign before amount
            sendData.add(hts);
        }
        TreeItem sendroot = new RecursiveTreeItem<>(sendData, RecursiveTreeObject::getChildren);
        senderTable.setRoot(sendroot);
        senderTable.setShowRoot(false);
        addSearchFieldSend(sendSearch, senderTable);
    }

    private void addSearchFieldReceiver(JFXTextField searchTextField, JFXTreeTableView treeTable) {
        searchTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                treeTable.setPredicate(new Predicate<TreeItem<HomeTable>>() {
                    @Override
                    public boolean test(TreeItem<HomeTable> child) {
                        Boolean flag = child.getValue().transID.getValue().contains(newValue)
                                || child.getValue().sender.getValue().toString().contains(newValue)
                                || child.getValue().receiver.getValue().toString().contains(newValue);
                        return flag;
                    }
                });
            }
        });
    }

    private void addSearchFieldSend(JFXTextField searchTextField, JFXTreeTableView treeTable) {
        searchTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                senderTable.setPredicate(new Predicate<TreeItem<HomeTableSend>>() {
                    @Override
                    public boolean test(TreeItem<HomeTableSend> child) {
                        Boolean flag = child.getValue().transIDsend.getValue().contains(newValue)
                                || child.getValue().sendersend.getValue().toString().contains(newValue)
                                || child.getValue().receiversend.getValue().toString().contains(newValue);
                        return flag;
                    }
                });
            }
        });
    }


    public static int getkey() throws SQLException {
        PreparedStatement getkey = DBConnect.getConn().prepareStatement(Execute.getKey);
        getkey.setString(1, LoginController.loggeduser);
        ResultSet set = getkey.executeQuery();
        if (set.next())
            return set.getInt("publickey");
        else return 0;
    }

    private String getbal(int mykey) throws SQLException {
        PreparedStatement getkey = DBConnect.getConn().prepareStatement(Execute.getBal);
        getkey.setInt(1, mykey);
        ResultSet mysetbal = getkey.executeQuery();
        if (mysetbal.next())
            return mysetbal.getString("balance");
        else
            return "00";
    }
}
