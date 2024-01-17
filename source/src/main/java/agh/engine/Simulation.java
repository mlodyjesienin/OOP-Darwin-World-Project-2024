package agh.engine;
import agh.Animal;
import agh.Plant;
import agh.Vector2d;
import agh.WorldMap;
import agh.daycare.DayCare;
import agh.daycare.MapVariant;
import agh.initialization.AnimalGeneration;
import agh.initialization.PlantGeneration;
import agh.simple.Boundary;
import agh.ui.SimulationPresenter;
import java.util.*;

public class Simulation extends Observable implements Runnable {
    private final Observer statisticer = new Statisticer();
    private SimulationPresenter presenter;
    private final DayCare dayCare;
    private final WorldMap worldMap;

    public Simulation(int geneVariant, MapVariant mapVariant, int mapHeight, int mapWidth, int startPlants, int plantsCount,
                      int startAnimals, int startEnergy, int energyRequirements, int energyReproduce, int maxMutation,
                      int minMutation, int geneSize, int energyLoss, int energyGain){

        Boundary boundary = new Boundary(new Vector2d(0,0), new Vector2d(mapWidth - 1, mapHeight - 1));

        AnimalGeneration animalGeneration = new AnimalGeneration(boundary, startAnimals, startEnergy, geneSize,
                                                                geneVariant);
        PlantGeneration plantGeneration = new PlantGeneration(boundary,startPlants);

        Map<Vector2d, List<Animal>> animals = animalGeneration.getAnimals();

        Map<Vector2d, Plant> plants = plantGeneration.getPlants();

        worldMap = new WorldMap(boundary,animals,plants);

        this.dayCare = new DayCare(mapVariant, worldMap, plantsCount, energyRequirements, energyReproduce, maxMutation,minMutation, energyLoss,energyGain);
    }

    public void registerPresenter(SimulationPresenter presenter){
        this.presenter = presenter;
        presenter.setWorldMap(worldMap);
    }

    @Override
    public void run() {
        for(int i = 0; i < 100; i++) {
            dayCare.simulateDay();
            presenter.mapChanged(dayCare);

            try {
                Thread.sleep(800);
            }
            catch (InterruptedException exception){
                System.out.println("Thread interrupted!");
            }
        }

    }
}
