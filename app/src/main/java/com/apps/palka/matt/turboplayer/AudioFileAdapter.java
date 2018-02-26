package com.apps.palka.matt.turboplayer;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by matt on 25.02.2018.
 */

public class AudioFileAdapter extends ArrayAdapter<AudioFile> {

    /** Resource ID for the background color of the list*/
    private int mColorResourceId;


    /**
     * This is our own custom constructor (it doesn't mirror a superclass constructor).
     * The context is used to inflate the layout file, and the list is the data we want
     * to populate into the lists.
     *
     * @param context        The current context. Used to inflate the layout file.
     * @param audioFiles    A List of AudioFiles objects to display in a list
     */
    public AudioFileAdapter(Activity context, ArrayList<AudioFile> audioFiles, int colorResourceId) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, audioFiles);
        mColorResourceId = colorResourceId;
    }

    /**
     * Provides a view for an AdapterView (ListView, GridView, etc.)
     *
     * @param position The position in the list of data that should be displayed in the
     *                 list item view.
     * @param convertView The recycled view to populate.
     * @param parent The parent ViewGroup that is used for inflation.
     * @return The View for the position in the AdapterView.
     */
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        // Get the {@link AudioFile} object located at this position in the list
        AudioFile currentAudioFile = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID titleName
        TextView nameTextView = (TextView) listItemView.findViewById(R.id.titleName);
        // Get the Title from the current Audio File object and
        // set this text on the name TextView
        nameTextView.setText(currentAudioFile.getTrackTitleId());

        // Find the TextView in the list_item.xml layout with the ID authorName
        TextView numberTextView = (TextView) listItemView.findViewById(R.id.authorName);
        // Get the AuthorName from the current AudioFile object and
        // set this text on the number TextView
        numberTextView.setText(currentAudioFile.getTrackAuthorId());

        // Find the ImageView in the list_item.xml layout with the ID coverArt
        ImageView iconView = (ImageView) listItemView.findViewById(R.id.coverArt);
        // Get the image resource ID from the current AndroidFlavor object and
        // set the image to iconView
        iconView.setImageResource(currentAudioFile.getTrackCoverImgId());


        //set the theme color for the list view
        View textContainer = listItemView.findViewById(R.id.textContainer);
        //find the color it refers to
        int color = ContextCompat.getColor(getContext(), mColorResourceId);
        //sets the bg color
        textContainer.setBackgroundColor(color);

        // Return the whole list item layout (containing 2 TextViews and an ImageView)
        // so that it can be shown in the ListView
        return listItemView;
    }
}
