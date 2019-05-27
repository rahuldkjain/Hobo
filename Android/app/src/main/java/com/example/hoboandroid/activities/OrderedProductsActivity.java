package com.example.hoboandroid.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.hoboandroid.CONSTANTS;
import com.example.hoboandroid.R;
import com.example.hoboandroid.adapters.OrderedProductAdapter;
import com.example.hoboandroid.models.ApiResponse;
import com.example.hoboandroid.models.OrderedProduct;
import com.example.hoboandroid.services.OrderService;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OrderedProductsActivity extends BaseActivity{

    RecyclerView orderedProductsRecyclerView;
    OrderedProductAdapter orderedProductsAdapter;
    List<OrderedProduct> orderedProductsList;
    int orderId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);

        if(isLoggedIn()) {
            orderId = getIntent().getIntExtra("OrderId", 1);

            orderedProductsRecyclerView = findViewById(R.id.recyclerView);

            orderedProductsAdapter = new OrderedProductAdapter(orderedProductsList);

            if(orderedProductsRecyclerView!= null) {

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                orderedProductsRecyclerView.setLayoutManager(linearLayoutManager);

                orderedProductsRecyclerView.setAdapter(orderedProductsAdapter);
            }
            //orderedProductsRecyclerView.setOnClickListener(this);


            //TODO login user's userId
            getProducts(orderId,getUserEmailId());

        }
        else    loginToContinue();



    }



    private void getProducts(int orderId,String emailId) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(CONSTANTS.ORDER_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client( new OkHttpClient())
                .build();

        OrderService orderService = retrofit.create(OrderService.class);



        //TODO login flag and session management

        orderService.getOrderDetails(orderId,""+emailId)
                .enqueue(new Callback<ApiResponse<List<OrderedProduct>>>() {

                    @Override
                    public void onResponse(Call<ApiResponse<List<OrderedProduct>>> call, Response<ApiResponse<List<OrderedProduct>>> response) {

                        //List<Category> categoryList = new ArrayList<>();

                        if(response.body() != null){
                            boolean b = orderedProductsList.addAll(response.body().getData());
                            Log.d("OrderedProductsActivity",response.body().toString()+" "+b);


                            orderedProductsAdapter.notifyDataSetChanged();

                        }
                    }




                    @Override
                    public void onFailure(Call<ApiResponse<List<OrderedProduct>>> call, Throwable t) {
                        Toast.makeText(OrderedProductsActivity.this,"Check your connection",Toast.LENGTH_LONG).show();
                        Log.d("OrderedProductsActivity",t.getMessage()+" failure");
                    }
                });



    }


}
