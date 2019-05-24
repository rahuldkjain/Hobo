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
import com.example.hoboandroid.R;
import com.example.hoboandroid.activities.LandingPageActivity;
import com.example.hoboandroid.models.category.Category;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.RecyclerViewHolder> {

    private List<Category> list;
    LandingPageActivity landingPageActivity = new LandingPageActivity();

    public CategoryAdapter(List<Category> list){
        this.list = list;
    }



    @NonNull
    @Override
    public CategoryAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view =  LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.category_list_item,viewGroup,false);
        view.setOnClickListener(landingPageActivity);
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

    public class RecyclerViewHolder extends RecyclerView.ViewHolder{
        public RecyclerViewHolder(View itemView){
            super(itemView);
        }
        public void bind(Category category){


            TextView title = itemView.findViewById(R.id.category_name);
            title.setText(category.getCategoryName());



            //Loading image from below url into imageView
            Glide.with(itemView.getContext())
                    .load(R.string.category_path + category.getCategoryImage())
                    .apply(new RequestOptions().override(250,250))
                            .into((ImageView) itemView.findViewById(R.id.category_image));



        }
    }
}
