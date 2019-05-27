package com.example.hoboandroid.adapters;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hoboandroid.R;
import com.example.hoboandroid.activities.CategoryActivity;
import com.example.hoboandroid.activities.OrderedProductsActivity;
import com.example.hoboandroid.activities.ProductInfoActivity;
import com.example.hoboandroid.models.OrderedProduct;

import java.util.List;

public class OrderedProductAdapter extends RecyclerView.Adapter<OrderedProductAdapter.RecyclerViewHolder> {
    private List<OrderedProduct> list;

    public OrderedProductAdapter(List<OrderedProduct> list){
        this.list = list;
    }



    @NonNull
    @Override
    public OrderedProductAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view =  LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.ordered_product_list_item,viewGroup,false);
        return new OrderedProductAdapter.RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderedProductAdapter.RecyclerViewHolder recyclerViewHolder, int position) {
        recyclerViewHolder.bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        if(this.list != null)   return list.size();
        else return 0;
    }


    public class RecyclerViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener{
        public RecyclerViewHolder(View itemView){
            super(itemView);
            itemView.setOnClickListener(this);
        }
        public void bind(OrderedProduct order){

           // TextView textView =  itemView.findViewById(R.id.orders_product_name);


            //TODO bind the respective list item with a row in recycler view





            //TODO ratings and product info button

            //Loading image from below url into imageView
/*
            Glide.with(itemView.getContext())
                    .load(R.string.category_path + category.getImage())
                    .into((ImageView) itemView.findViewById(R.id.category_image));
*/



        }
        @Override
        public void onClick(View view) {
            int itemPosition = getAdapterPosition();
            OrderedProduct item = list.get(itemPosition);
            Toast.makeText(view.getContext(), "A Ordered Product is clicked", Toast.LENGTH_LONG).show();
            //opening a product Page
            Intent intent = new Intent(view.getContext(), ProductInfoActivity.class);
            //TODO check the objects inserted into database and retrieved here
            intent.putExtra("Product",item.getProductId());
            view.getContext().startActivity(intent);
        }
    }
}
