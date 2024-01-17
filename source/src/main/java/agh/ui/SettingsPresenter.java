package agh.ui;
import agh.daycare.MapVariant;
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
    private TextField startPlants;
    @FXML
    private TextField growingPlants;
    @FXML
    private TextField startAnimals;
    @FXML
    private Label badWValue;
    @FXML
    private Label badHValue;
    @FXML
    private Label badSPValue;
    @FXML
    private Label badGPValue;
    @FXML
    private Label badAValue;

    @FXML
    private void onSimulationStartClicked() throws IOException {
        boolean canStart = true;
        String paramText = mapWidth.getText();
        int mapWidthVal = 0;

        try {
            mapWidthVal = Integer.parseInt(paramText);
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
                badWValue.setText("map width value must by greater than 0!");
                badWValue.setVisible(true);
                canStart = false;
            }
        }
        catch (NumberFormatException e){
            badWValue.setText("bad map width value!");
            badWValue.setVisible(true);
            canStart = false;
        }

        paramText = mapHeight.getText();
        int mapHeightVal = 0;
        try {
            mapHeightVal = Integer.parseInt(paramText);
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
                badHValue.setText("map height value must by greater than 0!");
                badHValue.setVisible(true);
                canStart = false;
            }
        }
        catch (NumberFormatException e){
            badHValue.setText("bad map height value!");
            badHValue.setVisible(true);
            canStart = false;
        }

        paramText = startAnimals.getText();
        int startAnimalsVal = 0;
        try {
            startAnimalsVal = Integer.parseInt(paramText);
            badAValue.setVisible(false);

            if (startAnimalsVal >= 0){
                badAValue.setVisible(false);

                if (startAnimalsVal <= mapWidthVal * mapHeightVal){
                    badAValue.setVisible(false);
                }
                else {
                    badAValue.setText("starting animals number too large!");
                    badAValue.setVisible(true);
                    canStart = false;
                }
            }
            else {
                badAValue.setText("starting animals number cannot be lower than 0!");
                badAValue.setVisible(true);
                canStart = false;
            }
        }
        catch (NumberFormatException e){
            badAValue.setText("bad starting animals value!");
            badAValue.setVisible(true);
            canStart = false;
        }

        paramText = startPlants.getText();
        int startPlantsVal = 0;
        try {
            startPlantsVal = Integer.parseInt(paramText);
            badSPValue.setVisible(false);

            if (startPlantsVal >= 0){
                badSPValue.setVisible(false);

                if (startPlantsVal <= mapWidthVal * mapHeightVal){
                    badSPValue.setVisible(false);
                }
                else {
                    badSPValue.setText("starting plants value too large!");
                    badSPValue.setVisible(true);
                    canStart = false;
                }
            }
            else {
                badSPValue.setText("starting plants value cannot be lower than 0!");
                badSPValue.setVisible(true);
                canStart = false;
            }
        }
        catch (NumberFormatException e){
            badSPValue.setText("bad starting plants value!");
            badSPValue.setVisible(true);
            canStart = false;
        }

        paramText = growingPlants.getText();
        int growingPlantsVal = 0;
        try {
            growingPlantsVal = Integer.parseInt(paramText);
            badGPValue.setVisible(false);

            if (growingPlantsVal > 0){
                badGPValue.setVisible(false);

                if (growingPlantsVal <= mapWidthVal * mapHeightVal){
                    badGPValue.setVisible(false);
                }
                else {
                    badGPValue.setText("too large number of plants growing every day!");
                    badGPValue.setVisible(true);
                    canStart = false;
                }
            }
            else {
                badGPValue.setText("number of plants growing every day cannot be lower than 0!");
                badGPValue.setVisible(true);
                canStart = false;
            }
        }
        catch (NumberFormatException e){
            badGPValue.setText("bad number of plants growing every day!");
            badGPValue.setVisible(true);
            canStart = false;
        }

        if (canStart){
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getClassLoader().getResource("simulation.fxml"));
            VBox viewRoot = loader.load();
            Stage stage = new Stage();
            configureStage(stage, viewRoot);
            stage.show();

            Simulation simulation = new Simulation(0, MapVariant.EARTH, mapHeightVal, mapWidthVal,
                    startPlantsVal, growingPlantsVal, startAnimalsVal, 36, 3, 3, 3, 3, 4, 5, 15);
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
