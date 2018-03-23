package com.apps.palka.matt.turboplayer;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class NowPlayingActivity extends AppCompatActivity {
    private int mColorResourceId;
    private int mTrackTitleId;
    private int mTrackAuthorId;
    private int mTrackId = -1;
    /**
     * Handles playback of all the sound files
     */
    private MediaPlayer mMediaPlayer;
    /**
     * Handles audio focus when playing a sound file
     */
    private AudioManager mAudioManager;

    /**
     * This listener gets triggered when the {@link MediaPlayer} has completed
     * playing the audio file.
     */
    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            // Now that the sound file has finished playing, release the media player resources.
            releaseMediaPlayer();
        }
    };

    /**
     * This listener gets triggered whenever the audio focus changes
     * (i.e., we gain or lose audio focus because of another app or device).
     */
    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                    focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                // The AUDIOFOCUS_LOSS_TRANSIENT case means that we've lost audio focus for a
                // short amount of time. The AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK case means that
                // our app is allowed to continue playing sound but at a lower volume. We'll treat
                // both cases the same way because our app is playing short sound files.

                // Pause playback
                mMediaPlayer.pause();

            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                // The AUDIOFOCUS_GAIN case means we have regained focus and can resume playback.
                mMediaPlayer.start();
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                // The AUDIOFOCUS_LOSS case means we've lost audio focus and
                // Stop playback and clean up resources
                releaseMediaPlayer();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_now_playing);

        // Create and setup the {@link AudioManager} to request audio focus
        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);


        mColorResourceId = getIntent().getExtras().getInt("colorID");
        mTrackTitleId = getIntent().getExtras().getInt("trackTitleID");
        mTrackAuthorId = getIntent().getExtras().getInt("trackAuthorID");
        if (getIntent().hasExtra("trackID")) {
            mTrackId = getIntent().getExtras().getInt("trackID");
        }


        /** sets background color according to audio category*/
        RelativeLayout background = findViewById(R.id.now_playing_screen);
        background.setBackgroundColor(getResources().getColor(mColorResourceId));

        /** find track title TextView and set it accordining to clicked item*/
        TextView titleTextView = findViewById(R.id.playing_title);
        titleTextView.setText(mTrackTitleId);

        /** find track author TextView and set it according to clicked item*/
        TextView authorTextView = findViewById(R.id.playing_artist);
        authorTextView.setText(mTrackAuthorId);

        /** Find pause, stop, play button */
        final ImageView playPauseButton = findViewById(R.id.pause_track);
        ImageView stopButton = findViewById(R.id.stop_track);


        /** After clicking the play / pause button either play or pause audio depending on
         * the current state of the player and change the button view according to it's state
         */
        playPauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // check if there is audio file to play, if not return early
                if (mTrackId == -1){
                    return;
                }
                if (mMediaPlayer.isPlaying()) {
                    mMediaPlayer.pause();
                    playPauseButton.setImageResource(R.drawable.ic_play_arrow_black_24dp);

                } else if (mMediaPlayer.isPlaying() == false) {
                    mMediaPlayer.start();
                    playPauseButton.setImageResource(R.drawable.ic_pause_black_24dp);
                }
            }
        });

        /*
         * Stop the playback after clicking the stop button and prepare it to be played from the beginning
         */
        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // check if there is audio file, if not return early
                if (mTrackId == -1){
                    return;
                }
                mMediaPlayer.pause();
                mMediaPlayer.seekTo(0);
                playPauseButton.setImageResource(R.drawable.ic_play_arrow_black_24dp);
            }
        });

        // Check if there is a audio file and if there is play it from the start of the activity
        if (mTrackId != -1) {
            play(mTrackId);
        }
    }

    // Helper method to play the media
    private void play(int media) {
        releaseMediaPlayer();
        // Request audio focus so in order to play the audio file. The app needs to play a
        // short audio file, so we will request audio focus with a short amount of time
        // with AUDIOFOCUS_GAIN_TRANSIENT.
        int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

        if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
            mMediaPlayer = MediaPlayer.create(this, media);

            mMediaPlayer.start();

            mMediaPlayer.setOnCompletionListener(mCompletionListener);
        }

    }

    /**
     * Clean up the media player by releasing its resources.
     */
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mMediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mMediaPlayer = null;

            // Regardless of whether or not we were granted audio focus, abandon it. This also
            // unregisters the AudioFocusChangeListener so we don't get anymore callbacks.
            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }
}
