package com.example.hoboandroid.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hoboandroid.R;
import com.example.hoboandroid.activities.OrderHistoryActivity;
import com.example.hoboandroid.models.Order;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.RecyclerViewHolder>  {
    private List<Order> list;
    private OrderHistoryActivity orderHistoryActivity = new OrderHistoryActivity();
    public OrderAdapter(List<Order> list){
        this.list = list;
    }



    @NonNull
    @Override
    public OrderAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view =  LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.category_list_item,viewGroup,false);
        view.setOnClickListener(orderHistoryActivity);
        return new OrderAdapter.RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderAdapter.RecyclerViewHolder recyclerViewHolder, int position) {
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
        public void bind(Order order){




            //TODO bind the respective list item with a row in recyler view

            //Loading image from below url into imageView
/*
            Glide.with(itemView.getContext())
                    .load(R.string.category_path + category.getImage())
                    .into((ImageView) itemView.findViewById(R.id.category_image));
*/



        }
    }
}
