package com.example.hoboandroid.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hoboandroid.R;
import com.example.hoboandroid.adapters.CategoryAdapter;

import java.util.ArrayList;
import java.util.List;


public class CategoryFragment extends Fragment {
    List itemsList;
    RecyclerView recyclerView;
    CategoryAdapter categoryAdapter;

    public CategoryFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recycler_view, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        



        recyclerView =view.findViewById(R.id.recyclerView);

        categoryAdapter = new CategoryAdapter(itemsList);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);


        recyclerView.setAdapter(categoryAdapter);

        //TODO should request for subcategories and populate them on the recycler view





    }
}
