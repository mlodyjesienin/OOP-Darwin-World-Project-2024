package agh.dayCareMechanism;

import agh.Animal;
import agh.Genes;
import agh.Vector2d;
import agh.WorldMap;
import agh.simple.Boundary;
import agh.simple.MapDirection;

import java.util.*;

public abstract class MovingMechanism {
    protected final WorldMap worldMap;
    protected Map<Vector2d, List<Animal>> animals;
    protected final Boundary boundary;
    protected final int energyLoss;
    protected final DayCare dayCare;
    private final Set<Animal> deadAnimals = new HashSet<>();
    MovingMechanism(WorldMap worldMap, int energyLoss, DayCare dayCare){
        this.worldMap = worldMap;
        this.animals = worldMap.getAnimals();
        this.boundary = worldMap.getBoundary();
        this.energyLoss = energyLoss;
        this.dayCare = dayCare;
    }
    void work(){
        Map<Vector2d, List<Animal>> animalsOnMap = worldMap.getAnimals();
        Map<Vector2d, List<Animal>> newAnimals = new HashMap<>();
        for(Map.Entry<Vector2d,List<Animal>> entry : animalsOnMap.entrySet()){
            List<Animal> animalList = entry.getValue();
            for(Animal animal : animalList){
                animal.setEnergy(animal.getEnergy() - energyLoss);
                if(animal.getEnergy() <= 0){
                    kill(animal);
                }
                else {
                    move(animal);
                    addAnimal(animal, newAnimals);
                }
            }
        }
        this.animals = newAnimals;
        worldMap.setAnimals(this.animals);
    }
    private void move(Animal animal){
        Genes genes = animal.getGenes();
        MapDirection moveDirection = genes.nextGene();
        Vector2d position = animal.getPosition();
        if (topBottomTouch(position,moveDirection)) {
            topBottomHandler(animal,moveDirection);
        }
        else if(sideWallTouch(animal.getPosition(),moveDirection)){
            position = sideWallHandler(position,moveDirection);
            animal.setPosition(position);
            animal.setDirection(moveDirection);
        }
        else{
            position = position.add(moveDirection.toUnitVector());
            animal.setPosition(position);
            animal.setDirection(moveDirection);
        }
    }
    private void addAnimal(Animal animal,Map<Vector2d, List<Animal>> newAnimals){
        List<Animal> animalsOnPosition;
        Vector2d position = animal.getPosition();
        if(newAnimals.containsKey(position)){
            animalsOnPosition = newAnimals.get(position);
        }
        else {
            animalsOnPosition = new LinkedList<>();

        }
        animalsOnPosition.add(animal);
        newAnimals.put(position,animalsOnPosition);
    }
    private boolean sideWallTouch(Vector2d position, MapDirection moveDirection){
        Vector2d potentialPosition = position.add(moveDirection.toUnitVector());
        int currX = potentialPosition.getX();
        int boundaryX =boundary.upperCorner().getX();
        return currX == -1 || currX == boundaryX +1;
    }
    private boolean topBottomTouch(Vector2d position, MapDirection moveDirection){
        Vector2d potentialPosition = position.add(moveDirection.toUnitVector());
        int currY = potentialPosition.getY();
        int boundaryY = boundary.upperCorner().getY();
        return currY == -1 || currY == boundaryY +1;
    }
    abstract Vector2d sideWallHandler(Vector2d position,MapDirection moveDirection);


    abstract void topBottomHandler(Animal animal, MapDirection moveDirection);
    private void kill(Animal animal){
        animal.setDeathDate(dayCare.getDayCount());
        deadAnimals.add(animal);
    }


}
