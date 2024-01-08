package agh;

import java.util.*;

import static java.lang.Math.floor;

public class World {
    public static void main(String[] args) {
        Simulation simulation = new Simulation(0, 0, 5, 5, 5, 2,
                10, 36, 3, 3, 3, 3, 4, 5, 15);

        simulation.run();
    }
}
