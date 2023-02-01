package Monsters;

public class Vampire extends Monsters {
    private String name = "Vampire";
    private int health = 2;
    private int attack = 2;
    private int movement = 2;
    private String location = "Lake";




    public Vampire() {
    }

    public Vampire(String name, int health, int attack, int movement, String location) {
        super(name, health, attack, movement, location);
    }

    @Override
    public int attack() {
        int damage = getAttack();
        return damage;
    }

    @Override
    public void move() {

    }

    @Override
    public void search() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getMovement() {
        return movement;
    }

    public void setMovement(int movement) {
        this.movement = movement;
    }

    @Override
    public String toString() {
        return "Vampire{" +
                "name = '" + name + '\'' +
                ", health = " + health +
                ", attack = " + attack +
                ", movement = " + movement +
                '}';
    }
}
