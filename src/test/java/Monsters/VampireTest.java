package Monsters;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VampireTest {

    Vampire vampire = new Vampire();

    @Test
    void getName() {
        assertEquals("Vampire", vampire.getName());
    }

    @Test
    void getHealth() {
        assertEquals(2, vampire.getHealth());
    }

    @Test
    void getAttack() {
        assertEquals(2, vampire.getAttack());
    }

    @Test
    void getMovement() {
        assertEquals(2, vampire.getMovement());
    }

    @Test
    void attack() {
        assertEquals(2, vampire.attack());
    }
}