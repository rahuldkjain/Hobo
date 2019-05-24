package com.example.hoboandroid.activities;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.hoboandroid.Api;
import com.example.hoboandroid.R;
import com.example.hoboandroid.adapters.CategoryAdapter;
import com.example.hoboandroid.adapters.ProductAdapter;
import com.example.hoboandroid.fragments.CategoryFragment;
import com.example.hoboandroid.models.Category;
import com.example.hoboandroid.models.Product;
import com.example.hoboandroid.models.ResponseFromApi;
import com.example.hoboandroid.services.ProductService;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LandingPageActivity extends BaseActivity implements View.OnClickListener {

    List<Product> productList = new ArrayList<>();
    ProductAdapter productRecyclerViewAdapter;
    RecyclerView productRecyclerView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_landing_page);

        Fragment fragment = new CategoryFragment();


        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction().add(R.id.landing_page_frame_layout,fragment);

        fragmentTransaction.commit();



        productRecyclerView = findViewById(R.id.landing_page_image_recycler_view);

        productRecyclerViewAdapter = new ProductAdapter(productList);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        productRecyclerView.setLayoutManager(linearLayoutManager);


        productRecyclerView.setAdapter(productRecyclerViewAdapter);

        Retrofit retrofit = Api.getclient("/product/getall/");

        ProductService service = retrofit.create(ProductService.class);

        service.getAllProducts()
                .enqueue(new Callback<ResponseFromApi>() {
                    @Override
                    public void onResponse(Call<ResponseFromApi> call, Response<ResponseFromApi> response) {

                        if(response.body() != null){
                            productList.addAll(response.body().getData());
                            Log.d("HOBOLandingPage",response.body().toString());

                            productRecyclerViewAdapter.notifyDataSetChanged();


                        }

                    }
                    @Override
                    public void onFailure(Call<ResponseFromApi> call, Throwable t) {
                        Toast.makeText(LandingPageActivity.this,"Check your connection",Toast.LENGTH_LONG).show();
                        Log.d("HOBOLandingPage",t.getMessage()+" failure");
                    }// happens when api is not able to be connect or getting any response(even a failure response is called a response)
                });








        //loadSlidingImages();

        //categoryList = new ArrayList<>();
        //categoryList.add(new Category());

        //getCategories();


    }


    private void loadSlidingImages() {

    }



    @Override
    public void onClick(View view) {

        //int itemPosition = categoryRecyclerView.getChildLayoutPosition(view);
        //Category item = categoryList.get(itemPosition);
        Toast.makeText(LandingPageActivity.this, "A category is clicked", Toast.LENGTH_LONG).show();
        //opening a category page
        Intent intent = new Intent(LandingPageActivity.this,CategoryActivity.class);
        //intent.putExtra("Category Object",item.getCategoryName());
        startActivity(intent);

    }
}
