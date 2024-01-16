package agh;

import agh.simple.MapDirection;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static agh.simple.MapDirection.NORTH;
import static java.util.Objects.hash;

public class Animal implements MapElement {
    private final UUID animalID = UUID.randomUUID();
    private Vector2d position;
    private MapDirection direction;
    private final int birthDate;
    private Integer deathDate = null;
    private final Set<Animal> children = new HashSet<>();
    private int energy;
    private final Genes genes;

    public Animal(Vector2d position, int birthDate, int energy, Genes genes, MapDirection direction){
        this.position = position;
        this.energy = energy;
        this.birthDate = birthDate;
        this.genes = genes;
        this.direction = direction;
    }

    public void setDeathDate(Integer deathDate) {
        this.deathDate = deathDate;
    }

    public Vector2d getPosition() { return position; }

    public Genes getGenes() {
        return genes;
    }

    public UUID getAnimalID() { return animalID; }

    public MapDirection getDirection() {
        return direction;
    }

    public int getEnergy() { return energy;}

    public int getBirthDate() { return birthDate;}

    public Integer getDeathDate() { return deathDate; }

    public Set<Animal> getChildren() {return children;}

    public boolean isDead() {return !(deathDate == null); }

    public void setDirection(MapDirection direction) {this.direction = direction;}

    public void setEnergy(int energy) {this.energy = energy;}

    public void setPosition(Vector2d position) {this.position = position;}
    public void addChild(Animal animal){children.add(animal);}

    @Override
    public boolean equals(Object other) {
        if (this == other)
            return true;
        if (!(other instanceof Animal))
            return false;
        return ((Animal) other).getAnimalID() == animalID;
    }
    public int hashCode() {
        return hash(animalID);
    }

    @Override
    public String toString() {
        return animalID + position.toString() + " " + direction.toString()+ " "+genes.toString()
                + " Energy " + Integer.toString(energy) + " " + Integer.toString(birthDate);
    }
}
