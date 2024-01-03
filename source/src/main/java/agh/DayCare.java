package agh;

public abstract class DayCare {
    private int dayCount = 0;
    private WorldMap map;
    DayCare(WorldMap map){this.map = map;}


    abstract void move();
    private void consumePlants(){

    }
    private void reproduce(){

    }
    private void plantGrowth(){

    }

    private void simulateDay(){
        dayCount++;
        move();
        consumePlants();
        reproduce();
        plantGrowth();
    }

}
