package walletData.Scenes;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import walletData.Main;
import walletData.controllers.LoginController;

import java.io.IOException;

public class LayOut {
    protected void mySend() throws IOException{
        Stage sendMoneyStage = Main.stage;
        Parent root = FXMLLoader.load(getClass().getResource("/walletData/fxml/sendVerification.fxml"));
        sendMoneyStage.setTitle("Hello World");
        sendMoneyStage.setScene(new Scene(root));
        sendMoneyStage.show();
    }

    protected void mylogin() throws IOException{
        Stage loginStage = Main.stage;
        Parent root = FXMLLoader.load(getClass().getResource("/walletData/fxml/login.fxml"));
        loginStage.setTitle("Login");
        loginStage.setScene(new Scene(root));
        loginStage.setResizable(false);
        loginStage.show();
    }

    protected void myregister() throws IOException{
        Stage registerStage = Main.stage;
        registerStage.setTitle("Register");
        Parent root = FXMLLoader.load(getClass().getResource("/walletData/fxml/register.fxml"));
        registerStage.setScene(new Scene(root));
        registerStage.setResizable(false);
        registerStage.show();
    }

    protected void myadmin() throws IOException{
        Stage adminStage = Main.stage;
        adminStage.setTitle("ADMIN");
        Parent root = FXMLLoader.load(getClass().getResource("/walletData/fxml/admin.fxml"));
        adminStage.setScene(new Scene(root));
        adminStage.setResizable(false);
        adminStage.show();
    }

    protected void myhome() throws IOException{
        Stage registerStage = Main.stage;
        registerStage.setTitle(LoginController.loggeduser);
        Parent root = FXMLLoader.load(getClass().getResource("/walletData/fxml/home.fxml"));
        registerStage.setScene(new Scene(root));
        registerStage.setResizable(false);
        registerStage.show();
    }




}
