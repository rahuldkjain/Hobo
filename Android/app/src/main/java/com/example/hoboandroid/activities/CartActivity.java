package com.example.hoboandroid.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.ArrayMap;
import android.util.Log;
import android.view.View;

import com.example.hoboandroid.Api;
import com.example.hoboandroid.CONSTANTS;
import com.example.hoboandroid.R;
import com.example.hoboandroid.adapters.CartItemAdapter;
import com.example.hoboandroid.adapters.SubCategoryAdapter;
import com.example.hoboandroid.models.ApiResponse;
import com.example.hoboandroid.models.Cart;
import com.example.hoboandroid.models.SubCategory;
import com.example.hoboandroid.models.cart.CartItem;
import com.example.hoboandroid.services.CartService;
import com.example.hoboandroid.services.OrderService;

import org.json.JSONObject;

import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CartActivity extends BaseActivity implements View.OnClickListener {

    List<CartItem> cartItemsList;
    CartItemAdapter cartItemAdapter;
    RecyclerView cartRecyclerView;
    CartItem cartItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        Retrofit retrofit = Api.getclient(CONSTANTS.ORDER_BASE_URL);

        OrderService orderService = retrofit.create(OrderService.class);


        if(getIntent().getBundleExtra("AddToCart") != null){
            Bundle bundle = getIntent().getBundleExtra("AddToExtra");

            if(isLoggedIn()) {
                Map<String, Object> jsonParams = new ArrayMap<>();
                jsonParams.put("userEmail", getUserEmailId());
                jsonParams.put("productId", bundle.getInt("ProductId"));
                jsonParams.put("merchantId", bundle.getString("MerchantId"));
                jsonParams.put("productImage", bundle.getString("ProductImage"));
                jsonParams.put("productPrice", bundle.getInt("ProductPrice"));

                RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), (new JSONObject(jsonParams)).toString());

                orderService.createCartItem(body).enqueue(new Callback<ApiResponse<CartItem>>() {
                    @Override
                    public void onResponse(Call<ApiResponse<CartItem>> call, Response<ApiResponse<CartItem>> response) {
                        if (response.body() != null && response.body().getData() != null) {
                            Log.d("CartActivity", response.body().getData().toString());
                        }
                    }

                    @Override
                    public void onFailure(Call<ApiResponse<CartItem>> call, Throwable t) {
                        //TODO add code to handle the failure no response
                        Log.d("CartActivity", "Response Failure");
                    }
                });
            }

            //TODO  in else part do guest cart, how to store item here and how to retreive the past items



        }

        cartItemAdapter = new CartItemAdapter(cartItemsList);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        cartRecyclerView.setLayoutManager(linearLayoutManager);


        // cartRecyclerView.setAdapter(cartItemAdapter); //Commented the setting because it tells to the recycler to display something from list

        //TODO now load cart items from user or from guest phone storage and store in the list
                                                                    //Adapter.notifyDataSetChanged


        if(isLoggedIn()){
            //TODO check backend url endpoint & variable name if it matches the get cart
            orderService.getCartItems(getUserEmailId()).enqueue(new Callback<ApiResponse<List<CartItem>>>() {
                @Override
                public void onResponse(Call<ApiResponse<List<CartItem>>> call, Response<ApiResponse<List<CartItem>>> response) {

                    Log.d("CartActivity",response.toString());
                    if(response.body() == null){
                        //TODO handle null body
                    }
                    else if(response.body().getData() != null && response.body().getData().size()!=0){


                        cartItemsList.addAll(response.body().getData());
                        Log.d("CartActivity","CartItems list "+cartItemsList.toString());
                        cartRecyclerView.setAdapter(cartItemAdapter);


                    }
                    else {
                        //TODO handle empty response list
                    }

                }

                @Override
                public void onFailure(Call<ApiResponse<List<CartItem>>> call, Throwable t) {

                }
            });
        }



    }

    @Override
    public void onClick(View v) {

    }
}
