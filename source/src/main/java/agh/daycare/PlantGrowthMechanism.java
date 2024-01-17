package agh.daycare;

import agh.Plant;
import agh.Vector2d;
import agh.WorldMap;
import agh.simple.Boundary;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static java.lang.Math.min;

public class PlantGrowthMechanism {
    private final WorldMap worldMap;
    private final int plantsCount;
    private Map<Vector2d, Plant> plants;
    private final List<Vector2d> positionsAvailable;
    PlantGrowthMechanism(WorldMap worldMap, int plantsCount){
        Boundary boundary = worldMap.getBoundary();
        this.worldMap = worldMap;
        this.plants = worldMap.getPlants();
        this.plantsCount = plantsCount;
        int maxX = boundary.upperCorner().getX();
        int maxY = boundary.upperCorner().getY();
        positionsAvailable = new ArrayList<>();
        for(int i=0;i<=maxX; i++){
            for(int j=0; j<= maxY; j++){
                Vector2d position = new Vector2d(i,j);
                if(!(plants.containsKey(position))){
                    positionsAvailable.add(position);
                }
            }
        }
    }
    public void addAvailablePosition(Vector2d position){
        positionsAvailable.add(position);
    }

    void work(){
        plants = worldMap.getPlants();
        int available = min(positionsAvailable.size(),plantsCount);
        Collections.shuffle(positionsAvailable);
        for(int i =0;i<available-1;i++){
            Vector2d position = positionsAvailable.remove(positionsAvailable.size()-1);
            Plant plant = new Plant(position);
            plants.put(position,plant);
        }
    }
}
