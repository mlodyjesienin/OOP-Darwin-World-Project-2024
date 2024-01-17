package agh.daycare;

public enum MapVariant {
    EARTH,
    PORTALS;

    @Override
    public String toString(){
        if (this == EARTH){
            return "Earth";
        }
        else {
            return "Portals";
        }
    }
}
