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
import static org.junit.jupiter.api.Assertions.*;

public class AnimalReproductionTest {
    @Test
    public void reproductionTest(){
        //poz Animal1 i Animal2,
        Vector2d position12 = new Vector2d(1,1);
        //poz Animal3,Animal4, Animal5  dad = Animal3 i mom = Animal4, bo Animal5 słabszy.
        Vector2d position345 = new Vector2d(3,4);
        //poz Animal6 i Animal7 ale nie będą spełniać warunków.
        Vector2d position67 = new Vector2d(0,5);

        List<MapDirection> genomeList1 = new ArrayList<>(List.of(SOUTH,SOUTH,SOUTH,SOUTH));
        List<MapDirection> genomeList2 = new ArrayList<>(List.of(EAST,EAST,EAST,EAST));
        List<MapDirection> genomeList3 = new ArrayList<>(List.of(NORTHEAST,NORTHEAST,NORTHEAST,NORTHEAST));
        List<MapDirection> genomeList4 = new ArrayList<>(List.of(WEST,WEST,WEST,WEST));
        List<MapDirection> genomeList5 = new ArrayList<>(List.of(NORTH,NORTH,NORTH,NORTH));
        List<MapDirection> genomeList6 = new ArrayList<>(List.of(SOUTHWEST,SOUTHWEST,SOUTHWEST,SOUTHWEST));


        Genes genes1 = new GenesNormal(genomeList1,0,4);
        Genes genes2 = new GenesNormal(genomeList2,0,4);
        Genes genes3 = new GenesNormal(genomeList3,0,4);
        Genes genes4 = new GenesNormal(genomeList4,0,4);
        Genes genes5 = new GenesNormal(genomeList5,0,4);
        Genes genes6 = new GenesNormal(genomeList6,0,4);
        Genes genes7 = new GenesNormal(genomeList6,0,4);

        Animal animal1 = new Animal(position12,0,50,genes1,NORTH);
        Animal animal2 = new Animal(position12,1,50,genes2,NORTH);
        Animal animal3 = new Animal(position345,0,100,genes3,NORTH);
        Animal animal4 = new Animal(position345,0,40,genes4,NORTH);
        Animal animal5 = new Animal(position345,0,20,genes5,NORTH);
        Animal animal6 = new Animal(position345,0,2,genes6,NORTH);
        Animal animal7 = new Animal(position345,0,2,genes7,NORTH);



        List<Animal> animalList12 = new LinkedList<>(List.of(animal1,animal2));
        List<Animal> animalList345 = new LinkedList<>(List.of(animal3,animal4,animal5));
        List<Animal> animalList67 = new LinkedList<>(List.of(animal6,animal7));

        //inicjalizacja mapy
        Map<Vector2d,List<Animal>> animals = new HashMap<>();
        Boundary boundary = new Boundary(new Vector2d(0,0),new Vector2d(5,5));
        animals.put(position12,animalList12);
        animals.put(position345,animalList345);
        animals.put(position67,animalList67);
        Map<Vector2d, Plant> plants = new HashMap<>();
        WorldMap worldMap = new WorldMap(boundary,animals,plants,3,2);
        DayCare dayCare = new DayCare(MapVariant.EARTH, GeneVariant.NORMAL,worldMap,0,10,10,
                0,0,0,0);

        //WHEN
        dayCare.getReproductionMechanism().work();

        //THEN
        //sprawdzenie liczby zwierząt
        animals = worldMap.getAnimals();
        assertEquals(3,animals.get(position12).size());
        assertEquals(4,animals.get(position345).size());
        assertEquals(2,animals.get(position67).size());

        //sprawdzenie rodziców
        assertEquals(animal1.getChildren(),animal2.getChildren());
        assertEquals(1,animal1.getChildren().size());
        assertEquals(animal3.getChildren(),animal4.getChildren());
        assertEquals(1,animal3.getChildren().size());
        assertEquals(0,animal5.getChildren().size());
        assertEquals(0,animal6.getChildren().size());
        assertEquals(0,animal7.getChildren().size());

        //sprawdzenie energii i genów
        List<Animal> childrenOfAnimal12 = new ArrayList<>(animal1.getChildren());
        Animal child12 = childrenOfAnimal12.get(0);
        assertEquals(20,child12.getEnergy());
        List<MapDirection> child12Genes = child12.getGenes().getGenes();
        List<MapDirection> expectedGenesVER1 = new ArrayList<>(List.of(SOUTH,SOUTH,EAST,EAST));
        List<MapDirection> expectedGenesVER2 = new ArrayList<>(List.of(EAST,EAST,SOUTH,SOUTH));
        assertTrue(child12Genes.equals(expectedGenesVER1) || child12Genes.equals(expectedGenesVER2));

        List<Animal> childrenOfAnimal34 = new ArrayList<>(animal3.getChildren());
        Animal child34 = childrenOfAnimal34.get(0);
        assertEquals(20,child34.getEnergy());
        List<MapDirection> child34Genes = child34.getGenes().getGenes();
        List<MapDirection> expectedGenesVER3 = new ArrayList<>(List.of(NORTHEAST,NORTHEAST,NORTHEAST,WEST));
        List<MapDirection> expectedGenesVER4 = new ArrayList<>(List.of(WEST,NORTHEAST,NORTHEAST,NORTHEAST));
        assertTrue(child34Genes.equals(expectedGenesVER3) || child34Genes.equals(expectedGenesVER4));

    }
}
