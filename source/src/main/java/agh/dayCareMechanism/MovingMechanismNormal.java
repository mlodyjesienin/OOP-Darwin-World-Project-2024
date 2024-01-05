package agh.dayCareMechanism;
import agh.Genes;
import agh.Animal;
import agh.Vector2d;
import agh.WorldMap;
import agh.simple.MapDirection;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class MovingMechanismNormal extends MovingMechanism{

    MovingMechanismNormal(WorldMap worldMap, int energyLoss, DayCare dayCare) {
        super(worldMap, energyLoss, dayCare);
    }

    @Override
    void work(){
        Map<Vector2d, List<Animal>> animals = worldMap.getAnimals();
        for(Map.Entry<Vector2d,List<Animal>> entry : animals.entrySet()){
            List<Animal> animalList = entry.getValue();
            Vector2d position = entry.getKey();
            for(Animal animal : animalList){
                animal.energy = animal.energy - energyLoss;
                if(animal.energy <= 0){
                    kill(animal,animalList);
                }
                else {
                    move(animal);
                    animalList.remove(animal);
                    addAnimal(animal,animals);
                }
            }
        }
    }
    private void move(Animal animal){
        Genes genes = animal.getGenes();
        MapDirection moveDirection = genes.nextGene();
        if(sideWallTouch(animal.position,moveDirection)){
            animal.position = sideWallHandler(animal.position,moveDirection);
            animal.direction = moveDirection;
        }
        else if (topBottomTouch(animal.position,moveDirection)) {
            animal.direction = moveDirection.reverseDirection();
        }
        else{
            animal.position = animal.position.add(animal.direction.toUnitVector());
            animal.direction = moveDirection;
        }
    }
    private void addAnimal(Animal animal,Map<Vector2d, List<Animal>> animals){
        List<Animal> animalsOnPosition;
        Vector2d position = animal.position;
        if(animals.containsKey(position)){
            animalsOnPosition = animals.get(position);
        }
        else {
            animalsOnPosition = new LinkedList<>();

        }
        animalsOnPosition.add(animal);
        animals.put(position,animalsOnPosition);
    }
    private boolean sideWallTouch(Vector2d position, MapDirection moveDirection){
        Vector2d potentialPosition = position.add(moveDirection.toUnitVector());
        int currX = potentialPosition.getX();
        int boundaryX = worldMap.boundary.upperCorner().getX();
        return currX == 0 || currX == boundaryX;
    }
    private boolean topBottomTouch(Vector2d position, MapDirection moveDirection){
        Vector2d potentialPosition = position.add(moveDirection.toUnitVector());
        int currY = potentialPosition.getY();
        int boundaryY = worldMap.boundary.upperCorner().getY();
        return currY == 0 || currY == boundaryY;
    }

    private Vector2d sideWallHandler(Vector2d position,MapDirection moveDirection){
        Vector2d potentialPosition = position.add(moveDirection.toUnitVector());
        int boundaryX = worldMap.boundary.upperCorner().getX();
        int currX = potentialPosition.getX();
        if(currX == boundaryX){
            return new Vector2d(0, potentialPosition.getY());
        }
        else{
            return new Vector2d(boundaryX, potentialPosition.getY());
        }
    }
    private void kill(Animal animal,List<Animal> animalList){
        animal.setDeathDate(dayCare.dayCount);
        animalList.remove(animal);
    }


}
