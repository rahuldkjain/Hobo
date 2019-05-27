package com.example.hoboandroid.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hoboandroid.Api;
import com.example.hoboandroid.R;
import com.example.hoboandroid.adapters.CategoryAdapter;
import com.example.hoboandroid.adapters.LandingPageProductAdapter;
import com.example.hoboandroid.models.ApiResponse;
import com.example.hoboandroid.models.category.Category;
import com.example.hoboandroid.models.category.ResponseCategory;
import com.example.hoboandroid.models.product.Product;
import com.example.hoboandroid.models.product.ResponseProductsList;
import com.example.hoboandroid.services.ProductService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LandingPageActivity extends BaseActivity implements View.OnClickListener {

    List<Product> productList = new ArrayList<>();
    LandingPageProductAdapter productRecyclerViewAdapter;
    RecyclerView productRecyclerView;

    List<Category> categoryList = new ArrayList<>();
    RecyclerView categoryRecyclerView;
    CategoryAdapter categoryAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_landing_page);

        getProducts();

        //loadSlidingImages();

        //categoryList = new ArrayList<>();
        //categoryList.add(new Category());

        getCategories();


    }

    private void getCategories() {

        categoryRecyclerView = findViewById(R.id.landing_page_category_recycler_view);

        categoryAdapter = new CategoryAdapter(categoryList);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        categoryRecyclerView.setLayoutManager(linearLayoutManager);


        categoryRecyclerView.setAdapter(categoryAdapter);


        Retrofit retrofit = Api.getclient("http://172.16.20.80:8080/","product/listcategory/");

        ProductService service = retrofit.create(ProductService.class);

        service.getCategories()
                .enqueue(new Callback<ApiResponse<List<Category>>>() {
                    @Override
                    public void onResponse(Call<ApiResponse<List<Category>>> call, Response<ApiResponse<List<Category>>> response) {

                        if(response.body() != null){
                            categoryList.addAll(response.body().getData());
                            Log.d("HOBOLandingPage",response.body().toString());

                            categoryAdapter.notifyDataSetChanged();


                        }

                    }
                    @Override
                    public void onFailure(Call<ApiResponse<List<Category>>> call, Throwable t) {
                        Toast.makeText(LandingPageActivity.this,"Check your connection",Toast.LENGTH_LONG).show();
                        Log.d("HOBOLandingPage",t.getMessage()+" failure");
                    }// happens when api is not able to be connect or getting any response(even a failure response is called a response)
                });




    }

    private void getProducts() {

        productRecyclerView = findViewById(R.id.landing_page_image_recycler_view);

        productRecyclerViewAdapter = new LandingPageProductAdapter(productList);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);


        productRecyclerView.setLayoutManager(linearLayoutManager);
        productRecyclerView.setOnClickListener(this);


        productRecyclerView.setAdapter(productRecyclerViewAdapter);

        Retrofit retrofit = Api.getclient(getResources().getString(R.string.product_host_address),"/product/getall/");

        ProductService service = retrofit.create(ProductService.class);

        service.getAllProducts()
                .enqueue(new Callback<ApiResponse<List<Product>>>() {
                    @Override
                    public void onResponse(Call<ApiResponse<List<Product>>> call, Response<ApiResponse<List<Product>>> response) {

                        if(response.body() != null){
                            productList.addAll(response.body().getData());
                            Log.d("HOBOLandingPage",response.body().toString());

                            productRecyclerViewAdapter.notifyDataSetChanged();


                        }

                    }
                    @Override
                    public void onFailure(Call<ApiResponse<List<Product>>> call, Throwable t) {
                        Toast.makeText(LandingPageActivity.this,"Check your connection",Toast.LENGTH_LONG).show();
                        Log.d("HOBOLandingPage",t.getMessage()+" failure");
                    }// happens when api is not able to be connect or getting any response(even a failure response is called a response)
                });

    }



    @Override
    public void onClick(View view) {


        //TODO differentiate for category activity and product activity
        //int itemPosition = categoryRecyclerView.getChildLayoutPosition(view);
        //Category item = categoryList.get(itemPosition);


    }
    @Override
    public void onBackPressed()
    {

        if(getSupportFragmentManager().getBackStackEntryCount() > 0)
            getSupportFragmentManager().popBackStack();
        else
            super.onBackPressed();
    }
}
