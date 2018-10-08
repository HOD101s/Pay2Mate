package walletData.controllers;

import javafx.fxml.FXML;
import walletData.Query.Execute;
import walletData.Scenes.LayOut;
import walletData.dbs.DBConnect;
import walletData.dbs.DBmethods;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegisterController extends LayOut{

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
        mylogin();
    }

    private boolean passRegex(String pass) {
        return pass.matches("^(?=.*[!@#$%^&*(),.?\":{}|<>])(?=.*[^a-zA-Z])(?=.*[0-9]).{4,}$");
    }

    private boolean doesUserExist(String username) throws SQLException {                                        //test username availability
        PreparedStatement userExists = DBConnect.getConn().prepareStatement(Execute.userExists);
        userExists.setString(1,username);
        return userExists.executeQuery().next();
    }

    private void registerUser(String username, String password) throws SQLException {
        //adds user to dbtable
        int pubkey = DBmethods.genPubKey();
        int prikey = DBmethods.genPriKey();
        PreparedStatement inUserData = DBConnect.getConn().prepareStatement(Execute.insertUserData);
        inUserData.setInt(1,pubkey);
        inUserData.setString(2,username);
        inUserData.setString(3,password);
        inUserData.executeUpdate();

        PreparedStatement inUserWall = DBConnect.getConn().prepareStatement(Execute.insertUserWallet);
        inUserWall.setInt(1,pubkey);
        inUserWall.setInt(2,prikey);
        inUserWall.setInt(3,10000);
        inUserWall.executeUpdate();

        usernameLabel.setText(String.format(("Welcome %s"), username));
        publickeyLabel.setText(String.format(("Public Key : %d"), pubkey));
        privatekeyLabel.setText(String.format(("Private Key : %d"), prikey));
    }
}
