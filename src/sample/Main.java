package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/view/calculator.fxml"));
        Parent root = loader.load();
        stage.setTitle("PCalculator");
        stage.setScene(new Scene(root, 230, 440));
        stage.setResizable(false);
        stage.show();
        stage.getIcons().add(new Image("sample/resource/icon/icon.png"));
        stage.setOnCloseRequest(event -> System.exit(0));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
