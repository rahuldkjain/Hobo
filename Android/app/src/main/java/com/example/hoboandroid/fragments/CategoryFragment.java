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

import com.example.hoboandroid.Api;
import com.example.hoboandroid.R;
import com.example.hoboandroid.activities.LandingPageActivity;
import com.example.hoboandroid.adapters.CategoryAdapter;
import com.example.hoboandroid.models.Category;
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


public class CategoryFragment extends Fragment {
    List<Category> itemsList = new ArrayList<>();
    RecyclerView recyclerView;
    CategoryAdapter categoryAdapter;

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

        categoryAdapter = new CategoryAdapter(itemsList);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);


        recyclerView.setAdapter(categoryAdapter);

        //TODO should request for categories and populate them on the recycler view
        getCategories();

    }

    private void getCategories() {

        Retrofit retrofit = Api.getclient("listcategory/");

        ProductService service = retrofit.create(ProductService.class);

        service.getCategories()
                .enqueue(new Callback<ResponseFromApi>() {
                    @Override
                    public void onResponse(Call<ResponseFromApi> call, Response<ResponseFromApi> response) {

                        if(response.body() != null){
                            itemsList.addAll(response.body().getData());
                            Log.d("HOBOLandingPage",response.body().toString());

                            categoryAdapter.notifyDataSetChanged();


                        }

                    }
                    @Override
                    public void onFailure(Call<ResponseFromApi> call, Throwable t) {
                        Toast.makeText(getContext(),"Check your connection",Toast.LENGTH_LONG).show();
                        Log.d("HOBOLandingPage",t.getMessage()+" failure");
                    }// happens when api is not able to be connect or getting any response(even a failure response is called a response)
                });





    }



}
