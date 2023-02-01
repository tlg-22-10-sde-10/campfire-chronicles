package Monsters;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WerewolfTest {
    Werewolf werewolf = new Werewolf();
    @Test
    void getName() {
        assertEquals("Werewolf", werewolf.getName());
    }

    @Test
    void getHealth() {
        assertEquals(2, werewolf.getHealth());
    }

    @Test
    void getAttack() {
        assertEquals(3, werewolf.getAttack());
    }

    @Test
    void getMovement() {
        assertEquals(1, werewolf.getMovement());
    }
}