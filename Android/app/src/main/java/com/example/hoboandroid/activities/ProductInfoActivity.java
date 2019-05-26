package com.example.hoboandroid.activities;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.ArrayMap;
import android.view.View;

import com.example.hoboandroid.R;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.example.hoboandroid.Api;
import com.example.hoboandroid.R;
import com.example.hoboandroid.models.ApiResponse;
import com.example.hoboandroid.models.Merchant;
import com.example.hoboandroid.models.MerchantProduct;
import com.example.hoboandroid.models.Order;
import com.example.hoboandroid.models.merchantproduct.MerchantProductResponse;
import com.example.hoboandroid.models.order.OrderMe;
import com.example.hoboandroid.models.order.OrderProductMe;
import com.example.hoboandroid.models.product.Product;
import com.example.hoboandroid.services.MerchantService;
import com.example.hoboandroid.services.OrderService;
import com.example.hoboandroid.services.ProductService;

import org.json.JSONObject;

import java.util.List;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import static okhttp3.logging.HttpLoggingInterceptor.Level.BODY;
public class ProductInfoActivity extends AppCompatActivity implements View.OnClickListener{


    List<MerchantProduct> merchantProductObject;
    Integer merchantid,productId;
    ImageView productImage;
    TextView productDesc,productName,productAttributes,productPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_info);
        //Retrofit retrofit= Api.getclient("http://172.16.20.80:8080/","product/");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://172.16.20.80:8080/")
                .client(new OkHttpClient())
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();
        ProductService service=retrofit.create(ProductService.class);


        productImage = findViewById(R.id.productInfoImage);
        productDesc = findViewById(R.id.productDescription2);
        productName = findViewById(R.id.productInfoName);
        productAttributes = findViewById(R.id.attributes2);
        productPrice = findViewById(R.id.productInfoPrice);

        productId =  getIntent().getIntExtra("Product",1);


        service.getProductById(productId)
                .enqueue(new Callback<ApiResponse<Product>>() {
                    @Override
                    public void onResponse(Call<ApiResponse<Product>> call, Response<ApiResponse<Product>> response) {
                        //Log.e("Hello","hi");
                        //Log.e("Hello",response.message());
                        if(response.body() != null){
                            Log.d("ProductInfoPage",response.body().toString());
                            productName.setText(response.body().getData().getProductName());
                            productDesc.setText(response.body().getData().getDescription());
                            productAttributes.setText(response.body().getData().getAttributes().getColor());
                            Glide.with(productImage.getContext())
                                    .load(response.body().getData().getProductImage().get(1))
                                    .into((productImage));
                        }
                    }
                    @Override
                    public void onFailure(Call<ApiResponse<Product>> call, Throwable t) {
                        Toast.makeText(ProductInfoActivity.this,"Check your connection",Toast.LENGTH_LONG).show();
                        Log.e("HOBOLandingPage",t.getMessage()+" failure");
                    }
                });
        Retrofit retrofit1 = new Retrofit.Builder()
                .baseUrl("http://172.16.20.101:8080/")
                .client(new OkHttpClient())
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();

        MerchantService service1=retrofit1.create(MerchantService.class);
        Log.d("LandingPageActivity","Sub "+getIntent().getStringExtra("Product"));
        service1.getTopProductMerchant(productId)
                .enqueue(new Callback<ApiResponse<MerchantProductResponse>>() {
                    @Override
                    public void onResponse(Call<ApiResponse<MerchantProductResponse>> call, Response<ApiResponse<MerchantProductResponse>> response1) {
                        if(response1.body() != null){
                            Log.e("Inmerchant",response1.body().getData().getPrice()+"");
                            productPrice.setText(response1.body().getData().getPrice());
                            merchantid = Integer.parseInt(response1.body().getData().getMerchantId());
                            //productMerchantName.setText(response1.body().getData().getMerchantId())
                        }
                    }
                    @Override
                    public void onFailure(Call<ApiResponse<MerchantProductResponse>> call, Throwable t) {
                        Toast.makeText(ProductInfoActivity.this,"Check your connection in merchant",Toast.LENGTH_LONG).show();
                        Log.e("HOBOLandingPage",t.getMessage()+" failure");
                    }
                });




        /*Retrofit retrofit2 = new Retrofit.Builder()
                .baseUrl("http://172.16.20.84:8082/orders/")
                .client(new OkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        OrderService orderService = retrofit2.create(OrderService.class);
        orderService.getOrderById(1)
                .enqueue(new Callback<ApiResponse<Order>>() {
            @Override
            public void onResponse(Call<ApiResponse<Order>> call, Response<ApiResponse<Order>> response) {
                Log.e("ProductInfoActivity",response.body().getData().toString());
            }

            @Override
            public void onFailure(Call<ApiResponse<Order>> call, Throwable t) {
                Log.e("ProductInfoActivity","No response on failure called");
            }
        });*/
        //TODO the merchant selection and reflecting the change caused by changing the merchant. merchant -- spinner, price- textview
        /*service1.getAllMerchants(productId).enqueue(new Callback<ApiResponse<List<MerchantProduct>>>() {
            @Override
            public void onResponse(Call<ApiResponse<List<MerchantProduct>>> call, Response<ApiResponse<List<MerchantProduct>>> response) {
                if(response.body() != null){
                    merchantProductObject.addAll(response.body().getData());


                }
            }

            @Override
            public void onFailure(Call<ApiResponse<List<MerchantProduct>>> call, Throwable t) {

            }
        });*/


        final Button buyNow=findViewById(R.id.buyNowButton);
        buyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Retrofit retrofit3 = new Retrofit.Builder()
                        .baseUrl("http://172.16.20.84:8082/")
                        .client(new OkHttpClient())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                OrderService orderService1 = retrofit3.create(OrderService.class);
                Map<String, Object> jsonParams = new ArrayMap<>();
                jsonParams.put("address1", "daf");
                jsonParams.put("address2", "asdfa");
                jsonParams.put("city", "chirala");
                jsonParams.put("deliveryDate","2018-12-13");
                jsonParams.put("orderDate","2019-06-2");
                jsonParams.put("orderPrice","8400");
                jsonParams.put("pincode","9090");
                jsonParams.put("userEmailId","mehak@gmail.com");
                jsonParams.put("userId","2");

                RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),(new JSONObject(jsonParams)).toString());

                orderService1.saveOrder(body)
                        .enqueue(new Callback<OrderMe>() {
                            @Override
                            public void onResponse(Call<OrderMe> call, Response<OrderMe> response) {
                                Log.e("Inordersave",response.body().toString());

                                Retrofit retrofit4 = new Retrofit.Builder()
                                        .baseUrl("http://172.16.20.84:8082/")
                                        .client(new OkHttpClient())
                                        .addConverterFactory(GsonConverterFactory.create())
                                        .build();
                                OrderService orderService2 = retrofit4.create(OrderService.class);
                                Map<String, Object> jsonParams1 = new ArrayMap<>();
                                jsonParams1.put("orderId", "15");
                                jsonParams1.put("productId",productId);
                                jsonParams1.put("merchantId",merchantid);
                                jsonParams1.put("quantity","1");
                                jsonParams1.put("productPrice",(int)Float.parseFloat(productPrice.getText().toString()));

                                RequestBody body1 = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),(new JSONObject(jsonParams1)).toString());

                                orderService2.saveProduct(body1)
                                        .enqueue(new Callback<OrderProductMe>() {
                                            @Override
                                            public void onResponse(Call<OrderProductMe> call, Response<OrderProductMe> response) {
                                                Log.e("Inorderproductsave",response.body().toString());
                                            }

                                            @Override
                                            public void onFailure(Call<OrderProductMe> call, Throwable t) {
                                                Log.e("Inorderproductsave", "failure");

                                            }
                                        });


                            }

                            @Override
                            public void onFailure(Call<OrderMe> call, Throwable t) {
                                Log.e("inorder save","failed");

                            }
                        });




                Intent intent = new Intent(getApplicationContext(), CheckoutPromptActivity.class);
                startActivity(intent);
            }
        });


    }
    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.addToCartButton){
            Intent intent = new Intent(getApplicationContext(),CartActivity.class);
            Bundle bundle =  new  Bundle();
            bundle.putString("ProductId",((TextView)((getWindow().getDecorView()).findViewById(R.id.productInfoName))).getText().toString());
            bundle.putInt("MerchantId",merchantid);
                    //((ViewGroup)((View)view.getParent()).getParent()).findViewById()

            intent.putExtra("AddToCart",bundle);
            view.getContext().startActivity(intent);

        }
        else if(view.getId() == R.id.buyNowButton ){
            Intent intent = new Intent(getApplicationContext(),CartActivity.class);
            view.getContext().startActivity(intent);
        }
        //Intent intent = new Intent(ProductInfoActivity.this,CartActivity.class);
    }
}