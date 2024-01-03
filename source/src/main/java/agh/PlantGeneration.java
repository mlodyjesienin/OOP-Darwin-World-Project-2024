package agh;

import java.util.HashMap;
import java.util.Map;

public class PlantGeneration {
    PlantGeneration(){};

    public Map<Vector2d,Plant> generate(){
        Vector2d position1 = new Vector2d(1,2);
        Vector2d position2 = new Vector2d(2,1);
        Plant plant1 = new Plant(position1);
        Plant plant2 = new Plant(position2);
        Map<Vector2d,Plant> plants= new HashMap<>();
        plants.put(position1,plant1);
        plants.put(position2,plant2);
        return plants;
    }
}
