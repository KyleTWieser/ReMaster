package com.example.remaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class PhoneCall extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_call);

        ImageButton decline = (ImageButton) findViewById(R.id.decline);
        decline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PhoneCall.this, LockedScreen.class);
                startActivity(intent);

            }
        });
        ImageButton answer = (ImageButton) findViewById(R.id.answer);
        answer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PhoneCall.this, BlankPage.class);
                startActivity(intent);
            }
        });
    }
}
