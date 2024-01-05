package agh.dayCareMechanism;

import agh.Animal;
import agh.Genes;
import agh.WorldMap;
import agh.simple.MapDirection;

import java.util.List;

public class ReproductionMechanismSpecial extends ReproductionMechanism {


    ReproductionMechanismSpecial(WorldMap worldMap, int energyRequirements, int energyReproduce, int maxMutation, int minMutation, DayCare dayCare) {
        super(worldMap, energyRequirements, energyReproduce, maxMutation, minMutation, dayCare);
    }

    @Override
    Genes createGene(List<MapDirection> childGenes) {
        return null;
    }


}
