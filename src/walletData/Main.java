package walletData;
import java.util.Scanner;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import walletData.dbs.Create;
import walletData.dbs.DBConnect;

public class Main extends Application {

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
        Scanner sc = new Scanner(System.in);
        DBConnect.connect();
        System.out.println("Do you want to set up dbs?\nY/N\n");
        String dbCreate = sc.nextLine();
        if(dbCreate.equals("Y") || dbCreate.equals("y")) {
            Create.create();
        }
        launch(args);
        DBConnect.disconnect();
    }
}
