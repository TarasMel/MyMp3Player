package com.example.mymp3player;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.mymp3player.notification.NotificationGenerator;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "App";

    private int trackPosition = 0;

    private Unbinder unbinder;
    private MediaPlayer mediaPlayer;


    @BindView(R.id.btn_play)
    Button buttonPlay;

    @BindView(R.id.btn_pause)
    Button buttonPause;

    @BindView(R.id.btn_stop)
    Button buttonStop;

    @OnClick({R.id.btn_play, R.id.btn_pause , R.id.btn_stop})
    void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_play:
                play();
                break;
            case R.id.btn_pause:
                pause();
                break;
            case R.id.btn_stop:
                stop();
                break;
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG, "work onCreate method");
        unbinder = ButterKnife.bind(this);
    }

    public void play() {
        NotificationGenerator.customNotification(getApplicationContext());
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(this, R.raw.yuko_khayat_vesnianka);
            mediaPlayer.start();
        }
        else if(!mediaPlayer.isPlaying()){
            mediaPlayer.seekTo(trackPosition);
            mediaPlayer.start();
        }
    }

    public void pause() {
        if (mediaPlayer != null){
            mediaPlayer.pause();
            trackPosition = mediaPlayer.getCurrentPosition();
        }
    }

    public void stop() {
        if (mediaPlayer != null){
            mediaPlayer.stop();
            mediaPlayer = null;
        }
    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        Log.i(TAG, "work onStart method");
//    }
//
    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "work onResume method");
    }



    @Override
    protected void onPause() {
        super.onPause();
//        Intent intent = new Intent(this, PlayService.class);
//        startService(intent);
        Log.i(TAG, "work onPause method");
        NotificationGenerator.customNotification(getApplicationContext());
    }

//    @Override
//    protected void onStop() {
//        super.onStop();
//        Log.i(TAG, "work onStop method");
//    }
//
//    @Override
//    protected void onRestart() {
//        super.onRestart();
//        Log.i(TAG, "work onRestart method");
//    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "work onDestroy method");
        unbinder.unbind();
    }
}
