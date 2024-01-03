package agh;

public class World {
    public static void main(String[] args){
        System.out.println("Hello World!");
        Animal animal1 = new Animal(new Vector2d(1,1));
        System.out.println(animal1.getGenes());

    }
}
