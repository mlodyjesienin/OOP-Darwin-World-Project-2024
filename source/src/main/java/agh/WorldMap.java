package agh;

import agh.simple.Boundary;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class WorldMap {
    Boundary bounds;
    private final UUID mapID = UUID.randomUUID();
    protected java.util.Map<Vector2d, List<Animal>> animals;
    protected java.util.Map<Vector2d, Plant> plants;
    private final DayCare dayCare;

    WorldMap(java.util.Map<Vector2d, List<Animal>> animals,java.util.Map<Vector2d, Plant> plants){
        this.animals = animals;
        this.plants = plants;
        dayCare = new DayCareNormal(this);
    }

}
