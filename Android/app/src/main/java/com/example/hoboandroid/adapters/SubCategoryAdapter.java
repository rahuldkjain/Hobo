package com.example.hoboandroid.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.hoboandroid.R;
import com.example.hoboandroid.activities.CategoryActivity;
import com.example.hoboandroid.activities.LandingPageActivity;
import com.example.hoboandroid.models.Category;
import com.example.hoboandroid.models.SubCategory;

import java.util.List;

public class SubCategoryAdapter extends RecyclerView.Adapter<SubCategoryAdapter.RecyclerViewHolder>{

    private List<SubCategory> list;
    CategoryActivity categoryActivity = new CategoryActivity();

    public SubCategoryAdapter(List<SubCategory> list){
        this.list = list;
    }



    @NonNull
    @Override
    public SubCategoryAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view =  LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.sub_category_list_item,viewGroup,false);
        view.setOnClickListener(categoryActivity);
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



    public class RecyclerViewHolder extends RecyclerView.ViewHolder{
        public RecyclerViewHolder(View itemView){
            super(itemView);
        }
        public void bind(SubCategory subCategory){


            TextView title = itemView.findViewById(R.id.sub_category_name);
            title.setText(subCategory.getSubCategoryName());



            //Loading image from below url into imageView
            Glide.with(itemView.getContext())
                    .load(R.string.category_path + subCategory.getSubCategoryImage())
                    .into((ImageView) itemView.findViewById(R.id.sub_category_image));




        }
    }
}
