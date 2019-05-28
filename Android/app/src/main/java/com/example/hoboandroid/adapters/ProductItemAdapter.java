package com.example.hoboandroid.adapters;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.hoboandroid.CONSTANTS;
import com.example.hoboandroid.R;
import com.example.hoboandroid.activities.LandingPageActivity;
import com.example.hoboandroid.activities.ProductInfoActivity;
import com.example.hoboandroid.activities.ProductListActivity;
import com.example.hoboandroid.fragments.ProductListFragment;
import com.example.hoboandroid.models.ApiResponse;
import com.example.hoboandroid.models.category.Category;
import com.example.hoboandroid.models.merchantproduct.MerchantProductResponse;
import com.example.hoboandroid.models.product.Product;
import com.example.hoboandroid.models.product.Product;
import com.example.hoboandroid.services.MerchantService;

import org.w3c.dom.Text;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProductItemAdapter  extends RecyclerView.Adapter<ProductItemAdapter.RecyclerViewHolder> {

    private List<Product> list;

    public ProductItemAdapter(List<Product> list){
        this.list = list;
    }



    @NonNull
    @Override
    public ProductItemAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view =  LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.product_list_item,viewGroup,false);
        return new ProductItemAdapter.RecyclerViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder recyclerViewHolder, int position) {
        recyclerViewHolder.bind((Product) list.get(position));
    }

    @Override
    public int getItemCount() {
        if(this.list != null)   return list.size();
        else return 0;
    }


    public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView productPrice,rating;
        public RecyclerViewHolder(View itemView){
            super(itemView);
            itemView.setOnClickListener(this);
        }
        public void bind(Product product){


            Log.e("ProductListFragment","adapter "+product.toString());
            TextView productName = itemView.findViewById(R.id.product_list_name);
            productName.setText(product.getProductName());



            productPrice = itemView.findViewById(R.id.product_list_price);
            //productPrice.setText(""+Product.getProductPrice());


            //TextView title = itemView.findViewById(R.id.?);
            //title.setText(Product.getProductId());

            rating = itemView.findViewById(R.id.product_list_rating);
            //rating.setText("Rating:"+Product.getRating());


            //Loading image from below url into imageView
            Glide.with(itemView.getContext())
                    .load( product.getProductImage().get(0))
                    .apply(new RequestOptions().override(100,100))
                    .into((ImageView) itemView.findViewById(R.id.product_list_image));



            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(CONSTANTS.MERCHANT_BASE_URL)
                    .client(new OkHttpClient())
                    .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                    .build();

            //Log.e("ProductItemAdapter","binding "+CONSTANTS.MERCHANT_BASE_URL + "  requesting merchantproduct product - "+product);
            MerchantService merchantService =  retrofit.create(MerchantService.class);

            merchantService.getTopProductMerchant(product.getProductId())
                    .enqueue(new Callback<ApiResponse<MerchantProductResponse>>() {
                        @Override
                        public void onResponse(Call<ApiResponse<MerchantProductResponse>> call, Response<ApiResponse<MerchantProductResponse>> response) {
                            if(response.body() != null){
                                productPrice.setText(response.body().getData().getPrice());
                                rating.setText(response.body().getData().getProductRating()+"/5.0");



                            //productMerchantName.setText(response1.body().getData().getMerchantId())
                                }

                            }
                    @Override
                    public void onFailure(Call<ApiResponse<MerchantProductResponse>> call, Throwable t) {
                        Toast.makeText(itemView.getContext(),"Check your connection in merchant",Toast.LENGTH_LONG).show();
                        Log.e("ProductItemAdapter",t.getMessage()+" failure");
                    }
                });



        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();

            Toast.makeText(view.getContext(), "A Product is clicked", Toast.LENGTH_LONG).show();
            //opening a category page
            Intent intent = new Intent(view.getContext(), ProductInfoActivity.class);
            intent.putExtra("Product",list.get(getAdapterPosition()).getProductId());
            //intent.putExtra("Category Object",item.getCategoryName());
            view.getContext().startActivity(intent);

        }

    }
}