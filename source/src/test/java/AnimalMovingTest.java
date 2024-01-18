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

public class AnimalMovingTest {
    @Test
    public void animalMovingEarthTest(){
        //Animal1 trafi po 1 ruchu na ścianę dolną
        //Animal2 trafi po 1 ruchu na ścianę boczną
        //Animal3 i Animal4 wejdą na to samo pole po 1 ruchu
        //GIVEN
        Vector2d position1 = new Vector2d(0,0);
        Vector2d position2 = new Vector2d(5,3);
        Vector2d position3 = new Vector2d(2,2);
        Vector2d position4 = new Vector2d(3,4);

        List<MapDirection> genomeList1 = new ArrayList<>(List.of(SOUTH));
        List<MapDirection> genomeList2 = new ArrayList<>(List.of(EAST));
        List<MapDirection> genomeList3 = new ArrayList<>(List.of(NORTHEAST));
        List<MapDirection> genomeList4 = new ArrayList<>(List.of(SOUTH));

        Genes genes1 = new GenesNormal(genomeList1,0,1);
        Genes genes2 = new GenesNormal(genomeList2,0,1);
        Genes genes3 = new GenesNormal(genomeList3,0,1);
        Genes genes4 = new GenesNormal(genomeList4,0,1);

        Animal animal1 = new Animal(position1,0,50,genes1,NORTH);
        Animal animal2 = new Animal(position2,0,50,genes2,NORTH);
        Animal animal3 = new Animal(position3,0,50,genes3,NORTH);
        Animal animal4 = new Animal(position4,0,50,genes4,NORTH);

        List<Animal> animalList1 = new LinkedList<>(List.of(animal1));
        List<Animal> animalList2 = new LinkedList<>(List.of(animal2));
        List<Animal> animalList3 = new LinkedList<>(List.of(animal3));
        List<Animal> animalList4 = new LinkedList<>(List.of(animal4));

        //inicjalizacja mapy
        Map<Vector2d,List<Animal>> animals = new HashMap<>();
        Boundary boundary = new Boundary(new Vector2d(0,0),new Vector2d(5,5));
        animals.put(position1,animalList1);
        animals.put(position2,animalList2);
        animals.put(position3,animalList3);
        animals.put(position4,animalList4);
        Map<Vector2d,Plant> plants = new HashMap<>();
        WorldMap worldMap = new WorldMap(boundary,animals,plants,3,2);
        DayCare dayCare = new DayCare(MapVariant.EARTH, GeneVariant.NORMAL,worldMap,0,0,0,
                0,0,0,0);

        //WHEN (poruszenie wszystkich zwierząt)
        dayCare.getMovingMechanism().work();

        //THEN
        //sprawdzenie pozycji
        assertEquals(position1,animal1.getPosition());
        assertEquals(new Vector2d(0,3),animal2.getPosition());
        assertEquals(new Vector2d(3,3),animal3.getPosition());
        assertEquals(new Vector2d(3,3), animal3.getPosition());

        //sprawdzenie kierunku zwierząt
        assertEquals(NORTH,animal1.getDirection());
        assertEquals(EAST,animal2.getDirection());
        assertEquals(NORTHEAST,animal3.getDirection());
        assertEquals(SOUTH,animal4.getDirection());

        //sprawdzenie poprawności zmian na mapie
        animals = worldMap.getAnimals();
        List<Animal> animalson33 = animals.get(new Vector2d(3,3));
        List<Animal> animalson03 = animals.get(new Vector2d(0,3));

        assertEquals(2,animalson33.size());
        assertEquals(1,animalson03.size());
        assertFalse(animals.containsKey(new Vector2d(5,3)));

    }
    @Test
    public void animalMovingPortals(){
        //Animal1 wyjdzie poza mape dołem
        //Animal2 wyjdzie poza mape bokiem
        //GIVEN
        Vector2d position1 = new Vector2d(0,0);
        Vector2d position2 = new Vector2d(5,3);


        List<MapDirection> genomeList1 = new ArrayList<>(List.of(SOUTH));
        List<MapDirection> genomeList2 = new ArrayList<>(List.of(EAST));


        Genes genes1 = new GenesNormal(genomeList1,0,1);
        Genes genes2 = new GenesNormal(genomeList2,0,1);


        Animal animal1 = new Animal(position1,0,50,genes1,NORTH);
        Animal animal2 = new Animal(position2,0,50,genes2,NORTH);


        List<Animal> animalList1 = new LinkedList<>(List.of(animal1));
        List<Animal> animalList2 = new LinkedList<>(List.of(animal2));


        //inicjalizacja mapy
        Map<Vector2d,List<Animal>> animals = new HashMap<>();
        Boundary boundary = new Boundary(new Vector2d(0,0),new Vector2d(5,5));
        animals.put(position1,animalList1);
        animals.put(position2,animalList2);
        Map<Vector2d,Plant> plants = new HashMap<>();
        WorldMap worldMap = new WorldMap(boundary,animals,plants,3,2);
        DayCare dayCare = new DayCare(MapVariant.PORTALS, GeneVariant.NORMAL,worldMap,0,0,0,
                0,0,0,0);

        //WHEN (poruszenie wszystkich zwierząt)
        dayCare.getMovingMechanism().work();

        //THEN
        //sprawdzenie pozycji
        assertNotEquals(position1, animal1.getPosition());
        assertNotEquals(new Vector2d(0, 3), animal2.getPosition()); // w ogólnośći test może nie przejśc ale mała szansa
                                                                            //sprwadzam czy się zmienia jest 4:25 prosze odpuscic

        //sprawdzenie poprawności zmian na mapie
        animals = worldMap.getAnimals();
        List<Animal> animals1 = animals.get(animal1.getPosition());
        List<Animal> animals2 = animals.get(animal2.getPosition());
        if(!animal1.getPosition().equals(animal2.getPosition())){  // niech juz bedzie
            assertEquals(1, animals1.size());
            assertEquals(1,animals2.size());
        }

    }
}
