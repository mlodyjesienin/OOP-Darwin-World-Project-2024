package agh.mapEntities;
import agh.simple.MapDirection;
import java.util.List;

public class GenesNormal extends Genes {

    public GenesNormal(List<MapDirection> genes, int currGene, int geneSize) {
        super(genes, currGene, geneSize);
    }

    @Override
    public MapDirection nextGene() {
        if(currGene == geneSize -1){
            currGene = 0;
            return genes.get(geneSize-1);
        }
        currGene ++;
        return genes.get(currGene-1);
    }
}
