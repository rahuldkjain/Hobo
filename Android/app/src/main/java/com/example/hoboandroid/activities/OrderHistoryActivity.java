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
    List<Order> orderList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);

        orderHistoryRecyclerView =findViewById(R.id.recyclerView);

        orderAdapter = new OrderAdapter(orderList);

     /*   LinearLayoutManager linearLayoutManager = new LinearLayoutManager(OrderHistoryActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        orderHistoryRecyclerView.setLayoutManager(linearLayoutManager);
*/

        orderHistoryRecyclerView.setAdapter(orderAdapter);
        
        
        //to get all the orders belonging to the current user and then populate them in the orderHistoryRecyclerView
        getOrders();
    }

    private void getOrders() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(CONSTANTS.ORDER_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client( new OkHttpClient())
                .build();

        OrderService service = retrofit.create(OrderService.class);



        //TODO login flag and session management

        service.getOrders("aman@gmail.com")
                .enqueue(new Callback<ApiResponse<List<Order>>>() {
                    @Override
                    public void onResponse(Call<ApiResponse<List<Order>>> call, Response<ApiResponse<List<Order>>> response) {

                    }

                    @Override
                    public void onFailure(Call<ApiResponse<List<Order>>> call, Throwable t) {

                    }
                });



    }

    @Override
    public void onClick(View view) {


        int itemPosition = orderHistoryRecyclerView.getChildLayoutPosition(view);
        Order orderItem = orderList.get(itemPosition);
        Toast.makeText(OrderHistoryActivity.this, "A Order is clicked for details", Toast.LENGTH_LONG).show();

        Intent intent = new Intent(OrderHistoryActivity.this,OrderedProductsActivity.class);
        //intent.putExtra("OrderId",orderItem.get());
        startActivity(intent);

    }
}
