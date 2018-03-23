package com.apps.palka.matt.turboplayer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;

public class MusicActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);

        final ArrayList<AudioFile> audioFiles = new ArrayList<>();

        audioFiles.add(new AudioFile(R.string.end_game, R.string.taylor_swift));
        audioFiles.add(new AudioFile(R.string.abbey_road, R.string.beatles));
        audioFiles.add(new AudioFile(R.string.satisfaction, R.string.rolling_stones));
        audioFiles.add(new AudioFile(R.string.starboy, R.string.weeknd));
        audioFiles.add(new AudioFile(R.string.wicked_games, R.string.weeknd));
        audioFiles.add(new AudioFile(R.string.smells_like_teen_spirit, R.string.nirvana));
        audioFiles.add(new AudioFile(R.string.hotel_california, R.string.eagles));
        audioFiles.add(new AudioFile(R.string.dont_speak, R.string.no_doubt));
        audioFiles.add(new AudioFile(R.string.love_story, R.string.taylor_swift));
        audioFiles.add(new AudioFile(R.string.come_as_you_are, R.string.nirvana));
        audioFiles.add(new AudioFile(R.string.perfect, R.string.ed_sheeran));

        AudioFileAdapter itemsAdapter = new AudioFileAdapter(this, audioFiles, R.color.categoryMusic);

        ListView listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(itemsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                /** Get access to items from Array list*/
                AudioFile audioFile = audioFiles.get(i);

                Intent intent = new Intent(getApplicationContext(), NowPlayingActivity.class);
                Bundle extras = new Bundle();
                extras.putInt("colorID", R.color.categoryMusic);
                extras.putInt("trackTitleID", audioFile.getTrackTitleId());
                extras.putInt("trackAuthorID", audioFile.getTrackAuthorId());
                if (audioFile.getAudioFileId() != -1) {
                    extras.putInt("trackID", audioFile.getAudioFileId());
                }
                intent.putExtras(extras);
                startActivity(intent);
            }
        });

    }
}
