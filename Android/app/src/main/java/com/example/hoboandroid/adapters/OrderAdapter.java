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
import com.example.hoboandroid.activities.OrderHistoryActivity;
import com.example.hoboandroid.activities.OrderedProductsActivity;
import com.example.hoboandroid.models.Order;
import com.example.hoboandroid.models.order.OrderData;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.RecyclerViewHolder>  {
    private List<OrderData> list;
    //private OrderHistoryActivity orderHistoryActivity = new OrderHistoryActivity();
    public OrderAdapter(List<OrderData> list){
            this.list = list;
            }



    @NonNull
    @Override
    public OrderAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
            View view =  LayoutInflater.from(viewGroup.getContext()).
            inflate(R.layout.order_history_list_item,viewGroup,false);
            //view.setOnClickListener(orderHistoryActivity);
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

    public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public RecyclerViewHolder(View itemView){
            super(itemView);
            itemView.setOnClickListener(this);
        }
        public void bind(OrderData orderData){


            TextView orderId = itemView.findViewById(R.id.orderhistory_orderid);
            TextView price=itemView.findViewById(R.id.orderhistory_price);
            TextView address=itemView.findViewById(R.id.orderhistory_address);
            TextView deliveryDate=itemView.findViewById(R.id.orderhistory_delivery_date);
            TextView orderDate=itemView.findViewById(R.id.orderhistory_order_date);


            orderId.setText(""+orderData.getOrderId());
            price.setText(""+orderData.getOrderPrice());
            address.setText(orderData.getAddress1()+" "+orderData.getAddress2()+" "+ orderData.getCity() + " " + orderData.getPincode());
            deliveryDate.setText(orderData.getDeliveryDate());
            orderDate.setText(orderData.getOrderDate());


            //TODO bind the respective list item with a row in recyler view

            //Loading image from below url into imageView
    /*
                Glide.with(itemView.getContext())
                        .load(R.string.category_path + category.getImage())
                        .into((ImageView) itemView.findViewById(R.id.category_image));
    */
        }

        @Override
        public void onClick(View v) {
            int itemPosition = getAdapterPosition();
            OrderData orderItem = list.get(itemPosition);
            Toast.makeText(v.getContext(), "A Order is clicked for details", Toast.LENGTH_LONG).show();

            Intent intent = new Intent(v.getContext(), OrderedProductsActivity.class);
            //intent.putExtra("OrderId",orderItem.get());
            v.getContext().startActivity(intent);
        }
    }
}
