package Monsters;

public abstract class Monsters {
    /*Fields*/
    private String name;
    private int health;
    private int attack;
    private int movement;
    private String location;

    /*Constructors*/

    public Monsters() {

    }

    public Monsters(String name, int health, int attack, int movement, String location) {
        this.name = name;
        this.health = health;
        this.attack = attack;
        this.movement = movement;
        this.location = location;
    }
    /*Abstract Methods*/

    public abstract int attack();


    public abstract void move();

    public abstract void search();











}
