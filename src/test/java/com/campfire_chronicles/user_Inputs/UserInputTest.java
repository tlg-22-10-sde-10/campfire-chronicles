package com.campfire_chronicles.user_Inputs;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static com.campfire_chronicles.user_Inputs.UserInput.getAction;
import static org.junit.jupiter.api.Assertions.*;

class UserInputTest {

    @Test
    void getActionHelpTest() throws Exception {
        InputStream simUserIn = System.in;
        ByteArrayInputStream command = new ByteArrayInputStream("help".getBytes());
        System.setIn(command);
        String action = getAction();
        System.setIn(simUserIn);
        assertEquals("help",action);
    }
    @Test
    void getActionQuitTest() throws Exception {
        InputStream simUserIn = System.in;
        ByteArrayInputStream command = new ByteArrayInputStream("quit".getBytes());
        System.setIn(command);
        String action = getAction();
        System.setIn(simUserIn);
        assertEquals("quit",action);
    }
    @Test
    void getActionNothingTest() throws Exception {
        InputStream simUserIn = System.in;
        ByteArrayInputStream command = new ByteArrayInputStream(" ".getBytes());
        System.setIn(command);
        String action = getAction();
        System.setIn(simUserIn);
        assertEquals("you do nothing",action);
    }
//    @Test
//    void getActionStatusTest() throws Exception {
//        InputStream simUserIn = System.in;
//        ByteArrayInputStream command = new ByteArrayInputStream("STaTus".getBytes());
//        System.setIn(command);
//        String action = getAction();
//        System.setIn(simUserIn);
//        assertEquals("status",action);
//    }
    @Test
    void getActionMapTest() throws Exception {
        InputStream simUserIn = System.in;
        ByteArrayInputStream command = new ByteArrayInputStream("Map".getBytes());
        System.setIn(command);
        String action = getAction();
        System.setIn(simUserIn);
        assertEquals("map",action);
    }
    @Test
    void getActionUseTest() throws Exception {
        InputStream simUserIn = System.in;
        ByteArrayInputStream command = new ByteArrayInputStream("Map".getBytes());
        System.setIn(command);
        String action = getAction();
        System.setIn(simUserIn);
        assertEquals("map",action);
    }
}