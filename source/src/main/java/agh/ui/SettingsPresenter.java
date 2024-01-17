package agh.ui;
import agh.daycare.GeneVariant;
import agh.daycare.MapVariant;
import agh.engine.Parameters;
import agh.engine.Simulation;
import agh.engine.SimulationEngine;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class SettingsPresenter implements Initializable {
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
    private TextField timeRefresh;
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
    private Label badTimeRefresh;
    @FXML
    private Label badFile;
    @FXML
    private ComboBox<String> mapVariant;
    @FXML
    private ComboBox<String> geneVariant;
    @FXML
    private ComboBox<String> configuration;
    private final SimulationEngine engine = new SimulationEngine();

    @Override
    public void initialize(URL location, ResourceBundle resources){
        updateConfigurations();
    }

    private void updateConfigurations(){
        ObservableList<String> options = FXCollections.observableArrayList();
        InputStream inputStream = Objects.requireNonNull(SettingsApp.class.getClassLoader().getResourceAsStream("configurations.csv"));
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;

        try {
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                options.add(parts[0]);
            }

            badFile.setVisible(false);
            configuration.setItems(options);
        }
        catch (IOException e){
            badFile.setVisible(true);
            badFile.setText("File opening error!");
        }
    }

    @FXML
    private void onApplyClicked(){
        String paramText = configuration.getValue();

        if (paramText != null){
            InputStream inputStream = Objects.requireNonNull(SettingsApp.class.getClassLoader().getResourceAsStream("configurations.csv"));
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;

            try {
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(" ");
                    if (parts[0].equals(paramText)){
                        mapWidth.setText(parts[1]);
                        mapHeight.setText(parts[2]);
                        startAnimals.setText(parts[3]);
                        startPlants.setText(parts[4]);
                        growingPlants.setText(parts[5]);
                        mapVariant.setValue(parts[6]);
                        geneVariant.setValue(parts[7]);
                        startEnergy.setText(parts[8]);
                        energyRequired.setText(parts[9]);
                        energyReproduce.setText(parts[10]);
                        minMutation.setText(parts[11]);
                        maxMutation.setText(parts[12]);
                        energyLoss.setText(parts[13]);
                        energyGain.setText(parts[14]);
                        geneSize.setText(parts[15]);
                        timeRefresh.setText(parts[16]);
                        break;
                    }
                }

                badFile.setVisible(false);
            }
            catch (IOException e){
                badFile.setVisible(true);
                badFile.setText("File opening error!");
            }
        }
    }

    @FXML
    public void onSaveClicked(){
        Parameters parameters = testParameters();

        if (parameters != null){
            Path resourcesPath = Path.of("src/main/resources");
            Path path = Path.of("src/main/resources/configurations.csv");
            try {
                List<String> lines = Files.readAllLines(path);
                String name = "config" + (lines.size() + 1);
                String configuration = name + " " + parameters + "\n";
                badFile.setVisible(false);

                try (BufferedWriter writer = Files.newBufferedWriter(resourcesPath.resolve("configurations.csv"),
                        StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
                    writer.write(configuration);
                    badFile.setVisible(false);
                } catch (IOException e) {
                    badFile.setVisible(true);
                    badFile.setText("File opening error!");
                }
            }
            catch (IOException e){
                badFile.setVisible(true);
                badFile.setText("File opening error!");
            }

            updateConfigurations();
        }
    }

    @FXML
    private void onSimulationStartClicked() throws IOException {
        Parameters parameters = testParameters();

        if (parameters != null){
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getClassLoader().getResource("simulation.fxml"));
            VBox viewRoot = loader.load();
            Stage stage = new Stage();
            configureStage(stage, viewRoot);
            stage.show();

            Simulation simulation = new Simulation(parameters);
            simulation.registerPresenter(loader.getController());
            engine.runAsyncInThreadPool(simulation);

            stage.setOnCloseRequest(event -> handleCloseRequest(stage, loader.getController()));
        }
    }

    private Parameters testParameters(){
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
        MapVariant mapVariantVal = MapVariant.EARTH;
        if (paramText != null){
            noMapVariant.setVisible(false);
            if (paramText.equals("PORTALS")){
                mapVariantVal = MapVariant.PORTALS;
            }
        }
        else {
            noMapVariant.setText("no map variant chosen!");
            noMapVariant.setVisible(true);
            canStart = false;
        }

        paramText = geneVariant.getValue();
        GeneVariant geneVariantVal = GeneVariant.NORMAL;
        if (paramText != null){
            noGeneVariant.setVisible(false);
            if (paramText.equals("SPECIAL")){
                geneVariantVal = GeneVariant.SPECIAL;
            }
        }
        else {
            noGeneVariant.setText("no gene variant chosen!");
            noGeneVariant.setVisible(true);
            canStart = false;
        }

        paramText = timeRefresh.getText();
        int timeRefreshVal = 1;
        try {
            timeRefreshVal = Integer.parseInt(paramText);
            badTimeRefresh.setVisible(false);

            if (timeRefreshVal > 0){
                badTimeRefresh.setVisible(false);
            }
            else {
                badTimeRefresh.setText("time refresh must be greater than 0!");
                badTimeRefresh.setVisible(true);
                canStart = false;
            }
        }
        catch (NumberFormatException e){
            badTimeRefresh.setText("bad time refresh value!");
            badTimeRefresh.setVisible(true);
            canStart = false;
        }

        if (canStart){
            return new Parameters(geneVariantVal, mapVariantVal, mapHeightVal, mapWidthVal,
                    startPlantsVal, growingPlantsVal, startAnimalsVal, startEnergyVal, energyRequiredVal,
                    energyReproduceVal, maxMutationVal, minMutationVal, geneSizeVal, energyLossVal,
                    energyGainVal, timeRefreshVal);
        }
        else {
            return null;
        }
    }

    private void handleCloseRequest(Stage stage, SimulationPresenter simulationPresenter){
        simulationPresenter.simulationClosed = true;
        stage.close();
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
