package agh.ui;
import agh.engine.Simulation;
import agh.engine.SimulationEngine;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;

public class SettingsPresenter {
    SimulationEngine engine = new SimulationEngine();
    @FXML
    private TextField mapWidth;
    @FXML
    private Label badValue;

    @FXML
    private void onSimulationStartClicked() throws IOException {
        String mapWidthValue = mapWidth.getText();

        try {
            int mapWidth = Integer.parseInt(mapWidthValue);

            if (mapWidth > 0){
                if (mapWidth < 2000){
                    badValue.setVisible(false);

                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getClassLoader().getResource("simulation.fxml"));
                    VBox viewRoot = loader.load();
                    Stage stage = new Stage();
                    configureStage(stage, viewRoot);
                    stage.show();

                    Simulation simulation = new Simulation(0, 0, 5, 5, 5, 2, 10, 36, 3, 3, 3, 3, 4, 5, 15);
                    simulation.registerPresenter(loader.getController());
                    engine.runAsyncInThreadPool(simulation);
                }
                else {
                    badValue.setText("map width too large!");
                    badValue.setVisible(true);
                }
            }
            else {
                badValue.setText("map width value cannot be lower than 0!");
                badValue.setVisible(true);
            }
        }
        catch (NumberFormatException e){
            badValue.setText("bad map width value!");
            badValue.setVisible(true);
        }
    }

    private void configureStage(Stage primaryStage, VBox viewRoot){
        var scene = new Scene(viewRoot);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Simulation App");
        primaryStage.minWidthProperty().bind(viewRoot.minWidthProperty());
        primaryStage.minHeightProperty().bind(viewRoot.minHeightProperty());
    }
}
