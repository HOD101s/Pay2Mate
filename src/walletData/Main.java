package walletData;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import walletData.dbs.Create;
import walletData.dbs.DBConnect;

public class Main extends Application {

    public static Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/walletData/images/walletlogo.png")));
        Parent root = FXMLLoader.load(getClass().getResource("/walletData/fxml/login.fxml"));
        primaryStage.setTitle("Pay2Mate");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        System.out.println("Pay2Mate");
        DBConnect.connect();
        Create.create();
        launch(args);
        DBConnect.disconnect();
    }
}
