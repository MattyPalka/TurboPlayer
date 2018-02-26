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

        ArrayList<AudioFile> audioFiles = new ArrayList<>();

        audioFiles.add(new AudioFile(R.string.forest, R.string.nature));
        audioFiles.add(new AudioFile(R.string.sea, R.string.nature));
        audioFiles.add(new AudioFile(R.string.coffee_house, R.string.urban));
        audioFiles.add(new AudioFile(R.string.birds, R.string.nature));
        audioFiles.add(new AudioFile(R.string.city_traffic, R.string.urban));

        AudioFileAdapter itemsAdapter = new AudioFileAdapter(this, audioFiles, R.color.categoryMeditation);

        ListView listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(itemsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), NowPlayingActivity.class);
                Bundle extras = new Bundle();
                extras.putInt("colorID", R.color.categoryMeditation);
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
                extras.putInt("colorID", R.color.categoryMeditation);
                intent.putExtras(extras);
                startActivity(intent);
            }
        });
    }
}
