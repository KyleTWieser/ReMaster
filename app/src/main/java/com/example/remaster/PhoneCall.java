package com.example.remaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;

public class PhoneCall extends AppCompatActivity {
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final String incomingContact;
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_phone_call);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON|
                WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD|
                WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED|
                WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);


        incomingContact = getIntent().getStringExtra("contact");

        System.out.println("in onCreate: "+ incomingContact);
        setContentView(R.layout.activity_phone_call);
        textView = (TextView) findViewById(R.id.textView4);
        textView.setText(incomingContact);

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
