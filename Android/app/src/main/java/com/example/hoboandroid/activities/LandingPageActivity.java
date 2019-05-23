package com.example.hoboandroid.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.hoboandroid.R;
import com.example.hoboandroid.adapters.CategoryAdapter;
import com.example.hoboandroid.fragments.CategoryFragment;
import com.example.hoboandroid.models.Category;
import com.example.hoboandroid.services.ProductService;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LandingPageActivity extends AppCompatActivity implements View.OnClickListener {

    List<Category> categoryList;
    CategoryAdapter categoryRecyclerViewAdapter;
    RecyclerView categoryRecyclerView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);


        categoryRecyclerView = findViewById(R.id.recyclerView);

        loadSlidingImages();

        getCategories();


    }


    private void getCategories() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(String.valueOf(R.string.category_api))
                .addConverterFactory(GsonConverterFactory.create())
                .client( new OkHttpClient())
                .build();

        ProductService service = retrofit.create(ProductService.class);

        service.getCategories()
                .enqueue(new Callback<List<Category>>() { // this enqueue method calls api asynchronously and success/error
                    @Override                              //hover over the enqueue method to check what this is
                    public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {

                        //List<Category> categoryList = new ArrayList<>();

                        if(response.body() != null){
                            boolean b = categoryList.addAll(response.body());
                            Log.d("HOBOLandingPage",response.body().toString());

                            categoryRecyclerViewAdapter = new CategoryAdapter(categoryList);

                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
                            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                            categoryRecyclerView.setLayoutManager(linearLayoutManager);


                            categoryRecyclerView.setAdapter(categoryRecyclerViewAdapter);
                            categoryRecyclerViewAdapter.notifyDataSetChanged();


                        }



                    } //even 404 response from api it's success here because the api is connected and responding

                    @Override
                    public void onFailure(Call<List<Category>> call, Throwable t) {
                        Toast.makeText(LandingPageActivity.this,"Check your connection",Toast.LENGTH_LONG).show();
                        Log.d("HOBOLandingPage",t.getMessage()+" failure");
                    }// happens when api is not able to be connect or getting any response(even a failure response is called a response)
                });





    }

    private void loadSlidingImages() {

    }

    @Override
    public void setContentView(int layoutResID)
    {
        //DrawerLayout fullView = (DrawerLayout) getLayoutInflater().inflate(R.layout.activity_base, null);
        //FrameLayout activityContainer = (FrameLayout) fullView.findViewById(R.id.landing_page_frame_layout);
        //getLayoutInflater().inflate(layoutResID, activityContainer, true);
        super.setContentView(layoutResID);
    }


    @Override
    public void onClick(final View view) {

        int itemPosition = categoryRecyclerView.getChildLayoutPosition(view);
        Category item = categoryList.get(itemPosition);
        Toast.makeText(LandingPageActivity.this, "A category is clicked", Toast.LENGTH_LONG).show();
        //opening a category page
        Intent intent = new Intent(LandingPageActivity.this,CategoryActivity.class);
        intent.putExtra("Category Object",item.getCategoryName());
        startActivity(intent);

    }
}
