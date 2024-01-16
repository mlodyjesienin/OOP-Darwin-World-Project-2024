package agh.dayCareMechanism;

import agh.Animal;
import agh.Genes;
import agh.Vector2d;
import agh.WorldMap;
import agh.simple.MapDirection;

import java.util.*;

import static java.lang.Math.floor;

public abstract class ReproductionMechanism {
    private final WorldMap worldMap;
    private final int energyRequirements;
    private final int energyReproduce;
    private final int maxMutation;
    private final int minMutation;
    private final DayCare dayCare;
    private final Comparator<Animal> betterAnimal = new BetterAnimal();
    private final List<MapDirection> allDirections = new ArrayList<>(List.of(MapDirection.NORTH,MapDirection.NORTHEAST,MapDirection.EAST,
            MapDirection.SOUTHEAST,MapDirection.SOUTH,MapDirection.SOUTHWEST,MapDirection.WEST,MapDirection.NORTHWEST));
    ReproductionMechanism(WorldMap worldMap, int energyRequirements, int energyReproduce, int maxMutation,
                          int minMutation, DayCare dayCare){
        this.worldMap = worldMap;
        this.energyRequirements = energyRequirements;
        this.energyReproduce = energyReproduce;
        this.maxMutation = maxMutation;
        this.minMutation = minMutation;
        this.dayCare = dayCare;

    }

    public void work(){
        Map<Vector2d, List<Animal>> newAnimals = new HashMap<>();
        for(Map.Entry<Vector2d,List<Animal>> entry: worldMap.getAnimals().entrySet()){
            Vector2d position = entry.getKey();
            List<Animal> animalList = entry.getValue();
            if(animalList.size() >1){
                List<Animal> possibleParents = checkRequirements(animalList);
                if(possibleParents.size() >1){
                    possibleParents.sort(betterAnimal);
                    for(int i=0;i<possibleParents.size()-1;i+=2) {
                        Animal child = reproduce(possibleParents.get(i), possibleParents.get(i + 1));
                        animalList.add(child);
                    }
                }
            }
            newAnimals.put(position,animalList);
        }
        worldMap.setAnimals(newAnimals);
    }
    private List<Animal> checkRequirements(List<Animal> animalList){
        List<Animal> possibleParents = new LinkedList<>();
        for(Animal animal : animalList){
            if(animal.getEnergy() >= energyRequirements){
                possibleParents.add(animal);
            }
        }
        return possibleParents;
    }

    private Animal reproduce(Animal dad, Animal mom){
        Random random = new Random();
        MapDirection startDirection = allDirections.get(random.nextInt(7));
        Animal child =  new Animal(dad.getPosition(),dayCare.getDayCount(),2*energyReproduce,newGenome(dad,mom), startDirection);
        dad.setEnergy(dad.getEnergy() - energyReproduce);
        mom.setEnergy(mom.getEnergy() - energyReproduce);
        return child;
    }

    Genes newGenome(Animal dad, Animal mom) {
        Genes dadGenes = dad.getGenes();
        Genes momGenes = mom.getGenes();
        float ratio = (float) mom.getEnergy() / (dad.getEnergy() + mom.getEnergy());
        int dadPart =  dadGenes.getGeneSize() - (int) floor(ratio*dadGenes.getGeneSize());
        int momPart = dadGenes.getGeneSize() - dadPart;
        List<MapDirection> left = new ArrayList<>();
        List<MapDirection> right = new ArrayList<>();
        Random random = new Random();
        switch (random.nextInt(2)){
            case 0 -> {
                left = extractPortion(dadGenes.getGenes(),0,dadPart);
                right = extractPortion(momGenes.getGenes(), dadPart,momGenes.getGeneSize());
            }
            case 1 -> {
                left = extractPortion(momGenes.getGenes(),0,momPart);
                right = extractPortion(dadGenes.getGenes(), momPart,momGenes.getGeneSize());
            }
        }
        List<MapDirection> childGenes = new ArrayList<>(left);
        childGenes.addAll(right);
        mutate(childGenes);
        return createGene(childGenes);
    }

    private static <E> List<E> extractPortion(List<E> originalList, int startIndex, int endIndex) {
        List<E> extractedList = new ArrayList<>();
        for (int i = startIndex; i < endIndex; i++) {
            extractedList.add(originalList.get(i));
        }
        return extractedList;
    }

    private void mutate(List<MapDirection> childGenes){
        Random random = new Random();
        List<Integer> mutationIndices = new ArrayList<>();
        int geneSize = childGenes.size();
        for(int i=0; i<geneSize;i++){
            mutationIndices.add(i);
        }
        Collections.shuffle(mutationIndices);
        int mutationNumber = minMutation +  random.nextInt(maxMutation - minMutation +1);
        for(int i=0; i<mutationNumber;i++){
            int genomeMutate = random.nextInt(geneSize);
            childGenes.set(mutationIndices.get(i),allDirections.get(genomeMutate));
        }
    }
    abstract Genes createGene(List<MapDirection> childGenes);
}
