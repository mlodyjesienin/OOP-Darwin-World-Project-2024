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
                    List<Animal> bestParents = findBestParents(possibleParents);
                    Animal child = reproduce(bestParents);
                    animalList.add(child);
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
    private List<Animal> findBestParents(List<Animal> possibleParents){
        Animal best;
        Animal secondBest;
        if(possibleParents.get(0).equals(betterAnimal(possibleParents.get(0), possibleParents.get(1)))){
            best = possibleParents.get(0);
            secondBest = possibleParents.get(1);
        }
        else{
            best = possibleParents.get(1);
            secondBest = possibleParents.get(0);
        }
        for(Animal possibleParent : possibleParents){
            if(best.equals(betterAnimal(possibleParent, best))){
                secondBest = best;
                best = possibleParent;
            }
            else{
                secondBest = betterAnimal(possibleParent,secondBest);
            }

        }
        return new LinkedList<>(List.of(best,secondBest));
    }

    private Animal betterAnimal(Animal animal1,Animal animal2){
        if(animal1.getEnergy() > animal2.getEnergy()){
            return animal1;
        }
        if(animal1.getEnergy() < animal2.getEnergy()){
            return animal2;
        }
        if(animal1.getBirthDate() < animal2.getBirthDate()){
            return animal1;
        }
        if(animal2.getBirthDate() < animal1.getBirthDate()){
            return animal2;
        }
        if(animal1.getChildren().size() > animal2.getChildren().size()){
            return animal1;
        }
        if(animal2.getChildren().size() > animal1.getChildren().size()){
            return animal2;
        }
        return animal1;
    }
    private Animal reproduce(List<Animal> parents){
        Animal dad = parents.get(0);
        Animal mom = parents.get(1);
        Animal child =  new Animal(dad.getPosition(),dayCare.getDayCount(),2*energyReproduce,newGenome(dad,mom));
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

    }
    abstract Genes createGene(List<MapDirection> childGenes);
}
