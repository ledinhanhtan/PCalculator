package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/view/sample.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("Calculator");
        primaryStage.setScene(new Scene(root, 230, 440));
        primaryStage.setResizable(false);
        primaryStage.show();
        System.out.println("Test");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
