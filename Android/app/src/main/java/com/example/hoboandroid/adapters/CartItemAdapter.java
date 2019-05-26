package com.example.hoboandroid.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.hoboandroid.Api;
import com.example.hoboandroid.CONSTANTS;
import com.example.hoboandroid.R;
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

    public CartItemAdapter(List<CartItem> list){
        this.list = list;
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



            Glide.with(itemView.getContext())
                    .load( cartItem.getProductImage())
                    .apply(new RequestOptions().override(100,100))
                    .into((ImageView) itemView.findViewById(R.id.product_list_image));

            TextView quantity = itemView.findViewById(R.id.cartItem_quantity);
            quantity.setText(cartItem.getQuantity());

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
