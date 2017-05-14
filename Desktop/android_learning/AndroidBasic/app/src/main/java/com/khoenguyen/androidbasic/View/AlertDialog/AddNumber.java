package com.khoenguyen.androidbasic.View.AlertDialog;

import com.khoenguyen.androidbasic.Model.AlertDialogListView.Word;
import com.khoenguyen.androidbasic.R;

import java.util.ArrayList;

/**
 * Created by Admin on 5/14/2017.
 */

public class AddNumber {
    ArrayList<Word> words;

    public ArrayList<Word> addWord(){
        //TODO: Add words here
        words = new ArrayList<Word>();
        words.add(new Word("one", "mot",R.drawable.number_one));
        words.add(new Word("two", "hai",R.drawable.number_two));
        words.add(new Word("three", "ba", R.drawable.number_three));
        words.add(new Word("four", "bon", R.drawable.number_four));
        words.add(new Word("five", "nam", R.drawable.number_five));
        words.add(new Word("six","sau",R.drawable.number_six));
        words.add(new Word("seven", "bay", R.drawable.number_seven));
        words.add(new Word("eight", "tam", R.drawable.number_eight));
        words.add(new Word("nine", "chin", R.drawable.number_nine));
        words.add(new Word("ten", "muoi", R.drawable.number_ten));
        return words;
    }
}
