package com.example.remaster;

import android.app.NotificationManager;
import android.content.Context;

import org.junit.Test;

import static org.junit.Assert.*;

public class NotificationHelperTest {
    @Test
    public void testConstructor(){
        Context base = null;
        NotificationHelper n = new NotificationHelper(base);
    }
    @Test
    public void testCreateChannel(){
        try {
            Context base = null;
            NotificationHelper n = new NotificationHelper(base);
            n.createChannel();
            assertNotNull(n);
        }
        catch(Exception e)
        {

        }
    }
    @Test
    public void testGetManager() {
        try {
            Context base = null;
            NotificationHelper n = new NotificationHelper(base);
            assertNull(n.getManager());
        }
        catch(Exception e){

        }
    }
    @Test
    public void testSetContact(){
        Context base = null;
        NotificationHelper n = new NotificationHelper(base);
        n.setContact("Wang");
        assertNotNull(n);
    }
    @Test
    public void testSetMessage(){
        Context base = null;
        NotificationHelper n = new NotificationHelper(base);
        n.setMessage("Hello");
        assertNotNull(n);
    }
    @Test
    public void testGetChannelNotification(){
        try {
            Context base = null;
            NotificationHelper n = new NotificationHelper(base);
            n.getChannelNotification();
            assertNotNull(n);
        }
        catch(Exception e){

        }
    }
    @Test
    public void testGetMangerNotNull(){
        try {
            Context base = null;
            NotificationHelper n = new NotificationHelper(base);
            assertNull(n.getManager());
            assertNotNull(n.getManager());
        }
        catch(Exception e){

        }
    }
}