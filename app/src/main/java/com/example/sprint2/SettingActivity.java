package com.example.sprint2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.Locale;

public class SettingActivity extends AppCompatActivity {

    int colorResolved;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        pickColor();
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_setting );
        setTitle( "Setting" );

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        RadioButton rbId = (RadioButton)findViewById( R.id.radioButtonId );
        RadioButton rbEn = (RadioButton)findViewById( R.id.radioButtonEn );
        RadioButton rbNl = (RadioButton)findViewById( R.id.radioButtonNl );

        RadioButton rbDefault = (RadioButton)findViewById( R.id.radioButtonDefault );
        RadioButton rbBlue = (RadioButton)findViewById( R.id.radioButtonBlue );
        RadioButton rbRed = (RadioButton)findViewById( R.id.radioButtonRed );
        RadioButton rbGray = (RadioButton)findViewById( R.id.radioButtonGray );

        rbId.setId(0);
        rbEn.setId(1);
        rbNl.setId(2);

        rbDefault.setId(0);
        rbBlue.setId(1);
        rbRed.setId(2);
        rbGray.setId(3);


        RadioGroup lang = (RadioGroup) findViewById(R.id.lang);
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radio);

        Button applyColor = (Button)findViewById( R.id.applyColor );

        applyColor.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedLang = lang.getCheckedRadioButtonId();
                int selectedId = radioGroup.getCheckedRadioButtonId();
                SharedPreferences.Editor editor = prefs.edit();

                switch(selectedId) {
                    case 0:
                        editor.putString("colorScheme", "orange");
                        break;
                    case 1:
                        editor.putString("colorScheme", "blue");
                        break;
                    case 2:
                        editor.putString("colorScheme", "red");
                        break;
                    case 3:
                        editor.putString("colorScheme", "gray");
                        break;
                    default:
                        editor.putString("colorScheme", "default");
                }
                editor.commit();

                switch (selectedLang) {
//                    case 0:
//                        setLocale("id");
//                        break;
                    case 1:
                        setLocale("en");
                        break;
                    case 2:
                        setLocale("nl");
//                        Locale locale3 = new Locale("nl");
//                        Locale.setDefault(locale3);
//                        Configuration config3 = new Configuration();
//                        config3.locale = locale3;
//                        getBaseContext().getResources().updateConfiguration(config3, getBaseContext().getResources().getDisplayMetrics());
//
//                        Toast.makeText(SettingActivity.this, "Locale in Netherland !", Toast.LENGTH_LONG).show();
                        break;
                    default:
                        setLocale("id");
                        break;
                }
//                Intent intent = new Intent(SettingActivity.this, MenuActivity.class);
//                startActivity(intent);
            }
        } );
    }

    public void setLocale(String lang) {
        Locale myLocale = new Locale(lang);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
        Toast.makeText(SettingActivity.this, "Locale: " + lang, Toast.LENGTH_LONG).show();
        Intent refresh = new Intent(this, MenuActivity.class);
        finish();
        startActivity(refresh);
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
