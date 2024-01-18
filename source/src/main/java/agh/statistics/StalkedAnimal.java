package agh.statistics;

import agh.mapEntities.Animal;
import agh.simple.MapDirection;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StalkedAnimal {
    private final Animal stalkedAnimal;
    public int energy;
    public int plantsEaten;
    public final List<Integer> genes;
    public int currGene;
    public int age;
    public int childCount;
    public int descendants;
    public Integer deathDate = null;
    StalkedAnimal(Animal animal, int currDay){
        stalkedAnimal = animal;
        energy = stalkedAnimal.getEnergy();
        age = currDay - stalkedAnimal.getBirthDate();
        childCount = stalkedAnimal.getChildren().size();
        plantsEaten = stalkedAnimal.getPlantsEaten();
        genes = genesAsIntegers(animal.getGenes().getGenes());
        MapDirection currGeneDirection = stalkedAnimal.getGenes().getGenes().get(stalkedAnimal.getGenes().getCurrGene());
        currGene = currGeneDirection.directionToInt();
        descendants = findAllDescendants();
    }

    public Animal getStalkedAnimal(){
        return stalkedAnimal;
    }

    private List<Integer> genesAsIntegers(List<MapDirection> genes){
        List<Integer> result = new ArrayList<>();
        for(MapDirection direction : genes){
            result.add(direction.directionToInt());
        }
        return result;
    }

    public void updateInfo(){
        if(stalkedAnimal.getDeathDate() == null){
            energy = stalkedAnimal.getEnergy();
            childCount = stalkedAnimal.getChildren().size();
            age++;
            deathDate = stalkedAnimal.getDeathDate();
            plantsEaten = stalkedAnimal.getPlantsEaten();
            MapDirection currGeneDirection = stalkedAnimal.getGenes().getGenes().get(stalkedAnimal.getGenes().getCurrGene());
            currGene = currGeneDirection.directionToInt();
            descendants = findAllDescendants();
        }
        else{
            deathDate = stalkedAnimal.getDeathDate();
        }
    }
    private int findAllDescendants(){ //actually DFS ALGORITHM.
        Set<Animal> allDescendants = new HashSet<>();
        Set<Animal> children = stalkedAnimal.getChildren();
        for(Animal child: children){
            if(!allDescendants.contains(child)){
                allDescendants.add(child);
                DFSVisit(allDescendants,child);
            }
        }
        return allDescendants.size();
    }
    private void DFSVisit(Set<Animal> allDescendants, Animal animal){
        Set<Animal> children = animal.getChildren();
        for(Animal child: children){
            if(!allDescendants.contains(child)){
                allDescendants.add(child);
                DFSVisit(allDescendants,child);
            }
        }
    }
}
