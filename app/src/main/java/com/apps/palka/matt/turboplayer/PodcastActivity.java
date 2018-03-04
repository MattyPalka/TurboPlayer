package com.apps.palka.matt.turboplayer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;

public class PodcastActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);

        final ArrayList<AudioFile> audioFiles = new ArrayList<>();

        audioFiles.add(new AudioFile(R.string.are_you_there, R.string.alien_guy));
        audioFiles.add(new AudioFile(R.string.living_with_parents, R.string.random_dude));
        audioFiles.add(new AudioFile(R.string.mom_let_me_out, R.string.this_kid));

        AudioFileAdapter itemsAdapter = new AudioFileAdapter(this, audioFiles, R.color.categoryPodcast);

        ListView listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(itemsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                /** Get access to items from Array list*/
                AudioFile audioFile = audioFiles.get(i);

                Intent intent = new Intent(getApplicationContext(), NowPlayingActivity.class);
                Bundle extras = new Bundle();
                extras.putInt("colorID", R.color.categoryPodcast);
                extras.putInt("trackTitleID", audioFile.getTrackTitleId());
                extras.putInt("trackAuthorID", audioFile.getTrackAuthorId());
                intent.putExtras(extras);
                startActivity(intent);
            }
        });

        LinearLayout nowPlayingBottom = (LinearLayout) findViewById(R.id.nowPlayingBox);
        nowPlayingBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), NowPlayingActivity.class);
                Bundle extras = new Bundle();
                extras.putInt("colorID", R.color.categoryPodcast);
                intent.putExtras(extras);
                startActivity(intent);
            }
        });
    }
}
