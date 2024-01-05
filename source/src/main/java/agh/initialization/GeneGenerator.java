package agh.initialization;

import agh.Genes;
import agh.GenesNormal;
import agh.GenesSpecial;
import agh.simple.MapDirection;

import java.util.*;

import agh.simple.MapDirection;

public class GeneGenerator{
    private final Random random = new Random();
    private final int geneSize;
    private final int geneVariant;
    GeneGenerator(int geneVariant,int geneSize){
       this.geneSize = geneSize;
       this.geneVariant = geneVariant;
    }

     Genes generate(){
        List<MapDirection> allDirections = new ArrayList<>(List.of(MapDirection.NORTH,MapDirection.NORTHEAST,MapDirection.EAST,
        MapDirection.SOUTHEAST,MapDirection.SOUTH,MapDirection.SOUTHWEST,MapDirection.WEST,MapDirection.NORTHWEST));
        List<MapDirection> genes= new ArrayList<>();
        for(int j=0;j<geneSize;j++){
            int randomInt = random.nextInt(8);
            genes.add(allDirections.get(randomInt));
        }
        int currGene = random.nextInt(geneSize);
        return switch (geneVariant){
            case 0 -> new GenesNormal(genes,currGene,geneSize);
            case 1 -> new GenesSpecial(genes,currGene,geneSize);
            default -> throw new IllegalStateException("Unexpected value: " + geneVariant);
        };
    }

}
