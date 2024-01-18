import agh.daycare.DayCare;
import agh.daycare.GeneVariant;
import agh.daycare.MapVariant;
import agh.mapEntities.*;
import agh.simple.Boundary;
import agh.simple.MapDirection;
import agh.simple.Vector2d;
import org.junit.jupiter.api.Test;

import java.util.*;

import static agh.simple.MapDirection.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class PlantTest {

    @Test
    public void plantConsumptionTest(){
        Vector2d position1 = new Vector2d(0,0);

        List<MapDirection> genomeList1 = new ArrayList<>(List.of(SOUTH));

        Genes genes1 = new GenesNormal(genomeList1,0,1);

        Animal animal1 = new Animal(position1,0,50,genes1,NORTH);

        List<Animal> animalList1 = new LinkedList<>(List.of(animal1));

        Plant plant = new Plant(position1);

        //inicjalizacja mapy
        Map<Vector2d,List<Animal>> animals = new HashMap<>();
        Boundary boundary = new Boundary(new Vector2d(0,0),new Vector2d(5,5));
        animals.put(position1,animalList1);
        Map<Vector2d, Plant> plants = new HashMap<>();
        plants.put(position1, plant);
        WorldMap worldMap = new WorldMap(boundary,animals,plants,3,2);
        DayCare dayCare = new DayCare(MapVariant.EARTH, GeneVariant.NORMAL,worldMap,0,0,0,
                0,0,0,10);

        //WHEN (zjedzenie roślinki)
        dayCare.getPlantConsumptionMechanism().work();

        //THEN
        //sprawdzenie pozycji
        plants = worldMap.getPlants();
        assertFalse(plants.containsKey(position1));
        //sprawdzenie statystyk zwierzęcia
        assertEquals(60,animal1.getEnergy());
        assertEquals(1,animal1.getPlantsEaten());


    }

    @Test
    public void plantGrowthTest(){
        //inicjalizacja mapy
        Map<Vector2d,List<Animal>> animals = new HashMap<>();
        Boundary boundary = new Boundary(new Vector2d(0,0),new Vector2d(5,5));
        Map<Vector2d, Plant> plants = new HashMap<>();
        WorldMap worldMap = new WorldMap(boundary,animals,plants,3,2);
        DayCare dayCare = new DayCare(MapVariant.EARTH, GeneVariant.NORMAL,worldMap,10,0,0,
                0,0,0,0);

        //WHEN i THEN (dodanie nowych roślinek i sprawdzenie czy liczba się zgadza)
        assertEquals(0,worldMap.getPlants().size());

        dayCare.getPlantGrowthMechanism().work(); //dzien 1
        assertEquals(10,worldMap.getPlants().size());

        dayCare.getPlantGrowthMechanism().work(); //dzien 2
        assertEquals(20,worldMap.getPlants().size());

        dayCare.getPlantGrowthMechanism().work(); //dzien 3
        assertEquals(30,worldMap.getPlants().size());

        dayCare.getPlantGrowthMechanism().work(); //dzien 4
        assertEquals(36,worldMap.getPlants().size()); // mapa od 0-5 wiec jest 6x6 czyli max 36

        dayCare.getPlantGrowthMechanism().work(); //dzien 5
        assertEquals(36,worldMap.getPlants().size());
    }
}
