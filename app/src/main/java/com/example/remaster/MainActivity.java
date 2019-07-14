package com.example.remaster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toolbar;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.remaster.MESSAGE";
    public static final String EXTRA_CONTACT = "com.example.remaster.CONTACT";
    EditText contactName;
    EditText sendMessage;
    EditText ogContactName;
    EditText ogSendMessage;
    TimePicker ogTimePicker;
    MessagesDBHandler dbHandlerView = new MessagesDBHandler(this, null, null, 1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ogContactName = (EditText) findViewById(R.id.contactText);
        ogSendMessage = (EditText) findViewById(R.id.messageText);
        ogTimePicker = (TimePicker) findViewById(R.id.timePicker1);
        try {
            Messages results = dbHandlerView.loadHandler();
            Times ogTimes = dbHandlerView.loadTimeHandler();
            ogSendMessage.setText(results.getMessage());
            ogContactName.setText(results.getContactName());
            ogTimePicker.setHour(ogTimes.getHour());
            ogTimePicker.setMinute(ogTimes.getMinute());

        }
        catch (SQLiteException e)
        {

        }

        dbHandlerView.close();
    }

    public void sendMessage(View view)
    {
        Intent intent = new Intent(this, DisplaySettings.class );
        contactName = (EditText) findViewById(R.id.contactText);
        String contact = contactName.getText().toString();
        sendMessage = (EditText) findViewById(R.id.messageText);
        String message = sendMessage.getText().toString();
        //intent.putExtra(EXTRA_CONTACT, contact);
        //intent.putExtra(EXTRA_MESSAGE, message);

        MessagesDBHandler dbHandler = new MessagesDBHandler(this, null, null, 1);
        int id = 1;
        Messages meso = new Messages(id, contact, message);
        dbHandler.addHandler(meso);
        dbHandler.close();
        TimePicker tp = (TimePicker) findViewById(R.id.timePicker1);
        int hour = tp.getHour();
        int minute = tp.getMinute();
        Times times = new Times(id, hour, minute);
        dbHandler.addTimeHandler(times);
        startActivity(intent);
    }

    public void loadData(View view)
    {

    }

}
