package agh;

import agh.simple.MapDirection;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static agh.simple.MapDirection.NORTH;

public class Animal implements MapElement {
    private final UUID animalID = UUID.randomUUID();
    private Vector2d position;
    private MapDirection direction = NORTH;
    private final int birthDate;
    private Integer deathDate = null;
    private Set<Animal> kids = new HashSet<>();
    private int energy;
    private final Genes genes;

    public Animal(Vector2d position, int birthDate, int energy, Genes genes){
        this.position = position;
        this.energy = energy;
        this.birthDate = birthDate;
        this.genes = genes;
    }

    public Vector2d getPosition() { return position; }

    public Genes getGenes() {
        return genes;
    }

    public UUID getAnimalID() { return animalID; }

    public MapDirection getDirection() { return direction; }

    public int getEnergy() { return energy;}

    public int getBirthDate() { return birthDate;}

    public Integer getDeathDate() { return deathDate; }

    public boolean isDead() {return !(deathDate == null); }

    @Override
    public boolean equals(Object other) {
        if (this == other)
            return true;
        if (!(other instanceof Animal))
            return false;
        return ((Animal) other).getAnimalID() == this.animalID;
    }

    @Override
    public String toString() {
        return position.toString() + " " + direction.toString()+ " "+genes.toString()
                + " Energy " + Integer.toString(energy) + " " + Integer.toString(birthDate);
    }
}
