package com.example.hoboandroid.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.hoboandroid.R;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.example.hoboandroid.Api;
import com.example.hoboandroid.R;
import com.example.hoboandroid.models.ApiResponse;
import com.example.hoboandroid.models.Merchant;
import com.example.hoboandroid.models.MerchantProduct;
import com.example.hoboandroid.models.product.Product;
import com.example.hoboandroid.services.MerchantService;
import com.example.hoboandroid.services.ProductService;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import static okhttp3.logging.HttpLoggingInterceptor.Level.BODY;
public class ProductInfoActivity extends AppCompatActivity implements View.OnClickListener{


    List<MerchantProduct> merchantProductObject;
    Integer merchantid;

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
        final ImageView productImage=findViewById(R.id.productInfoImage);
        final TextView productDesc= findViewById(R.id.productDescription2);
        final TextView productName=findViewById(R.id.productInfoName);
        final TextView productAttributes=findViewById(R.id.attributes2);
        final TextView productPrice=findViewById(R.id.productInfoPrice);

        int productId = Integer.parseInt(getIntent().getStringExtra("Product"));


        service.getProductById(productId)
                .enqueue(new Callback<ApiResponse<Product>>() {
                    @Override
                    public void onResponse(Call<ApiResponse<Product>> call, Response<ApiResponse<Product>> response) {
                        //Log.e("Hello","hi");
                        //Log.e("Hello",response.message());
                        if(response.body() != null){
                            Log.e("ProductInfoPage",response.body().toString());
                            productName.setText(response.body().getData().getProductName());
                            productDesc.setText(response.body().getData().getDescription());
                            productAttributes.setText(response.body().getData().getAttributes().getColor());
                            Log.e("Image",response.body().getData().getProductImage().get(0));
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
        Log.e("Hello","hi");
        Retrofit retrofit1 = new Retrofit.Builder()
                .baseUrl("http://172.16.20.101:8080/")
                .client(new OkHttpClient())
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();

        MerchantService service1=retrofit1.create(MerchantService.class);
        Log.d("LandingPageActivity","Sub "+getIntent().getStringExtra("Product"));
        service1.getTopProductMerchant(productId)
                .enqueue(new Callback<ApiResponse<MerchantProduct>>() {
                    @Override
                    public void onResponse(Call<ApiResponse<MerchantProduct>> call, Response<ApiResponse<MerchantProduct>> response1) {
                        if(response1.body() != null){
                            Log.e("Inmerchant",response1.body().getData().getPrice()+"");
                            productPrice.setText(response1.body().getData().getPrice()+"");
                            merchantid = response1.body().getData().getMerchantId();
                            //productMerchantName.setText(response1.body().getData().getMerchantId())
                        }
                    }
                    @Override
                    public void onFailure(Call<ApiResponse<MerchantProduct>> call, Throwable t) {
                        Toast.makeText(ProductInfoActivity.this,"Check your connection in merchant",Toast.LENGTH_LONG).show();
                        Log.e("HOBOLandingPage",t.getMessage()+" failure");
                    }
                });


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