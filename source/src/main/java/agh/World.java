package agh;

import java.util.*;

public class World {

    public static void main(String[] args){
        Simulation simulation = new Simulation(0,0,10,20,10,10,
                15,36,3,3,3,3,4,5);

        simulation.run();
    }
}
