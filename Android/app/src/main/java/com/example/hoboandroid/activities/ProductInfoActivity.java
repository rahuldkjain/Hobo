package com.example.hoboandroid.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.hoboandroid.R;

public class ProductInfoActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_info);

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(ProductInfoActivity.this,)
    }
}
