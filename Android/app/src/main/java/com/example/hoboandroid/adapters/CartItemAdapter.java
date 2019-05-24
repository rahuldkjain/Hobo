package com.example.hoboandroid.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hoboandroid.R;
import com.example.hoboandroid.models.Cart;

import java.util.List;

public class CartItemAdapter  extends RecyclerView.Adapter<CartItemAdapter.RecyclerViewHolder> {

    private List<Cart> list;

    public CartItemAdapter(List<Cart> list){
        this.list = list;
    }



    @NonNull
    @Override
    public CartItemAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        //TODO create list item for cart and inflate here
        View view =  LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.category_list_item,viewGroup,false);
        //view.setOnClickListener(productListActivity);
        return new CartItemAdapter.RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartItemAdapter.RecyclerViewHolder recyclerViewHolder, int position) {
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
        public void bind(Cart cart){


           //TextView title = itemView.findViewById(R.id.cartItemName);
           // title.setText(category.getCategoryName());



            //Loading image from below url into imageView
            /*Glide.with(itemView.getContext())
                    .load(R.string.category_path + category.getImage())
                    .into((ImageView) itemView.findViewById(R.id.category_image));
*/


        }
    }
}
