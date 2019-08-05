package com.example.remaster;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

/**
 * This is the locked screen.
 */
public class LockedScreen extends Activity {
    /**
     * A static variable that counting how many times user click decline button
     */
    private static int count = 0;
    /**
     * The period between each incoming phone call
     */
    private static int TIME_OUT = 30000;

    /**
     * Simulate LockedScreen and perform at most three times incoming call.
     * @param savedInstanceState a bundle object that is passed into the onCreate method for every Andriod activity
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_locked_screen);
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        if (count != 3) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(LockedScreen.this, PhoneCall.class);
                    startActivity(i);
                    finish();
                }
            }, TIME_OUT);
            count++;
        }
        else{
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(LockedScreen.this, MainActivity.class);
                    startActivity(i);
                    finish();
                }
            }, 0);
            count = 0;
        }

    }
}
