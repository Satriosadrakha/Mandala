package com.example.sprint2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class LearningTipsActivity extends AppCompatActivity {

    int colorResolved;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        pickColor();
        super.onCreate( savedInstanceState );
        setTitle( "Tips Belajar" );
        setContentView( R.layout.activity_learning_tips );

        LinearLayout tips = (LinearLayout)findViewById( R.id.tips );
        TextView title = (TextView)findViewById( R.id.title );
        TextView content = (TextView)findViewById( R.id.content);
        TableLayout table = (TableLayout)findViewById( R.id.table );
        table.setBackgroundColor( colorResolved );

        Intent intent = getIntent();

        int lesson_id = intent.getIntExtra(LessonActivity.EXTRA_CATEGORY_ID,0);

        GradientDrawable border;
        tips.setBackgroundResource(R.drawable.tags_rounded_corners);
        border = (GradientDrawable) tips.getBackground();
        border.setStroke(20, colorResolved);

        title.setText(
                String.valueOf( getResources().getStringArray(R.array.learningTipsTitle)[lesson_id])
        );

        content.setText(
                String.valueOf( getResources().getStringArray(R.array.learningTipsContent)[lesson_id])
        );

        if(lesson_id!=0){
            int i = 0;
            boolean header = false;
            while (true){
                try{
                    /*
                     * 1st row = TextView
                     */
                    TableRow tr = new TableRow(this);
                    LinearLayout outerLayout = new LinearLayout(this);
                    outerLayout.setBackgroundColor( getResources().getColor( R.color.white )  );

                    //Add Text
                    TextView text1 = new TextView(this);
                    TextView text2 = new TextView(this);
                    text1.setTextColor( Color.BLACK );
                    text2.setTextColor( Color.BLACK );

                    int weight1 = 1;
                    int weight2 = 1;

                    switch(lesson_id) {
                        case 0:
                            // code block
                            break;
                        case 1:
                            text1.setText(String.valueOf( getResources().getStringArray(R.array.percakapanNama)[i]));
                            text2.setText(String.valueOf( getResources().getStringArray(R.array.percakapanSunda)[i]));
                            text2.append( "\n(" + getResources().getStringArray( R.array.percakapanIndonesia )[i] );
                            text2.append( ")" );
                            weight1 = 5;
                            weight2 = 1;
                            break;
                        case 2:
                            text1.setText(String.valueOf( getResources().getStringArray(R.array.hewanIndonesia)[i]));
                            text2.setText(String.valueOf( getResources().getStringArray(R.array.hewanSunda)[i]));
                            break;
                        case 3:
                            text1.setText(String.valueOf( getResources().getStringArray(R.array.warnaIndonesia)[i]));
                            text2.setText(String.valueOf( getResources().getStringArray(R.array.warnaSunda)[i]));
                            break;
                        case 4:
                            text1.setText(String.valueOf( getResources().getStringArray(R.array.bungaIndonesia)[i]));
                            text2.setText(String.valueOf( getResources().getStringArray(R.array.bungaSunda)[i]));
                            break;
                        case 5:
                            text1.setText(String.valueOf( getResources().getStringArray(R.array.dapurIndonesia)[i]));
                            text2.setText(String.valueOf( getResources().getStringArray(R.array.dapurSunda)[i]));
                            break;
                        default:
                            // code block
                    }

                    if(header == false){
                        text1.setText("Bahasa Indonesia");
                        text2.setText("Bahasa Sunda");
                        text1.setTypeface(null, Typeface.BOLD);
                        text2.setTypeface(null, Typeface.BOLD);
                        header = true;
                        i--;
//                    continue;
                    }

                    LinearLayout.LayoutParams textLayoutParams1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                    textLayoutParams1.weight = weight1;
                    textLayoutParams1.setMargins(20, 20, 20, 20);

                    LinearLayout.LayoutParams textLayoutParams2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                    textLayoutParams2.weight = weight2;
                    textLayoutParams2.setMargins(20, 20, 20, 20);

                    //Add Image
//        ImageView picture = new ImageView(this);
//        LinearLayout.LayoutParams pictureLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//        picture.setImageResource(R.drawable.a);
//        pictureLayoutParams.setMargins(20, 0, 0, 0);

                    View lineVertical = new View(this);
                    TableRow.LayoutParams separatorLayoutParamsV = new TableRow.LayoutParams(2, TableRow.LayoutParams.MATCH_PARENT);
                    separatorLayoutParamsV.setMargins(20, 0, 20, 0);
                    lineVertical.setBackgroundColor( colorResolved);

                    outerLayout.addView(text1, textLayoutParams1);
                    outerLayout.addView(lineVertical, separatorLayoutParamsV);
                    outerLayout.addView(text2, textLayoutParams2);
//        outerLayout.addView(picture, pictureLayoutParams);
                    outerLayout.setWeightSum( 2 );
//                    outerLayout.setLayoutParams(new LinearLayout.LayoutParams( LinearLayout.LayoutParams.MATCH_PARENT, 500));
                    tr.addView(outerLayout);

                    /*
                     * 2nd row = View (separator)
                     */
                    TableRow separator = new TableRow(this);
                    View line = new View(this);
                    TableRow.LayoutParams separatorLayoutParams = new TableRow.LayoutParams(400, 2);
                    separatorLayoutParams.setMargins(0, 0, 0, 0);
                    line.setBackgroundColor( colorResolved);
                    separator.addView(line, separatorLayoutParams);

                    table.addView(tr);
                    table.addView(separator);

                    i++;

                }catch (Exception e){
                    break;
                }
            }
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
