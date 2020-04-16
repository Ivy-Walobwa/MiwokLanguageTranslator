package com.example.mimoklanguagetranslator;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class WordAdapter extends ArrayAdapter<Word> {

    private int mColorResourceId;

    public WordAdapter(Activity context, ArrayList<Word> words, int colorResourceId) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0,words);
        mColorResourceId = colorResourceId;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        // Get the {@link Word} object located at this position in the list
        Word wordTranslation = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID miwok_version
        TextView miwokTextView = (TextView) listItemView.findViewById(R.id.miwok_version);
        // Get the word from the current Word object and
        // set this text on the miwok TextView
        miwokTextView.setText(wordTranslation.getMiwokTranslation());

        // Find the TextView in the list_item.xml layout with the ID eng_version
        TextView englishTextView = (TextView) listItemView.findViewById(R.id.eng_version);
        // Get the word from the current Word object and
        // set this text on the english TextView
        englishTextView.setText(wordTranslation.getDefaultTranslation());

        // Find the ImageView in the list_item.xml layout with the ID list_icon
        ImageView iconView = (ImageView) listItemView.findViewById(R.id.list_icon);

        if(wordTranslation.hasImage()){
            // Get the image resource ID from the current Word object and
            // set the image to iconView
            iconView.setImageResource(wordTranslation.getImageResourceId());
            iconView.setVisibility(View.VISIBLE);
        }else{
            iconView.setVisibility(View.GONE);
        }

        View textContainer = listItemView.findViewById(R.id.text_container);
        int color = ContextCompat.getColor(getContext(), mColorResourceId);
        textContainer.setBackgroundColor(color);
        // Return the whole list item layout (containing 2 TextViews)
        // so that it can be shown in the ListView
        return listItemView;
    }
}
