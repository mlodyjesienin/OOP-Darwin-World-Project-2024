package agh;

public abstract class DayCare {
    private int dayCount = 0;
    protected WorldMap worldMap;

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
