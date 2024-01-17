package agh.engine;
import agh.daycare.GeneVariant;
import agh.daycare.MapVariant;

public record Parameters(GeneVariant geneVariant, MapVariant mapVariant, int mapHeight, int mapWidth,
                         int startPlants, int plantsCount, int startAnimals, int startEnergy,
                         int energyRequirements, int energyReproduce, int maxMutation, int minMutation,
                         int geneSize, int energyLoss, int energyGain, int timeRefresh) {}
