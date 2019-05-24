package com.example.hoboandroid.adapters;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.hoboandroid.R;
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
                inflate(R.layout.category_list_item,viewGroup,false);
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


    public class RecyclerViewHolder extends RecyclerView.ViewHolder{
        public RecyclerViewHolder(View itemView){
            super(itemView);
        }
        public void bind(OrderedProduct order){

            TextView textView =  itemView.findViewById(R.id.orders_product_name);


            //TODO bind the respective list item with a row in recycler view




            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int i = getAdapterPosition();
                }
            });

            //TODO ratings and product info button

            //Loading image from below url into imageView
/*
            Glide.with(itemView.getContext())
                    .load(R.string.category_path + category.getImage())
                    .into((ImageView) itemView.findViewById(R.id.category_image));
*/



        }
    }
}
