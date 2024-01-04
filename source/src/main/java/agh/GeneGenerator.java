package agh;

import java.util.*;

public class GeneGenerator{
    private final Random random = new Random();
    private final int geneSize;
    private final int geneVariant;
    GeneGenerator(int geneVariant,int geneSize){
       this.geneSize = geneSize;
       this.geneVariant = geneVariant;
    }

     Genes generate(){
        List<Integer> genes= new ArrayList<>();
        for(int j=0;j<geneSize;j++){
            genes.add(random.nextInt(8));
        }
        int currGene = random.nextInt(geneSize);
        return switch (geneVariant){
            case 0 -> new GenesNormal(genes,currGene,geneSize);
            case 1 -> new GenesSpecial(genes,currGene,geneSize);
            default -> throw new IllegalStateException("Unexpected value: " + geneVariant);
        };
    }
}
