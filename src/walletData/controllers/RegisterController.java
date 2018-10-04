package walletData.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import walletData.DBConnect;
import walletData.DBmethods;
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
    static TextField regusername;

    @FXML
    private PasswordField regconpassword;

    @FXML
    private Button register;

    @FXML
    private Button backToLogin;

    @FXML
    private PasswordField regpassword;

    @FXML
    private Label publickeyLabel;

    @FXML
    private Label privatekeyLabel;

    @FXML
    private Label usernameLabel;

    @FXML
    public void newRegister() {
        String username = regusername.getText();
        String password = regpassword.getText();
        String confpassword = regconpassword.getText();

        if (username.isEmpty() || password.isEmpty() || confpassword.isEmpty()) {
            publickeyLabel.setText("Fields cannot be empty");
        } else if (!password.equals(confpassword)) {
            publickeyLabel.setText("Passwords do not match");
        } else if (doesUserExist(username)) {
            publickeyLabel.setText("User already exists");
        } else {
            registerUser(username, password);
        }
    }

    boolean doesUserExist(String username) {
        boolean found = false;
        try {
            String query = String.format("SELECT * FROM `users` WHERE username = '%s';", username);
            found = DBConnect.getStatement().executeQuery(query).next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return found;
    }

    void registerUser(String username, String password) {
        try {

            int pubkey = DBmethods.genPubKey();
            int prikey = DBmethods.genPriKey();
            String insert = String.format("INSERT INTO `users`(`username`, `password`,`balance`,`publickey`,`privatekey`) VALUES ('%s','%s','%d','%d','%d')", username, password,1000,pubkey,prikey); //change table name
            DBConnect.getStatement().executeUpdate(insert);

            createtransactiontable();

            usernameLabel.setText(String.format(("Welcome %s"),username));
            publickeyLabel.setText(String.format(("Public Key : %d"),pubkey));
            privatekeyLabel.setText(String.format(("Private Key : %d"),prikey));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createtransactiontable(){
        try {
            String tablequery = String.format("CREATE TABLE %s (TransactionIDs varchar(255));",regusername.getText()); //add fields
            DBConnect.getStatement().executeUpdate(tablequery);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @FXML
    public void backToLogin() {
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
}
