package com.example.remaster;

import org.junit.Test;

import static org.junit.Assert.*;

public class MessagesTest {
    @Test
    public void testDefaultConstructor(){
        Messages m = new Messages();
        assertNotNull(m);
    }
    @Test
    public void testSpecificConstructor(){
        Messages m = new Messages(1, "Wang", "Hello");
        assertNotNull(m);
    }
    @Test
    public void testSetID(){
        Messages m = new Messages(1, "Wang", "Hello");
        m.setID(2);
        assertEquals(2, m.getID());
    }
    @Test
    public void testSetContactName(){
        Messages m = new Messages(1, "Wang", "Hello");
        m.setContactName("Emma");
        assertEquals("Emma", m.getContactName());
    }
    @Test
    public void testSetMessage(){
        Messages m = new Messages(1, "Wang", "Hello");
        m.setMessage("World");
        assertEquals("World", m.getMessage());
    }
}