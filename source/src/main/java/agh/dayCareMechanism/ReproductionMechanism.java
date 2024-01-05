package agh.dayCareMechanism;

import agh.WorldMap;

public abstract class ReproductionMechanism {
    private final WorldMap worldMap;
    private final int energyRequirements;
    private final int energyReproduce;
    private final int maxMutation;
    private final int minMutation;
    ReproductionMechanism(WorldMap worldMap, int energyRequirements, int energyReproduce, int maxMutation,
                          int minMutation){
        this.worldMap = worldMap;
        this.energyRequirements = energyRequirements;
        this.energyReproduce = energyReproduce;
        this.maxMutation = maxMutation;
        this.minMutation = minMutation;
    }

    abstract void work();
}
