package agh;

import java.util.List;
import java.util.Map;

public class MapInitialization {
    Map<Vector2d, List<Animal>> animals;
    Map<Vector2d,Plant> plants;
    MapInitialization(int mapVariant, Map<Vector2d, List<Animal>> animals, Map<Vector2d,Plant> plants){
            this.animals = animals;
            this.plants = plants;
    }

}
