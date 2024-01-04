package agh;

import java.util.List;

public class GenesSpecial extends Genes{

    GenesSpecial(List<Integer> genes, int currGene, int geneSize) {
        super(genes, currGene, geneSize);
    }

    @Override
    int nextGene() {
        return 1;
    }
}
