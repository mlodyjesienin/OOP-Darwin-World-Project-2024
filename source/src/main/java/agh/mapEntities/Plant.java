package agh.mapEntities;

import agh.simple.Vector2d;

public class Plant implements MapElement {
    private final Vector2d position;

    public Plant(Vector2d position){this.position = position;}

    public Vector2d getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return position.toString();
    }
}
