package com.apps.palka.matt.turboplayer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toolbar;

public class NowPlayingActivity extends AppCompatActivity {
    private int mColorResourceId;
    private int mTrackTitleId;
    private int mTrackAuthorId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_now_playing);
        mColorResourceId= getIntent().getExtras().getInt("colorID");
        mTrackTitleId = getIntent().getExtras().getInt("trackTitleID");
        mTrackAuthorId = getIntent().getExtras().getInt("trackAuthorID");


        /** sets background color according to audio category*/
        RelativeLayout background = (RelativeLayout) findViewById(R.id.now_playing_screen);
        background.setBackgroundColor(getResources().getColor(mColorResourceId));

        /** find track title TextView and set it accordining to clicked item*/
        TextView titleTextView = (TextView) findViewById(R.id.playing_title);
        titleTextView.setText(mTrackTitleId);

        /** find track author TextView and set it according to clicked item*/
        TextView authorTextView = (TextView) findViewById(R.id.playing_artist);
        authorTextView.setText(mTrackAuthorId);



    }
}
