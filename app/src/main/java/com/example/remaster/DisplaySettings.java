package com.example.remaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Locale;

public class DisplaySettings extends AppCompatActivity {

    private TextView mTextViewCountDown;
    private Button mButtonStartPause;
    private Button mButtonReset;
    private Button mButtonEditMessage;
    private CountDownTimer mCountDownTimer;
    private boolean mTimerRunning;
    private long mTimeLeftInMillis;
    private long ogStartTime;

    MessagesDBHandler dbHandler = new MessagesDBHandler(this, null, null, 1);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_settings);

        mTextViewCountDown = findViewById(R.id.text_view_countdown);
        mButtonStartPause = findViewById(R.id.button_start_pause);
        mButtonReset = findViewById(R.id.button_reset);
        mButtonEditMessage = findViewById(R.id.edit_message);

        MessagesDBHandler dbHandler = new MessagesDBHandler(this, null, null, 1);
        Messages results = dbHandler.loadHandler();
        Intent intent = getIntent();
        String contact = results.getContactName();
        String message = results.getMessage();
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

        mButtonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTimer();
            }
        });

        mButtonEditMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editMessage();
            }
        });

        updateCountDownText();

        TextView contactView = findViewById(R.id.contactView);
        TextView messageView = findViewById(R.id.messageView);
        contactView.setText(contact);
        messageView.setText(message);
    }

    private void startTimer() {
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                mTimerRunning = false;
                mButtonStartPause.setText("Reset");
                mButtonStartPause.setVisibility(View.INVISIBLE);
                mButtonReset.setVisibility(View.VISIBLE);
                sendCall();
            }
        }.start();

        mTimerRunning = true;
        mButtonStartPause.setText("Pause");
        mButtonReset.setVisibility(View.INVISIBLE);
    }

    private void pauseTimer() {
        mCountDownTimer.cancel();
        mTimerRunning = false;
        mButtonStartPause.setText("Start");
        mButtonReset.setVisibility(View.VISIBLE);
    }

    private void resetTimer() {
        mTimeLeftInMillis = ogStartTime;
        updateCountDownText();
        mButtonReset.setVisibility(View.INVISIBLE);
        mButtonStartPause.setVisibility(View.VISIBLE);
    }

    private void updateCountDownText() {
        int hours = (int) (mTimeLeftInMillis / 1000) / 3600;
        int minutes = (int) (mTimeLeftInMillis / 1000) / 60 % 60;
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;

        String timeLeftFormatted = String.format(Locale.getDefault(),"%02d:%02d:%02d", hours, minutes, seconds);

        mTextViewCountDown.setText(timeLeftFormatted);
    }

    private void editMessage() {
        Intent intent = new Intent(this, MainActivity.class );
        startActivity(intent);
    }
    public void sendCall() {
        Intent intent = new Intent(this, PhoneCall.class );
        startActivity(intent);
    }
}
