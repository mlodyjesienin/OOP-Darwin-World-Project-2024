package agh.ui;

import agh.WorldMap;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class SimulationPresenter {
    private WorldMap map;
    @FXML
    private Label infoLabel;

    public void setWorldMap(WorldMap map){
        this.map = map;
    }

    public void mapChanged(){
        drawMap();
    }

    private void drawMap(){
        infoLabel.setText(map.toString());
    }
}
