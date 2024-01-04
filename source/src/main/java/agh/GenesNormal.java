package agh;

import java.util.List;

public class GenesNormal extends Genes{
    GenesNormal(List<Integer> genes, int currGene, int geneSize) {
        super(genes, currGene, geneSize);
    }

    @Override
    int nextGene() {
        if(currGene == geneSize -1){
            currGene = 0;
            return genes.get(geneSize-1);
        }
        currGene ++;
        return genes.get(currGene-1);
    }
}
