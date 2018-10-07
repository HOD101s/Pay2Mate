package walletData.controllers;

import javafx.fxml.FXML;
import java.util.regex.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import walletData.Query.Execute;
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
        try {
            if (regname.getText().isEmpty() || regpassword.getText().isEmpty() || regconpassword.getText().isEmpty()) {
                publickeyLabel.setText("Fields cannot be empty");
            } else if (!regpassword.getText().equals(regconpassword.getText())) {
                publickeyLabel.setText("Passwords do not match");
            } else if (doesUserExist(regname.getText())) {
                publickeyLabel.setText("User already exists");
            } else if(!passRegex(regpassword.getText())){
                publickeyLabel.setText("Password : at least 1 Special Character 1 Character 1 Digit");
            } else
                registerUser(regname.getText(), regpassword.getText());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void backToLogin() throws IOException {                                                             //Stages Login
        Stage loginStage = Main.stage;
        root = FXMLLoader.load(getClass().getResource("/walletData/fxml/login.fxml"));
        loginStage.setTitle("Login");
        loginStage.setScene(new Scene(root));
        loginStage.setResizable(false);
        loginStage.show();
    }

    private boolean passRegex(String pass) {
        return pass.matches("^(?=.*[!@#$%^&*(),.?\":{}|<>])(?=.*[^a-zA-Z])(?=.*[0-9]).{4,8}$");
    }

    private boolean doesUserExist(String username) throws SQLException {                                        //test username availability
        return DBConnect.getStatement().executeQuery(String.format(Execute.userExists, username)).next();
    }

    private void registerUser(String username, String password) throws SQLException {        //adds user to dbtable
        int pubkey = DBmethods.genPubKey();
        int prikey = DBmethods.genPriKey();
        DBConnect.getStatement().executeUpdate(String.format(Execute.insertUserData, pubkey, username, password));
        DBConnect.getStatement().executeUpdate(String.format(Execute.insertUserWallet, pubkey, prikey, 10000));
        usernameLabel.setText(String.format(("Welcome %s"), username));
        publickeyLabel.setText(String.format(("Public Key : %d"), pubkey));
        privatekeyLabel.setText(String.format(("Private Key : %d"), prikey));
    }
}
