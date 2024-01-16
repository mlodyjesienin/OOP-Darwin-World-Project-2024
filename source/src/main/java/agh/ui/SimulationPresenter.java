package agh.ui;
import agh.Animal;
import agh.Plant;
import agh.WorldMap;
import agh.dayCareMechanism.DayCare;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import java.util.List;

public class SimulationPresenter {
    private WorldMap map;
    @FXML
    private GridPane mapGrid;

    public void setWorldMap(WorldMap map){
        this.map = map;
    }

    public void mapChanged(DayCare dayCare){
        Platform.runLater(() -> {
            drawMap(dayCare);
        });
    }

    private void drawMap(DayCare dayCare){
        clearGrid();
        int sizeX = map.getBoundary().upperCorner().getX() - map.getBoundary().lowerCorner().getX() + 1;
        int sizeY = map.getBoundary().upperCorner().getY() - map.getBoundary().lowerCorner().getY() + 1;

        for (int i = 0; i < sizeX + 1; i++){
            mapGrid.getColumnConstraints().add(new ColumnConstraints(50));
        }
        for (int i = 0; i < sizeY + 1; i++){
            mapGrid.getRowConstraints().add(new RowConstraints(50));
        }

        ImageView imageView = new ImageView(new Image("images/animal1.png"));
        for (List<Animal> an: map.getAnimals().values()){
            switch (an.get(0).getDirection()){
                case NORTH -> imageView = new ImageView(new Image("images/animal1.png"));
                case NORTHEAST -> imageView = new ImageView(new Image("images/animal2.png"));
                case EAST -> imageView = new ImageView(new Image("images/animal3.png"));
                case SOUTHEAST -> imageView = new ImageView(new Image("images/animal4.png"));
                case SOUTH -> imageView = new ImageView(new Image("images/animal5.png"));
                case SOUTHWEST -> imageView = new ImageView(new Image("images/animal6.png"));
                case WEST -> imageView = new ImageView(new Image("images/animal7.png"));
                case NORTHWEST -> imageView = new ImageView(new Image("images/animal8.png"));
            }

            GridPane.setHalignment(imageView, HPos.CENTER);
            mapGrid.add(imageView, an.get(0).getPosition().getX(), sizeY - an.get(0).getPosition().getY() - 1);
        }

        for (Plant plant: map.getPlants().values()){
            imageView = new ImageView(new Image("images/plant.png"));
            GridPane.setHalignment(imageView, HPos.CENTER);
            mapGrid.add(imageView, plant.getPosition().getX(), sizeY - plant.getPosition().getY() - 1);
        }
    }

    private void clearGrid(){
        mapGrid.getChildren().retainAll(mapGrid.getChildren().get(0));
        mapGrid.getColumnConstraints().clear();
        mapGrid.getRowConstraints().clear();
    }
}
