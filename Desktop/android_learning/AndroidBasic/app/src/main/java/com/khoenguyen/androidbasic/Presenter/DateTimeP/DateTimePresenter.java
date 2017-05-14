package com.khoenguyen.androidbasic.Presenter.DateTimeP;

import com.khoenguyen.androidbasic.Model.DateTimeM.DateTime;
import com.khoenguyen.androidbasic.View.DateTimeV.ViewDateTime;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by Admin on 5/14/2017.
 */

public class DateTimePresenter implements DataTimeImp{
    DateTime dateTime = new DateTime();
    ViewDateTime mViewDateTime;
    public DateTimePresenter(ViewDateTime v){
        mViewDateTime = v;
    }

    @Override
    public void getTime() {
        mViewDateTime.getDefaultTime();
    }
}
