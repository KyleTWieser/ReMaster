package com.example.remaster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.content.Context;


import java.util.Calendar;
import java.util.Locale;

public class DisplaySettings extends AppCompatActivity {
    private static final long START_TIME_IN_MILLIS = 000000;
    private TextView mTextViewCountDown;
    //private Button mButtonStartPause;
    //private Button mButtonReset;
    //private Button mButtonEditMessage;
    private Button mButtonCancel;
    private Button mButtonAdd_15_Minutes;
    private CountDownTimer mCountDownTimer;
    private boolean mTimerRunning;
    private long mTimeLeftInMillis;
    private long ogStartTime;
    private String name;
    private String contact;
    private String message;
    private Boolean sendMessage;

    MessagesDBHandler dbHandler = new MessagesDBHandler(this, null, null, 1);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sendMessage = false;
        if(getIntent().hasExtra("doSend")) {
            sendMessage = getIntent().getBooleanExtra("doSend", false);
            System.out.println(sendMessage);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_settings);

        mTextViewCountDown = findViewById(R.id.text_view_countdown);
        //mButtonStartPause = findViewById(R.id.button_start_pause);
        //mButtonReset = findViewById(R.id.button_reset);

        mButtonCancel = findViewById(R.id.button_cancel);
     //   mButtonEditMessage = findViewById(R.id.edit_message);
        mButtonAdd_15_Minutes = findViewById(R.id.button_add_minutes);
        MessagesDBHandler dbHandler = new MessagesDBHandler(this, null, null, 1);
        Messages results = dbHandler.loadHandler();
        Intent intent = getIntent();
        contact = results.getContactName();
        message = results.getMessage();
        Times times = dbHandler.loadTimeHandler();
        Calendar c = Calendar.getInstance();
        long startH= (c.get(Calendar.HOUR_OF_DAY)) * 3600000;
        long startM = c.get(Calendar.MINUTE) * 60000;
        long startS = c.get(Calendar.SECOND) * 1000;
        long startTime = startH + startM + startS;
        long endH = times.getHour() * 3600000;
        long endM = times.getMinute() * 60000;
        long endTime = endH + endM;
        ogStartTime = endTime - startTime;
        if (startTime >= endTime)
        {
            long temp = 86400000;
            temp -= startTime;
            temp += endTime;
            ogStartTime = temp;

        }
        mTimeLeftInMillis = ogStartTime;

        startTimer();
        mButtonAdd_15_Minutes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTimerRunning)
                {
                    addMinutes();
                }
                else
                {
                    startTimer();
                }
            }
        });
        /*
        mButtonStartPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if (mTimerRunning)
                {
                    pauseTimer();
                }
                else
                {
                    startTimer();
                }
            }

        });
        */
        /*
        mButtonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTimer();
            }
        });
        */
        mButtonCancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                cancelTimer();
                Intent intent = new Intent(DisplaySettings.this, MainActivity.class);
                startActivity(intent);
            }
        });

//        mButtonEditMessage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                editMessage();
//            }
//        });

        updateCountDownText();

        TextView contactView = findViewById(R.id.contactView);
        TextView messageView;
        String noMes = " ";
        messageView = findViewById(R.id.messageView);
        name = contact;
        contactView.setText(contact);
        if (sendMessage) {
            messageView.setText(message);
        } else {
            messageView.setText(noMes);
        }
    }

    private void startTimer() {
        final Context context = getApplicationContext();
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();
                System.out.println("mTimeLeftInMillis: " + mTimeLeftInMillis);
                if (sendMessage && (mTimeLeftInMillis <= 60000))
                {
                    System.out.println("in the if statement of startTimer");
                    NotificationHelper notificationHelper = new NotificationHelper(context);
                    notificationHelper.setContact(contact);
                    notificationHelper.setMessage(message);
                    NotificationCompat.Builder nb = notificationHelper.getChannelNotification();
                    notificationHelper.getManager().notify(1, nb.build());
                    sendMessage = false;
                }
            }

            @Override
            public void onFinish() {
                mTimerRunning = false;
                //mButtonStartPause.setText("Reset");
                //mButtonStartPause.setVisibility(View.INVISIBLE);
                //mButtonReset.setVisibility(View.VISIBLE);
                sendCall();
            }
        }.start();

        mTimerRunning = true;
        //mButtonStartPause.setText("Pause");
        //mButtonReset.setVisibility(View.INVISIBLE);
    }
    /*
    private void pauseTimer() {
        mCountDownTimer.cancel();
        mTimerRunning = false;
        mButtonStartPause.setText("Start");
        mButtonReset.setVisibility(View.VISIBLE);
    }
    */
    /*
    private void resetTimer() {
        mTimeLeftInMillis = ogStartTime;
        updateCountDownText();
        mButtonReset.setVisibility(View.INVISIBLE);
        mButtonStartPause.setVisibility(View.VISIBLE);
    }
    */
    private void addMinutes(){
        mCountDownTimer.cancel();
        mTimeLeftInMillis += 15*60*1000;
        updateCountDownText();
        startTimer();
    }
    private void cancelTimer(){
        mCountDownTimer.cancel();
        mTimeLeftInMillis = START_TIME_IN_MILLIS;
        mTimerRunning = false;
        updateCountDownText();
    }
    private void updateCountDownText() {
        int hours = (int) (mTimeLeftInMillis / 1000) / 3600;
        int minutes = (int) (mTimeLeftInMillis / 1000) / 60 % 60;
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;

        String timeLeftFormatted = String.format(Locale.getDefault(),"%02d:%02d:%02d", hours, minutes, seconds);

        mTextViewCountDown.setText(timeLeftFormatted);
    }

//    private void editMessage() {
//        Intent intent = new Intent(this, MainActivity.class );
//        startActivity(intent);
//    }
    public void sendCall() {
        Intent intent = new Intent(this, PhoneCall.class );
        intent.putExtra("contact", name);
        startActivity(intent);
    }
}
