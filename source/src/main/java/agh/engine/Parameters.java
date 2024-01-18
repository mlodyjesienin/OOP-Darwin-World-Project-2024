package agh.engine;
import agh.daycare.GeneVariant;
import agh.daycare.MapVariant;

public record Parameters(GeneVariant geneVariant, MapVariant mapVariant, int mapHeight, int mapWidth,
                         int startPlants, int plantsCount, int startAnimals, int startEnergy,
                         int energyRequirements, int energyReproduce, int maxMutation, int minMutation,
                         int geneSize, int energyLoss, int energyGain, int timeRefresh) {
    @Override
    public String toString(){
        return mapWidth + " " + mapHeight + " " + startAnimals + " " + startPlants + " " + plantsCount +
                " " + mapVariant.toString() + " " + geneVariant.toString() + " " + startEnergy +
                " " + energyRequirements + " " + energyReproduce + " " + minMutation + " " + maxMutation +
                " " + energyLoss + " " + energyGain + " " + geneSize + " " + timeRefresh;
    }
}
