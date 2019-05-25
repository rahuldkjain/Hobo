package com.example.hoboandroid.fragments;

import android.content.Context;
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
import android.widget.SearchView;
import android.widget.Toast;

import com.example.hoboandroid.Api;
import com.example.hoboandroid.R;
import com.example.hoboandroid.adapters.LandingPageProductAdapter;
import com.example.hoboandroid.models.ApiResponse;
import com.example.hoboandroid.models.Merchant;
import com.example.hoboandroid.models.MerchantProduct;
import com.example.hoboandroid.models.product.Product;
import com.example.hoboandroid.models.product.ProductListItem;
import com.example.hoboandroid.services.MerchantService;
import com.example.hoboandroid.services.ProductService;
import com.example.hoboandroid.services.SearchService;

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
    LandingPageProductAdapter landingPageProductAdapter;
    List<Product> productList = new ArrayList<>();
    List<ProductListItem> productListItems = new ArrayList<>();



    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {





        return inflater.inflate(R.layout.activity_product_list, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        productRecyclerView = view.findViewById(R.id.products_recycler_view);
        landingPageProductAdapter = new LandingPageProductAdapter(productList);

        productRecyclerView.setAdapter(landingPageProductAdapter);

        productRecyclerView.setOnClickListener(this);



    }

    public void getSearchedProducts(String searchQuery) {
        Log.d("ProductListFragment",searchQuery);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getResources().getString(R.string.search_port_address)+"search/")
                .addConverterFactory(GsonConverterFactory.create())
                .client( new OkHttpClient())
                .build();

        SearchService service = retrofit.create(SearchService.class);

        service.searchQuery(searchQuery).enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                productList.addAll(response.body());
                landingPageProductAdapter.notifyDataSetChanged();
                Log.e("ProductListActivity",response.body().toString());

                getOtherAttributes();
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

            }
        });




    }

    public void getProducts(String subcategory) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getResources().getString(R.string.product_host_address)+"product/category/")
                .addConverterFactory(GsonConverterFactory.create())
                .client( new OkHttpClient())
                .build();

        ProductService service = retrofit.create(ProductService.class);

        service.getProductsByCatAndSub(null,subcategory)
                .enqueue(new Callback<List<Product>>() {
                    @Override
                    public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {

                        if(response.body() != null){

                            productList.addAll(response.body());
                            landingPageProductAdapter.notifyDataSetChanged();
                            Log.e("ProductListActivity",response.body().toString());

                            getOtherAttributes();

                        }


                    }


                    @Override
                    public void onFailure(Call<List<Product>> call, Throwable t) {
                        Toast.makeText(getContext(), "Check your connection", Toast.LENGTH_LONG).show();
                        Log.e("ProductListActivity", t.getMessage() + " failure");
                    }
                });




    }

    private void getOtherAttributes() {

        for(final Product product: productList){

            final ProductListItem productListItem = new ProductListItem(
                    product.getProductId(),
                    product.getProductName(),
                    product.getProductImage().get(0));

            //To get the top rated product
            Retrofit retrofit = Api.getclient(getResources().getString(R.string.merchant_host_address),"/merchantproduct/topproductmerchant/");

            final MerchantService merchantService =  retrofit.create(MerchantService.class);

            merchantService.getTopMerchant(Integer.parseInt(product.getProductId())).enqueue(new Callback<ApiResponse<Merchant>>() {
                @Override
                public void onResponse(Call<ApiResponse<Merchant>> call, Response<ApiResponse<Merchant>> response) {
                    if(response.body() != null){
                        String merchantId = response.body().getData().getMerchantId();

                        //to get the price and rating for the product
                        Retrofit priceRetrofit = Api.getclient(getResources().getString(R.string.merchant_host_address),"/merchantproduct/topproductmerchant/");
                        merchantService.getMerchantProduct(merchantId,product.getProductId()).enqueue(new Callback<ApiResponse<MerchantProduct>>() {
                            @Override
                            public void onResponse(Call<ApiResponse<MerchantProduct>> call, Response<ApiResponse<MerchantProduct>> response) {
                                if(response.body() != null) {
                                        float merchantId = response.body().getData().getProductRating();
                                        int price = response.body().getData().getPrice();

                                        productListItem.setRating(merchantId);
                                        productListItem.setProductPrice(price);


                                        productListItems.add(productListItem);

                                    }

                                }

                            @Override
                            public void onFailure(Call<ApiResponse<MerchantProduct>> call, Throwable t) {

                            }
                        });

                    }

                }

                @Override
                public void onFailure(Call<ApiResponse<Merchant>> call, Throwable t) {

                }
            });



        }
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(hidden){
            //TODO what happens when fragment goes hidden

        }else{


        }
    }
}

