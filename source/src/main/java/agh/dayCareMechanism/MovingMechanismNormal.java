package agh.dayCareMechanism;
import agh.Genes;
import agh.Animal;
import agh.Vector2d;
import agh.WorldMap;
import agh.simple.MapDirection;

import java.util.*;

public class MovingMechanismNormal extends MovingMechanism {

    MovingMechanismNormal(WorldMap worldMap, int energyLoss, DayCare dayCare) {
        super(worldMap, energyLoss, dayCare);
    }
    @Override
    public Vector2d sideWallHandler(Vector2d position,MapDirection moveDirection){
        Vector2d potentialPosition = position.add(moveDirection.toUnitVector());
        int boundaryX = boundary.upperCorner().getX();
        int currX = potentialPosition.getX();
        if(currX == boundaryX){
            return new Vector2d(0, potentialPosition.getY());
        }
        else{
            return new Vector2d(boundaryX, potentialPosition.getY());
        }
    }

    @Override
    void topBottomHandler(Animal animal, MapDirection moveDirection) {
        animal.setDirection(moveDirection.reverseDirection());
    }
}
