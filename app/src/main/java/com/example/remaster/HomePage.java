package com.example.remaster;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class HomePage extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        /**
         * Button and functionality to start app from home screen
         */
        Button getStartedButton = (Button) findViewById(R.id.getStartedButt);
        getStartedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePage.this, MainActivity.class);
                startActivity(intent);
            }
        });

        /**
         * Button and functionality to view instructions page
         */
        Button instrButton = (Button) findViewById(R.id.button2);
        instrButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePage.this, InstActivity.class);
                startActivity(intent);
            }
        });
    }
}
