package com.example.remaster;

import androidx.appcompat.app.AppCompatActivity;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;
import java.util.Calendar;


public class MainActivity extends AppCompatActivity {
    //public static final String EXTRA_MESSAGE = "com.example.remaster.MESSAGE";
    //public static final String EXTRA_CONTACT = "com.example.remaster.CONTACT";
    public static Boolean timeGood = false;
    EditText contactName;
    EditText sendMessage;
    EditText ogContactName;
    EditText ogSendMessage;
    //TimePicker ogTimePicker;
    MessagesDBHandler dbHandlerView = new MessagesDBHandler(this, null, null, 1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ogContactName = (EditText) findViewById(R.id.contactText);
        ogSendMessage = (EditText) findViewById(R.id.messageText);
        //ogTimePicker = (TimePicker) findViewById(R.id.timePicker1);
        try {
            Messages results = dbHandlerView.loadHandler();
            Times ogTimes = dbHandlerView.loadTimeHandler();
            ogSendMessage.setText(results.getMessage());
            ogContactName.setText(results.getContactName());
            //ogTimePicker.setHour(ogTimes.getHour());
            //ogTimePicker.setMinute(ogTimes.getMinute());

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

        Calendar c = Calendar.getInstance();

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
        long cH= (c.get(Calendar.HOUR_OF_DAY)) * 3600000;
        long cM = c.get(Calendar.MINUTE) * 60000;
        long cS = c.get(Calendar.SECOND) * 1000;
        long currentTime = cH + cM + cS;
        long endH = times.getHour() * 3600000;
        long endM = times.getMinute() * 60000;
        long newTime = endH + endM;

        checkTimes(currentTime, newTime, intent);
    }

    private void checkTimes(long currentTime, long newTime, final Intent intent) {
        if(currentTime > newTime) {
            AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setCancelable(true);
                alert.setTitle("Woah!");
                alert.setMessage("That's over 4 hours from now!\n Are you sure about that time?");
                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        timeGood = false;
                    }
                });
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        timeGood = true;
                        startActivity(intent);
                    }
                });
                alert.show();


        }
        else{
            startActivity(intent);
        }
    }

    public void loadData(View view)
    {

    }

}
