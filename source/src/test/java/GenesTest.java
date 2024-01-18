import agh.mapEntities.Genes;
import agh.mapEntities.GenesNormal;
import agh.mapEntities.GenesSpecial;
import agh.simple.MapDirection;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static agh.simple.MapDirection.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GenesTest {
    @Test
    public void normalGenesTest(){
        List<MapDirection> genomeList = new ArrayList<>(List.of(NORTH,NORTHEAST,SOUTH,WEST));
        Genes genes = new GenesNormal(genomeList,0,4);
        assertEquals(NORTH,genes.nextGene());
        assertEquals(NORTHEAST,genes.nextGene());
        assertEquals(SOUTH,genes.nextGene());
        assertEquals(WEST,genes.nextGene());
        assertEquals(NORTH,genes.nextGene());
        assertEquals(NORTHEAST,genes.nextGene());
    }
    @Test
    public void specialGenesTest(){
        List<MapDirection> genomeList = new ArrayList<>(List.of(SOUTH,SOUTHWEST,NORTH));
        Genes genes = new GenesSpecial(genomeList,1,3);
        assertEquals(SOUTHWEST,genes.nextGene());
        assertEquals(NORTH,genes.nextGene());
        assertEquals(NORTH,genes.nextGene());
        assertEquals(SOUTHWEST,genes.nextGene());
        assertEquals(SOUTH,genes.nextGene());
        assertEquals(SOUTH,genes.nextGene());
        assertEquals(SOUTHWEST,genes.nextGene());
    }
}
