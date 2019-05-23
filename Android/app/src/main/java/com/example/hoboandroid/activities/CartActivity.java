package com.example.hoboandroid.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.hoboandroid.R;
import com.example.hoboandroid.adapters.CartItemAdapter;
import com.example.hoboandroid.adapters.SubCategoryAdapter;
import com.example.hoboandroid.models.Cart;
import com.example.hoboandroid.models.SubCategory;

import java.util.List;

public class CartActivity extends AppCompatActivity implements View.OnClickListener {

    List<Cart> cartItemsList;
    CartItemAdapter cartItemAdapter;
    RecyclerView cartRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        cartItemAdapter = new CartItemAdapter(cartItemsList);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        cartRecyclerView.setLayoutManager(linearLayoutManager);


        cartRecyclerView.setAdapter(cartItemAdapter);


    }

    @Override
    public void onClick(View v) {

    }
}
