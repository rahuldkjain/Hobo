package com.example.hoboandroid.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.ArrayMap;
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

import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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
        TextView quantity;
        public RecyclerViewHolder(View itemView){
            super(itemView);
        }
        public void bind(final CartItem cartItem){



            Log.e("CartActivity","inside viewholder "+cartItem.toString());
            Retrofit retrofit = Api.getclient(CONSTANTS.MERCHANT_BASE_URL);

            MerchantService merchantService = retrofit.create(MerchantService.class);

            TextView productName = itemView.findViewById(R.id.cartProductItemName);
            productName.setText(cartItem.getProductName());

            TextView productPriceTextView = itemView.findViewById(R.id.cartItemProductPrice);
            productPriceTextView.setText(""+cartItem.getProductPrice());


            TextView cartItemMerchant = itemView.findViewById(R.id.cartItem_merchant);
            cartItemMerchant.setText(""+cartItem.getMerchantName());

            Glide.with(itemView.getContext())
                    .load( cartItem.getProductImage().get(0))
                    .apply(new RequestOptions().override(100,100))
                    .into((ImageView) itemView.findViewById(R.id.cartProductItemImage));

            quantity = itemView.findViewById(R.id.cartItem_quantity);
            quantity.setText(""+cartItem.getQuantity());

            //To update the price and alert us in the cart
          /*  merchantService.getTopProductMerchant(cartItem.getProductId())
                    .enqueue(new Callback<ApiResponse<MerchantProductResponse>>() {
                        @Override
                        public void onResponse(Call<ApiResponse<MerchantProductResponse>> call, Response<ApiResponse<MerchantProductResponse>> response) {
                            if(response.body()!= null){

                                int productPrice = (int)Float.parseFloat(response.body().getData().getPrice());
                                if( productPrice != cartItem.getProductPrice()){
                                    TextView productPriceTextView = itemView.findViewById(R.id.cartItemProductPrice);
                                    productPriceTextView.setText(""+productPrice);

                                    TextView priceChanged = itemView.findViewById(R.id.cartPriceUpdatedText);
                                    priceChanged.setText("Price has been updated");
                                }


                            }
                        }

                        @Override
                        public void onFailure(Call<ApiResponse<MerchantProductResponse>> call, Throwable t) {

                        }
                    });
*/

            //for deletion of cart item
            Button deleteItemCart = itemView.findViewById(R.id.cartItem_delete);

            deleteItemCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(((CartActivity)context).isLoggedIn()) {
                        deletingCartItem(cartItem);
                        notifyDataSetChanged();
                    }

                }
            });



            Button addItemCart = itemView.findViewById(R.id.cartItem_plus);

            addItemCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(((CartActivity)context).isLoggedIn()) {
                        addingCartItem(cartItem);

                    }

                }
            });




            Button removeItemCart = itemView.findViewById(R.id.cartItem_minus);

            removeItemCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(((CartActivity)context).isLoggedIn()) {
                        removingCartItemByOne(cartItem);
                        notifyDataSetChanged();
                    }
                }
            });



        }

        public void removingCartItemByOne(final CartItem cartItem) {

            if(cartItem.getQuantity() - 1 == 0){
                deletingCartItem(cartItem);
                Toast.makeText(itemView.getContext(),"Cannot delete more further",Toast.LENGTH_SHORT).show();
            }
            else {

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(CONSTANTS.ORDER_BASE_URL)
                        .client(new OkHttpClient())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                OrderService orderService = retrofit.create(OrderService.class);
                Map<String, Object> jsonParams = new ArrayMap<>();
                jsonParams.put("cartItemId",cartItem.getCartItemId());
                jsonParams.put("userEmail", cartItem.getUserEmail());
                jsonParams.put("productId",cartItem.getProductId() );
                jsonParams.put("merchantId", cartItem.getMerchantId());
                jsonParams.put("productImage",cartItem.getProductImage());
                jsonParams.put("productName",cartItem.getProductName() );
                jsonParams.put("productPrice", cartItem.getProductPrice());
                jsonParams.put("quantity", cartItem.getQuantity()-1);


                RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), (new JSONObject(jsonParams)).toString());

                Log.d("Cart deleting one item","Request body" + jsonParams.toString());

                orderService.updateCartItem(body)
                        .enqueue(new Callback<ApiResponse<CartItem>>() {
                            @Override
                            public void onResponse(Call<ApiResponse<CartItem>> call, Response<ApiResponse<CartItem>> response) {
                                Log.e("one removing Cart", "response in cart"+response.message());
                                if (response.body() != null) {
                                    Log.e("one removing Cart", "response body in cart"+response.body().getData().toString());
                                    }
                                notifyItemChanged(getAdapterPosition());
                                int updatedQuantity = cartItem.getQuantity()-1;
                                list.get(getAdapterPosition()).setQuantity(updatedQuantity);
                                quantity.setText(""+updatedQuantity);
                                //  Log.e("CartActivity", "in add cart response "+response.body().toString() );
                            }

                            @Override
                            public void onFailure(Call<ApiResponse<CartItem>> call, Throwable t) {
                                //TODO add code to handle the failure no response
                                Log.d("CartActivity", "Response Failure");
                            }
                        });
            }

        }

        public void deletingCartItem(final CartItem cartItem) {


            Retrofit retrofitDelete= new Retrofit.Builder()
                    .baseUrl(CONSTANTS.ORDER_BASE_URL)
                    .client(new OkHttpClient())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            OrderService orderService=retrofitDelete.create(OrderService.class);

            orderService.deleteCartItem(cartItem.getCartItemId())
                    .enqueue(new Callback<ApiResponse<String>>() {
                        @Override
                        public void onResponse(Call<ApiResponse<String>> call, Response<ApiResponse<String>> response) {
                            Log.e("In cart delete", "in response");
                            if(response.body()!=null){
                                //Log.e("Inside cart response", response.body().toString());

                                Toast.makeText(itemView.getContext(),response.body().getData(),Toast.LENGTH_SHORT).show();
                            }
                            list.remove(getAdapterPosition());
                            ((CartActivity)context).cartItemsList.remove(getAdapterPosition());
                            notifyItemRemoved(getAdapterPosition());
                            notifyDataSetChanged();
                        }

                        @Override
                        public void onFailure(Call<ApiResponse<String>> call, Throwable t) {
                            Log.e("Cart delete", "failed");
                        }
                    });


        }

        public void addingCartItem(final CartItem cartItem) {
            if(cartItem.getQuantity() + 1 > 10){
                Toast.makeText(itemView.getContext(),"Cannot have more than at same time",Toast.LENGTH_SHORT).show();
            }
            else {

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(CONSTANTS.ORDER_BASE_URL)
                        .client(new OkHttpClient())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                OrderService orderService = retrofit.create(OrderService.class);
                Map<String, Object> jsonParams = new ArrayMap<>();
                jsonParams.put("cartItemId",cartItem.getCartItemId());
                jsonParams.put("userEmail", cartItem.getUserEmail());
                jsonParams.put("productId",cartItem.getProductId() );
                jsonParams.put("merchantId", cartItem.getMerchantId());
                jsonParams.put("productImage",cartItem.getProductImage());
                jsonParams.put("productName",cartItem.getProductName() );
                jsonParams.put("productPrice", cartItem.getProductPrice());
                jsonParams.put("quantity", cartItem.getQuantity()+1);


                RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), (new JSONObject(jsonParams)).toString());

                Log.d("Cart deleting one item","Request body" + jsonParams.toString());

                orderService.updateCartItem(body)
                        .enqueue(new Callback<ApiResponse<CartItem>>() {
                            @Override
                            public void onResponse(Call<ApiResponse<CartItem>> call, Response<ApiResponse<CartItem>> response) {
                                Log.e("one removing Cart", "response in cart"+response.message());
                                if (response.body() != null) {
                                    Log.e("one removing Cart", "response body in cart"+response.body().getData().toString());
                                }
                                notifyItemChanged(getAdapterPosition());
                                int updatedQuantity = cartItem.getQuantity()+1;
                                list.get(getAdapterPosition()).setQuantity(updatedQuantity);
                                quantity.setText(""+updatedQuantity);
                                //  Log.e("CartActivity", "in add cart response "+response.body().toString() );
                            }

                            @Override
                            public void onFailure(Call<ApiResponse<CartItem>> call, Throwable t) {
                                //TODO add code to handle the failure no response
                                Log.d("CartActivity", "Response Failure");
                            }
                        });
            }
        }

    }
}
