package com.example.wardrobewizardadmin;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.widget.Toast;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        VideoView videoView = (VideoView) findViewById(R.id.videoView);
        videoView.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.newadminvideo);

        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
//        videoView.setMediaController(mediaController);

        videoView.start();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                abcd();
            }
        }, 3000);

    }
    public void abcd()
    {
        Intent intent = new Intent(MainActivity.this,loginpage.class);
        startActivity(intent);
        finish();

    }
}