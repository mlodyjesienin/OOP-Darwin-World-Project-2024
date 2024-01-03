package agh;

import agh.simple.MapDirection;

import java.util.HashSet;
import java.util.Set;

import static agh.simple.MapDirection.NORTH;

public class Animal implements MapElement {
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

    public Vector2d getPosition() {
        return position;
    }

    public Genes getGenes() {
        return genes;
    }
}
