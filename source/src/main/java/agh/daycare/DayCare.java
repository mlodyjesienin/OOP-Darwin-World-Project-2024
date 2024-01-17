package agh.daycare;
import agh.mapEntities.WorldMap;

public class DayCare {
    private int dayCount = 0;
    private final MovingMechanism movingMechanism;
    private final PlantConsumptionMechanism plantConsumptionMechanism;
    private final PlantGrowthMechanism plantGrowthMechanism;
    private final ReproductionMechanism reproductionMechanism;
    public final WorldMap worldMap;
    public DayCare(MapVariant mapVariant, WorldMap worldMap, int plantsCount, int energyRequirements, int energyReproduce,
                   int maxMutation, int minMutation, int energyLoss, int energyGain){
        this.worldMap = worldMap;

        switch (mapVariant){
            case EARTH -> {
                movingMechanism = new MovingMechanismNormal(worldMap,energyLoss,this);
                reproductionMechanism = new ReproductionMechanismNormal(worldMap, energyRequirements,
                        energyReproduce, maxMutation, minMutation, this);
            }
            case PORTALS -> {
                movingMechanism = new MovingMechanismSpecial(worldMap, energyLoss, this, energyReproduce);
                reproductionMechanism = new ReproductionMechanismSpecial(worldMap, energyRequirements,
                        energyReproduce, maxMutation, minMutation, this);
            }
            default -> throw new IllegalStateException("Unexpected value: " + mapVariant);
        }

        plantGrowthMechanism = new PlantGrowthMechanism(worldMap,plantsCount);
        plantConsumptionMechanism = new PlantConsumptionMechanism(worldMap, energyGain, plantGrowthMechanism);
    }

    public int getDayCount() {
        return dayCount;
    }

    public void simulateDay(){
        dayCount++;
        movingMechanism.work(); //also includes killing mechanism and lowering energy
        plantConsumptionMechanism.work();
        reproductionMechanism.work();
        plantGrowthMechanism.work();
    }
}
