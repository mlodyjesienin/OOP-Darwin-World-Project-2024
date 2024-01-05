package agh;

import agh.dayCareMechanism.DayCare;
import agh.initialization.AnimalGeneration;
import agh.initialization.PlantGeneration;
import agh.simple.Boundary;

import java.util.*;

public class Simulation extends Observable implements Runnable {
    private final Observer statisticer = new Statisticer();
    private final DayCare dayCare;

    Simulation(int geneVariant, int mapVariant, int mapHeight, int mapWidth, int startPlants, int plantsCount,
               int startAnimals, int startEnergy, int energyRequirements, int energyReproduce, int maxMutation,
               int minMutation, int geneSize, int energyLoss){

        Boundary boundary = new Boundary(new Vector2d(0,0),new Vector2d(mapWidth, mapHeight));

        AnimalGeneration animalGeneration = new AnimalGeneration(boundary, startAnimals, startEnergy, geneSize,
                                                                geneVariant);
        PlantGeneration plantGeneration = new PlantGeneration(boundary,startPlants);

        Map<Vector2d, List<Animal>> animals = animalGeneration.getAnimals();

        Map<Vector2d,Plant> plants = plantGeneration.getPlants();

        WorldMap worldMap = new WorldMap(boundary,animals,plants);

        this.dayCare = new DayCare(mapVariant, worldMap,plantsCount,energyRequirements,energyRequirements,
                maxMutation,minMutation, energyLoss);
        drawMap(dayCare);

    }

    private void drawMap(DayCare dayCare){
        WorldMap worldMap = dayCare.worldMap;
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
        dayCare.simulateDay();
        drawMap(dayCare);
        dayCare.simulateDay();
        drawMap(dayCare);
        dayCare.simulateDay();
        drawMap(dayCare);
        dayCare.simulateDay();
        drawMap(dayCare);
        dayCare.simulateDay();
        drawMap(dayCare);
    }
}
