package com.example.hoboandroid.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.example.hoboandroid.R;
import com.example.hoboandroid.adapters.OrderAdapter;
import com.example.hoboandroid.adapters.OrderedProductAdapter;
import com.example.hoboandroid.models.Category;
import com.example.hoboandroid.models.Order;
import com.example.hoboandroid.models.OrderedProduct;
import com.example.hoboandroid.services.OrderService;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OrderedProductsActivity extends AppCompatActivity implements View.OnClickListener {

    RecyclerView orderedProductsRecyclerView;
    OrderedProductAdapter orderedProductsAdapter;
    List<OrderedProduct> orderedProductsList;
    String orderId = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);

        Intent intent = getIntent();
        if(intent != null)
            orderId  = intent.getStringExtra("OrderId");

        orderedProductsRecyclerView =findViewById(R.id.recyclerView);

        orderedProductsAdapter = new OrderedProductAdapter(orderedProductsList);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(OrderedProductsActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        orderedProductsRecyclerView.setLayoutManager(linearLayoutManager);


        orderedProductsRecyclerView.setAdapter(orderedProductsAdapter);

        orderedProductsRecyclerView.setOnClickListener(this);




        //TODO login user's userId
        getProducts(orderId,"userId");


    }



    private void getProducts(String orderId,String userId) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(String.valueOf(R.string.ordered_api))
                .addConverterFactory(GsonConverterFactory.create())
                .client( new OkHttpClient())
                .build();

        OrderService service = retrofit.create(OrderService.class);



        //TODO login flag and session management

        service.getOrderDetails(Integer.parseInt(orderId),Integer.parseInt(userId))
                .enqueue(new Callback<List<OrderedProduct>>() {

                    @Override
                    public void onResponse(Call<List<OrderedProduct>> call, Response<List<OrderedProduct>> response) {

                        //List<Category> categoryList = new ArrayList<>();

                        if(response.body() != null){
                            boolean b = orderedProductsList.addAll(response.body());
                            Log.d("HOBOLandingPage",response.body().toString()+" "+b);


                            orderedProductsAdapter.notifyDataSetChanged();

                        }
                    }




                    @Override
                    public void onFailure(Call<List<OrderedProduct>> call, Throwable t) {
                        Toast.makeText(OrderedProductsActivity.this,"Check your connection",Toast.LENGTH_LONG).show();
                        Log.d("HOBOLandingPage",t.getMessage()+" failure");
                    }
                });



    }

    @Override
    public void onClick(View view) {
        int itemPosition = orderedProductsRecyclerView.getChildLayoutPosition(view);
        OrderedProduct item = orderedProductsList.get(itemPosition);
        Toast.makeText(OrderedProductsActivity.this, "A Ordered Product is clicked", Toast.LENGTH_LONG).show();
        //opening a category page
        Intent intent = new Intent(OrderedProductsActivity.this,CategoryActivity.class);
        //TODO check the objects inserted into database and retrieved here
        intent.putExtra("Ordered Product Object",item.getProductId());
        startActivity(intent);
    }
}
