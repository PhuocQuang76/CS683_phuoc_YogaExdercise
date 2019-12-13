package com.androiddev.termproject;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.androiddev.termproject.database.DBHelper;
import com.androiddev.termproject.database.project.favorite.FavoriteDbHelper;
import com.androiddev.termproject.database.project.yoga.YogaDbHelper;
import com.androiddev.termproject.dto.Yoga;


public class YogaLectureListDetailActivity extends AppCompatActivity {

    public static final String EXTRA_YOGA_POSITION = "yoga_class_id";
    Yoga yogaClass;
    EditText minuteEdit;
    DBHelper mDBHelper;
    FavoriteDbHelper mFavoriteDbHelper;
    int yogaListId;

    ImageView imageView;
    TextView textViewTitle;
    TextView textViewSummary;
    private TextView hyperLink;
    Spanned textLink;


    private class AddRoutineToFavoriteList extends AsyncTask<Integer, Void, Boolean> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Boolean doInBackground(Integer... params) {
            int routineId = params[0];
            int routineTime = params[1];

            try {
                mFavoriteDbHelper.addRoutineToFavorite(routineId, routineTime);
                return true;
            }catch (Exception e){
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean success) {
            super.onPostExecute(success);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstaceState){ ;
        super.onCreate(savedInstaceState);
        setContentView(R.layout.yoga_lecture_list_detail_activity);

        //Set the toolbar as the activity's app bar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        mFavoriteDbHelper = FavoriteDbHelper.get(this);

       //Get yoga class position and id
        int yogaListPosition = (Integer) getIntent().getExtras().get(EXTRA_YOGA_POSITION);
        yogaClass = YogaDbHelper.get(getApplicationContext()).getYogaRoutine(yogaListPosition);

        //Get yogaClass Id
        yogaListId = yogaClass.getId();

        //get layout information
        int yogaClassImage = yogaClass.getImageId();

        imageView = (ImageView) findViewById(R.id.detail_image);
        textViewTitle = (TextView) findViewById(R.id.detail_title);
        textViewSummary = (TextView) findViewById(R.id.detail_summary);
        hyperLink = (TextView) findViewById(R.id.watch_video_yoga);

        imageView.setImageDrawable(ContextCompat.getDrawable(this, yogaClassImage));
        textViewTitle.setText(yogaClass.getTitle());
        textViewSummary.setText(yogaClass.getSummary());

        String linkText = yogaClass.getLinks();
        textLink = Html.fromHtml(linkText);
        hyperLink.setMovementMethod(LinkMovementMethod.getInstance());
        hyperLink.setText(textLink);


        minuteEdit = (EditText) findViewById(R.id.input_minute);

        Button addList = (Button) findViewById(R.id.addToList_button);

        //Set Listener to the "add List" Button
        addList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String minuteInputedString;
                int minuteInputedInteger;
                minuteInputedString = minuteEdit.getText().toString().trim();

                if (minuteInputedString.length() == 0){
                    minuteInputedInteger = 0;
                }else {
                    minuteInputedInteger = Integer.parseInt(minuteInputedString);
                }

                if(minuteInputedInteger <= 0){
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Input minute you want to play", Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }else if(minuteInputedInteger > 0){

                    AddRoutineToFavoriteList addRoutineToFavoriteList = new AddRoutineToFavoriteList();
                    addRoutineToFavoriteList.execute(yogaListId, minuteInputedInteger);

                    //Send intent to ket UI go back to the AppTabsActivity
                    Intent intent = new Intent(YogaLectureListDetailActivity.this, AppTabsActivity.class);
                    startActivity(intent);
                }
            }
        });


    }


}

