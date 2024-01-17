package agh.daycare;

import agh.*;
import agh.simple.MapDirection;

import java.util.List;
import java.util.Random;

public class ReproductionMechanismSpecial extends ReproductionMechanism {


    ReproductionMechanismSpecial(WorldMap worldMap, int energyRequirements, int energyReproduce, int maxMutation, int minMutation, DayCare dayCare) {
        super(worldMap, energyRequirements, energyReproduce, maxMutation, minMutation, dayCare);
    }

    @Override
    Genes createGene(List<MapDirection> childGenes) {
        Random random = new Random();
        int size = childGenes.size();
        int currGene = random.nextInt(size);
        return new GenesSpecial(childGenes,currGene,size);
    }


}
