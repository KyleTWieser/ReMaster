package com.example.remaster;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build;

import androidx.core.app.NotificationCompat;


public class NotificationHelper extends ContextWrapper{
    public static final String channelID = "channelID";
    public static final String channelName = "Channel Name";
    public String contact;
    public String message;

    private NotificationManager nManager;

    public NotificationHelper(Context base){
        super(base);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChannel();
        }
    }

    //creates channel for notification to come through
    @TargetApi(Build.VERSION_CODES.O)
    public void createChannel() {
        NotificationChannel channel = new NotificationChannel(channelID, channelName, NotificationManager.IMPORTANCE_HIGH);
        channel.enableLights(true);
        channel.enableVibration(true);
        channel.setLightColor(R.color.colorPrimary);
        channel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
        getManager().createNotificationChannel(channel);
    }

    //manages how it will be sent
    public NotificationManager getManager() {
        if (nManager == null){
            nManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }

        return nManager;
    }

    //customizes the contact name from display settings
    public void setContact(String contact) {
        this.contact = contact;
    }

    //customizes the message from display settings
    public void setMessage(String message) {
        this.message = message;
    }

    //builds the notification structure
    public NotificationCompat.Builder getChannelNotification() {
        return new NotificationCompat.Builder(getApplicationContext(), channelID)
                .setContentTitle(contact)
                .setContentText(message)
                .setSmallIcon(R.drawable.ic_message);
    }
}
