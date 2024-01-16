package agh.ui;
import agh.engine.Simulation;
import agh.engine.SimulationEngine;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;

public class SettingsPresenter {
    SimulationEngine engine = new SimulationEngine();
    @FXML
    private TextField mapWidth;
    @FXML
    private TextField mapHeight;
    @FXML
    private Label badWValue;
    @FXML
    private Label badHValue;

    @FXML
    private void onSimulationStartClicked() throws IOException {
        boolean canStart = true;
        int mapWidthVal = 0;
        int mapHeightVal = 0;
        String mapWidthText = mapWidth.getText();

        try {
            mapWidthVal = Integer.parseInt(mapWidthText);
            badWValue.setVisible(false);

            if (mapWidthVal > 0){
                badWValue.setVisible(false);

                if (mapWidthVal <= 2000){
                    badWValue.setVisible(false);
                }
                else {
                    badWValue.setText("map width too large!");
                    badWValue.setVisible(true);
                    canStart = false;
                }
            }
            else {
                badWValue.setText("map width value cannot be lower than 0!");
                badWValue.setVisible(true);
                canStart = false;
            }
        }
        catch (NumberFormatException e){
            badWValue.setText("bad map width value!");
            badWValue.setVisible(true);
            canStart = false;
        }

        String mapHeightValue = mapHeight.getText();

        try {
            mapHeightVal = Integer.parseInt(mapHeightValue);
            badHValue.setVisible(false);

            if (mapHeightVal > 0){
                badHValue.setVisible(false);

                if (mapHeightVal <= 2000){
                    badHValue.setVisible(false);
                }
                else {
                    badHValue.setText("map height too large!");
                    badHValue.setVisible(true);
                    canStart = false;
                }
            }
            else {
                badHValue.setText("map height value cannot be lower than 0!");
                badHValue.setVisible(true);
                canStart = false;
            }
        }
        catch (NumberFormatException e) {
            badHValue.setText("bad map height value!");
            badHValue.setVisible(true);
            canStart = false;
        }

        if (canStart){
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getClassLoader().getResource("simulation.fxml"));
            VBox viewRoot = loader.load();
            Stage stage = new Stage();
            configureStage(stage, viewRoot);
            stage.show();

            Simulation simulation = new Simulation(0, 0, mapHeightVal, mapWidthVal, 5, 2, 10, 36, 3, 3, 3, 3, 4, 5, 15);
            simulation.registerPresenter(loader.getController());
            engine.runAsyncInThreadPool(simulation);
        }
    }

    private void configureStage(Stage primaryStage, VBox viewRoot){
        var scene = new Scene(viewRoot);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Simulation App");
        primaryStage.getIcons().add(new Image("images/icon.ico"));
        primaryStage.minWidthProperty().bind(viewRoot.minWidthProperty());
        primaryStage.minHeightProperty().bind(viewRoot.minHeightProperty());
    }
}
