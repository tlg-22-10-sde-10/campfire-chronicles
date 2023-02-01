package Monsters;

public class Werewolf extends Monsters {
    private String name = "Werewolf";
    private int health = 2;
    private int attack = 3;
    private int movement = 1;
    private String location;

    public Werewolf() {
    }

    public Werewolf(String name, int health, int attack, int movement, String location) {
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
        return "Werewolf{" +
                "name = '" + name + '\'' +
                ", health = " + health +
                ", attack = " + attack +
                ", movement = " + movement +
                '}';
    }
}
