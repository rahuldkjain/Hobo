package com.example.hoboandroid.activities;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.hoboandroid.R;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView(View view) {
        DrawerLayout fullView = (DrawerLayout) getLayoutInflater().inflate(R.layout.activity_landing_page, null);
        //FrameLayout activityContainer = (FrameLayout) fullView.findViewById(R.id.activity_content);
        //getLayoutInflater().inflate(layoutResID, activityContainer, true);
        super.setContentView(fullView);
    }
}
