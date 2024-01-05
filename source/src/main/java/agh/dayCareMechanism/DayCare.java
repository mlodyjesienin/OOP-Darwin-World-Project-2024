package agh.dayCareMechanism;

import agh.WorldMap;

public class DayCare {
    private int dayCount = 0;
    private final MovingMechanism movingMechanism;
    private final PlantConsumptionMechanism plantConsumptionMechanism;
    private final PlantGrowthMechanism plantGrowthMechanism;
    private final ReproductionMechanism reproductionMechanism;
    public final WorldMap worldMap;
    public DayCare(int mapVariant, WorldMap worldMap, int plantsCount, int energyRequirements, int energyReproduce,
                   int maxMutation, int minMutation, int energyLoss){
        this.worldMap = worldMap;
        if (mapVariant == 1) {
            movingMechanism = new MovingMechanismSpecial(worldMap,energyLoss,this);
            reproductionMechanism = new ReproductionMechanismSpecial(worldMap, energyRequirements,
                    energyReproduce, maxMutation, minMutation);
        } else {
            movingMechanism = new MovingMechanismNormal(worldMap,energyLoss,this);
            reproductionMechanism = new ReproductionMechanismNormal(worldMap, energyRequirements,
                    energyReproduce, maxMutation, minMutation);
        }

        plantConsumptionMechanism = new PlantConsumptionMechanism(worldMap);
        plantGrowthMechanism = new PlantGrowthMechanism(worldMap,plantsCount);


    }

    public int getDayCount() {return dayCount;}

    public void simulateDay(){
        dayCount++;
        movingMechanism.work(); //also includes killing mechanism and lowering energy
        plantConsumptionMechanism.work();
        reproductionMechanism.work();
        plantGrowthMechanism.work();
    }

}
