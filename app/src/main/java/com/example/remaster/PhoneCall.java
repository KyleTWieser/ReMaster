package com.example.remaster;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class PhoneCall extends AppCompatActivity {
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final String incomingContact;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_call);

        if (getIntent().hasExtra("SentFrom")) {
            incomingContact = getIntent().getStringExtra("SentFrom");
        } else {
            throw new IllegalArgumentException("Activity cannot find extras ");
        }
        System.out.println("in onCreate: "+ incomingContact);
        setContentView(R.layout.activity_phone_call);
        textView = (TextView) findViewById(R.id.textView4);
        textView.setText(incomingContact);
    }
}
