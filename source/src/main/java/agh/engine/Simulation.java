package agh.engine;
import agh.mapEntities.Animal;
import agh.mapEntities.Plant;
import agh.simple.Vector2d;
import agh.mapEntities.WorldMap;
import agh.daycare.DayCare;
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
    private final int timeRefresh;

    public Simulation(Parameters parameters){

        Boundary boundary = new Boundary(new Vector2d(0,0), new Vector2d(parameters.mapWidth() - 1,
                parameters.mapHeight() - 1));

        AnimalGeneration animalGeneration = new AnimalGeneration(boundary, parameters.startAnimals(),
                parameters.startEnergy(), parameters.geneSize(), parameters.geneVariant());

        PlantGeneration plantGeneration = new PlantGeneration(boundary, parameters.startPlants());

        Map<Vector2d, List<Animal>> animals = animalGeneration.getAnimals();

        Map<Vector2d, Plant> plants = plantGeneration.getPlants();

        int topJungleRow = plantGeneration.getTopJungleRow();
        int bottomJungleRow = plantGeneration.getBottomJungleRow();

        worldMap = new WorldMap(boundary,animals,plants,topJungleRow,bottomJungleRow);

        this.dayCare = new DayCare(parameters.mapVariant(), worldMap, parameters.plantsCount(),
                parameters.energyRequirements(), parameters.energyReproduce(), parameters.maxMutation(),
                parameters.minMutation(), parameters.energyLoss(), parameters.energyGain());

        timeRefresh = parameters.timeRefresh();
    }

    public void registerPresenter(SimulationPresenter presenter){
        this.presenter = presenter;
        presenter.setWorldMap(worldMap);
    }

    @Override
    public void run() {
        while (!presenter.simulationClosed) {
            if (presenter.pauseSimulation){
                continue;
            }

            dayCare.simulateDay();
            presenter.mapChanged(dayCare);

            try {
                Thread.sleep(timeRefresh);
            }
            catch (InterruptedException e){
                System.out.println("Thread interrupted!");
            }
        }
    }
}
