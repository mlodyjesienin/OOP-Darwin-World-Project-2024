package agh;

import agh.simple.MapDirection;

import java.util.List;

public class GenesSpecial extends Genes {


    public GenesSpecial(List<MapDirection> genes, int currGene, int geneSize) {
        super(genes, currGene, geneSize);
    }

    @Override
    public MapDirection nextGene() {
        return MapDirection.NORTH;
    }

}
