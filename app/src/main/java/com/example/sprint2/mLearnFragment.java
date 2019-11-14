package com.example.sprint2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.widget.ImageViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class mLearnFragment extends Fragment {
    private String mText;
    private int mColor;

    private View mContent;
    private ScrollView mScrollView;

    public static final String LESSON_ID = "lessonID";

    public static Fragment newInstance(String text, int color) {
        Fragment frag = new mLearnFragment();
        Bundle args = new Bundle();
        frag.setArguments(args);
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_m_learn, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        ImageView profileImage;
        super.onViewCreated(view, savedInstanceState);

        // SqLite database handler
//        db = new DatabaseHelper(getActivity().getApplicationContext());

//        HashMap<String, String> user = db.getUserDetails();

//        int progress = user.get("progress");

        // initialize views
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String storedPreference = prefs.getString( "colorScheme","blue" );

        String colorStr;
        int colorResolved;
        switch(storedPreference) {
            case "orange":
                colorStr = "#" + Integer.toHexString(ContextCompat.getColor(getActivity(), R.color.colorOrange) & 0x00ffffff);
                colorResolved =  getResources().getColor(R.color.colorOrange);
                break;
            case "blue":
                colorStr = "#" + Integer.toHexString(ContextCompat.getColor(getActivity(), R.color.colorBlue) & 0x00ffffff);
                colorResolved =  getResources().getColor(R.color.colorBlue);
                break;
            case "red":
                colorStr = "#" + Integer.toHexString(ContextCompat.getColor(getActivity(), R.color.colorRed) & 0x00ffffff);
                colorResolved =  getResources().getColor(R.color.colorRed);
                break;
            case "grey":
                colorStr = "#" + Integer.toHexString(ContextCompat.getColor(getActivity(), R.color.colorGrey) & 0x00ffffff);
                colorResolved =  getResources().getColor(R.color.colorGrey);
                break;
            default:
                colorStr = "#" + Integer.toHexString(ContextCompat.getColor(getActivity(), R.color.colorPrimary) & 0x00ffffff);
                colorResolved =  getResources().getColor(R.color.colorPrimary);
        }

        mContent = view.findViewById(R.id.mLearnFragment);
        mScrollView = (ScrollView) view.findViewById(R.id.sView);

        if (storedPreference=="orange"){
            view.setBackgroundResource(R.drawable.bg6);
        } else {
            view.setBackgroundColor(getResources().getColor(R.color.white));;
        }


        final View aksara = view.findViewById(R.id.aksara);
        aksara.setOnClickListener((View v)->{
            Intent intent = new Intent(getActivity(), LessonPlaceholderActivity.class);
            startActivity(intent);
        });

        aksara.setBackgroundResource(R.drawable.tags_rounded_corners);
        GradientDrawable borderAksara = (GradientDrawable) aksara.getBackground();
        borderAksara.setStroke(4, colorResolved);

        Intent intent = new Intent(getActivity(), LessonActivity.class);

        final View basic1 = view.findViewById(R.id.basic_1);
        basic1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra(LESSON_ID, 0);
                startActivity(intent);
            }
        });

        basic1.setBackgroundResource(R.drawable.tags_rounded_corners);
        GradientDrawable borderBasic1 = (GradientDrawable) basic1.getBackground();
        borderBasic1.setStroke(4, colorResolved);

        final View basic2 = view.findViewById(R.id.basic_2);
        basic2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra(LESSON_ID, 1);
                //Toast.makeText(getApplicationContext(),String.valueOf(lessonID),Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });

        basic2.setBackgroundResource(R.drawable.tags_rounded_corners);
        GradientDrawable borderBasic2 = (GradientDrawable) basic2.getBackground();
        borderBasic2.setStroke(4, colorResolved);

        final View hewan = view.findViewById(R.id.hewan);
        hewan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra(LESSON_ID, 2);
                startActivity(intent);
            }
        });

        hewan.setBackgroundResource(R.drawable.tags_rounded_corners);
        GradientDrawable borderHewan = (GradientDrawable) hewan.getBackground();
        borderHewan.setStroke(4, colorResolved);

        final View warna = view.findViewById(R.id.warna);
        warna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra(LESSON_ID, 3);
                startActivity(intent);
            }
        });

        warna.setBackgroundResource(R.drawable.tags_rounded_corners);
        GradientDrawable borderWarna = (GradientDrawable) warna.getBackground();
        borderWarna.setStroke(4, colorResolved);

        final View bunga = view.findViewById(R.id.bunga);
        bunga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra(LESSON_ID, 4);
                startActivity(intent);
            }
        });

        bunga.setBackgroundResource(R.drawable.tags_rounded_corners);
        GradientDrawable borderBunga = (GradientDrawable) bunga.getBackground();
        borderWarna.setStroke(4, colorResolved);

        final View dapur = view.findViewById(R.id.dapur);
        dapur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra(LESSON_ID, 5);
                startActivity(intent);
            }
        });

        dapur.setBackgroundResource(R.drawable.tags_rounded_corners);
        GradientDrawable borderDapur = (GradientDrawable) dapur.getBackground();
        borderWarna.setStroke(4, colorResolved);

        // session manager
//        session = new SessionManager(getActivity().getApplicationContext());

        // Logout button click event

//        Button btnLogout = (Button) getActivity().findViewById(R.id.btnLogout);
//        btnLogout.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                logoutUser();
//            }
//        });
    }

    private void logoutUser() {
        FirebaseAuth.getInstance().signOut();

        Toast.makeText(getActivity().getApplicationContext(),"Log out telah berhasil", Toast.LENGTH_LONG).show();

        // Launching the login activity
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);
        getActivity().finish();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}