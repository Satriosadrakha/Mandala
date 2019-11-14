package com.example.sprint2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Toast;

import com.example.sprint2.DatabaseHelper.DatabaseHelper;
import com.example.sprint2.Model.Character;
import com.google.firebase.auth.FirebaseAuth;

public class MenuActivity extends AppCompatActivity {
    DatabaseHelper db;
    private long backPressedTime;
    private static final String SELECTED_ITEM = "arg_selected_item";

    private BottomNavigationView mBottomNav;
    private int mSelectedItem;
    private Toolbar toolbar;

    int colorResolved;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        toolbar = (Toolbar) findViewById(R.id.tool_bar);
//
//        if (toolbar != null) {
//            setSupportActionBar(toolbar);
//        }

        pickColor();

        super.onCreate(savedInstanceState);
        setTitle( R.string.nav_beranda );
        setContentView(R.layout.activity_menu);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
//        String storedPreference = prefs.getString( "colorScheme","blue" );
//
//        switch(storedPreference) {
//            case "orange":
//                toolbar.setBackground( new ColorDrawable(getResources().getColor(R.color.colorOrange)) );
//                break;
//            case "blue":
//                toolbar.setBackground( new ColorDrawable(getResources().getColor(R.color.colorBlue)) );
//                break;
//            case "red":
//                toolbar.setBackground( new ColorDrawable(getResources().getColor(R.color.colorRed)) );
//                break;
//            case "grey":
//                toolbar.setBackground( new ColorDrawable(getResources().getColor(R.color.colorGrey)) );
//                break;
//            default:
//                toolbar.setBackground( new ColorDrawable(getResources().getColor(R.color.colorPrimary)) );
//        }

        if (!prefs.getBoolean("firstTime", false)) {
            db = new DatabaseHelper(this);
            String swara[] = getResources().getStringArray(R.array.swara);
            String ngalagena[] = getResources().getStringArray(R.array.ngalagena);

            for (int i=0; i<7; i++){
                db.createCharacter(new Character(swara[i]));
            }
            for (int i=0; i<23; i++){
                db.createCharacter(new Character(ngalagena[i]));
            }
            db.closeDB();

            // mark first time has ran.
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("firstTime", true);
            editor.commit();
        }

        mBottomNav = (BottomNavigationView) findViewById(R.id.navigation);
        mBottomNav.setOnNavigationItemSelectedListener((@NonNull MenuItem item)-> {
            selectFragment(item);
            return true;
        });
        /*mBottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                selectFragment(item);
                return true;
            }
        });*/
        MenuItem selectedItem;
        if (savedInstanceState != null) {
            mSelectedItem = savedInstanceState.getInt(SELECTED_ITEM, 0);
            selectedItem = mBottomNav.getMenu().findItem(mSelectedItem);
        } else {
            selectedItem = mBottomNav.getMenu().getItem(0);
        }
        selectFragment(selectedItem);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        switch (item.getItemId()) {
            case R.id.action_settings:
                Intent intentSetting = new Intent(MenuActivity.this, SettingActivity.class);
                startActivity(intentSetting );
                return true;
            case R.id.action_about_us:
                Intent intentAbout = new Intent(MenuActivity.this, AboutActivity.class);
                startActivity(intentAbout);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(SELECTED_ITEM, mSelectedItem);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onBackPressed() {

        if(backPressedTime + 2000 > System.currentTimeMillis()){
            moveTaskToBack(true);
            finish();
        } else{
            Toast.makeText( this, "Tekan kembali untuk selesai", Toast.LENGTH_SHORT ).show();
        }
        backPressedTime = System.currentTimeMillis();
    }

//    private void logoutUser() {
//        FirebaseAuth.getInstance().signOut();
//
//        // Launching the login activity
//        Intent intent = new Intent(MenuActivity.this, LoginActivity.class);
//        startActivity(intent);
//        finish();
//    }

    private void selectFragment(MenuItem item) {
        Fragment frag = null;
        // init corresponding fragment
        switch (item.getItemId()) {
            case R.id.menu_home:
                /*frag = MandalaFragment.newInstance(getString(R.string.text_home),
                        getColorFromRes(R.color.color_home));*/
                frag = new mLearnFragment();

                break;
            case R.id.menu_notifications:
                frag = mLearnFragment.newInstance(getString(R.string.text_notifications),
                        getColorFromRes(R.color.color_notifications));
                break;
            case R.id.menu_search:
                frag = mLearnFragment.newInstance(getString(R.string.text_search),
                        getColorFromRes(R.color.color_search));
                break;
        }
        // update selected item
        mSelectedItem = item.getItemId();

        // uncheck the other items.
        for (int i = 0; i< mBottomNav.getMenu().size(); i++) {
            MenuItem menuItem = mBottomNav.getMenu().getItem(i);
            menuItem.setChecked(menuItem.getItemId() == item.getItemId());
        }

        updateToolbarText(item.getTitle());

        if (frag != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.add(R.id.container, frag, frag.getTag());
            ft.commit();
        }
    }

    private void updateToolbarText(CharSequence text) {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(text);
        }
    }

    private int getColorFromRes(@ColorRes int resId) {
        return ContextCompat.getColor(this, resId);
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
