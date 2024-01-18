package agh.ui;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.*;

public class SettingsApp extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("settings.fxml"));
        VBox viewRoot = loader.load();

        configureStage(primaryStage, viewRoot);
        primaryStage.show();

        primaryStage.setOnCloseRequest(event -> handleCloseRequest(primaryStage, loader.getController()));
    }

    private void handleCloseRequest(Stage stage, SettingsPresenter controller){
        controller.stopEngine();
        stage.close();
    }

    private void configureStage(Stage primaryStage, VBox viewRoot){
        var scene = new Scene(viewRoot);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Settings - Darvin World");
        primaryStage.getIcons().add(new Image("images/icon.ico"));
        primaryStage.minWidthProperty().bind(viewRoot.minWidthProperty());
        primaryStage.minHeightProperty().bind(viewRoot.minHeightProperty());
    }
}
