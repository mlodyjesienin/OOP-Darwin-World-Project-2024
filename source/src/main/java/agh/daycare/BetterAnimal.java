package agh.daycare;

import agh.mapEntities.Animal;

import java.util.Comparator;

public class BetterAnimal implements Comparator<Animal> {
    @Override
    public int compare(Animal animal1, Animal animal2) {
        if(animal1.getEnergy() > animal2.getEnergy()){
            return 1;
        }
        if(animal1.getEnergy() < animal2.getEnergy()){
            return -1;
        }
        if(animal1.getBirthDate() < animal2.getBirthDate()){
            return 1;
        }
        if(animal2.getBirthDate() < animal1.getBirthDate()){
            return -1;
        }
        return Integer.compare(animal1.getChildren().size(), animal2.getChildren().size());
    }
}
