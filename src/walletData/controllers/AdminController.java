package walletData.controllers;

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
import java.util.ArrayList;
import java.util.Iterator;

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

    ObservableList<ModelTable> data;

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

    @FXML
    void delRequest(ActionEvent event) {
        ObservableList<ModelTable> req = FXCollections.observableArrayList();
        for (ModelTable bean : data) {
            if (bean.getUpdate().isSelected()) {
                req.add(bean);
            }
        }
        ArrayList<ModelTable> foo = new ArrayList<ModelTable>(req);
        System.out.println(foo);
        Iterator<ModelTable> it = foo.iterator();
        while (it.hasNext()) {
            ModelTable r = it.next();
            remReq(r.getPublickey(), r.getRequest());
        }
        data.removeAll(req);
    }

    private void remReq(String pkey, String reqamt) {
        try {
            PreparedStatement delReq = DBConnect.getConn().prepareStatement(Execute.delReq);
            delReq.setString(1, pkey);
            delReq.setString(2, reqamt);
            delReq.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void buildData() throws SQLException {
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

