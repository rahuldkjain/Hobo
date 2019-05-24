package com.example.hoboandroid.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.hoboandroid.R;
import com.example.hoboandroid.models.Product;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.RecyclerViewHolder> {

        List<Product> list;

        public ProductAdapter(List<Product> list){
                this.list = list;
                }
        
        
        
        @NonNull
        @Override
        public ProductAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
                View view =  LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.product_list_item,viewGroup,false);
                return new RecyclerViewHolder(view);
        }
        
        @Override
        public void onBindViewHolder(@NonNull ProductAdapter.RecyclerViewHolder recyclerViewHolder, int position) {
                recyclerViewHolder.bind(list.get(position));
        }
        
        @Override
        public int getItemCount() {
                if(this.list != null)   return list.size();
                else return 0;
        }
        
        public class RecyclerViewHolder extends RecyclerView.ViewHolder{
            public RecyclerViewHolder(View itemView){
                super(itemView);
            }
            public void bind(Product product){
                    TextView productName = itemView.findViewById(R.id.productItemName);
                    productName.setText(product.getProductName());

                    // productRating = itemView.findViewById(R.id.productItemRating);
                    //productName.setText(product.getProductName());

                    TextView productPrice = itemView.findViewById(R.id.productItemPrice);

                    RatingBar  rating = itemView.findViewById(R.id.productItemRating);
                    //rating.setRating(Float.parseFloat(product.getProductRating()));
                    rating.setEnabled(false);


                            Glide.with(itemView.getContext())
                                    .load(R.string.category_path + product.getProductImage().get(0))
                                    .into((ImageView) itemView.findViewById(R.id.category_image));


            }
        }
}