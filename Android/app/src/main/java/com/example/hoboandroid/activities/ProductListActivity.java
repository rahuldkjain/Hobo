package com.example.hoboandroid.activities;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.hoboandroid.R;
import com.example.hoboandroid.adapters.ProductItemAdapter;
import com.example.hoboandroid.fragments.ProductListFragment;
import com.example.hoboandroid.models.product.Product;
import com.example.hoboandroid.models.product.ProductListItem;

import java.util.ArrayList;
import java.util.List;

public class ProductListActivity extends BaseActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_category);



        Intent intent = getIntent();

        Bundle bundle = new Bundle();
        bundle.putString("type",intent.getStringExtra("type"));
        ProductListFragment productsFragment = new ProductListFragment();
        //based on which we should search
        switch (intent.getStringExtra("type")){
            case "SearchQuery":
                bundle.putString("SearchQuery",intent.getStringExtra("SearchQuery"));
                break;
            case "SubCategory":
                //bundle.putString("Category",intent.getStringExtra("Category"));
                bundle.putString("SubCategory",intent.getStringExtra("SubCategory"));
                break;
        }


        productsFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction()
                /*.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)*/
                .add(R.id.reusable_categories_xml, productsFragment, "ProductsFragment").commit();





        //FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction().add(R.id.product_list_fragment, fragment,"ProductListFragment");
        //fragmentTransaction.commit();

        //TODO how to give the query


    }

}
