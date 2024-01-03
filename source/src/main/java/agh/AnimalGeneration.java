package agh;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class AnimalGeneration {

    public AnimalGeneration(int geneVariant){

    }
    public Map<Vector2d, List<Animal>> generate(){
        Vector2d position1 = new Vector2d(1,1);
        Vector2d position2 = new Vector2d(2,2);
        Animal animal1 = new Animal(position1);
        Animal animal2 = new Animal(position2);
        List<Animal> animalsOnPosition1 = new LinkedList<>(List.of(animal1));
        List<Animal> animalsOnPosition2 = new LinkedList<>(List.of(animal2));
        Map<Vector2d,List<Animal>> animals = new HashMap<>();
        animals.put(position1,animalsOnPosition1);
        animals.put(position2,animalsOnPosition2);
        return animals;


    }
}
