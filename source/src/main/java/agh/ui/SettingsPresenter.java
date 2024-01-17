package agh.ui;
import agh.daycare.GeneVariant;
import agh.daycare.MapVariant;
import agh.engine.Parameters;
import agh.engine.Simulation;
import agh.engine.SimulationEngine;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
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
    private TextField startEnergy;
    @FXML
    private TextField energyRequired;
    @FXML
    private TextField energyReproduce;
    @FXML
    private TextField minMutation;
    @FXML
    private TextField maxMutation;
    @FXML
    private TextField geneSize;
    @FXML
    private TextField energyLoss;
    @FXML
    private TextField energyGain;
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
    private Label badEValue;
    @FXML
    private Label badReqValue;
    @FXML
    private Label badRepValue;
    @FXML
    private Label badMinValue;
    @FXML
    private Label badMaxValue;
    @FXML
    private Label badSizeValue;
    @FXML
    private Label badLossValue;
    @FXML
    private Label badGainValue;
    @FXML
    private Label noMapVariant;
    @FXML
    private Label noGeneVariant;
    @FXML
    private ComboBox<String> mapVariant;
    @FXML
    private ComboBox<String> geneVariant;

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

        paramText = startEnergy.getText();
        int startEnergyVal = 0;
        try {
            startEnergyVal = Integer.parseInt(paramText);
            badEValue.setVisible(false);

            if (startEnergyVal >= 0){
                badEValue.setVisible(false);
            }
            else {
                badEValue.setText("start energy cannot be lower than 0!");
                badEValue.setVisible(true);
                canStart = false;
            }
        }
        catch (NumberFormatException e){
            badEValue.setText("bad number of start energy!");
            badEValue.setVisible(true);
            canStart = false;
        }

        paramText = energyRequired.getText();
        int energyRequiredVal = 0;
        try {
            energyRequiredVal = Integer.parseInt(paramText);
            badReqValue.setVisible(false);

            if (energyRequiredVal > 0){
                badReqValue.setVisible(false);
            }
            else {
                badReqValue.setText("energy required cannot be lower than 0!");
                badReqValue.setVisible(true);
                canStart = false;
            }
        }
        catch (NumberFormatException e){
            badReqValue.setText("bad number of energy required!");
            badReqValue.setVisible(true);
            canStart = false;
        }

        paramText = energyReproduce.getText();
        int energyReproduceVal = 0;
        try {
            energyReproduceVal = Integer.parseInt(paramText);
            badRepValue.setVisible(false);

            if (energyReproduceVal >= 0){
                badRepValue.setVisible(false);
            }
            else {
                badRepValue.setText("energy reproduce cannot be lower than 0!");
                badRepValue.setVisible(true);
                canStart = false;
            }
        }
        catch (NumberFormatException e){
            badRepValue.setText("bad number of energy reproduce!");
            badRepValue.setVisible(true);
            canStart = false;
        }

        paramText = minMutation.getText();
        int minMutationVal = 0;
        try {
            minMutationVal = Integer.parseInt(paramText);
            badMinValue.setVisible(false);

            if (minMutationVal >= 0){
                badMinValue.setVisible(false);
            }
            else {
                badMinValue.setText("minimum mutation cannot be lower than 0!");
                badMinValue.setVisible(true);
                canStart = false;
            }
        }
        catch (NumberFormatException e){
            badMinValue.setText("bad number of minimum mutation!");
            badMinValue.setVisible(true);
            canStart = false;
        }

        paramText = maxMutation.getText();
        int maxMutationVal = 0;
        try {
            maxMutationVal = Integer.parseInt(paramText);
            badMaxValue.setVisible(false);

            if (maxMutationVal >= 0){
                badMaxValue.setVisible(false);

                if (maxMutationVal >= minMutationVal){
                    badMaxValue.setVisible(false);
                }
                else {
                    badMaxValue.setText("maximum mutation must be lower than minimum mutation!");
                    badMaxValue.setVisible(true);
                    canStart = false;
                }
            }
            else {
                badMaxValue.setText("maximum mutation cannot be lower than 0!");
                badMaxValue.setVisible(true);
                canStart = false;
            }
        }
        catch (NumberFormatException e){
            badMaxValue.setText("bad number of maximum mutation!");
            badMaxValue.setVisible(true);
            canStart = false;
        }

        paramText = geneSize.getText();
        int geneSizeVal = 0;
        try {
            geneSizeVal = Integer.parseInt(paramText);
            badSizeValue.setVisible(false);

            if (geneSizeVal >= 0){
                badSizeValue.setVisible(false);
            }
            else {
                badSizeValue.setText("gene size cannot be lower than 0!");
                badSizeValue.setVisible(true);
                canStart = false;
            }
        }
        catch (NumberFormatException e){
            badSizeValue.setText("bad number of gene size!");
            badSizeValue.setVisible(true);
            canStart = false;
        }

        paramText = energyLoss.getText();
        int energyLossVal = 0;
        try {
            energyLossVal = Integer.parseInt(paramText);
            badLossValue.setVisible(false);

            if (energyLossVal >= 0){
                badLossValue.setVisible(false);
            }
            else {
                badLossValue.setText("energy loss cannot be lower than 0!");
                badLossValue.setVisible(true);
                canStart = false;
            }
        }
        catch (NumberFormatException e){
            badLossValue.setText("bad number of energy loss!");
            badLossValue.setVisible(true);
            canStart = false;
        }

        paramText = energyGain.getText();
        int energyGainVal = 0;
        try {
            energyGainVal = Integer.parseInt(paramText);
            badGainValue.setVisible(false);

            if (energyGainVal >= 0){
                badGainValue.setVisible(false);
            }
            else {
                badGainValue.setText("energy gain cannot be lower than 0!");
                badGainValue.setVisible(true);
                canStart = false;
            }
        }
        catch (NumberFormatException e){
            badGainValue.setText("bad number of energy gain!");
            badGainValue.setVisible(true);
            canStart = false;
        }

        paramText = mapVariant.getValue();
        MapVariant mapVariantValue = MapVariant.EARTH;
        if (paramText != null){
            noMapVariant.setVisible(false);
            if (paramText.equals("PORTALS")){
                mapVariantValue = MapVariant.PORTALS;
            }
        }
        else {
            noMapVariant.setText("no map variant chosen!");
            noMapVariant.setVisible(true);
            canStart = false;
        }

        paramText = geneVariant.getValue();
        GeneVariant geneVariantValue = GeneVariant.NORMAL;
        if (paramText != null){
            noGeneVariant.setVisible(false);
            if (paramText.equals("SPECIAL")){
                geneVariantValue = GeneVariant.SPECIAL;
            }
        }
        else {
            noGeneVariant.setText("no gene variant chosen!");
            noGeneVariant.setVisible(true);
            canStart = false;
        }

        if (canStart){
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getClassLoader().getResource("simulation.fxml"));
            VBox viewRoot = loader.load();
            Stage stage = new Stage();
            configureStage(stage, viewRoot);
            stage.show();

            Parameters parameters = new Parameters(geneVariantValue, mapVariantValue, mapHeightVal, mapWidthVal,
                    startPlantsVal, growingPlantsVal, startAnimalsVal, startEnergyVal, energyRequiredVal,
                    energyReproduceVal, maxMutationVal, minMutationVal, geneSizeVal, energyLossVal, energyGainVal);
            Simulation simulation = new Simulation(parameters);
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
