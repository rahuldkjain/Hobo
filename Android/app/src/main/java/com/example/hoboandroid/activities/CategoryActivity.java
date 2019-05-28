package com.example.hoboandroid.activities;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.hoboandroid.CONSTANTS;
import com.example.hoboandroid.R;
import com.example.hoboandroid.adapters.SubCategoryAdapter;
import com.example.hoboandroid.models.ApiResponse;
import com.example.hoboandroid.models.SubCategory;
import com.example.hoboandroid.services.ProductService;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CategoryActivity extends BaseActivity implements View.OnClickListener {

    List<SubCategory> subCategoryList  = new ArrayList<>();
    SubCategoryAdapter subCategoryRecyclerViewAdapter;
    RecyclerView subCategoryRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_category);
        //getting the intent to retrieve attributes
        Intent intent = getIntent();
        String category = intent.getStringExtra("Category");
        subCategoryRecyclerView = findViewById(R.id.reusable_recycler_view);

        subCategoryRecyclerViewAdapter = new SubCategoryAdapter(subCategoryList);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        subCategoryRecyclerView.setLayoutManager(linearLayoutManager);


        subCategoryRecyclerView.setAdapter(subCategoryRecyclerViewAdapter);

        getSubCategories(category);



    }
    private void getSubCategories(String category) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(CONSTANTS.PRODUCT_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client( new OkHttpClient())
                .build();

        ProductService service = retrofit.create(ProductService.class);


        service.getSubCategory(category)
                .enqueue(new Callback<ApiResponse<List<SubCategory>>>() {

                    @Override                              //hover over the enqueue method to check what this is
                    public void onResponse(Call<ApiResponse<List<SubCategory>>> call, Response<ApiResponse<List<SubCategory>>> response) {

                        //List<Category> categoryList = new ArrayList<>();

                        if(response.body() != null){
                            subCategoryList.addAll(response.body().getData());


                            subCategoryRecyclerViewAdapter.notifyDataSetChanged();


                        }



                    } //even 404 response from api it's success here because the api is connected and responding

                    @Override
                    public void onFailure(Call<ApiResponse<List<SubCategory>>> call, Throwable t) {
                        Toast.makeText(getApplicationContext(),"Check your connection",Toast.LENGTH_LONG).show();
                        Log.d("HOBOLandingPage",t.getMessage()+" failure");
                    }// happens when api is not able to be connect or getting any response(even a failure response is called a response)
                });


    }

    @Override
    public void onClick(View view) {
        int itemPosition = subCategoryRecyclerView.getChildLayoutPosition(view);
        SubCategory item = subCategoryList.get(itemPosition);
        Toast.makeText(getApplicationContext(), "A Sub category is clicked", Toast.LENGTH_LONG).show();

        /*Fragment fragmentSearch = getSupportFragmentManager().findFragmentByTag("ProductsFragment");
        if (fragmentSearch != null) {

            Bundle bundle = new Bundle();
            bundle.putString("type","SubCategory");
            bundle.putString("SubCategory",item.getSubCategoryName());


            getSupportFragmentManager().beginTransaction()
                    *//*.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)*//*
                    .replace(R.id.reusable_categories_xml, fragmentSearch, "ProductsFragment").commit();

        }
        else{*/
        //opening a sub category activity
        Intent intent = new Intent(getApplicationContext(), ProductListActivity.class);
        intent.putExtra("type", "SubCategory");
        intent.putExtra("SubCategory", item.getSubCategoryName());
        view.getContext().startActivity(intent);
        // }
    }
}
