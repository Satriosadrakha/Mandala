package com.example.sprint2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.GradientDrawable;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class LessonActivity extends AppCompatActivity {
    public static final String EXTRA_CATEGORY_ID = "extraCategoryID";
    public static final String EXTRA_CATEGORY_NAME = "extraCategoryName";

    String title;
    int colorResolved;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        pickColor();
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_lesson );

        Intent intent = getIntent();

        int lessonID = intent.getIntExtra(mLearnFragment.LESSON_ID,0);

//        Toast.makeText(getApplicationContext(),String.valueOf(lessonID),Toast.LENGTH_SHORT).show();

        TextView title1 = (TextView)findViewById( R.id.title1 );
        TextView title2 = (TextView)findViewById( R.id.title2 );
        TextView subTitle1 = (TextView)findViewById( R.id.subTitle1 );
        TextView subTitle2 = (TextView)findViewById( R.id.subTitle2 );

        subTitle1.setText( getResources().getStringArray( R.array.button_lesson )[lessonID] );
        subTitle2.setText( getResources().getStringArray( R.array.button_lesson )[lessonID+1] );
        title = getResources().getStringArray( R.array.nav_lesson )[lessonID];

        title1.setText( getResources().getString( R.string.button_lesson_title1 ));
        title2.setText( getResources().getString( R.string.button_lesson_title2 ));
        setTitle( title );



        GradientDrawable border;

        LinearLayout tips = findViewById( R.id.tips );
        tips.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LessonActivity.this, LearningTipsActivity.class);
                intent.putExtra( EXTRA_CATEGORY_ID, lessonID);
                startActivity( intent );
            }
        } );
        tips.setBackgroundResource(R.drawable.tags_rounded_corners);
        tips.setPadding( 0, 20, 0, 20 );
        border = (GradientDrawable) tips.getBackground();
        border.setStroke(4, colorResolved);

        LinearLayout buttonStartQuiz1 = findViewById( R.id.lesson1 );
        buttonStartQuiz1.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LessonActivity.this, StartQuizActivity.class);
                intent.putExtra( EXTRA_CATEGORY_ID, (lessonID+1)*2-1);
                intent.putExtra( EXTRA_CATEGORY_NAME, String.valueOf( getResources().getStringArray(R.array.categoryDisplay)[(lessonID+1)*2-2]));

                startActivity( intent );
            }
        } );
        buttonStartQuiz1.setBackgroundResource(R.drawable.tags_rounded_corners);
        border = (GradientDrawable) buttonStartQuiz1.getBackground();
        border.setStroke(4, colorResolved);

        LinearLayout buttonStartQuiz2 = findViewById( R.id.lesson2 );
        buttonStartQuiz2.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LessonActivity.this, StartQuizActivity.class);
                intent.putExtra( EXTRA_CATEGORY_ID, (lessonID+1)*2);
                intent.putExtra( EXTRA_CATEGORY_NAME, String.valueOf( getResources().getStringArray(R.array.categoryDisplay)[(lessonID+1)*2-1]));
                //StartQuizActivity.REQUEST_CODE_QUIZ = 2;
                startActivity( intent );
            }
        } );
        buttonStartQuiz2.setBackgroundResource(R.drawable.tags_rounded_corners);
        border = (GradientDrawable) buttonStartQuiz2.getBackground();
        border.setStroke(4, colorResolved);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                Intent intentSetting = new Intent(LessonActivity.this, SettingActivity.class);
                startActivity(intentSetting );
                return true;
            case R.id.action_about_us:
                Intent intentAbout = new Intent(LessonActivity.this, AboutActivity.class);
                startActivity(intentAbout);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void pickColor(){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String storedPreference = prefs.getString( "colorScheme","blue" );

        switch(storedPreference) {
            case "orange":
                colorResolved =  getResources().getColor(R.color.colorOrange);
                setTheme( R.style.AppTheme_Orange );
                break;
            case "blue":
                colorResolved =  getResources().getColor(R.color.colorBlue);
                setTheme( R.style.AppTheme_Blue );
                break;
            case "red":
                colorResolved =  getResources().getColor(R.color.colorRed);
                setTheme( R.style.AppTheme_Red );
                break;
            case "grey":
                colorResolved =  getResources().getColor(R.color.colorGrey);
                setTheme( R.style.AppTheme_Grey );
                break;
            default:
                colorResolved =  getResources().getColor(R.color.colorPrimary);
                setTheme( R.style.AppTheme );

        }
    }
}
