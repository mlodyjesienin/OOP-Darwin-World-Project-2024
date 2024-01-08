package agh.initialization;

import agh.Plant;
import agh.Vector2d;
import agh.simple.Boundary;

import java.util.HashMap;
import java.util.Map;

public class PlantGeneration {
    Map<Vector2d, Plant> plants = new HashMap<>();
    public PlantGeneration(Boundary boundary, int startPlants){
        int maxWidth = boundary.upperCorner().getX();
        int maxHeight = boundary.upperCorner().getY();
        RandomPositionGenerator randomPlantsPosition = new RandomPositionGenerator(maxWidth,maxHeight,startPlants);
        generate(randomPlantsPosition);
    };

    public void generate(RandomPositionGenerator randomPlantsPosition){
        for(Vector2d position: randomPlantsPosition){
            Plant singlePlant = new Plant(position);
            plants.put(position,singlePlant);
        }
    }

    public Map<Vector2d, Plant> getPlants() {
        return plants;
    }
}
