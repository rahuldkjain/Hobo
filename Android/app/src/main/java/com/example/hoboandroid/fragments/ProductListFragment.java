package com.example.hoboandroid.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hoboandroid.CONSTANTS;
import com.example.hoboandroid.R;
import com.example.hoboandroid.adapters.ProductItemAdapter;
import com.example.hoboandroid.models.ApiResponse;
import com.example.hoboandroid.models.merchantproduct.MerchantProductResponse;
import com.example.hoboandroid.models.product.Product;
import com.example.hoboandroid.models.product.ProductListItem;
import com.example.hoboandroid.services.MerchantService;
import com.example.hoboandroid.services.ProductService;
import com.example.hoboandroid.services.SearchService;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ProductListFragment extends Fragment{
    RecyclerView productRecyclerView;
    ProductItemAdapter productItemAdapter;
    List<Product> productList = new ArrayList<>();
    View failureView;
    ImageView failureViewImage;
    TextView failureViewText;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {





        return inflater.inflate(R.layout.fragment_category_product_recycler_view, container, false);
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //TextView subCatName = view.findViewById(R.id.category_name_fragment);


        Bundle bundle = getArguments();
        switch (bundle.getString("type")){
            case "SearchQuery":

                getSearchedProducts(bundle.getString("SearchQuery"));
                //subCatName.setText("Search Results for "+bundle.getString("SearchQuery"));

                break;
            case "SubCategory":
                Log.e("ProductListFragment","Inside Onview Created method "+bundle.getString("SubCategory"));
                getProducts(bundle.getString("SubCategory"));
                //subCatName.setText(bundle.getString("SubCategory"));

                break;
        }

        Log.e("ProductListFragment","Inside OnviewCreated method");


        productRecyclerView = view.findViewById(R.id.fragment_category_product_id);

        productItemAdapter = new ProductItemAdapter(productList);



        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        productRecyclerView.setLayoutManager(linearLayoutManager);



        FrameLayout frameLayout = view.findViewById(R.id.frame_fragment_cprc);

        failureView = ((ViewGroup)frameLayout).findViewById(R.id.product_list_failure);

        failureViewImage = failureView.findViewById(R.id.oops_image_view);
        failureViewText = failureView.findViewById(R.id.oops_text_view);



        //TODO create the adapter and notify the adapter when the network changes are done


    }

    public void getSearchedProducts(String searchQuery) {
        Log.d("ProductListFragment",searchQuery);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(CONSTANTS.SEARCH_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client( new OkHttpClient())
                .build();

        SearchService service = retrofit.create(SearchService.class);

        service.searchQuery(searchQuery).enqueue(new Callback<ApiResponse<JSONObject>>() {
            @Override
            public void onResponse(Call<ApiResponse<JSONObject>> call, Response<ApiResponse<JSONObject>> response) {
                Log.d("ProductListFragment","product list response "+response.body().toString());

                if(response.body() != null) {
                    Log.d("ProductListFragment","inside response and not null - "+response.body().toString());
                    /*if(productList.size()!= 0 ){
                        productRecyclerView.setAdapter(productItemAdapter);
                        productItemAdapter.notifyDataSetChanged();
                    }
                    else{
                        Toast.makeText(getContext(),"No items found.",Toast.LENGTH_LONG).show();
                        failureViewImage.setVisibility(View.VISIBLE);
                        failureViewText.setVisibility(View.VISIBLE);
                        failureViewText.setText(failureViewText.getText()+"! No items found");
                    }*/
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<JSONObject>> call, Throwable t) {

            }
        });






    }

    public void getProducts(String subcategory) {
        Log.e("ProductListFragment",subcategory);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(CONSTANTS.PRODUCT_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client( new OkHttpClient())
                .build();

        ProductService service = retrofit.create(ProductService.class);

        service.getProductsBySubCat(subcategory)
                .enqueue(new Callback<ApiResponse<List<Product>>>() {
                    @Override
                    public void onResponse(Call<ApiResponse<List<Product>>> call, Response<ApiResponse<List<Product>>> response) {

                        if(response.body() != null){

                            productList.addAll(response.body().getData());
                            Log.d("ProductListFragment","product list response body"+response.body().toString());

                            if(productList.size()!= 0 ){
                                productRecyclerView.setAdapter(productItemAdapter);
                                productItemAdapter.notifyDataSetChanged();
                            }
                            else{
                                Toast.makeText(getContext(),"No items found.",Toast.LENGTH_LONG).show();
                                failureViewImage.setVisibility(View.VISIBLE);
                                failureViewText.setVisibility(View.VISIBLE);
                                failureViewText.setText(failureViewText.getText()+"! No items found");
                            }

                        }


                    }


                    @Override
                    public void onFailure(Call<ApiResponse<List<Product>>> call, Throwable t) {
                        Toast.makeText(getContext(), "Check your connection", Toast.LENGTH_LONG).show();
                        Log.e("ProductListActivity", t.getMessage() + " failure");
                    }
                });




    }




}

