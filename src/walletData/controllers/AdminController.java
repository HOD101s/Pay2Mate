package walletData.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

public class AdminController extends LayOut {

    @FXML
    private TextField amount;

    @FXML
    private TextField publicKey;

    @FXML
    private Label status;

    @FXML
    private TableView<ModelTable> tableview;

    @FXML
    private TableColumn<ModelTable, String> tpublickey;

    @FXML
    private TableColumn<ModelTable, String> trequest;

    @FXML
    private TableColumn<ModelTable, CheckBox> update;

    @FXML
    void initialize() {
        tpublickey.setCellValueFactory(new PropertyValueFactory<ModelTable, String>("publickey"));
        trequest.setCellValueFactory(new PropertyValueFactory<ModelTable, String>("request"));
        update.setCellValueFactory(new PropertyValueFactory<ModelTable, CheckBox>("update"));

        try {
            buildData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private ObservableList<ModelTable> data;

    public void buildData() throws SQLException {
        data = FXCollections.observableArrayList();
        PreparedStatement build = DBConnect.getConn().prepareStatement(Execute.pubreqinsert);
        ResultSet rs = build.executeQuery();
        while (rs.next()) {
            ModelTable mt = new ModelTable();
            mt.publickey.set(rs.getString("publickey"));
            mt.request.set(rs.getString("request"));
            data.add(mt);
        }
        tableview.setItems(data);
    }

    public void adminMoney() {
        try {
            PreparedStatement addMoney = DBConnect.getConn().prepareStatement(Execute.addMoney);
            addMoney.setInt(1, Integer.parseInt(amount.getText()));
            addMoney.setInt(2, Integer.parseInt(publicKey.getText()));
            addMoney.executeUpdate();
            status.setText("Transaction Successful.");
        } catch (SQLException e) {
            e.printStackTrace();
            status.setText("Transaction cannot be processed.");
        }
    }

    public void openLogin() throws IOException {
        mylogin();
    }
}

