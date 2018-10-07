package walletData.Scenes;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import walletData.Main;
import walletData.controllers.LoginController;

import java.io.IOException;

public class LayOut {

    private void myMaster(String myURL , String name) throws IOException{
        Stage mystage = Main.stage;
        Parent root = FXMLLoader.load(getClass().getResource(myURL));
        mystage.setTitle(name);
        mystage.setScene(new Scene(root));
        mystage.show();
    }
    protected void mySend() throws IOException{
        myMaster("/walletData/fxml/sendVerification.fxml","Send Money");
    }

    protected void mylogin() throws IOException{
        myMaster("/walletData/fxml/login.fxml","Login");
    }

    protected void myregister() throws IOException{
        myMaster("/walletData/fxml/register.fxml","Register");
    }

    protected void myadmin() throws IOException{
        myMaster("/walletData/fxml/admin.fxml","ADMIN");
    }

    protected void myhome() throws IOException{
        myMaster("/walletData/fxml/home.fxml",LoginController.loggeduser);
    }

    protected void myrequest() throws IOException{
        myMaster("/walletData/fxml/request.fxml","Request");
    }
}
