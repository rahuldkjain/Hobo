package com.example.hoboandroid.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.hoboandroid.CONSTANTS;
import com.example.hoboandroid.R;
import com.example.hoboandroid.adapters.OrderAdapter;
import com.example.hoboandroid.models.ApiResponse;
import com.example.hoboandroid.models.Order;
import com.example.hoboandroid.models.order.OrderData;
import com.example.hoboandroid.services.OrderService;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OrderHistoryActivity extends AppCompatActivity implements View.OnClickListener {

    RecyclerView orderHistoryRecyclerView;
    OrderAdapter orderAdapter;
    List<OrderData> orderList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_category);

        orderHistoryRecyclerView =findViewById(R.id.reusable_recycler_view);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        orderHistoryRecyclerView.setLayoutManager(linearLayoutManager);

        orderAdapter = new OrderAdapter(orderList);

        orderHistoryRecyclerView.setAdapter(orderAdapter);

        //to get all the orders belonging to the current user and then populate them in the orderHistoryRecyclerView
        getOrders();
    }

    private void getOrders() {
        Retrofit retrofit2 = new Retrofit.Builder()
                .baseUrl("http://172.16.20.84:8082/")
                .client(new OkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        OrderService orderService=retrofit2.create(OrderService.class);

        orderService.getOrders("email@gmail.com")
                .enqueue(new Callback<ApiResponse<List<OrderData>>>() {
                    @Override
                    public void onResponse(Call<ApiResponse<List<OrderData>>> call, Response<ApiResponse<List<OrderData>>> response2) {

                        //Log.e("In order","hello");
                        if (response2.body() != null) {
                            orderList.addAll(response2.body().getData());
                            Log.e("In order history", orderList.toString());

                            orderAdapter.notifyDataSetChanged();
                        }
                    }
                    @Override
                    public void onFailure(Call<ApiResponse<List<OrderData>>> call, Throwable t) {
                        Log.e("In history Fialure","Failed");
                    }
                });
    }

    @Override
    public void onClick(View view) {


        int itemPosition = orderHistoryRecyclerView.getChildLayoutPosition(view);
        OrderData orderItem = orderList.get(itemPosition);
        Toast.makeText(OrderHistoryActivity.this, "A Order is clicked for details", Toast.LENGTH_LONG).show();

        Intent intent = new Intent(OrderHistoryActivity.this,OrderedProductsActivity.class);
        intent.putExtra("OrderId",orderItem.getOrderId());
        startActivity(intent);

    }
}



