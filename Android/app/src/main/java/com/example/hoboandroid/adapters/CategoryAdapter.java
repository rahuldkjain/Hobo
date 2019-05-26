package com.example.hoboandroid.adapters;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.hoboandroid.R;
import com.example.hoboandroid.activities.CategoryActivity;
import com.example.hoboandroid.activities.LandingPageActivity;
import com.example.hoboandroid.fragments.CategoryFragment;
import com.example.hoboandroid.models.category.Category;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.RecyclerViewHolder> {

    private List<Category> list;

    public CategoryAdapter(List<Category> list){
        this.list = list;
    }



    @NonNull
    @Override
    public CategoryAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view =  LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.sub_category_list_item,viewGroup,false);
        //view.setOnClickListener(landingPageActivity);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.RecyclerViewHolder recyclerViewHolder, int position) {
        recyclerViewHolder.bind((Category) list.get(position));
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
        public void bind(Category category){


            TextView title = itemView.findViewById(R.id.sub_category_name);
            title.setText(category.getCategoryName());



            //Loading image from below url into imageView
            Glide.with(itemView.getContext())
                    .load( category.getCategoryImage())
                    .apply(new RequestOptions().override(100,100))
                            .into((ImageView) itemView.findViewById(R.id.sub_category_image));



        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Bundle bundle = new Bundle();
            bundle.putString("Category",list.get(position).getCategoryName());

            //TODO onclick for the categories
            Intent intent = new Intent(v.getContext(), CategoryActivity.class);
            intent.putExtras(bundle);
            v.getContext().startActivity(intent);
            /*CategoryFragment categoryFragment = new CategoryFragment();
            categoryFragment.setArguments(bundle);
            ((Activity)v.getContext()).getSupportFragmentManager().beginTransaction()
                    *//*.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)*//*
                    .add(R.id.base_activity_frame, categoryFragment, "CategoryFragment")
                    .commit();*/
        }
    }
}
