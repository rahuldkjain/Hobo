package com.example.hoboandroid.adapters;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.request.RequestOptions;
import com.example.hoboandroid.R;
import com.example.hoboandroid.activities.CategoryActivity;
import com.example.hoboandroid.fragments.CategoryFragment;
import com.example.hoboandroid.fragments.ProductListFragment;
import com.example.hoboandroid.models.SubCategory;

import java.util.List;

public class SubCategoryAdapter extends RecyclerView.Adapter<SubCategoryAdapter.RecyclerViewHolder> implements View.OnClickListener {

    private List<SubCategory> list;


    public SubCategoryAdapter(List<SubCategory> list){
        this.list = list;
    }



    @NonNull
    @Override
    public SubCategoryAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view =  LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.sub_category_list_item,viewGroup,false);
        view.setOnClickListener(this);
        return new SubCategoryAdapter.RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubCategoryAdapter.RecyclerViewHolder recyclerViewHolder, int position) {
        recyclerViewHolder.bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        if(this.list != null)   return list.size();
        else return 0;
    }

    @Override
    public void onClick(View v) {


    }


    public class RecyclerViewHolder extends RecyclerView.ViewHolder{
        public RecyclerViewHolder(View itemView){
            super(itemView);
        }
        public void bind(SubCategory subCategory){


            TextView title = itemView.findViewById(R.id.sub_category_name);
            title.setText(subCategory.getSubCategoryName());



            //Loading image from below url into imageView
            Glide.with(itemView.getContext())
                    .load(subCategory.getSubCategoryImage())
                    .into((ImageView) itemView.findViewById(R.id.sub_category_image));




        }
    }
}
