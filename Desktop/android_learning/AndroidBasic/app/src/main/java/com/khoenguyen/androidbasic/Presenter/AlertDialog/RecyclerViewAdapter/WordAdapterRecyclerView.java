package com.khoenguyen.androidbasic.Presenter.AlertDialog.RecyclerViewAdapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.khoenguyen.androidbasic.Model.AlertDialogListView.Word;
import com.khoenguyen.androidbasic.R;

import java.util.ArrayList;

/**
 * Created by Admin on 5/14/2017.
 */

public class WordAdapterRecyclerView extends RecyclerView.Adapter<WordAdapterRecyclerView.ViewHolder> {
    private ArrayList<Word> mWords;
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mEnglish, mVietNam;
        public ImageView mImg;

        public ViewHolder(View v) {
            super(v);
            mEnglish = (TextView) v.findViewById(R.id.english);
            mVietNam = (TextView) v.findViewById(R.id.vietnam);
            mImg = (ImageView) v.findViewById(R.id.list_item_icon);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public WordAdapterRecyclerView(ArrayList<Word> wordList) {
        mWords = wordList;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        // set the view's size, margins, paddings and layout parameters
        return new ViewHolder(v);
    }


    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.mEnglish.setText(mWords.get(position).getmEnglish());
        holder.mVietNam.setText(mWords.get(position).getmVietNamese());
        if (mWords.get(position).hasImage()) {
            holder.mImg.setImageResource(mWords.get(position).getmImageResourceId());
        } else {
            holder.mImg.setVisibility(View.GONE);
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mWords.size();
    }
}
