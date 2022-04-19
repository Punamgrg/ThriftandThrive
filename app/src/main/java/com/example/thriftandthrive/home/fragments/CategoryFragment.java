package com.example.thriftandthrive.home.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thriftandthrive.R;
import com.example.thriftandthrive.api.response.Category;
import com.example.thriftandthrive.home.fragments.home.adapters.CategoryAdapter;
import com.example.thriftandthrive.utils.DataHolder;

import java.util.List;


public class CategoryFragment extends Fragment {
    RecyclerView fullCategoryRV;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_category, container, false);
    }
    @Override

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fullCategoryRV = view.findViewById(R.id.fullCatRV);
        if (DataHolder.categories != null){
            showCategoryView(DataHolder.categories);
        }
    }

    private void showCategoryView(List<Category> categories) {
        fullCategoryRV.setHasFixedSize(true);
        fullCategoryRV.setLayoutManager(new GridLayoutManager(getContext(), 4));
        CategoryAdapter categoryAdapter = new CategoryAdapter(categories, getContext(), true, false, null);
        fullCategoryRV.setAdapter(categoryAdapter);


    }
}