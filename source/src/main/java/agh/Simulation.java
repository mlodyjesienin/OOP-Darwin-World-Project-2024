package agh;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Simulation implements Runnable{
    private WorldMap worldMap;

    Simulation(int geneVariant, int mapVariant, int mapHeight, int mapWidth, int startPlants, int PlantsCount,
               int startAnimals, int startEnergy, int energyRequirements, int energyReproduce, int maxMutation,
               int minMutation, int geneSize){
        AnimalGeneration animalGeneration = new AnimalGeneration(geneVariant);
        PlantGeneration plantGeneration = new PlantGeneration();
        Map<Vector2d, List<Animal>> animals = animalGeneration.generate();
        Map<Vector2d,Plant> plants = plantGeneration.generate();
        MapInitialization mapInitialization =  new MapInitialization(mapVariant,animals,plants);

    }
    @Override
    public void run() {

    }
}
