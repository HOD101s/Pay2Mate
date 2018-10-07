package walletData.controllers;

import com.jfoenix.controls.JFXTextField;
import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import walletData.Query.Execute;
import walletData.Scenes.LayOut;
import walletData.dbs.DBConnect;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminController extends LayOut{

    @FXML
    private JFXTextField amount;

    @FXML
    private JFXTextField publicKey;

    @FXML
    private Label status;

//    @FXML
//    private TableView<RequestEntry> requestTable;
//
//    @FXML
//    private TableColumn publickey;
//
//    @FXML
//    private TableColumn requestedamount;

//    @FXML
//    private void initialize(){
//
//        publickey.setCellValueFactory(cellData -> cellData.getValue().publickey().asObject());
//        requestedamount.setCellValueFactory(cellData -> cellData.getValue().requestedamount().asObject());
//    }
//
//    public String publickey(){
//
//        return publickey.get();
//    }
//
//    private void tableSet() throws SQLException{
//        ResultSet pubs = DBConnect.getStatement().executeQuery(Execute.pubinsert);
//        ResultSet pubs = DBConnect.getStatement().executeQuery(Execute.reqinsert);
//    }

    public void adminMoney(){
        try {
            DBConnect.getStatement().executeUpdate(String.format(Execute.addMoney,Integer.parseInt(amount.getText()),Integer.parseInt(publicKey.getText())));
            status.setText("Successful Transaction");
        } catch (SQLException e) {
            e.printStackTrace();
            status.setText("Could Not Complete Transaction");
        }
    }

    public void openLogin() throws IOException{
        mylogin();
    }
}

//class RequestEntry  {
//    public SimpleIntegerProperty publickey = new SimpleIntegerProperty();
//    public SimpleIntegerProperty request = new SimpleIntegerProperty();
//    public RequestEntry() {
//        public int getpublickey () {
//            return publickey.get();
//        }
//
//        public int getrequest () {
//            return request.get();
//        }
//    }
//}
//
//class RequestEntryDAO{
//    private RequestEntry createRequest(ResultSet rs) {
//        RequestEntry p = new RequestEntry();
//        try {
//            p.publickey(rs.getInt("publickey"));
//            p.request(rs.getString("request"));
//        } catch (SQLException ex) {
//        }
//        return p;
//    }
//
//    public List<RequestEntry> getpublickey() {
//        List<RequestEntry> list = new ArrayList<>();
//        try {
//            ResultSet rs = DBConnect.getStatement().executeQuery(Execute.pubinsert);
//            while (rs.next()) {
//                RequestEntry p = createRequest(rs);
//                list.add(p);
//            }
//        } catch (SQLException ex) {
//        }
//        return list;
//    }
//
//    public List<RequestEntry> getrequest() {
//        List<RequestEntry> list = new ArrayList<>();
//        try {
//            ResultSet rs = DBConnect.getStatement().executeQuery(Execute.reqinsert);
//            while (rs.next()) {
//                RequestEntry p = createRequest(rs);
//                list.add(p);
//            }
//        } catch (SQLException ex) {
//        }
//        return list;
//    }


//}
