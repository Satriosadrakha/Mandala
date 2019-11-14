package com.example.sprint2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.media.MediaPlayer;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sprint2.DatabaseHelper.DatabaseHelper;
import com.example.sprint2.Model.Character;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AksaraLessonActivity extends AppCompatActivity {
    private ListView lv;
    public static ArrayList<Character> modelArrayList;
    private CustomAdapter customAdapter;

    DatabaseHelper db;

    String[] listviewTitle;
    int[] listviewImage;
    String[] listviewShortDescription;
    int colorResolved;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        pickColor();
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_aksara_lesson );

        db = new DatabaseHelper(this);

        int lessonId = getIntent().getIntExtra("LESSON_ID", 0);
        Log.d("LessonID", String.valueOf( lessonId ) );

        setTitle( "Pelajaran " + (lessonId + 1) );

        List<Character> character = new ArrayList<>(  );

        List<Character> chara = new ArrayList<Character>();
        chara = db.getAllCharacters();
        for(Character characterchara : chara) {
            Log.d("List", characterchara.getSunda());
        }

        Log.d("Char", db.getCharacterSunda(1) );

        int listnum = 3;
        int start;
        switch (lessonId) {
            case 0:
                start = 1;
                listnum = 7;
                modelArrayList = getModel(start, listnum);
                break;
            case 1:
                start = 8;
                modelArrayList = getModel(start, listnum);
                break;
            case 2:
                start = 11;
                modelArrayList = getModel(start, listnum);
                break;
            case 3:
                start = 14;
                modelArrayList = getModel(start, listnum);
                break;
            case 4:
                start = 17;
                modelArrayList = getModel(start, listnum);
                break;
            case 5:
                start = 20;
                modelArrayList = getModel(start, listnum);
                break;
            case 6:
                start = 23;
                modelArrayList = getModel(start, listnum);
                break;
            case 7:
                listnum = 5;
                start = 26;
                modelArrayList = getModel(start, listnum);
                break;
        }

        TextView aksaraLessontext1 = (TextView)findViewById(R.id.textView1);
        TextView aksaraLessontext2 = (TextView)findViewById(R.id.textView2);

        String[] text1 = getResources().getStringArray(R.array.aksaraLesson1);
        String[] text2 = getResources().getStringArray(R.array.aksaraLesson2);

        aksaraLessontext1.setText(text1[lessonId]);
        aksaraLessontext2.setText(text2[lessonId]);

        lv = (ListView) findViewById(R.id.list_row);

        customAdapter = new CustomAdapter(this);
        lv.setAdapter(customAdapter);
    }

    private ArrayList<Character> getModel(int start, int listnum){
        ArrayList<Character> list = new ArrayList<>();
        listviewTitle = new String[listnum];
        listviewImage = new int[listnum];
        listviewShortDescription = new String[listnum];
        for (int i=start; i<start+listnum; i++){
            listviewTitle[i-start] = db.getCharacter( i ).getSunda();
            if(i==2){
                listviewImage[i-start] = getResources().getIdentifier( "e_", "drawable", getPackageName());
            } else{
                listviewImage[i-start] = getResources().getIdentifier( listviewTitle[i-start], "drawable", getPackageName());
            }
            listviewShortDescription[i-start] = String.valueOf( getResources().getStringArray(R.array.shortdesc)[i-1] );

            Log.d("Title", listviewTitle[i-start]);
            Log.d("Image", String.valueOf( listviewImage[i-start] ));

            Character model = new Character();
            model.setSunda(listviewTitle[i-start]);
            model.setContoh(listviewShortDescription[i-start]);
            model.setImage(listviewImage[i-start]);
            model.setSound( i );

            list.add(model);
        }
        return list;
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
                Intent intentSetting = new Intent(this, SettingActivity.class);
                startActivity(intentSetting );
                return true;
            case R.id.action_about_us:
                Intent intentAbout = new Intent(this, AboutActivity.class);
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
