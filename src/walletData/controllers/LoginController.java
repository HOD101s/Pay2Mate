package walletData.controllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import walletData.DBConnect;
import walletData.Main;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;


import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {



    @FXML
    static GridPane root;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private Button login;

    @FXML
    private Button register;

    @FXML
    private Label status;

    static String loggeduser;

    @FXML
    void onLogin(ActionEvent event) {
        System.out.println("OnLogin called");
        String query = "SELECT * FROM `users` WHERE `username` = '%s' && `password` = '%s'";            //edit in our table name
        query = String.format(query, username.getText(), password.getText());

        if(username.getText().isEmpty() || password.getText().isEmpty()){
            status.setText("username or password cannot be empty");
        }else{
            try{
                ResultSet set =
                        DBConnect.getStatement()
                                .executeQuery(query);
                if(set.next()){
                    loggeduser = username.getText();
                    status.setText("Logged in !");

                    try {
                        Stage registerStage = Main.stage;
                        registerStage.setTitle("Register");
                        Parent root = FXMLLoader.load(getClass().getResource("/walletData/fxml/home.fxml"));
                        registerStage.setScene(new Scene(root));
                        registerStage.setResizable(false);
                        registerStage.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }else{
                    status.setText("Incorrect username or password");
                }
                set.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

//    public void openHome() throws IOException{
//        Stage homeStage = Main.stage;
//        root = FXMLLoader.load(getClass().getResource("/walletData/fxml/home.fxml"));
//        homeStage.setTitle(loggeduser);
//        homeStage.setScene(new Scene(root));
//        homeStage.setResizable(false);
//        homeStage.show();
//    }

    @FXML
    void onRegister(ActionEvent event) throws IOException {
        Stage registerStage = Main.stage;
        registerStage.setTitle("Register");
        root = FXMLLoader.load(getClass().getResource("/walletData/fxml/register.fxml"));
        registerStage.setScene(new Scene(root));
        registerStage.setResizable(false);
        registerStage.show();
    }


}
