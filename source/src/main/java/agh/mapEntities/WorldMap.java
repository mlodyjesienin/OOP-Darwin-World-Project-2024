package agh.mapEntities;

import agh.mapEntities.Animal;
import agh.mapEntities.Plant;
import agh.simple.Boundary;
import agh.simple.Vector2d;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class WorldMap {
    private final Boundary boundary;
    private final UUID mapID = UUID.randomUUID();
    private Map<Vector2d, List<Animal>> animals;
    private final int topJungleRow;
    private final int bottomJungleRow;
    private Map<Vector2d, Plant> plants;

    public WorldMap(Boundary boundary, Map<Vector2d, List<Animal>> animals, Map<Vector2d, Plant> plants, int topJungleRow,
                    int bottomJungleRow){
        this.boundary = boundary;
        this.animals = animals;
        this.plants = plants;
        this.topJungleRow = topJungleRow;
        this.bottomJungleRow = bottomJungleRow;
    }

    public Map<Vector2d, List<Animal>> getAnimals() {
        return animals;
    }
    public Map<Vector2d, Plant> getPlants() {
        return plants;
    }
    public Boundary getBoundary(){
        return boundary;
    }
    public UUID getMapID() {
        return mapID;
    }
    public void setAnimals(Map<Vector2d, List<Animal>> animals){
        this.animals = animals;
    }
    public void setPlants(Map<Vector2d,Plant> plants){
        this.plants = plants;
    }

    public int getBottomJungleRow() { return bottomJungleRow;}

    public int getTopJungleRow() {return topJungleRow;}
}
