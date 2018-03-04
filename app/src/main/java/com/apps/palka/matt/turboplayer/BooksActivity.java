package com.apps.palka.matt.turboplayer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class BooksActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);

        final ArrayList<AudioFile> audioFiles = new ArrayList<>();

        audioFiles.add(new AudioFile(R.string.chrismas_carol, R.string.charles_dickens));
        audioFiles.add(new AudioFile(R.string.troy, R.string.homer));
        audioFiles.add(new AudioFile(R.string.romeo_and_juliet, R.string.william_shakespear));

        AudioFileAdapter itemsAdapter = new AudioFileAdapter(this, audioFiles, R.color.categoryBooks);

        ListView listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(itemsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                /** Get access to items from Array list*/
                AudioFile audioFile = audioFiles.get(i);

                Intent intent = new Intent(getApplicationContext(), NowPlayingActivity.class);
                Bundle extras = new Bundle();
                extras.putInt("colorID", R.color.categoryBooks);
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
                extras.putInt("colorID", R.color.categoryBooks);
                intent.putExtras(extras);
                startActivity(intent);
            }
        });
    }
}
