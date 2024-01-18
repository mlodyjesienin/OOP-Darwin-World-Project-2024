package agh.mapEntities;
import agh.simple.MapDirection;
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

    public int getGeneSize() {return geneSize;}

    public int getCurrGene() {return currGene;}

    public List<MapDirection> getGenes() {return genes;}

    public abstract MapDirection nextGene();

    @Override
    public boolean equals(Object other) {
        if (this == other)
            return true;
        if (!(other instanceof Genes))
            return false;
        return ((Genes) other).getGenes() .equals(this.getGenes());
    }

    @Override
    public String toString() {
        return "Genes: " + genes.toString() + " curr " + Integer.toString(currGene) + " size " +Integer.toString(geneSize);
    }
}
