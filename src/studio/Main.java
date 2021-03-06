package studio;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import studio.blocklib.BlockLibrary;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Global.running = true;

        BlockLibrary.init();

        Parent root = FXMLLoader.load(getClass().getResource("/res/main.fxml"));

        primaryStage.setScene(new Scene(root));
        primaryStage.setWidth(1280);
        primaryStage.setHeight(720);
        primaryStage.setTitle("Logica Studio");
        primaryStage.setOnCloseRequest(event -> {
            Global.running = false;
        });
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
