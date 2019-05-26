package com.example.hoboandroid.adapters;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.hoboandroid.R;
import com.example.hoboandroid.activities.LandingPageActivity;
import com.example.hoboandroid.activities.ProductInfoActivity;
import com.example.hoboandroid.models.product.Product;

import java.util.List;

public class LandingPageProductAdapter extends RecyclerView.Adapter<LandingPageProductAdapter.RecyclerViewHolder> {

        List<Product> list;

        public LandingPageProductAdapter(List<Product> list){
                this.list = list;
                }
        
        

        @NonNull
        @Override
        public LandingPageProductAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
                View view =  LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.landing_page_product_item,viewGroup,false);
                return new RecyclerViewHolder(view);
        }
        
        @Override
        public void onBindViewHolder(@NonNull LandingPageProductAdapter.RecyclerViewHolder recyclerViewHolder, int position) {
                recyclerViewHolder.bind(list.get(position));
        }
        
        @Override
        public int getItemCount() {
                if(this.list != null)   return list.size();
                else return 0;
        }
        
        public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            public RecyclerViewHolder(View itemView){
                super(itemView);
                itemView.setOnClickListener(this);
            }
            public void bind(Product product){
                    TextView productName = itemView.findViewById(R.id.landing_product_name);
                    productName.setText(product.getProductName());

                    // productRating = itemView.findViewById(R.id.productItemRating);
                    //productName.setText(product.getProductName());

                    //RatingBar  rating = itemView.findViewById(R.id.productItemRating);
                    //rating.setRating(Float.parseFloat(product.getProductRating()));
                    //rating.setEnabled(false);

                    Glide.with(itemView.getContext())
                          .load( product.getProductImage().get(0))
                           .apply(new RequestOptions().override(500,500))
                            .into((ImageView) itemView.findViewById(R.id.landing_product_image));


            }

                @Override
                public void onClick(View view) {
                        Toast.makeText(view.getContext(), "A Product is clicked", Toast.LENGTH_LONG).show();
                        //opening a category page
                        Intent intent = new Intent(view.getContext(), ProductInfoActivity.class);
                        intent.putExtra("Product",list.get(getAdapterPosition()).getProductId());
                        //intent.putExtra("Category Object",item.getCategoryName());
                        view.getContext().startActivity(intent);
                }
        }
}