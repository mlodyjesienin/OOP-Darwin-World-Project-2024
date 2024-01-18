import agh.daycare.GeneVariant;
import agh.initialization.AnimalGeneration;
import agh.initialization.JungleBoundaries;
import agh.initialization.PlantGeneration;
import agh.simple.Boundary;
import agh.simple.Vector2d;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InitializationTest {
    @Test
    public void jungleInitTest(){
        //GIVEN
        Boundary boundary1 = new Boundary(new Vector2d(0,0),new Vector2d(9,9));
        Boundary boundary2 = new Boundary(new Vector2d(0,0),new Vector2d(7,3));
        Boundary boundary3 = new Boundary(new Vector2d(0,0), new Vector2d(1,36));
        //WHEN
        JungleBoundaries jungleBoundaries1 = new JungleBoundaries(boundary1);
        JungleBoundaries jungleBoundaries2 = new JungleBoundaries(boundary2);
        JungleBoundaries jungleBoundaries3 = new JungleBoundaries(boundary3);
        //THEN
        assertEquals(5,jungleBoundaries1.jungleTopRow);
        assertEquals(4,jungleBoundaries1.jungleBottomRow);

        assertEquals(1,jungleBoundaries2.jungleTopRow);
        assertEquals(1,jungleBoundaries2.jungleBottomRow);

        assertEquals(21,jungleBoundaries3.jungleTopRow);
        assertEquals(14,jungleBoundaries3.jungleBottomRow);

    }
    @Test
    public void animalInitTest(){
        //GIVEN
        Boundary boundary = new Boundary(new Vector2d(0,0), new Vector2d(9,9));

        AnimalGeneration animalGeneration = new AnimalGeneration(boundary,39,1,1, GeneVariant.NORMAL);
        // THEN
        assertEquals(39,animalGeneration.getAnimals().size());

    }

    @Test
    public void plantInitTest(){
        Boundary boundary = new Boundary(new Vector2d(0,0), new Vector2d(20,20));

        PlantGeneration plantGeneration = new PlantGeneration(boundary,100);
        // THEN
        assertEquals(100,plantGeneration.getPlants().size());
    }
}
