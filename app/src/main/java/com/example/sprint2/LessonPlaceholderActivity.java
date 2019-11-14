package com.example.sprint2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.GradientDrawable;
import android.preference.PreferenceManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class LessonPlaceholderActivity extends AppCompatActivity {

    int colorResolved;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        pickColor();
        super.onCreate( savedInstanceState );
        setTitle( "Aksara Sunda" );
        setContentView( R.layout.activity_lesson_placeholder );

        //int categoryId = getIntent().getIntExtra("CATEGORY_ID", 0);

        GradientDrawable border;

        Button intro = (Button) findViewById(R.id.intro);
        intro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), IntroActivity.class);
                startActivity(intent);
            }
        });
        intro.setBackgroundResource(R.drawable.tags_rounded_corners);
        border = (GradientDrawable) intro.getBackground();
        border.setStroke(4, colorResolved);

        final Intent intent = new Intent(getBaseContext(), AksaraLessonActivity.class);

        Button swara = (Button) findViewById(R.id.swara);
        swara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("LESSON_ID", 0);
                startActivity(intent);
            }
        });
        swara.setBackgroundResource(R.drawable.tags_rounded_corners);
        border = (GradientDrawable) swara.getBackground();
        border.setStroke(4, colorResolved);

        Button button1 = (Button) findViewById(R.id.kaganga1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("LESSON_ID", 1);
                startActivity(intent);
            }
        });
        button1.setBackgroundResource(R.drawable.tags_rounded_corners);
        border = (GradientDrawable) button1.getBackground();
        border.setStroke(4, colorResolved);

        Button button2 = (Button) findViewById(R.id.kaganga2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("LESSON_ID", 2);
                startActivity(intent);
            }
        });
        button2.setBackgroundResource(R.drawable.tags_rounded_corners);
        border = (GradientDrawable) button2.getBackground();
        border.setStroke(4, colorResolved);

        Button button3 = (Button) findViewById(R.id.kaganga3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("LESSON_ID", 3);
                startActivity(intent);
            }
        });
        button3.setBackgroundResource(R.drawable.tags_rounded_corners);
        border = (GradientDrawable) button3.getBackground();
        border.setStroke(4, colorResolved);

        Button button4 = (Button) findViewById(R.id.kaganga4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("LESSON_ID", 4);
                startActivity(intent);
            }
        });
        button4.setBackgroundResource(R.drawable.tags_rounded_corners);
        border = (GradientDrawable) button4.getBackground();
        border.setStroke(4, colorResolved);

        Button button5 = (Button) findViewById(R.id.kaganga5);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("LESSON_ID", 5);
                startActivity(intent);
            }
        });
        button5.setBackgroundResource(R.drawable.tags_rounded_corners);
        border = (GradientDrawable) button5.getBackground();
        border.setStroke(4, colorResolved);

        Button button6 = (Button) findViewById(R.id.kaganga6);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("LESSON_ID", 6);
                startActivity(intent);
            }
        });
        button6.setBackgroundResource(R.drawable.tags_rounded_corners);
        border = (GradientDrawable) button6.getBackground();
        border.setStroke(4, colorResolved);

        Button button7 = (Button) findViewById(R.id.kaganga7);
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("LESSON_ID", 7);
                startActivity(intent);
            }
        });
        button7.setBackgroundResource(R.drawable.tags_rounded_corners);
        border = (GradientDrawable) button7.getBackground();
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
                Intent intentSetting = new Intent(LessonPlaceholderActivity.this, SettingActivity.class);
                startActivity(intentSetting );
                return true;
            case R.id.action_about_us:
                Intent intentAbout = new Intent(LessonPlaceholderActivity.this, AboutActivity.class);
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
