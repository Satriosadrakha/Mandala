package com.example.sprint2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

public class SplashActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 1;
    private ImageView imageView;
    private Animation blink;
    private int intervalsplash = 3000;
    private long backPressedTime;
    int colorResolved;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        pickColor();
        super.onCreate(savedInstanceState);
        this.requestWindowFeature( Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);
        imageView = findViewById(R.id.news_icon);
        blink = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blink);
        imageView.startAnimation(blink);

        // start activity after some amount of time
        new Handler().postDelayed(() ->
                startActivity(new Intent(getApplicationContext()
                        ,MenuActivity.class)), Long.parseLong(String.valueOf(intervalsplash)));
        //new Handler().postDelayed(() ->
        //startActivity(new Intent(getApplicationContext(),
        //MainMenu.class)), Long.parseLong(String.valueOf(intervalsplash)));
    }


    public void onAnimationStart(Animation animation) {
        //TODO:
    }


    public void onAnimationEnd(Animation animation) {
        //TODO:
    }


    public void onAnimationRepeat(Animation animation) {
        //TODO:
    }

    @Override
    public void onBackPressed() {

        if(backPressedTime + 2000 > System.currentTimeMillis()){
            moveTaskToBack(true);
            finish();
        } else{
            Toast.makeText( this, "Tekan kembali untuk keluar aplikasi", Toast.LENGTH_SHORT ).show();
        }
        backPressedTime = System.currentTimeMillis();
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
