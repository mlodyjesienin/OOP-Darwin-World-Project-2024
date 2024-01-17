package agh.daycare;
import agh.mapEntities.Animal;
import agh.mapEntities.Genes;
import agh.simple.Vector2d;
import agh.mapEntities.WorldMap;
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
        for(List<Animal> animalList : animalsOnMap.values()){
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
        rotateAnimal(animal, moveDirection);
        if (topBottomTouch(animal)) {
            topBottomHandler(animal);
        }
        else if(sideWallTouch(animal)){
            sideWallHandler(animal);
        }
        else{
            normalHandler(animal);
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

    private void rotateAnimal(Animal animal, MapDirection moveDirection){
        int gene = moveDirection.directionToInt();
        MapDirection newDirection = animal.getDirection();
        for(int i=0;i<gene;i++){
            newDirection = newDirection.next();
        }
        animal.setDirection(newDirection);
    }
    private boolean sideWallTouch(Animal animal){
        Vector2d position = animal.getPosition();
        Vector2d potentialPosition = position.add(animal.getDirection().toUnitVector());
        int currX = potentialPosition.getX();
        int boundaryX =boundary.upperCorner().getX();
        return currX == -1 || currX == boundaryX +1;
    }
    private boolean topBottomTouch(Animal animal){
        Vector2d position = animal.getPosition();
        Vector2d potentialPosition = position.add(animal.getDirection().toUnitVector());
        int currY = potentialPosition.getY();
        int boundaryY = boundary.upperCorner().getY();
        return currY == -1 || currY == boundaryY +1;
    }
    private void normalHandler(Animal animal){
        Vector2d position = animal.getPosition();
        position = position.add(animal.getDirection().toUnitVector());
        animal.setPosition(position);
    }
    abstract void sideWallHandler(Animal animal);

    abstract void topBottomHandler(Animal animal);
    private void kill(Animal animal){
        animal.setDeathDate(dayCare.getDayCount());
        deadAnimals.add(animal);
    }
}
