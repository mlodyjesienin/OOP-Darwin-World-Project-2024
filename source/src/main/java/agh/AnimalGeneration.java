package agh;

import agh.simple.Boundary;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class AnimalGeneration {
    Map<Vector2d, List<Animal>> animals = new HashMap<>();
    public AnimalGeneration(Boundary boundary,int startAnimals, int startEnergy, int geneSize,
                            int geneVariant){
        int maxWidth = boundary.upperCorner().getX();
        int maxHeight = boundary.upperCorner().getY();
        GeneGenerator geneGenerator = new GeneGenerator(geneVariant, geneSize);
        RandomPositionGenerator randomPositionGenerator = new RandomPositionGenerator(maxWidth,maxHeight,startAnimals);
        generate(randomPositionGenerator,geneGenerator, startEnergy);

    }

    public Map<Vector2d, List<Animal>> getAnimals() {
        return animals;
    }

    public void generate(RandomPositionGenerator randomPositionGenerator, GeneGenerator geneGenerator,
                         int startEnergy){
        for(Vector2d position: randomPositionGenerator){
            Genes genes = geneGenerator.generate();
            Animal animal = new Animal(position,0,startEnergy,genes);
            addAnimal(position,animal);
        }


    }
    private void addAnimal(Vector2d position, Animal animal){
        List<Animal> animalsOnPosition;
        if(animals.containsKey(position)){
            animalsOnPosition = animals.get(position);
        }
        else {
            animalsOnPosition = new LinkedList<>();

        }
        animalsOnPosition.add(animal);
        animals.put(position,animalsOnPosition);
    }
}
