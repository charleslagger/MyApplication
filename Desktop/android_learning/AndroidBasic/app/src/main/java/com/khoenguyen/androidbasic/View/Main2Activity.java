package com.khoenguyen.androidbasic.View;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.khoenguyen.androidbasic.Model.AlertDialogListView.Word;
import com.khoenguyen.androidbasic.Presenter.AlertDialog.ListViewAdapter.WordAdapterListView;
import com.khoenguyen.androidbasic.Presenter.AlertDialog.RecyclerViewAdapter.WordAdapterRecyclerView;
import com.khoenguyen.androidbasic.Presenter.DateTimeP.DateTimePresenter;
import com.khoenguyen.androidbasic.R;
import com.khoenguyen.androidbasic.View.AlertDialog.AddNumber;
import com.khoenguyen.androidbasic.View.DateTimeV.ViewDateTime;
import com.turkialkhateeb.materialcolorpicker.ColorChooserDialog;
import com.turkialkhateeb.materialcolorpicker.ColorListener;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class Main2Activity extends AppCompatActivity implements ViewDateTime{
    private SetDateTime setDateTime;
    ImageButton imageButtonAlarm;
    ImageView mImageView;
    private MediaPlayer mMediaPlayer;
    TextView txtDate, txtTime, txtDateChoose, txtTimeChoose;
    AddNumber addNumber;
    Button btnShowPopupLv, btnShowPopupRc;
    AlertDialog.Builder alertDialog;
    ListView listView;
    RecyclerView recyclerView;
    ArrayList<Word> words;
    DateTimePresenter dateTimePresenter;
    public static final int REQUEST_IMAGE_CAPTURE = 1;
    public static final int REQUEST_GALLERY = 2;
    private static final String IMAGE_DIRECTORY = "/basic";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        //chay logo tren action bar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        playMusic();


        addNumber = new AddNumber();
        btnShowPopupLv = (Button)findViewById(R.id.btnShowPopup);
        btnShowPopupRc = (Button) findViewById(R.id.btnShowPopup2);
        words = new ArrayList<>();
        words = addNumber.addWord();
        alertDialog = new AlertDialog.Builder(Main2Activity.this);
        btnShowPopupLv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LayoutInflater inflater = getLayoutInflater();
                View convertView = (View) inflater.inflate(R.layout.word_listview_row, null);
                alertDialog.setView(convertView);
                alertDialog.setTitle("ListView");
                listView = (ListView) convertView.findViewById(R.id.listView);
                WordAdapterListView adapter = new WordAdapterListView(Main2Activity.this,R.layout.list_item, words);
                listView.setAdapter(adapter);
                alertDialog.show();
            }
        });
        btnShowPopupRc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View convertView = LayoutInflater.from(Main2Activity.this).inflate(R.layout.word_recycler_row, null);
                alertDialog.setView(convertView);
                alertDialog.setTitle("RecyclerView");
                recyclerView = (RecyclerView) convertView.findViewById(R.id.my_recycler_view);
                recyclerView.setLayoutManager(new LinearLayoutManager(Main2Activity.this));
                recyclerView.setHasFixedSize(true);
                WordAdapterRecyclerView adapter1 = new WordAdapterRecyclerView(words);
                recyclerView.setAdapter(adapter1);
                alertDialog.show();
            }
        });

        txtDate = (TextView)findViewById(R.id.txtDate);
        txtTime = (TextView)findViewById(R.id.txtTime);
        txtDateChoose = (TextView)findViewById(R.id.txtDateChoose);
        txtTimeChoose = (TextView)findViewById(R.id.txtTimeChoose);
        dateTimePresenter = new DateTimePresenter(this);
        dateTimePresenter.getTime();
        imageButtonAlarm = (ImageButton) findViewById(R.id.time_ic);
        imageButtonAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickerDialog();
                showDatePickerDialog();
            }
        });
        //timeDefault = (TextView)findViewById(R.id.timeDefault);
        //setDateTime = new SetDateTime(this, txtDate, txtTime, timeDefault, imageButtonAlarm);
        //setDateTime.addEventFormWidgets();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.camera:
                showPictureDialog();
                return true;
            case R.id.colors:
                ColorChooserDialog dialog = new ColorChooserDialog(this);
                dialog.setTitle("Choose Color");
                dialog.setColorListener(new ColorListener() {
                    @Override
                    public void OnColorClick(View v, int color) {
                        //Todo: do whatever you want to with the values
                        RelativeLayout rl = (RelativeLayout)findViewById(R.id.rl);
                        rl.setBackgroundColor(color);
                    }
                });
                //customize the dialog however you want
                dialog.show();
                return true;
            case R.id.check:
                //
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void showTimePickerDialog() {
        TimePickerDialog.OnTimeSetListener callback=new TimePickerDialog.OnTimeSetListener() {
            public void onTimeSet(TimePicker view,
                                  int hourOfDay, int minute) {
                //Xử lý lưu giờ và AM,PM
                String s=hourOfDay +":"+minute;
                int hourTam=hourOfDay;
                if(hourTam>12)
                    hourTam=hourTam-12;
                txtTime.setText
                        (hourTam +":"+minute +(hourOfDay>12?" PM":" AM"));
                //lưu giờ thực vào tag
                txtTime.setTag(s);
                //lưu viết lại giờ vào hourFinish
                //cal.set(Calendar.HOUR_OF_DAY, hourOfDay);
                //cal.set(Calendar.MINUTE, minute);
                //hourFinish=cal.getTime();
            }
        };
        //các lệnh dưới này xử lý ngày giờ trong TimePickerDialog
        //sẽ giống với trên TextView khi mở nó lên
        String s=txtTime.getTag()+"";
        //Log.v("NoteActivity", "chuoi s " + s);
        String strArr[]=s.split(":");
        int gio=Integer.parseInt(strArr[0]);
        int phut=Integer.parseInt(strArr[1]);

        TimePickerDialog time = new TimePickerDialog(
                Main2Activity.this,
                callback, gio, phut, true);
        time.setTitle("Chọn giờ hoàn thành");
        time.show();
    }
    private void showDatePickerDialog() {
        DatePickerDialog.OnDateSetListener callback=new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker view, int year,
                                  int monthOfYear,
                                  int dayOfMonth) {
                //Mỗi lần thay đổi ngày tháng năm thì cập nhật lại TextView Date
                txtDate.setText(
                        (dayOfMonth) +"/"+(monthOfYear+1)+"/"+year);
            }
        };
        //các lệnh dưới này xử lý ngày giờ trong DatePickerDialog
        //sẽ giống với trên TextView khi mở nó lên
        String s=txtDate.getText()+"";
        String strArrtmp[]=s.split("/");
        int ngay = Integer.parseInt(strArrtmp[0]);
        int thang = Integer.parseInt(strArrtmp[1])-1;
        int nam = Integer.parseInt(strArrtmp[2]);
        DatePickerDialog dialog = new DatePickerDialog(
                Main2Activity.this,
                callback, nam, thang, ngay);
        dialog.setTitle("Set Date");
        dialog.show();
    }
    private void playMusic() {
        mMediaPlayer = MediaPlayer.create(this, R.raw.wakeupalone);
        Button playButton = (Button)findViewById(R.id.btnYes);
        playButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Toast.makeText(Main2Activity.this, "Play the song", Toast.LENGTH_SHORT).show();
                mMediaPlayer.start();
                mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener(){
                    @Override
                    public void onCompletion(MediaPlayer mp){
                        Toast.makeText(Main2Activity.this, "I'm done", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });


        Button pauseButton = (Button)findViewById(R.id.btnPause);
        pauseButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Toast.makeText(Main2Activity.this, "Pause the song", Toast.LENGTH_SHORT).show();
                mMediaPlayer.pause();
            }
        });
    }

    @Override
    protected void onStop(){
        super.onStop();
        releaseMediaPlayer();
    }
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
        }
    }

    @Override
    public void getDefaultTime() {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat dfm = null;
        dfm=new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String strDate=dfm.format(c.getTime());
        txtDate.setText(strDate);

        dfm=new SimpleDateFormat("hh:mm a",Locale.getDefault());
        String strTime=dfm.format(c.getTime());
        txtTime.setText(strTime);
        //timeDefault.setText(strDate + " " + strTime);
        //lấy giờ theo 24 để lập trình theo Tag
        dfm=new SimpleDateFormat("HH:mm",Locale.getDefault());
        txtTime.setTag(dfm.format(c.getTime()));

        txtDateChoose.setText(strDate);
        txtTimeChoose.setText(strTime);
    }

    @Override
    public void getCurrentTime() {

    }

    private void showPictureDialog(){
        android.app.AlertDialog.Builder pictureDialog = new android.app.AlertDialog.Builder(this);
        pictureDialog.setTitle("Select Action");
        String[] pictureDialogItems = {
                "Take photo",
                "Choose photo" };
        pictureDialog.setItems(pictureDialogItems,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                dispatchTakePictureIntent();
                                break;
                            case 1:
                                choosePhotoFromGallary();
                                break;
                        }
                    }
                });
        pictureDialog.show();
    }
    //take a photo with camera app
    protected void dispatchTakePictureIntent() {
        mImageView = (ImageView) findViewById(R.id.cameraImport);
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }
    //Get the thumbnail
    //return Intent delivered to onActivityResult() as a small Bitmap in the extras, under the key "data".
    // The following code retrieves this image and displays it in an ImageView.
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            mImageView.setImageBitmap(imageBitmap);
            saveImage(imageBitmap);
        }else if(requestCode == REQUEST_GALLERY && resultCode == RESULT_OK){
            if (data != null) {
                //Get data from Uri
                Uri contentURI = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), contentURI);
                    //String path = saveImage(bitmap);
                    mImageView.setImageBitmap(bitmap);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public void choosePhotoFromGallary() {
        Intent mediaScanIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(mediaScanIntent, REQUEST_GALLERY);
    }

    public String saveImage(Bitmap myBitmap) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        myBitmap.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
        File wallpaperDirectory = new File(
                Environment.getExternalStorageDirectory() + IMAGE_DIRECTORY);
        // have the object build the directory structure, if needed.
        if (!wallpaperDirectory.exists()) {
            wallpaperDirectory.mkdirs();
        }

        try {
            File f = new File(wallpaperDirectory, Calendar.getInstance()
                    .getTimeInMillis() + ".jpg");
            f.createNewFile();
            FileOutputStream fo = new FileOutputStream(f);
            fo.write(bytes.toByteArray());
            MediaScannerConnection.scanFile(this,
                    new String[]{f.getPath()},
                    new String[]{"image/jpeg"}, null);
            fo.close();
            Log.d("TAG", "File Saved::--->" + f.getAbsolutePath());

            return f.getAbsolutePath();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return "";
    }
}
