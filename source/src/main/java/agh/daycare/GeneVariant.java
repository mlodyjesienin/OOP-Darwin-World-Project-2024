package agh.daycare;

public enum GeneVariant {
    NORMAL,
    SPECIAL;

    @Override
    public String toString(){
        if (this == NORMAL){
            return "Normal";
        }
        else {
            return "Special";
        }
    }
}
