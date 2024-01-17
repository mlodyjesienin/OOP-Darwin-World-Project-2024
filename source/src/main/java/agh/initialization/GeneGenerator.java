package agh.initialization;
import agh.mapEntities.Genes;
import agh.mapEntities.GenesNormal;
import agh.mapEntities.GenesSpecial;
import agh.daycare.GeneVariant;
import agh.simple.MapDirection;
import java.util.*;

public class GeneGenerator{
    private final Random random = new Random();
    private final int geneSize;
    private final GeneVariant geneVariant;
    GeneGenerator(GeneVariant geneVariant,int geneSize){
       this.geneSize = geneSize;
       this.geneVariant = geneVariant;
    }

     Genes generate(){
        List<MapDirection> allDirections = new ArrayList<>(List.of(MapDirection.NORTH,MapDirection.NORTHEAST,MapDirection.EAST,
        MapDirection.SOUTHEAST,MapDirection.SOUTH,MapDirection.SOUTHWEST,MapDirection.WEST,MapDirection.NORTHWEST));
        List<MapDirection> genes= new ArrayList<>();
        for(int j = 0;j < geneSize; j++){
            int randomInt = random.nextInt(8);
            genes.add(allDirections.get(randomInt));
        }
        int currGene = random.nextInt(geneSize);
        return switch (geneVariant){
            case NORMAL -> new GenesNormal(genes, currGene, geneSize);
            case SPECIAL -> new GenesSpecial(genes, currGene, geneSize);
            default -> throw new IllegalStateException("Unexpected value: " + geneVariant);
        };
    }

}
