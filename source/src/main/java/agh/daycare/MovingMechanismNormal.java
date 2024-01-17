package agh.daycare;
import agh.Animal;
import agh.Vector2d;
import agh.WorldMap;

public class MovingMechanismNormal extends MovingMechanism {

    MovingMechanismNormal(WorldMap worldMap, int energyLoss, DayCare dayCare) {
        super(worldMap, energyLoss, dayCare);
    }
    @Override
    public void sideWallHandler(Animal animal){
        Vector2d position = animal.getPosition();
        Vector2d potentialPosition = position.add(animal.getDirection().toUnitVector());
        Vector2d newPosition;
        int boundaryX = boundary.upperCorner().getX();
        int currX = potentialPosition.getX();
        if(currX == boundaryX){
            newPosition = new Vector2d(0, potentialPosition.getY());
        }
        else{
            newPosition  = new Vector2d(boundaryX, potentialPosition.getY());
        }
        animal.setPosition(newPosition);
    }

    @Override
    void topBottomHandler(Animal animal) {
        animal.setDirection(animal.getDirection().reverseDirection());
    }
}
