package agh;

import agh.simple.Boundary;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class WorldMap {
    Boundary boundary;
    private final UUID mapID = UUID.randomUUID();
    protected java.util.Map<Vector2d, List<Animal>> animals;
    protected java.util.Map<Vector2d, Plant> plants;

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
}
