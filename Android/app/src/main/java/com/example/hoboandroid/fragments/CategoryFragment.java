package com.example.hoboandroid.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hoboandroid.Api;
import com.example.hoboandroid.R;
import com.example.hoboandroid.adapters.CategoryAdapter;
import com.example.hoboandroid.adapters.SubCategoryAdapter;
import com.example.hoboandroid.models.ApiResponse;
import com.example.hoboandroid.models.SubCategory;
import com.example.hoboandroid.models.category.Category;
import com.example.hoboandroid.services.ProductService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class CategoryFragment extends Fragment implements View.OnClickListener {
    List<SubCategory> itemsList = new ArrayList<>();
    RecyclerView recyclerView;
    SubCategoryAdapter subCategoryAdapter;




    public CategoryFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recycler_view, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        recyclerView = view.findViewById(R.id.recyclerView);

        subCategoryAdapter = new SubCategoryAdapter(itemsList);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);


        recyclerView.setAdapter(subCategoryAdapter);
        recyclerView.setOnClickListener(this);

        Bundle bundle = new Bundle();
        String category = bundle.getString("Category");

        Retrofit retrofit = Api.getclient(getResources().getString(R.string.product_host_address));

        ProductService productService = retrofit.create(ProductService.class);
        productService.getSubCategory(category).enqueue(new Callback<ApiResponse<List<SubCategory>>>() {
            @Override
            public void onResponse(Call<ApiResponse<List<SubCategory>>> call, Response<ApiResponse<List<SubCategory>>> response) {
                if(response.body() != null){

                    itemsList.addAll(response.body().getData());
                    subCategoryAdapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onFailure(Call<ApiResponse<List<SubCategory>>> call, Throwable t) {

            }
        });

        //TODO should request for categories and populate them on the recycler view
        getCategories();

    }

    private void getCategories() {



    }


    @Override
    public void onClick(View v) {
       // ProductListFragment productListFragment = new ProductListFragment();
       // FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction().add(R.id.base_activity_frame,v.)
    }
}
