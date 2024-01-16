package agh.ui;
import agh.engine.Simulation;
import agh.engine.SimulationEngine;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;

public class SettingsPresenter {
    SimulationEngine engine = new SimulationEngine();

    @FXML
    private void onSimulationStartClicked() throws IOException {
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

    private void configureStage(Stage primaryStage, VBox viewRoot){
        var scene = new Scene(viewRoot);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Simulation App");
        primaryStage.minWidthProperty().bind(viewRoot.minWidthProperty());
        primaryStage.minHeightProperty().bind(viewRoot.minHeightProperty());
    }
}
