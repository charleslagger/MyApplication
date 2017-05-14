package com.khoenguyen.androidbasic.Presenter.AlertDialog.ListViewAdapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.khoenguyen.androidbasic.Model.AlertDialogListView.Word;
import com.khoenguyen.androidbasic.R;

import java.util.ArrayList;

/**
 * Created by Admin on 5/14/2017.
 * Callback lai thong tin tu list cac Word gui sang View
 */

public class WordAdapterListView extends ArrayAdapter<Word>{
    Context mContext;
    int layoutResourceId;
    ArrayList<Word> objects = null;
    public WordAdapterListView(@NonNull Context context, int layoutResourceId, @NonNull ArrayList<Word> objects) {
        super(context, layoutResourceId, objects);
        this.mContext = context;
        this.layoutResourceId = layoutResourceId;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        //neu view chua ton tai thi nap view moi, con khong thi tai su dung lai
        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(
                    layoutResourceId, parent, false);
        }
        Word my_word = getItem(position);
        TextView englishWord = (TextView) listItemView.findViewById(R.id.english);
        TextView vietNameseWord = (TextView) listItemView.findViewById(R.id.vietnam);

        englishWord.setText(my_word.getmEnglish());
        vietNameseWord.setText(my_word.getmVietNamese());
        ImageView imageView = (ImageView) listItemView.findViewById(R.id.list_item_icon);
        if (my_word.hasImage()) {
            imageView.setImageResource(my_word.getmImageResourceId());
        } else {
            imageView.setVisibility(View.GONE);
        }

        return listItemView;
    }
}
