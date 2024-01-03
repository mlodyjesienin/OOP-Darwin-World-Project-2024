package agh;

import agh.enums.MapDirection;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static agh.enums.MapDirection.NORTH;

public class Animal {
    private Vector2d position;
    private MapDirection direction = NORTH;
    private final int birthDate = 0;
    private Integer deathDate = null;
    private Set<Animal> kids = new HashSet<>();
    private int energy = 100;
    private final Genes genes;

    public Animal(Vector2d position){
        this.position = position;
        this.genes = new NormalGenes();
    }

    public Genes getGenes() {
        return genes;
    }
}
