package agh.mapEntities;

import agh.simple.MapDirection;

import java.util.List;

public class GenesSpecial extends Genes {
    private  int currDirection = 1; //1 = going forward -1 = going backwards

    public GenesSpecial(List<MapDirection> genes, int currGene, int geneSize) {
        super(genes, currGene, geneSize);
    }

    @Override
    public MapDirection nextGene() {
        if (currDirection == 1) {
            if (currGene == geneSize - 1) {
                currDirection = -1;
                return genes.get(geneSize - 1);
            }
            currGene++;
            return genes.get(currGene - 1);

        }
        else{
            if (currGene == 0) {
                currDirection = 1;
                return genes.get(0);
            }
            currGene--;
            return genes.get(currGene + 1);
        }
    }

}
