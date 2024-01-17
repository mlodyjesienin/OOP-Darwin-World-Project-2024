package agh.daycare;

import agh.Plant;
import agh.Vector2d;
import agh.WorldMap;
import agh.simple.Boundary;

import java.util.*;

import static java.lang.Math.min;

public class PlantGrowthMechanism {
    private final WorldMap worldMap;
    private final int plantsCount;
    private final int topJungleRow;
    private final int bottomJungleRow;
    private Map<Vector2d, Plant> plants;
    private final List<Vector2d> desertAvailable;
    private final List<Vector2d>  jungleAvailable;
    PlantGrowthMechanism(WorldMap worldMap, int plantsCount){
        Boundary boundary = worldMap.getBoundary();
        this.worldMap = worldMap;
        this.topJungleRow = worldMap.getTopJungleRow();
        this.bottomJungleRow = worldMap.getBottomJungleRow();
        this.plants = worldMap.getPlants();
        this.plantsCount = plantsCount;
        int maxX = boundary.upperCorner().getX();
        int maxY = boundary.upperCorner().getY();
        desertAvailable = new ArrayList<>();
        jungleAvailable = new ArrayList<>();
        for(int i=0;i<=maxX; i++){
            for(int j=0; j<= maxY; j++){
                Vector2d position = new Vector2d(i,j);
                if(!(plants.containsKey(position))){
                    if(bottomJungleRow <= j && j <= topJungleRow){
                        jungleAvailable.add(position);
                    }
                    else{
                        desertAvailable.add(position);
                    }
                }
            }
        }
    }
    public void addAvailablePosition(Vector2d position){
        int y = position.getY();
        if(bottomJungleRow<=y && y<= topJungleRow){
            jungleAvailable.add(position);
        }
        else{
            desertAvailable.add(position);
        }
    }

    void work(){
        plants = worldMap.getPlants();
        Collections.shuffle(jungleAvailable);
        Collections.shuffle(desertAvailable);
        Random random = new Random();
        Vector2d position;
        for(int i =0;i<plantsCount;i++){
            int randint = random.nextInt(10);
            if(randint < 8 && !jungleAvailable.isEmpty()){
                position = jungleAvailable.remove(jungleAvailable.size()-1);
            }
            else if(!desertAvailable.isEmpty()){
                position = desertAvailable.remove(desertAvailable.size()-1);
            }
            else{
                break;
            }
            Plant plant = new Plant(position);
            plants.put(position,plant);
        }
    }
}
