package agh.dayCareMechanism;

import agh.WorldMap;

public abstract class MovingMechanism {
    protected final WorldMap worldMap;
    protected final int energyLoss;
    protected final DayCare dayCare;
    MovingMechanism(WorldMap worldMap, int energyLoss, DayCare dayCare){
        this.worldMap = worldMap;
        this.energyLoss = energyLoss;
        this.dayCare = dayCare;
    }
    abstract void work();
}
