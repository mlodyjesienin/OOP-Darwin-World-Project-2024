package agh.initialization;

import agh.daycare.GeneVariant;
import agh.mapEntities.Animal;
import agh.mapEntities.Genes;
import agh.simple.Boundary;
import agh.simple.MapDirection;
import agh.simple.Vector2d;

import java.util.*;

public class AnimalGeneration {
    Map<Vector2d, List<Animal>> animals = new HashMap<>();
    public AnimalGeneration(Boundary boundary,int startAnimals, int startEnergy, int geneSize, GeneVariant geneVariant){
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
        Random random = new Random();
        List<MapDirection> allDirections = new ArrayList<>(List.of(MapDirection.NORTH,MapDirection.NORTHEAST,MapDirection.EAST,
                MapDirection.SOUTHEAST,MapDirection.SOUTH,MapDirection.SOUTHWEST,MapDirection.WEST,MapDirection.NORTHWEST));
        for(Vector2d position: randomPositionGenerator){
            Genes genes = geneGenerator.generate();
            MapDirection startDirection = allDirections.get(random.nextInt(allDirections.size()));
            Animal animal = new Animal(position,0,startEnergy,genes, startDirection);
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
