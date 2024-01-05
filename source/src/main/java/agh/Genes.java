package agh;

import agh.simple.MapDirection;

import java.util.ArrayList;
import java.util.List;

public abstract class Genes {
    protected final List<MapDirection> genes;
    protected int currGene;
    protected final int geneSize;

    Genes(List<MapDirection> genes, int currGene, int geneSize){
        this.genes = genes;
        this.currGene = currGene;
        this.geneSize = geneSize;
    }

    public abstract MapDirection nextGene();

    @Override
    public String toString() {
        return "Genes: " + genes.toString() + " curr " + Integer.toString(currGene) + " size " +Integer.toString(geneSize);
    }
}
