package com.example.hoboandroid.activities;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.hoboandroid.R;
import com.example.hoboandroid.adapters.ProductAdapter;
import com.example.hoboandroid.fragments.CategoryFragment;
import com.example.hoboandroid.fragments.ProductListFragment;
import com.example.hoboandroid.fragments.SearchProductListFragment;
import com.example.hoboandroid.models.Product;
import com.example.hoboandroid.services.MerchantService;
import com.example.hoboandroid.services.ProductService;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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


        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction().add(R.id.product_list_fragment, fragment,"ProductListFragment");
        fragmentTransaction.commit();

        //TODO how to give the query
        ((ProductListFragment) fragment).getSearchedProducts("Query");



    }

}
