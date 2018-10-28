package com.gevdev.looper;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.VideoView;

public class LeekspinActivity extends Activity {

    VideoView videoView;
    Chronometer chrono;
    TextView textView;
    private final String TAG = "GEVDEVGEV";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.leekspin_layout);
    }

    @Override
    protected void onStart() {
        super.onStart();

        videoView = findViewById(R.id.leek_videoview);
        chrono = findViewById(R.id.leek_chrono);
        textView = findViewById(R.id.leek_loopCounter);

        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.leekspin);
        videoView.setVideoURI(uri);

        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                //mp.setLooping(true);
                mp.setVideoScalingMode(MediaPlayer.VIDEO_SCALING_MODE_SCALE_TO_FIT_WITH_CROPPING);
                videoView.start();
                chrono.start();
            }
        });

        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onCompletion(MediaPlayer mp) {
                String countString = textView.getText().toString();
                int count = Integer.parseInt(countString);
                count++;
                textView.setText(Integer.toString(count));
                videoView.start();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        chrono.setBase(SystemClock.elapsedRealtime());
        textView.setText("0");
    }
}
