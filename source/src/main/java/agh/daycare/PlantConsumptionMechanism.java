package agh.daycare;

import agh.*;
import agh.WorldMap;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlantConsumptionMechanism {
    private final WorldMap worldMap;
    private final int energyGain;
    private final PlantGrowthMechanism plantGrowthMechanism;
    private final Comparator<Animal> betterAnimal = new BetterAnimal();
    PlantConsumptionMechanism(WorldMap worldMap, int energyGain, PlantGrowthMechanism plantGrowthMechanism){
        this.worldMap = worldMap;
        this.energyGain = energyGain;
        this.plantGrowthMechanism = plantGrowthMechanism;
    }

    public void work(){
        Map<Vector2d, List<Animal>> animals = worldMap.getAnimals();
        Map<Vector2d, Plant> plants = worldMap.getPlants();
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
            bestAnimal = switch (betterAnimal.compare(animal,bestAnimal)){
                case 1 -> bestAnimal;
                case -1 -> animal;
                default -> bestAnimal;
            };
        }
        return bestAnimal;
    }
}
