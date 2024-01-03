package agh;

import agh.simple.Boundary;

import java.util.HashMap;
import java.util.UUID;

public class WorldMap {
    Boundary bounds;
    private final UUID mapID = UUID.randomUUID();
    protected final java.util.Map<Vector2d, Animal> animals = new HashMap<>();
    protected final java.util.Map<Vector2d, Plant> plants = new HashMap<>();
    private final DayCare dayCare = DayCare(this);

}
