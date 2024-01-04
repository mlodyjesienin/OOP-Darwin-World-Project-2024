package agh;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class RandomPositionGenerator implements Iterable<Vector2d> {
    private final List<Vector2d> randomVectors;
    public RandomPositionGenerator(int maxWidth, int maxHeight, int animalCount){
        ArrayList<Vector2d> allPossiblePositions = generateAllVectors(maxWidth,maxHeight,animalCount);
        Collections.shuffle(allPossiblePositions);
        randomVectors = allPossiblePositions.subList(0, animalCount);
    }

    private ArrayList<Vector2d> generateAllVectors(int maxWidth, int maxHeight, int grassCount){
        ArrayList<Vector2d> allPossiblePositions = new ArrayList<Vector2d>();
        for(int i=0; i<=maxWidth; i++){
            for(int j=0; j<=maxHeight; j++){
                allPossiblePositions.add(new Vector2d(i,j));
            }
        }
        return allPossiblePositions;
    }
    @Override
    public Iterator<Vector2d> iterator() {
        return randomVectors.iterator();
    }
}
