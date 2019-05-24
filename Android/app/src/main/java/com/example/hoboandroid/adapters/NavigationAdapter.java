package com.example.hoboandroid.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hoboandroid.R;

import java.util.List;

public class NavigationAdapter extends RecyclerView.Adapter<NavigationAdapter.ViewHolder> {
    private List<String> mDataset;
    private OnItemClickListener mListener;


    public interface OnItemClickListener {
        public void onClick(View view, int position);
    }

    /**
     * Custom viewholder for our planet views.
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mTextView;

        public ViewHolder(TextView v) {
            super(v);
            mTextView = v;
        }
    }

    public NavigationAdapter(List myDataset, OnItemClickListener listener) {
        mDataset = myDataset;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater vi = LayoutInflater.from(parent.getContext());
        View v = vi.inflate(R.layout.drawer_list_item, parent, false);
        TextView tv = (TextView) v.findViewById(android.R.id.text1);
        return new ViewHolder(tv);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.mTextView.setText(mDataset.get(position));
        holder.mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onClick(view, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }



/*
    private List<String> list;

    public NavigationAdapter(List<String> list){
        this.list = list;
    }



    @NonNull
    @Override
    public NavigationAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view =  LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.category_list_item,viewGroup,false);
        //view.setOnClickListener(landingPageActivity);
        return new NavigationAdapter.RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NavigationAdapter.RecyclerViewHolder recyclerViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class RecyclerViewHolder {
        public RecyclerViewHolder(View itemView){
            super(itemView);
        }
        public void bind(Category category){


            TextView title = itemView.findViewById(R.id.category_name);
            title.setText(category.getCategoryName());



            //Loading image from below url into imageView
            Glide.with(itemView.getContext())
                    .load(R.string.category_path + category.getImage())
                    .into((ImageView) itemView.findViewById(R.id.category_image));



        }
    }*/
}
