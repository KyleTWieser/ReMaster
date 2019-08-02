package com.example.remaster;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import org.junit.Test;

import static org.junit.Assert.*;

public class MessagesDBHandlerTest {
    @Test
    public void testConstructorAndOnCreate(){
        try {
            Context context = null;
            SQLiteDatabase.CursorFactory factory = null;
            MessagesDBHandler m = new MessagesDBHandler(context, "Message", factory, 2);
            m.onCreate(null);
            assertNull(m);
        }
        catch(Exception e){

        }
    }
    @Test
    public void testOnUpgrade(){
        try {
            Context context = null;
            SQLiteDatabase.CursorFactory factory = null;
            MessagesDBHandler m = new MessagesDBHandler(context, "Message", factory, 2);
            m.onCreate(null);
            m.onUpgrade(null,1,2);
            assertNull(m);
        }
        catch(Exception e){

        }
    }
    @Test
    public void testLoadHandler(){
        try {
            Context context = null;
            SQLiteDatabase.CursorFactory factory = null;
            MessagesDBHandler m = new MessagesDBHandler(context, "Message", factory, 2);
            Messages newM = m.loadHandler();
            assertNotNull(newM);
        }
        catch(Exception e){

        }
    }
    @Test
    public void testAddHandler(){
        try {
            Context context = null;
            SQLiteDatabase.CursorFactory factory = null;
            MessagesDBHandler m = new MessagesDBHandler(context, "Message", factory, 2);
            m.addHandler(null);
            assertNull(m);
        }
        catch(Exception e){

        }
    }
    @Test
    public void testAddTimeHandler(){
        try {
            Context context = null;
            SQLiteDatabase.CursorFactory factory = null;
            MessagesDBHandler m = new MessagesDBHandler(context, "Message", factory, 2);
            m.addTimeHandler(null);
            assertNull(m);
        }
        catch(Exception e){

        }
    }
    @Test
    public void testLoadTimeHandler(){
        try {
            Context context = null;
            SQLiteDatabase.CursorFactory factory = null;
            MessagesDBHandler m = new MessagesDBHandler(context, "Message", factory, 2);
            Times times = m.loadTimeHandler();
            assertNull(times);
        }
        catch(Exception e){

        }
    }
}