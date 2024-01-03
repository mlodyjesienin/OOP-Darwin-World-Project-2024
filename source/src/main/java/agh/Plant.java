package agh;

public class Plant implements MapElement {
    private final Vector2d position;

    Plant(Vector2d position){this.position = position;}

    public Vector2d getPosition() {
        return position;
    }
}
