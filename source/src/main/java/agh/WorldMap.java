package agh;

import agh.simple.Boundary;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class WorldMap {
    private final Boundary boundary;
    private final UUID mapID = UUID.randomUUID();
    private java.util.Map<Vector2d, List<Animal>> animals;
    private java.util.Map<Vector2d, Plant> plants;

    WorldMap(Boundary boundary, Map<Vector2d, List<Animal>> animals, Map<Vector2d, Plant> plants){
        this.boundary = boundary;
        this.animals = animals;
        this.plants = plants;
    }

    public Map<Vector2d, List<Animal>> getAnimals() {
        return animals;
    }
    public Map<Vector2d, Plant> getPlants() {
        return plants;
    }
    public Boundary getBoundary(){return boundary;}
    public UUID getMapID() {return mapID;}
    public void setAnimals(Map<Vector2d, List<Animal>> animals){this.animals = animals;}
    public void setPlants(Map<Vector2d,Plant> plants){this.plants = plants;}
}
