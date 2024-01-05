package agh.dayCareMechanism;

import agh.Animal;
import agh.Vector2d;
import agh.WorldMap;
import agh.simple.MapDirection;

public class MovingMechanismSpecial extends MovingMechanism{


    MovingMechanismSpecial(WorldMap worldMap, int energyLoss, DayCare dayCare) {
        super(worldMap, energyLoss, dayCare);
    }

    @Override
    Vector2d sideWallHandler(Vector2d position, MapDirection moveDirection) {
        return null;
    }

    @Override
    void topBottomHandler(Animal animal, MapDirection moveDirection) {

    }
}
