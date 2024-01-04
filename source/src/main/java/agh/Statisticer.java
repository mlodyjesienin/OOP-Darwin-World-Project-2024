package agh;

import java.util.Observable;
import java.util.Observer;

public class Statisticer implements Observer {
    private int deathCount = 0;


    Statisticer(){
    }

    @Override
    public void update(Observable o, Object arg) {
        deathCount++;
    }
}
