package Monsters;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GhostTest {
    Ghost ghost = new Ghost();
    @Test
    void getName() {
        assertEquals("Ghost",ghost.getName());
    }

    @Test
    void getHealth() {
        assertEquals(2,ghost.getHealth());
    }

    @Test
    void getAttack() {
        assertEquals(1,ghost.getAttack());
    }

    @Test
    void getMovement() {
        assertEquals(3,ghost.getMovement());
    }
}