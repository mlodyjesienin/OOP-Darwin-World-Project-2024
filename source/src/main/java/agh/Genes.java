package agh;

import java.util.ArrayList;
import java.util.List;

public abstract class Genes {
    protected final List<Integer> genes;
    protected int currGene;
    protected final int geneSize;

    Genes(List<Integer> genes, int currGene, int geneSize){
        this.genes = genes;
        this.currGene = currGene;
        this.geneSize = geneSize;
    }

    abstract int nextGene();

    @Override
    public String toString() {
        return "Genes: " + genes.toString() + " curr " + Integer.toString(currGene) + " size " +Integer.toString(geneSize);
    }
}
