package com.example.hoboandroid.activities;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.hoboandroid.R;
import com.example.hoboandroid.adapters.ProductAdapter;
import com.example.hoboandroid.fragments.CategoryFragment;
import com.example.hoboandroid.fragments.ProductListFragment;
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

public class ProductListActivity extends AppCompatActivity {

    RecyclerView productRecyclerView;
    ProductAdapter productAdapter;
    List<Product> productList;
    boolean searchFlag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        Fragment fragment = new ProductListFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction().add(R.id.reusable_categories_xml, fragment);
        fragmentTransaction.commit();

        //based on which we should search
        String value = getAnyInput();
        if (value != null && !value.equals("")){
            searchFlag = false;
            getProducts(value);
        }
        else
            getProducts();
        productRecyclerView =findViewById(R.id.recyclerView);

        productAdapter = new ProductAdapter(productList);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ProductListActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        productRecyclerView.setLayoutManager(linearLayoutManager);


        productRecyclerView.setAdapter(productAdapter);

    }

    private String getAnyInput(){
        return getIntent().getStringExtra("SubCategoryName");
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
                        Toast.makeText(ProductListActivity.this, "Check your connection", Toast.LENGTH_LONG).show();
                        Log.e("ProductListActivity", t.getMessage() + " failure");
                    }
                });




    }

    private void getRatings() {
        for(Product product:productList){
            Retrofit ratingRetrofit = new Retrofit.Builder()
                    .baseUrl(getString(R.string.product_api))
                    .addConverterFactory(GsonConverterFactory.create())
                    .client( new OkHttpClient())
                    .build();

            MerchantService service = ratingRetrofit.create(MerchantService.class);

            /*

            service.getProductRating(product.getProductId())
                    .enqueue(new Callback<List<Product>>() { // this enqueue method calls api asynchronously and success/error
                        @Override                              //hover over the enqueue method to check what this is
                        public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {

                            if(response.body() != null){

                                productList.addAll(response.body());
                                productAdapter.notifyDataSetChanged(); // to notify the current recycler view we use recyclerView.ada
                                Log.e("ProductListActivity",response.body().toString());

                                getRatings();

                            }


                        } //even 404 response from api it's success here because the api is connected and responding



                        @Override
                        public void onFailure(Call<List<Product>> call, Throwable t) {
                            Toast.makeText(ProductListActivity.this,"Check your connection",Toast.LENGTH_LONG).show();
                            Log.e("ProductListActivity",t.getMessage()+" failure");
                        }// happens when api is not able to be connect or getting any response(even a failure response is called a response)
                    });
              */
        }
    }

    //TODO search function  using search
    void getProducts(){

    }
}
