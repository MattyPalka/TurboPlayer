package com.apps.palka.matt.turboplayer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;

public class MeditationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);

        final ArrayList<AudioFile> audioFiles = new ArrayList<>();

        audioFiles.add(new AudioFile(R.raw.still_mind_4_minute_body_scan, R.string.body_meditation, R.string.still_mind));
        audioFiles.add(new AudioFile(R.raw.padraig_ten_minute_mindfulness_of_breathing, R.string.padraig, R.string.free_mindfulness));
        audioFiles.add(new AudioFile(R.raw.marc_breath_sound_body_meditation, R.string.body_meditation, R.string.breathing));
        audioFiles.add(new AudioFile(R.raw.free_mindfulness_10_minute_just_bells, R.string.bells, R.string.free_mindfulness));
        audioFiles.add(new AudioFile(R.raw.life_happens_5_minute_breathing, R.string.life_happens, R.string.breathing));
        audioFiles.add(new AudioFile(R.raw.free_mindfulness_mountain_meditation, R.string.mountain, R.string.free_mindfulness));
        audioFiles.add(new AudioFile(R.raw.vidyamala_tension_release, R.string.vidyamala, R.string.tension_release));

        AudioFileAdapter itemsAdapter = new AudioFileAdapter(this, audioFiles, R.color.categoryMeditation);

        ListView listView = findViewById(R.id.list);

        listView.setAdapter(itemsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                /** Get access to items from Array list*/
                AudioFile audioFile = audioFiles.get(i);



                Intent intent = new Intent(getApplicationContext(), NowPlayingActivity.class);
                Bundle extras = new Bundle();
                extras.putInt("colorID", R.color.categoryMeditation);
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
