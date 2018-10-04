package walletData;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import static walletData.DBmethods.*;

public class Main extends Application {
    //Test push

    public static Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception{
        stage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("/walletData/fxml/login.fxml"));
        primaryStage.setTitle("Pay2Mate");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        DBConnect.connect();
        launch(args);
        DBConnect.disconnect();
    }
}
