package agh;

import agh.simple.Boundary;

import java.util.*;

public class Simulation extends Observable implements Runnable {
    private WorldMap worldMap;
    private final Observer statisticer = new Statisticer();

    Simulation(int geneVariant, int mapVariant, int mapHeight, int mapWidth, int startPlants, int PlantsCount,
               int startAnimals, int startEnergy, int energyRequirements, int energyReproduce, int maxMutation,
               int minMutation, int geneSize){

        Boundary boundary = new Boundary(new Vector2d(0,0),new Vector2d(mapWidth, mapHeight));

        AnimalGeneration animalGeneration = new AnimalGeneration(boundary, startAnimals, startEnergy, geneSize,
                                                                geneVariant);
        PlantGeneration plantGeneration = new PlantGeneration(boundary,startPlants);

        Map<Vector2d, List<Animal>> animals = animalGeneration.getAnimals();

        Map<Vector2d,Plant> plants = plantGeneration.getPlants();

        WorldMap worldMap = new WorldMap(boundary,animals,plants);
        drawMap(worldMap);

    }

    private void drawMap(WorldMap worldMap ){
        Map<Vector2d, List<Animal>> animals = worldMap.getAnimals();
        Map<Vector2d,Plant> plants = worldMap.getPlants();
        System.out.println("ANIMALS:");
        for(Map.Entry<Vector2d, List<Animal>> entry: animals.entrySet()){
            Vector2d position = entry.getKey();
            List<Animal> animalEntry = entry.getValue();
            System.out.println(position + " " + animalEntry);
        }
        System.out.println("PLANTS:");
        for(Map.Entry<Vector2d, Plant> entry: plants.entrySet()){
            Vector2d position = entry.getKey();
            Plant plant = entry.getValue();
            System.out.println(position + " " + plant);
        }
    }

    @Override
    public void run() {

    }
}
