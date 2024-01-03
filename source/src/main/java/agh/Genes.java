package agh;

import java.util.ArrayList;
import java.util.List;

public abstract class Genes {
    protected final List<Integer> genes = new ArrayList<>(List.of(1,2,3,4,5,6,7,8));
    protected int currGene = 0;

    abstract void nextGene();
}
