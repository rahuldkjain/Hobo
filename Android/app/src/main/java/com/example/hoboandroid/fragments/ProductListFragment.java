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
    ProductListItem productListItem;
    ProductItemAdapter productItemAdapter;
    List<Product> productList = new ArrayList<>();
    Product product;
    volatile List<ProductListItem> productListItems = new ArrayList<>();



    @Override
    public View onCreateView(LayoutInflater inflater,
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

        productItemAdapter = new ProductItemAdapter(productListItems);


        productRecyclerView.setAdapter(productItemAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        productRecyclerView.setLayoutManager(linearLayoutManager);





        //TODO create the adapter and notify the adapter when the network changes are done


    }

    public void getSearchedProducts(String searchQuery) {
        Log.d("ProductListFragment",searchQuery);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getResources().getString(R.string.search_port_address)+"search/")
                .addConverterFactory(GsonConverterFactory.create())
                .client( new OkHttpClient())
                .build();

        SearchService service = retrofit.create(SearchService.class);

        service.searchQuery(searchQuery).enqueue(new Callback<ApiResponse<List<Product>>>() {
            @Override
            public void onResponse(Call<ApiResponse<List<Product>>> call, Response<ApiResponse<List<Product>>> response) {
                if(response.body() != null) {
                    productList.addAll(response.body().getData());
                    Log.d("ProductListFragment","product list response body "+response.body().toString());

                    getOtherAttributes();
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<List<Product>>> call, Throwable t) {

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

                            getOtherAttributes();

                        }


                    }


                    @Override
                    public void onFailure(Call<ApiResponse<List<Product>>> call, Throwable t) {
                        Toast.makeText(getContext(), "Check your connection", Toast.LENGTH_LONG).show();
                        Log.e("ProductListActivity", t.getMessage() + " failure");
                    }
                });




    }

    private void getOtherAttributes() {

        Log.d("productlist   ", productList.toString());

        for(int i=0;i<productList.size();i++){
            product = productList.get(i);

            productListItem = new ProductListItem(
                    product.getProductId(),
                    product.getProductName(),
                    product.getProductImage().get(0));
            //productListItems.add(productListItem);

            //productListItems.add(productListItem);

            //To get the top rated product
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(CONSTANTS.MERCHANT_BASE_URL)
                    .client(new OkHttpClient())
                    .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                    .build();

            Log.e("ProductListFragment","getOtherAttributes "+CONSTANTS.MERCHANT_BASE_URL + "   "+product.getProductName());
            MerchantService merchantService =  retrofit.create(MerchantService.class);

            merchantService.getTopProductMerchant(product.getProductId())
                    .enqueue(new Callback<ApiResponse<MerchantProductResponse>>() {
                        @Override
                        public void onResponse(Call<ApiResponse<MerchantProductResponse>> call, Response<ApiResponse<MerchantProductResponse>> response) {
                            if(response.body() != null){
                                //Log.e("Inmerchant",response.body().getData().toString()+"");


                                productListItem.setProductPrice((int)Float.parseFloat(response.body().getData().getPrice()));
                                productListItem.setRating(Float.parseFloat(response.body().getData().getProductRating()));


                                productListItems.add(productListItem);


                                Log.e("Inside ",productListItem.toString());


                                productItemAdapter.notifyDataSetChanged();
                                //productMerchantName.setText(response1.body().getData().getMerchantId())
                            }
                        }
                        @Override
                        public void onFailure(Call<ApiResponse<MerchantProductResponse>> call, Throwable t) {
                            Toast.makeText(getContext(),"Check your connection in merchant",Toast.LENGTH_LONG).show();
                            Log.e("ProductListFragment",t.getMessage()+" failure");
                        }
                    });


            Log.e("ProductListFragment","list items" +productListItems.toString());


        }




    }



}

