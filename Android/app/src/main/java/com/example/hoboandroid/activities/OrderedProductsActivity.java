package com.example.hoboandroid.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.hoboandroid.R;
import com.example.hoboandroid.adapters.OrderAdapter;
import com.example.hoboandroid.models.Order;
import com.example.hoboandroid.services.OrderService;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OrderedProductsActivity extends AppCompatActivity {

    RecyclerView orderedProductsRecyclerView;
    OrderAdapter orderedProductsAdapter;
    List<Order> orderedProductsList;
    String orderId = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);

        Intent intent = getIntent();
        if(intent != null)
            orderId  = intent.getStringExtra("OrderId");

        orderedProductsRecyclerView =findViewById(R.id.recyclerView);

        orderedProductsAdapter = new OrderAdapter(orderedProductsList);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(OrderedProductsActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        orderedProductsRecyclerView.setLayoutManager(linearLayoutManager);


        orderedProductsRecyclerView.setAdapter(orderedProductsAdapter);


        //TODO login user's userId
        getProducts(orderId,"user");


    }

    private void getProducts(String orderId,String userId) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(String.valueOf(R.string.ordered_api))
                .addConverterFactory(GsonConverterFactory.create())
                .client( new OkHttpClient())
                .build();

        OrderService service = retrofit.create(OrderService.class);



        //TODO login flag and session management

        service.getOrders("userId")
                .enqueue(new Callback<List<Order>>() {

                    @Override
                    public void onResponse(Call<List<Order>> call, Response<List<Order>> response) {

                        //List<Category> categoryList = new ArrayList<>();

                        if(response.body() != null){
                            boolean b = orderedProductsList.addAll(response.body());
                            Log.d("HOBOLandingPage",response.body().toString()+" "+b);


                            orderedProductsAdapter.notifyDataSetChanged();

                        }
                    }




                    @Override
                    public void onFailure(Call<List<Order>> call, Throwable t) {
                        Toast.makeText(OrderedProductsActivity.this,"Check your connection",Toast.LENGTH_LONG).show();
                        Log.d("HOBOLandingPage",t.getMessage()+" failure");
                    }
                });



    }
}
