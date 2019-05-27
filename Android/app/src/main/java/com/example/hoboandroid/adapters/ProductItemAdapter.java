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
import com.example.hoboandroid.R;
import com.example.hoboandroid.activities.LandingPageActivity;
import com.example.hoboandroid.activities.ProductInfoActivity;
import com.example.hoboandroid.activities.ProductListActivity;
import com.example.hoboandroid.fragments.ProductListFragment;
import com.example.hoboandroid.models.category.Category;
import com.example.hoboandroid.models.product.Product;
import com.example.hoboandroid.models.product.ProductListItem;

import java.util.List;

public class ProductItemAdapter  extends RecyclerView.Adapter<ProductItemAdapter.RecyclerViewHolder> {

    private List<ProductListItem> list;

    public ProductItemAdapter(List<ProductListItem> list){
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
        recyclerViewHolder.bind((ProductListItem) list.get(position));
    }

    @Override
    public int getItemCount() {
        if(this.list != null)   return list.size();
        else return 0;
    }


    public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public RecyclerViewHolder(View itemView){
            super(itemView);
            itemView.setOnClickListener(this);
        }
        public void bind(ProductListItem productListItem){


            Log.e("ProductListFragment","adapter "+productListItem.toString());
            TextView productName = itemView.findViewById(R.id.product_list_name);
            productName.setText(productListItem.getProductName());


            TextView productPrice = itemView.findViewById(R.id.product_list_price);
            productPrice.setText(""+productListItem.getProductPrice());


            //TextView title = itemView.findViewById(R.id.?);
            //title.setText(productListItem.getProductId());

            TextView rating = itemView.findViewById(R.id.product_list_rating);
            rating.setText("Rating:"+productListItem.getRating());



            //Loading image from below url into imageView
            Glide.with(itemView.getContext())
                    .load( productListItem.getImage())
                    .apply(new RequestOptions().override(100,100))
                    .into((ImageView) itemView.findViewById(R.id.product_list_image));



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