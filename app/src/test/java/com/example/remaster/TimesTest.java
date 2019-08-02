package com.example.remaster;

import org.junit.Test;

import static org.junit.Assert.*;

public class TimesTest {

    @Test
    public void testDefaultConstructor(){
        Times times = new Times();
        assertNotNull(times);
    }
    @Test
    public void testSpecificConstructor(){
        Times times = new Times(1,2,3);
        assertNotNull(times);
    }
    @Test
    public void testSetTimeID(){
        Times times = new Times();
        times.setTimeId(1);
        assertEquals(1, times.getTimeId());
    }
    @Test
    public void testSetHour(){
        Times times = new Times();
        times.setHour(2);
        assertEquals(2, times.getHour());

    }
    @Test
    public void testSetMinutes(){
        Times times = new Times();
        times.setMinute(3);
        assertEquals(3, times.getMinute());
    }
}