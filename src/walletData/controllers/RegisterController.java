package walletData.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import walletData.dbs.DBConnect;
import walletData.dbs.DBmethods;
import walletData.Main;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import java.io.IOException;
import java.sql.SQLException;

public class RegisterController {
    @FXML
    private GridPane root;

    @FXML
    private TextField regname;

    @FXML
    private PasswordField regpassword;

    @FXML
    private PasswordField regconpassword;

    @FXML
    private Label publickeyLabel;

    @FXML
    private Label privatekeyLabel;

    @FXML
    private Label usernameLabel;

    @FXML
    public void newRegister() {
        if (regname.getText().isEmpty() || regpassword.getText().isEmpty() || regconpassword.getText().isEmpty()) {
            publickeyLabel.setText("Fields cannot be empty");
        } else if (!regpassword.getText().equals(regconpassword.getText())) {
            publickeyLabel.setText("Passwords do not match");
        } else if (doesUserExist(regname.getText())) {
            publickeyLabel.setText("User already exists");
        } else {
            try {
                registerUser(regname.getText(), regpassword.getText());
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void backToLogin() {                                                             //Stages Login
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

    private boolean doesUserExist(String username) {                                        //test username availability
        boolean found = false;
        try {
            String query = String.format("SELECT * FROM `userdata` WHERE username = '%s';", username);
            found = DBConnect.getStatement().executeQuery(query).next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return found;
    }

    private void registerUser(String username, String password) throws SQLException{        //adds user to dbtable
        int pubkey = 0;
        int prikey = 0;
        try {
             pubkey = DBmethods.genPubKey();
             prikey = DBmethods.genPriKey();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String insertuserdata = String.format("INSERT INTO `userdata` (`publickey`,`username`,`password`) VALUES ('%d','%s','%s')",pubkey,username, password);
            DBConnect.getStatement().executeUpdate(insertuserdata);
        String insertuserwallet = String.format("INSERT INTO `userwallet` (`publickey`,`privatekey`,`balance`) VALUES ('%d','%d','%d');",pubkey,prikey,10000);
            DBConnect.getStatement().executeUpdate(insertuserwallet);
            usernameLabel.setText(String.format(("Welcome %s"),username));
            publickeyLabel.setText(String.format(("Public Key : %d"),pubkey));
            privatekeyLabel.setText(String.format(("Private Key : %d"),prikey));
    }
}
