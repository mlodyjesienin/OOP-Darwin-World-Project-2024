package agh.dayCareMechanism;

import agh.*;
import agh.WorldMap;
import agh.simple.Boundary;
import agh.simple.MapDirection;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlantConsumptionMechanism {
    private final WorldMap worldMap;
    private Map<Vector2d, List<Animal>> animals;
    private Map<Vector2d, Plant> plants;
    private final int energyGain;
    private final PlantGrowthMechanism plantGrowthMechanism;
    PlantConsumptionMechanism(WorldMap worldMap, int energyGain, PlantGrowthMechanism plantGrowthMechanism){
        this.worldMap = worldMap;
        this.energyGain = energyGain;
        this.plantGrowthMechanism = plantGrowthMechanism;
    }

    public void work(){
        animals = worldMap.getAnimals();
        plants = worldMap.getPlants();
        Animal animal;
        Map<Vector2d,Plant> newPlants = new HashMap<>();
        for(Plant plant: plants.values()){
            Vector2d position = plant.getPosition();
            if(animals.containsKey(position)){
                List<Animal> animalList = animals.get(position);
                if(animalList.size() > 1){
                    animal = resolveConflict(animalList);
                }
                else{
                    animal = animalList.get(0);
                }
                consume(animal,plant);
            }
            else{
                newPlants.put(plant.getPosition(),plant);
            }
        }
        plants = newPlants;
        worldMap.setPlants(plants);

    }

    void consume(Animal animal, Plant plant){
        animal.setEnergy(animal.getEnergy() + energyGain);
        plantGrowthMechanism.addAvailablePosition(plant.getPosition());
    }
    private Animal resolveConflict(List<Animal> animalList) {
        Animal bestAnimal = animalList.get(0);
        for (Animal animal : animalList) {
            bestAnimal = betterAnimal(animal,bestAnimal);
        }
        return bestAnimal;
    }

    private Animal betterAnimal(Animal animal1,Animal animal2){
        if(animal1.getEnergy() > animal2.getEnergy()){
            return animal1;
        }
        if(animal1.getEnergy() < animal2.getEnergy()){
            return animal2;
        }
        if(animal1.getBirthDate() < animal2.getBirthDate()){
            return animal1;
        }
        if(animal2.getBirthDate() < animal1.getBirthDate()){
            return animal2;
        }
        if(animal1.getChildren().size() > animal2.getChildren().size()){
            return animal1;
        }
        if(animal2.getChildren().size() > animal1.getChildren().size()){
            return animal2;
        }
        return animal1;

    }
}
