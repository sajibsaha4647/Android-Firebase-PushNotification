package com.example.firebasepushnotification;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        getFirebaseMessage(remoteMessage.getNotification().getTitle(), remoteMessage.getNotification().getBody());
        System.out.println("receive message");
//        Toast.makeText(getApplicationContext(), "message receive", Toast.LENGTH_SHORT).show();

    }

    public void getFirebaseMessage(String title, String body) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
            NotificationChannel channel = new NotificationChannel("012", "channel_name", NotificationManager.IMPORTANCE_HIGH);
            channel.setDescription("channel_description");
            notificationManager.createNotificationChannel(channel);

        }


        Intent fullScreenIntent = new Intent(this, MainActivity.class);
        PendingIntent fullScreenPendingIntent = PendingIntent.getActivity(this, 0,
                fullScreenIntent, PendingIntent.FLAG_UPDATE_CURRENT);


        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this, "012")
                        .setSmallIcon(R.drawable.ic_launcher_background)
                        .setContentTitle("Full Screen Alarm Test")
                        .setContentText("This is a test")
                        .setContentIntent(fullScreenPendingIntent)
                        .setFullScreenIntent(fullScreenPendingIntent, true)
                .setOngoing(true)
                .setTimeoutAfter(30000)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_CALL)
                .setFullScreenIntent(fullScreenPendingIntent, true)
                .addAction(R.drawable.ic_bell, "Receive", fullScreenPendingIntent)
                .addAction(R.drawable.ic_bell, "Reject", fullScreenPendingIntent)
                .setContentText("calling...");
        NotificationManagerCompat.from(this).notify(1, notificationBuilder.build());


    }


}
