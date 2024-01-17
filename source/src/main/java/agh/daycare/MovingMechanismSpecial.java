package agh.daycare;

import agh.Animal;
import agh.Vector2d;
import agh.WorldMap;

import java.util.Random;

public class MovingMechanismSpecial extends MovingMechanism{
    private final int  energyReproduce;

    MovingMechanismSpecial(WorldMap worldMap, int energyLoss, DayCare dayCare, int energyReproduce) {
        super(worldMap, energyLoss, dayCare);
        this.energyReproduce = energyReproduce;
    }

    @Override
    void sideWallHandler(Animal animal) {
        Random random = new Random();
        Vector2d randomPosition = new Vector2d(random.nextInt(boundary.upperCorner().getX()), random.nextInt(boundary.upperCorner().getY()));
        animal.setPosition(randomPosition);
    }

    @Override
    void topBottomHandler(Animal animal) {
        sideWallHandler(animal); //it doesnt matter what type of wall is it lol
    }
}
