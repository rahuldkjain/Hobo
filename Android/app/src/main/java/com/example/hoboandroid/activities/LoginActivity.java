package com.example.hoboandroid.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.hoboandroid.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }


    final EditText username = findViewById(R.id.username);
    final EditText passwrd = findViewById(R.id.password);
    final Button login = findViewById(R.id.login);
    final ProgressBar loadingProgress = findViewById(R.id.loading);







}
