package com.example.mymp3player.notification;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import android.support.v4.app.NotificationCompat;
import android.widget.RemoteViews;

import com.example.mymp3player.MainActivity;
import com.example.mymp3player.R;

class NotificationGenerator {

    static final String NOTIFY_PLAY = "play";
    static final String NOTIFY_PAUSE = "pause";
    static final String NOTIFY_STOP = "stop";

    public static void customNotification (Context context) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.notification_layout);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {

            String CHANNEL_ID = context.getResources().getString(R.string.my_channel_id);
            CharSequence name = context.getResources().getString(R.string.channel_name);
            String Description = context.getResources().getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_HIGH;

            NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, name, importance);
            mChannel.setDescription(Description);
            mChannel.enableLights(true);
            notificationManager.createNotificationChannel(mChannel);
        }
        Intent notifyIntent = new Intent(context, MainActivity.class);
        notifyIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent).setSmallIcon(R.mipmap.ic_launcher)
                .setAutoCancel(true).setCustomBigContentView(remoteViews)
                .setContentTitle(context.getResources().getString(R.string.content_title));
        setListeners(remoteViews, context);
        notificationManager.notify(0 , builder.build());
    }

    private static void setListeners(RemoteViews view, Context context) {
        Intent play = new Intent(NOTIFY_PLAY);
        Intent pause = new Intent(NOTIFY_PAUSE);
        Intent stop = new Intent(NOTIFY_STOP);

        PendingIntent pendingPlay = PendingIntent.getBroadcast(context, 0, play,
                PendingIntent.FLAG_UPDATE_CURRENT);
        view.setOnClickPendingIntent(R.id.btn_not_play, pendingPlay);

        PendingIntent pendingPause = PendingIntent.getBroadcast(context, 0, pause,
                PendingIntent.FLAG_UPDATE_CURRENT);
        view.setOnClickPendingIntent(R.id.btn_not_play, pendingPause);

        PendingIntent pendingDelete = PendingIntent.getBroadcast(context, 0, stop,
                PendingIntent.FLAG_UPDATE_CURRENT);
        view.setOnClickPendingIntent(R.id.btn_not_play, pendingDelete);
    }
}
