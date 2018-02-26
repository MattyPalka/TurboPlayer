package com.apps.palka.matt.turboplayer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RelativeLayout;

public class NowPlayingActivity extends AppCompatActivity {
    private int mColorResourceId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_now_playing);
        int colorId = getIntent().getExtras().getInt("colorID");
        mColorResourceId = colorId;
        RelativeLayout background = (RelativeLayout) findViewById(R.id.nowPlayingScreen);
        background.setBackgroundColor(getResources().getColor(mColorResourceId));

    }
}
