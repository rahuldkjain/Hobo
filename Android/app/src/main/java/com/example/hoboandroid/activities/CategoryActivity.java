package com.example.hoboandroid.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.hoboandroid.R;
import com.example.hoboandroid.adapters.CategoryAdapter;
import com.example.hoboandroid.adapters.SubCategoryAdapter;
import com.example.hoboandroid.models.Category;
import com.example.hoboandroid.models.SubCategory;
import com.example.hoboandroid.services.ProductService;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CategoryActivity extends AppCompatActivity implements View.OnClickListener {

    List<SubCategory> subCategoryList;
    SubCategoryAdapter subCategoryRecyclerViewAdapter;
    RecyclerView subCategoryRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_category);
        //getting the intent to retrieve attributes
        Intent intent = getIntent();
        String category = intent.getStringExtra("Category Object");

        subCategoryRecyclerViewAdapter = new SubCategoryAdapter(subCategoryList);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        subCategoryRecyclerView.setLayoutManager(linearLayoutManager);


        subCategoryRecyclerView.setAdapter(subCategoryRecyclerViewAdapter);

        getSubCategories(category);


    }
    private void getSubCategories(String category) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(String.valueOf(R.string.category_api))
                .addConverterFactory(GsonConverterFactory.create())
                .client( new OkHttpClient())
                .build();

        ProductService service = retrofit.create(ProductService.class);


        service.getSubCategory(category)
                .enqueue(new Callback<List<SubCategory>>() {

                    @Override                              //hover over the enqueue method to check what this is
                    public void onResponse(Call<List<SubCategory>> call, Response<List<SubCategory>> response) {

                        //List<Category> categoryList = new ArrayList<>();

                        if(response.body() != null){
                            boolean b = subCategoryList.addAll(response.body());
                            Log.d("HOBOLandingPage",response.body().toString()+" "+b);


                            subCategoryRecyclerViewAdapter.notifyDataSetChanged();


                        }



                    } //even 404 response from api it's success here because the api is connected and responding

                    @Override
                    public void onFailure(Call<List<SubCategory>> call, Throwable t) {
                        Toast.makeText(CategoryActivity.this,"Check your connection",Toast.LENGTH_LONG).show();
                        Log.d("HOBOLandingPage",t.getMessage()+" failure");
                    }// happens when api is not able to be connect or getting any response(even a failure response is called a response)
                });


    }

    @Override
    public void onClick(View view) {
        int itemPosition = subCategoryRecyclerView.getChildLayoutPosition(view);
        SubCategory item = subCategoryList.get(itemPosition);
        Toast.makeText(CategoryActivity.this, "A Sub category is clicked", Toast.LENGTH_LONG).show();



        //opening a sub category activity
        Intent intent = new Intent(CategoryActivity.this,ProductListActivity.class);
        intent.putExtra("Sub Category Object",item.getSubCategoryName());
        startActivity(intent);
    }
}
