package com.example.hoboandroid.activities;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.hoboandroid.R;
import com.example.hoboandroid.fragments.ProductListFragment;

public class ProductListActivity extends AppCompatActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);



        Intent intent = getIntent();
        Bundle  bundle = new Bundle();

        //based on which we should search
        switch (intent.getStringExtra("type")){
            case "Search":
                bundle.putString("SearchQuery",intent.getStringExtra("SearchQuery"));
                break;
            case "SubCategory":
                bundle.putString("SubCategory",intent.getStringExtra("SubCategory"));
                break;
        }


        bundle.putString("type",intent.getStringExtra("type"));

        Fragment fragment = new ProductListFragment();
        fragment.setArguments(bundle);


        //FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction().add(R.id.product_list_fragment, fragment,"ProductListFragment");
        //fragmentTransaction.commit();

        //TODO how to give the query
        ((ProductListFragment) fragment).getSearchedProducts("Query");



    }

}
