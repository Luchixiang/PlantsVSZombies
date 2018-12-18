package window;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class MainFrame extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("welcome.fxml"));
        primaryStage.setScene(new Scene(root,900,600));
        primaryStage.setResizable(false);
        primaryStage.setTitle("植物大战僵尸");
        primaryStage.show();
        primaryStage.setOnCloseRequest(event -> System.exit(1));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
