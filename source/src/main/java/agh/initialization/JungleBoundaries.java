package agh.initialization;

import agh.simple.Boundary;

public class JungleBoundaries {
    public final int jungleBottomRow;
    public final int jungleTopRow;
    public JungleBoundaries(Boundary boundary){
        int mapHeight = boundary.upperCorner().getY() +1;
        int jungleRows = 0;
        float junglepercent = 0;
        while(junglepercent < 0.1999){
            jungleRows++;
            junglepercent+= (float) 1 /mapHeight;
        }
        jungleBottomRow = (mapHeight - jungleRows) / 2;
        jungleTopRow = jungleBottomRow + jungleRows - 1;
    }

}
