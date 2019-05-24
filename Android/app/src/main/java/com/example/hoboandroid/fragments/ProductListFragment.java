package com.example.hoboandroid.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.hoboandroid.R;
import com.example.hoboandroid.activities.ProductListActivity;
import com.example.hoboandroid.adapters.ProductAdapter;
import com.example.hoboandroid.models.Product;
import com.example.hoboandroid.services.ProductService;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ProductListFragment extends Fragment implements View.OnClickListener{
    RecyclerView productRecyclerView;
    ProductAdapter productAdapter;
    List<Product> productList;
    String type;




    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {





        return inflater.inflate(R.layout.activity_product_list, container, false);
    }

    public void search(){

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        productRecyclerView =view.findViewById(R.id.recyclerView);

        productAdapter = new ProductAdapter(productList);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        productRecyclerView.setLayoutManager(linearLayoutManager);


        productRecyclerView.setAdapter(productAdapter);

        productRecyclerView.setOnClickListener(this);


    }

    public void getSearchedProducts(String searchQuery) {

    }

    private void getProducts(String subcategory) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getString(R.string.product_api))
                .addConverterFactory(GsonConverterFactory.create())
                .client( new OkHttpClient())
                .build();

        ProductService service = retrofit.create(ProductService.class);

        service.getProrductsByCatAndSub(null,subcategory)
                .enqueue(new Callback<List<Product>>() {
                    @Override
                    public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {

                        if(response.body() != null){

                            productList.addAll(response.body());
                            productAdapter.notifyDataSetChanged();
                            Log.e("ProductListActivity",response.body().toString());

                            getRatings();

                        }


                    }


                    @Override
                    public void onFailure(Call<List<Product>> call, Throwable t) {
                        Toast.makeText(getContext(), "Check your connection", Toast.LENGTH_LONG).show();
                        Log.e("ProductListActivity", t.getMessage() + " failure");
                    }
                });




    }

    private void getRatings() {
    }

    @Override
    public void onClick(View v) {

    }
}

