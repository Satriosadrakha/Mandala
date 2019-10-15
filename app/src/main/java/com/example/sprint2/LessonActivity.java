package com.example.sprint2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class LessonActivity extends AppCompatActivity {
    public static final String EXTRA_CATEGORY_ID = "extraCategoryID";
    public static final String EXTRA_CATEGORY_NAME = "extraCategoryName";

    String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_lesson );

        Intent intent = getIntent();

        int lessonID = intent.getIntExtra(mLearnFragment.LESSON_ID,0);

//        Toast.makeText(getApplicationContext(),String.valueOf(lessonID),Toast.LENGTH_SHORT).show();

        TextView title1 = (TextView)findViewById( R.id.title1 );
        TextView title2 = (TextView)findViewById( R.id.title2 );
        TextView subTitle1 = (TextView)findViewById( R.id.subTitle1 );
        TextView subTitle2 = (TextView)findViewById( R.id.subTitle2 );
        if(lessonID==0){
            subTitle1.setText( "Menerjemahkan\nAksara Sunda -> Aksara Latin\nSatu suku kata" );
            subTitle2.setText( "Menerjemahkan\nAksara Sunda -> Aksara Latin\nDua suku kata" );
            title = "Pelajaran Dasar 1";
        } else if (lessonID==1){
            subTitle1.setText( "Menerjemahkan\nBahasa Sunda -> Bahasa Indonesia" );
            subTitle2.setText( "Menerjemahkan\nBahasa Indonesia -> Bahasa Sunda" );
            title = "Pelajaran Dasar 2";
        } else if (lessonID==2){
            subTitle1.setText( "Menerjemahkan\nBahasa Indonesia <-> Bahasa Sunda" );
            subTitle2.setText( "Menerjemahkan\nAksara Sunda <-> Aksara Latin" );
            title = "Pelajaran mengenai hewan";
        } else if (lessonID==3){
            subTitle1.setText( "Menerjemahkan\nBahasa Indonesia <-> Bahasa Sunda" );
            subTitle2.setText( "Menerjemahkan\nAksara Sunda <-> Aksara Latin" );
            title = "Pelajaran mengenai warna";
        }
        title1.setText( "Pelajaran 1");
        title2.setText( "Pelajaran 2");
        setTitle( title );

        LinearLayout tips = findViewById( R.id.tips );
        tips.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LessonActivity.this, LearningTipsActivity.class);
                intent.putExtra( EXTRA_CATEGORY_ID, lessonID);
                startActivity( intent );
            }
        } );

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
    }


}
