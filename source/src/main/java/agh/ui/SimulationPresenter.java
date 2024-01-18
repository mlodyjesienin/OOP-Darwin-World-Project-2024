package agh.ui;
import agh.mapEntities.Animal;
import agh.mapEntities.Plant;
import agh.mapEntities.WorldMap;
import agh.daycare.DayCare;
import agh.statistics.Statisticer;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.List;

public class SimulationPresenter {
    private WorldMap map;
    private int referenceEnergy;
    public boolean simulationClosed = false;
    public volatile boolean pauseSimulation = false;
    @FXML
    private GridPane mapGrid;
    @FXML
    private Button stopSimulation;
    @FXML
    private Button showStats;
    @FXML
    private VBox mapStats;
    @FXML
    private VBox animalStats;
    @FXML
    private Label animalsCount;
    @FXML
    private Label plantsCount;
    @FXML
    private Label freeFields;
    @FXML
    private Label commonGene;
    @FXML
    private Label avgEnergy;
    @FXML
    private Label avgLive;
    @FXML
    private Label avgChildren;
    @FXML
    private Label animalEnergy;
    private Statisticer statisticer;

    public void setWorldMap(WorldMap map){
        this.map = map;
    }

    public void setStatisticer(Statisticer statisticer){
        this.statisticer = statisticer;
    }

    public void setReferenceEnergy(int energy){
        referenceEnergy = energy;
    }

    @FXML
    public void onShowStatsClicked(){
        if (showStats.getText().equals("Show stats")){
            showStats.setText("Hide stats");
        }
        else {
            showStats.setText("Show stats");
        }

        mapStats.setVisible(!mapStats.isVisible());
        mapStats.setManaged(!mapStats.isManaged());
        updateMapStats();
    }

    @FXML
    private void updateMapStats(){
        animalsCount.setText("Alive animals: " + statisticer.currAliveCount);
        plantsCount.setText("Number of plants: " + statisticer.currPlantCount);
        freeFields.setText("Free fields: " + statisticer.availableSpace);
        commonGene.setText("Most common gene: " + statisticer.popularGenome);
        avgEnergy.setText("Average energy level: " + Math.round(statisticer.averageEnergy));
        avgLive.setText("Average life time: " + Math.round(statisticer.averageLifeLength));
        avgChildren.setText("Average children count: " + Math.round(statisticer.averageChildren));
    }

    @FXML
    public void onSimulationStopClicked(){
        pauseSimulation = !pauseSimulation;

        if (stopSimulation.getText().equals("STOP")){
            stopSimulation.setText("START");
        }
        else {
            stopSimulation.setText("STOP");
        }
    }

    public void mapChanged(DayCare dayCare){
        Platform.runLater(() -> {
            drawMap(dayCare);
            updateMapStats();
        });
    }

    private void drawMap(DayCare dayCare){
        int sizeX = map.getBoundary().upperCorner().getX() - map.getBoundary().lowerCorner().getX() + 1;
        int sizeY = map.getBoundary().upperCorner().getY() - map.getBoundary().lowerCorner().getY() + 1;
        double cellSize = (mapGrid.getScene().getWindow().getHeight() - 100) / sizeY;
        clearGrid(sizeX, sizeY);

        for (int i = 0; i < sizeX; i++){
            mapGrid.getColumnConstraints().add(new ColumnConstraints(cellSize));
        }
        for (int i = 0; i < sizeY; i++){
            mapGrid.getRowConstraints().add(new RowConstraints(cellSize));
        }

        for (Plant plant: map.getPlants().values()){
            StackPane cell = new StackPane();
            mapGrid.add(cell, plant.getPosition().getX(), sizeY - plant.getPosition().getY() - 1);
            cell.setStyle("-fx-background-color: #00cc00;");
        }

        Image image = new Image("images/animal1.png");
        for (List<Animal> an: map.getAnimals().values()){
            switch (an.get(0).getDirection()){
                case NORTH -> image = new Image("images/animal1.png");
                case NORTHEAST -> image = new Image("images/animal2.png");
                case EAST -> image = new Image("images/animal3.png");
                case SOUTHEAST -> image = new Image("images/animal4.png");
                case SOUTH -> image = new Image("images/animal5.png");
                case SOUTHWEST -> image = new Image("images/animal6.png");
                case WEST -> image = new Image("images/animal7.png");
                case NORTHWEST -> image = new Image("images/animal8.png");
            }

            ImageView imageView = new ImageView(image);
            int colorValue = (240 - 50) / referenceEnergy * an.get(0).getEnergy();
            Color overlayColor = Color.rgb(Math.max(240 - colorValue, 50), 0, 0);
            PixelReader pixelReader = imageView.getImage().getPixelReader();
            int width = (int)imageView.getImage().getWidth();
            int height = (int)imageView.getImage().getHeight();

            WritableImage newImage = new WritableImage(width, height);
            PixelWriter pixelWriter = newImage.getPixelWriter();

            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    Color originalColor = pixelReader.getColor(x, y);

                    Color newColor = new Color(
                            overlayColor.getRed(),
                            overlayColor.getGreen(),
                            overlayColor.getBlue(),
                            originalColor.getOpacity()
                    );

                    pixelWriter.setColor(x, y, newColor);
                }
            }

            imageView.setImage(newImage);
            imageView.setFitWidth(cellSize * 0.8);
            imageView.setFitHeight(cellSize * 0.8);

            GridPane.setHalignment(imageView, HPos.CENTER);
            imageView.setOnMouseClicked(event -> handleLabelClick(an.get(0)));
            mapGrid.add(imageView, an.get(0).getPosition().getX(), sizeY - an.get(0).getPosition().getY() - 1);
        }
    }

    private void handleLabelClick(Animal animal){
        //animalStats.setVisible(!mapStats.isVisible());
        //animalStats.setManaged(!mapStats.isManaged());
    }

    private void updateAnimalStats(){
        //animalEnergy.setText("Energy: " + animal.getEnergy());
    }

    private void clearGrid(int sizeX, int sizeY){
        mapGrid.getChildren().retainAll(mapGrid.getChildren().get(0));
        mapGrid.getColumnConstraints().clear();
        mapGrid.getRowConstraints().clear();

        for (int i = 0; i < sizeY; i++){
            for (int j = 0; j < sizeX; j++){
                StackPane cell = new StackPane();
                mapGrid.add(cell, i, j);
                cell.setStyle("-fx-background-color: #a8e7a8;");
            }
        }
    }
}
