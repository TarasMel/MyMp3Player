package com.example.mymp3player.notification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.example.mymp3player.MainActivity;

public class NotificationBroadcast extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        try {
            MainActivity m = (MainActivity) context;
            if (intent.getAction().equals(NotificationGenerator.NOTIFY_PLAY)) {
                m.play();
            }
            if (intent.getAction().equals(NotificationGenerator.NOTIFY_PAUSE)) {
                m.pause();
            }
            if (intent.getAction().equals(NotificationGenerator.NOTIFY_STOP)) {
                m.stop();
            }
        }catch (NullPointerException e ){
            e.printStackTrace();
            Log.d("NULL", e.toString());
            Toast.makeText(context, "Drop NullPointer", Toast.LENGTH_SHORT).show();
        }
    }
}
