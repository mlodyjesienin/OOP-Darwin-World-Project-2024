package agh.statistics;

import agh.daycare.BetterAnimal;
import agh.daycare.DayCare;
import agh.mapEntities.Animal;
import agh.simple.MapDirection;
import agh.simple.Vector2d;

import java.util.*;

public class Statisticer {
    public boolean extendedStatisticsSHOW = false;
    public int dayCount;
    public int deathCount = 0;
    public int currAliveCount;
    public int currPlantCount;
    private int sumLifeLength = 0;
    public float averageLifeLength = 0;
    public float averageEnergy = 0;
    public float averageChildren = 0;
    public List<Integer> popularGenome = new ArrayList<>();
    public int availableSpace =0;
    public StalkedAnimal stalkedAnimal = null;
    private final DayCare dayCare;

    public Statisticer(DayCare dayCare){
        dayCount = dayCare.getDayCount();
        currAliveCount = dayCare.worldMap.getAnimals().size();
        currPlantCount = dayCare.worldMap.getPlants().size();
        this.dayCare = dayCare;
    }

    public void showStats(){
        standardStatistics();
        if(extendedStatisticsSHOW){
            extendedStatistics();
        }
        if(stalkedAnimal != null){
            stalkedAnimal.updateInfo();
        }
    }
    public void plantGrowthEvent(){
        currPlantCount++;
    }
    public void plantEatenEvent(){
        currPlantCount--;
    }
    public  void birthEvent(){
        currAliveCount++; // cout << "the miracle of life..."
    }
    public void deathEvent(Animal animal) {
        sumLifeLength+= animal.getDeathDate() - animal.getBirthDate();
        deathCount++;
        currAliveCount--;
    }

    private void standardStatistics(){
        dayCount++;
        averageLifeLength = (float) sumLifeLength/deathCount;
    }
    private void extendedStatistics(){
        int sumEnergy = 0;
        int sumChildren = 0;
        Map<List<MapDirection>, Integer> allGenomes= new HashMap<>();
        Map<Vector2d,List<Animal>> animals = dayCare.worldMap.getAnimals();
        for(List<Animal> animalList: animals.values()){
            for(Animal animal: animalList){
                sumEnergy +=animal.getEnergy();
                sumChildren += animal.getChildren().size();
                addToMap(animal,allGenomes);
            }
        }
        averageEnergy = (float) sumEnergy /currAliveCount;
        averageChildren = (float) sumChildren /currAliveCount;
        popularGenome = mostPopularGenome(allGenomes);
        availableSpace = findAvailablespace();
    }
    private void addToMap(Animal animal, Map<List<MapDirection>, Integer> allGenomes){
        List<MapDirection> genes = animal.getGenes().getGenes();
        if(allGenomes.containsKey(genes)){
            int count = allGenomes.get(genes);
            allGenomes.put(genes,count+1);
        }
        else {
            allGenomes.put(genes,1);
        }
    }

    private List<Integer> mostPopularGenome(Map<List<MapDirection>, Integer> allGenomes){
        int maxValue = 0;
        List<MapDirection> popular = new ArrayList<>();
        for(Map.Entry<List<MapDirection>,Integer> entry: allGenomes.entrySet()){
            Integer count = entry.getValue();
            if(count>=maxValue){
                maxValue = count;
                popular = entry.getKey();
            }
        }
        List<Integer> popularAsInteger = new ArrayList<>();
        for(MapDirection direction: popular){
            popularAsInteger.add(direction.directionToInt());
        }
        return popularAsInteger;
    }

    private int findAvailablespace() {
        List<Vector2d> availableSpace = new ArrayList<>();
        Map<Vector2d,List<Animal>> animals = dayCare.worldMap.getAnimals();
        List<Vector2d> jungleAvailable = dayCare.getPlantGrowthMechanism().getJungleAvailable();
        List<Vector2d> desertAvailable = dayCare.getPlantGrowthMechanism().getDesertAvailable();
        for(Vector2d position:jungleAvailable){
            if(!animals.containsKey(position)){
                availableSpace.add(position);
            }
        }
        for(Vector2d position:desertAvailable){
            if(!animals.containsKey(position)){
                availableSpace.add(position);
            }
        }
        return availableSpace.size();
    }

    public void startStalking(Animal animal){
        stalkedAnimal = new StalkedAnimal(animal,dayCare.getDayCount());
    }
    public void stopStalking(){stalkedAnimal = null;}
    public void startExtendedStatistics(){
        extendedStatisticsSHOW = true;
        extendedStatistics();
    }
    public void stopExtendedStatistics(){extendedStatisticsSHOW = false;}
    public Animal bestAnimal(List<Animal> animalList) {
        BetterAnimal betterAnimal = new BetterAnimal();
        Animal bestAnimal = animalList.get(0);
        for (Animal animal : animalList) {
            bestAnimal = switch (betterAnimal.reversed().compare(animal,bestAnimal)){
                case 1 -> bestAnimal;
                case -1 -> animal;
                default -> bestAnimal;
            };
        }
        return bestAnimal;
    }
}
