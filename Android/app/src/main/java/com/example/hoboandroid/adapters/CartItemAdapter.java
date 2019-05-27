package com.example.hoboandroid.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.hoboandroid.Api;
import com.example.hoboandroid.CONSTANTS;
import com.example.hoboandroid.R;
import com.example.hoboandroid.activities.CartActivity;
import com.example.hoboandroid.models.ApiResponse;
import com.example.hoboandroid.models.Cart;
import com.example.hoboandroid.models.cart.CartItem;
import com.example.hoboandroid.models.merchantproduct.MerchantProductResponse;
import com.example.hoboandroid.services.MerchantService;
import com.example.hoboandroid.services.OrderService;
import com.example.hoboandroid.services.ProductService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CartItemAdapter  extends RecyclerView.Adapter<CartItemAdapter.RecyclerViewHolder> {

    private List<CartItem> list;
    private Context context;

    public CartItemAdapter(List<CartItem> list, Context context){
        this.list = list;
        this.context = context;
    }



    @NonNull
    @Override
    public CartItemAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        //TODO create list item for cart and inflate here
        View view =  LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.cart_list_item,viewGroup,false);
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
        public void bind(final CartItem cartItem){

            Retrofit retrofit = Api.getclient(CONSTANTS.MERCHANT_BASE_URL);

            MerchantService merchantService = retrofit.create(MerchantService.class);

            TextView productName = itemView.findViewById(R.id.cartProductItemName);
            productName.setText(cartItem.getProductName());

            TextView productPriceTextView = itemView.findViewById(R.id.cartProductItemPrice);
            productPriceTextView.setText(cartItem.getProductPrice());

            ((CartActivity)context).addToTotalPrice(cartItem.getProductPrice()*cartItem.getQuantity());



            Glide.with(itemView.getContext())
                    .load( cartItem.getProductImage())
                    .apply(new RequestOptions().override(100,100))
                    .into((ImageView) itemView.findViewById(R.id.product_list_image));

            final TextView quantity = itemView.findViewById(R.id.cartItem_quantity);
            quantity.setText(cartItem.getQuantity());

            //To update the price and alert us in the cart
            merchantService.getTopProductMerchant(cartItem.getProductId())
                    .enqueue(new Callback<ApiResponse<MerchantProductResponse>>() {
                        @Override
                        public void onResponse(Call<ApiResponse<MerchantProductResponse>> call, Response<ApiResponse<MerchantProductResponse>> response) {
                            if(response.body()!= null){

                                int productPrice = (int)Float.parseFloat(response.body().getData().getPrice());
                                if( productPrice != cartItem.getProductPrice()){
                                    TextView productPriceTextView = itemView.findViewById(R.id.cartProductItemPrice);
                                    productPriceTextView.setText(productPrice);

                                    TextView priceChanged = itemView.findViewById(R.id.cartPriceUpdatedText);
                                    priceChanged.setText("Price has been updated");
                                }


                            }
                        }

                        @Override
                        public void onFailure(Call<ApiResponse<MerchantProductResponse>> call, Throwable t) {

                        }
                    });


            //for deletion of cart item
            Button deleteItemCart = itemView.findViewById(R.id.cartItem_delete);

            deleteItemCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(((CartActivity)context).isLoggedIn())
                            deletingCartItem(cartItem);

                }
            });



            Button addItemCart = itemView.findViewById(R.id.cartItem_plus);

            addItemCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(((CartActivity)context).isLoggedIn())
                            addingCartItem(cartItem);

                }
            });




            Button removeItemCart = itemView.findViewById(R.id.cartItem_minus);

            removeItemCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(((CartActivity)context).isLoggedIn())
                            removingCartItemByOne(cartItem);

                }
            });



        }

        private void removingCartItemByOne(CartItem cartItem) {

            if(cartItem.getQuantity() - 1 == 0){
                deletingCartItem(cartItem);
                //TODO refresh
            }
            else {
                cartItem.setQuantity(cartItem.getQuantity()-1);

                Retrofit retrofitDelete = Api.getclient(CONSTANTS.ORDER_BASE_URL);

                OrderService orderService = retrofitDelete.create(OrderService.class);

                orderService.updateCartItem(cartItem)
                        .enqueue(new Callback<ApiResponse<CartItem>>() {
                            @Override
                            public void onResponse(Call<ApiResponse<CartItem>> call, Response<ApiResponse<CartItem>> response) {
                                if(response.body() != null && response.body().getData()!=null){
                                    Log.d("CartItemAdapter","Successfully Updated - "+response.body().getData().toString());
                                }
                                ((CartActivity)context).cartItemAdapter.notifyItemChanged(getAdapterPosition());
                            }

                            @Override
                            public void onFailure(Call<ApiResponse<CartItem>> call, Throwable t) {
                                Toast.makeText(itemView.getContext(),"Couldn't connect please try again",Toast.LENGTH_SHORT).show();
                                Log.d("CartItemAdapter","onFailure in removingCartItem response couldn't connect - Cart" );
                            }
                        });
            }

        }

        private void deletingCartItem(final CartItem cartItem) {


            cartItem.setQuantity(cartItem.getQuantity()-1);

            Retrofit retrofitDelete = Api.getclient(CONSTANTS.ORDER_BASE_URL);

            OrderService orderService = retrofitDelete.create(OrderService.class);

            orderService.deleteCartItem(cartItem.getCartItemId())
                    .enqueue(new Callback<ApiResponse<CartItem>>() {
                        @Override
                        public void onResponse(Call<ApiResponse<CartItem>> call, Response<ApiResponse<CartItem>> response) {
                            if(response.body() != null && response.body().getData()!=null){
                                if(cartItem.getCartItemId()==response.body().getData().getCartItemId()) {
                                    Toast.makeText(itemView.getContext(),"Deleting the product from cart",Toast.LENGTH_SHORT).show();
                                    Log.d("CartItemAdapter", "Successfully deleted - " + response.body().getData().toString());
                                }
                                else{
                                    Log.d("CartItemAdapter", "cart Item deletion didn't return same item Id - " + response.body().getData().toString());
                                }

                            }
                            ((CartActivity)context).getCartItems();
                        }

                        @Override
                        public void onFailure(Call<ApiResponse<CartItem>> call, Throwable t) {
                            Toast.makeText(itemView.getContext(),"Couldn't connect please try again",Toast.LENGTH_SHORT).show();
                            Log.d("CartItemAdapter","onFailure response in deletingCartItem couldn't connect - Cart" );
                        }
                    });


        }

        private void addingCartItem(final CartItem cartItem) {
            if(cartItem.getQuantity() + 1 > 2){
                Toast.makeText(itemView.getContext(),"Cannot have more than two at same time",Toast.LENGTH_SHORT).show();
            }
            else {
                cartItem.setQuantity(cartItem.getQuantity()+1);

                Retrofit retrofitDelete = Api.getclient(CONSTANTS.ORDER_BASE_URL);

                OrderService orderService = retrofitDelete.create(OrderService.class);

                orderService.updateCartItem(cartItem)
                        .enqueue(new Callback<ApiResponse<CartItem>>() {
                            @Override
                            public void onResponse(Call<ApiResponse<CartItem>> call, Response<ApiResponse<CartItem>> response) {
                                if(response.body() != null && response.body().getData()!=null){
                                    if(cartItem.getCartItemId()==response.body().getData().getCartItemId()) {
                                        Toast.makeText(itemView.getContext(),"Added the product from cart",Toast.LENGTH_SHORT).show();
                                        Log.d("CartItemAdapter", "Successfully added - " + response.body().getData().toString());
                                    }
                                    else{
                                        Log.d("CartItemAdapter", "cart Item deletion didn't return same item Id - " + response.body().getData().toString());
                                    }
                                    ((CartActivity)context).cartItemAdapter.notifyItemChanged(getAdapterPosition());
                                }
                            }

                            @Override
                            public void onFailure(Call<ApiResponse<CartItem>> call, Throwable t) {
                                Toast.makeText(itemView.getContext(),"Couldn't connect please try again",Toast.LENGTH_SHORT).show();
                                Log.d("CartItemAdapter","onFailure response couldn't connect - Cart" );
                            }
                        });
            }
        }
    }
}
