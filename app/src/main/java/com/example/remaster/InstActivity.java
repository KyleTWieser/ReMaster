package com.example.remaster;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class InstActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inst);

        TextView tv = (TextView) findViewById(R.id.textView_main);
        tv.setMovementMethod(new ScrollingMovementMethod());

    }

    public void getStarted(View view){
        startActivity(new Intent(InstActivity.this, MainActivity.class));
    }
}
