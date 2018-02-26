package com.apps.palka.matt.turboplayer;

/**
 * Created by matt on 25.02.2018.
 * {@link AudioFile} represents the audio file that the user want to listen. No matter the audio type
 * it contains resource IDs for the audio file, title, author and cover image for the specific track
 */

public class AudioFile {

    /** Audio resource for the track */
    private int mAudioFileId;

    /** String resource for the track author / artist */
    private int mTrackAuthorId;

    /** String resource for the track title / name */
    private int mTrackTitleId;

    /** Image resource for the track cover image */
    private int mTrackCoverImgId;

    /**
     * Create a new AudioFile object
     * @param audioFileId is the resource ID of the audio file
     * @param trackAuthorId is the author or the song artist of the audio file
     * @param trackTitleId is the title of the audio file
     * @param trackCoverImgId is the drawable resource for cover image connected with the audio file
     */
    public AudioFile(int audioFileId, int trackTitleId, int trackAuthorId, int trackCoverImgId){
        mAudioFileId = audioFileId;
        mTrackAuthorId = trackAuthorId;
        mTrackTitleId = trackTitleId;
        mTrackCoverImgId = trackCoverImgId;
    }

    public AudioFile (int trackTitleId, int trackAuthorId){
        mTrackAuthorId = trackAuthorId;
        mTrackTitleId = trackTitleId;
    }

    /** Get the audio file resource ID */
    public int getAudioFileId(){
        return mAudioFileId;
    }

    /** Get the track author string ID*/
    public int getTrackAuthorId(){
        return mTrackAuthorId;
    }

    /** Get the track title string ID*/
    public int getTrackTitleId(){
        return mTrackTitleId;
    }

    /** Get the track cover image resource ID*/
    public int getTrackCoverImgId(){
        return mTrackCoverImgId;
    }

}
