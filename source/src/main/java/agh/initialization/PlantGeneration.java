package agh.initialization;

import agh.mapEntities.Plant;
import agh.simple.Vector2d;
import agh.simple.Boundary;

import java.util.*;

public class PlantGeneration {
    private final Map<Vector2d, Plant> plants = new HashMap<>();
    final int topJungleRow;
    final int bottomJungleRow;
    public PlantGeneration(Boundary boundary, int startPlants){
        int maxX = boundary.upperCorner().getX();
        int maxY = boundary.upperCorner().getY();
        JungleBoundaries jungleBoundaries = new JungleBoundaries(boundary);
        bottomJungleRow = jungleBoundaries.jungleBottomRow;
        topJungleRow = jungleBoundaries.jungleTopRow;
        List<Vector2d> jungleAvailable = new LinkedList<>();
        List<Vector2d> desertAvailable = new LinkedList<>();
        allPositions(maxX,maxY, jungleAvailable, desertAvailable);
        generate(startPlants,jungleAvailable,desertAvailable);
    };

    private void allPositions(int maxX, int maxY, List<Vector2d> jungleAvailable,
                              List<Vector2d> desertAvailable) {
        for (int i = 0; i <= maxX; i++) {
            for (int j = 0; j <= maxY; j++) {
                Vector2d position = new Vector2d(i, j);
                if (bottomJungleRow <= j && j <= topJungleRow) {
                    jungleAvailable.add(position);
                } else {
                    desertAvailable.add(position);
                }
            }
        }
    }
    public void generate(int startPlants, List<Vector2d> jungleAvailable, List<Vector2d> desertAvailable){
        Collections.shuffle(jungleAvailable);
        Collections.shuffle(desertAvailable);
        Random random = new Random();
        Vector2d pos;
        for(int i=0;i<startPlants;i++){
            int randint = random.nextInt(10);
            if(randint <8){
                pos = jungleAvailable.remove(jungleAvailable.size()-1);
            }
            else{
                pos = desertAvailable.remove(desertAvailable.size()-1);

            }
            Plant plant = new Plant(pos);
            plants.put(pos, plant);
        }
    }

    public Map<Vector2d, Plant> getPlants(){ return plants;}

    public int getBottomJungleRow() { return bottomJungleRow;}

    public int getTopJungleRow() { return topJungleRow;}
}
