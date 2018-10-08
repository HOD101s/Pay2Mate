package walletData.controllers;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import walletData.Query.Execute;
import walletData.Scenes.LayOut;
import walletData.dbs.DBConnect;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AdminController extends LayOut{

    @FXML
    private TextField amount;

    @FXML
    private TextField publicKey;

    @FXML
    private Label status;

    TableController tb = new TableController();


    public void adminMoney(){
        try {
            DBConnect.getStatement().executeUpdate(String.format(Execute.addMoney,Integer.parseInt(amount.getText()),Integer.parseInt(publicKey.getText())));
            status.setText("Transaction Successful.");
        } catch (SQLException e) {
            e.printStackTrace();
            status.setText("Transaction cannot be processed.");
        }
    }

    public void openLogin() throws IOException{
        mylogin();
    }
}

class TableController implements Initializable{

    @FXML
    private TableView<ModelTable> myTable;


    TableColumn<ModelTable,String> tpublickey;


    TableColumn<ModelTable,String> trequest;

    ObservableList<ModelTable> oblist = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            ResultSet adTable = DBConnect.getStatement().executeQuery(Execute.pubreqinsert);
            adTable.last();
            while(adTable.previous()){
                oblist.add(new ModelTable(adTable.getString("publickey"),adTable.getString("request")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        tpublickey.setCellFactory(new PropertyValueFactory("publickey"));
        trequest.setCellFactory(new PropertyValueFactory("request"));

        myTable.setItems(oblist);
    }
}

