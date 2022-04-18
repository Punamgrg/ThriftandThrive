package com.example.thriftandthrive.home.fragments.home;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thriftandthrive.R;
import com.example.thriftandthrive.api.ApiClient;
import com.example.thriftandthrive.api.response.AllProductResponse;
import com.example.thriftandthrive.api.response.Category;
import com.example.thriftandthrive.api.response.CategoryResponse;
import com.example.thriftandthrive.api.response.Product;
import com.example.thriftandthrive.api.response.Slider;
import com.example.thriftandthrive.api.response.SliderResponse;
import com.example.thriftandthrive.home.fragments.home.adapters.CategoryAdapter;
import com.example.thriftandthrive.home.fragments.home.adapters.ShopAdapter;
import com.example.thriftandthrive.home.fragments.home.adapters.SliderAdapter;
import com.example.thriftandthrive.search.SearchActivity;
import com.example.thriftandthrive.utils.DataHolder;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment {
    RecyclerView allProductRV, categoryRV;
    ProgressBar loadingProgress;
    TextView viewAllTV, searchIt;
    LinearLayout searchLL;
    SliderView imageSlider;
    BottomNavigationView bottomNavigationView;
    TextView nameTV;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }
//    public void setBottomNavigationView(BottomNavigationView bottomNavigationView) {
//        this.bottomNavigationView = bottomNavigationView;
//    }
    public  void setBottomNavigationView(BottomNavigationView bottomNavigationView)
    {
        this.bottomNavigationView = bottomNavigationView;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        allProductRV = view.findViewById(R.id.allProductRV);
        categoryRV = view.findViewById(R.id.categoryRV);
        loadingProgress = view.findViewById(R.id.loadingProgress);
        imageSlider = view.findViewById(R.id.imageSlider);
        viewAllTV = view.findViewById(R.id.viewALLTV);
        searchLL = view.findViewById(R.id.searchLL);
        searchIt = view.findViewById(R.id.searchIt);
        serverCall();
        getCategoriesOnline();
        getSliders();
        searchClickListeners();
        setClickListeners();


    }

    private void searchClickListeners() {
        searchIt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                startActivity(intent);

            }
        });
    }

    //    private void searchClickListeners() {
//        searchIt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent= new Intent(getActivity(), SearchActivity.class);
//                startActivity(intent);
//            }
//        });
//    }
    private void setClickListeners() {
        viewAllTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomNavigationView.setSelectedItemId(R.id.categoryMenu);
            }
        });
        searchLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
    private void getSliders() {
        Call<SliderResponse> sliderResponseCall = ApiClient.getClient().getSliders();
        sliderResponseCall.enqueue((new Callback<SliderResponse>() {
            @Override
            public void onResponse(Call<SliderResponse> call, Response<SliderResponse> response) {
                if (response.isSuccessful()){
                    if (!response.body().getError()){
                        setSlider(response.body().getSliders());
                    }
                }
            }

            @Override
            public void onFailure(Call<SliderResponse> call, Throwable t) {

            }
        }));
    }

    private void setSlider(List<Slider> sliders) {
        SliderAdapter sliderAdapter = new SliderAdapter(sliders, getContext(), true);
        sliderAdapter.setClickLister(new SliderAdapter.OnSliderClickLister() {
            @Override
            public void onSliderClick(int position, Slider slider) {
                Toast.makeText(getContext(), "from This is item in position" +position, Toast.LENGTH_SHORT).show();
            }
        });
        imageSlider.setSliderAdapter(sliderAdapter);
        imageSlider.setIndicatorAnimation(IndicatorAnimationType.WORM); //set indicator animation by using IndicatorAnimationType. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        imageSlider.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        imageSlider.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        imageSlider.setIndicatorUnselectedColor(Color.GRAY);
        imageSlider.setScrollTimeInSec(4); //set scroll delay in seconds :
        imageSlider.startAutoCycle();


    }


    private void getCategoriesOnline() {
        Call<CategoryResponse> categoryResponseCall = ApiClient.getClient().getCategories();
        categoryResponseCall.enqueue(new Callback<CategoryResponse>() {
            @Override
            public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {
                if (response.isSuccessful()) {
                    DataHolder.categories = response.body().getCategories();
                    showCategories(response.body().getCategories());

                }
            }

            @Override
            public void onFailure(Call<CategoryResponse> call, Throwable t) {

            }
        });

    }

    private void showCategories(List<Category> categories) {
        List<Category> temp;
        if (categories.size() > 8) {
            temp = new ArrayList<>();
            for (int i = 0; i < 8; i++) {
                temp.add(categories.get(categories.size() - i - 1));
            }
        } else {
            temp = categories;
        }
        categoryRV.setHasFixedSize(true);
        categoryRV.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        CategoryAdapter categoryAdapter = new CategoryAdapter(temp, getContext(), true);
        categoryRV.setAdapter(categoryAdapter);
    }

    private void serverCall() {
        Call<AllProductResponse> allProductResponseCall = ApiClient.getClient().getAllProducts();
        allProductResponseCall.enqueue(new Callback<AllProductResponse>() {
            @Override
            public void onResponse(Call<AllProductResponse> call, Response<AllProductResponse> response) {
                if (response.isSuccessful()) {
                    setProductRecylerView(response.body().getProducts());
                }
            }

            @Override
            public void onFailure(Call<AllProductResponse> call, Throwable t) {
                Toast.makeText(getActivity(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void setProductRecylerView(List<Product> products) {
        allProductRV.setHasFixedSize(true);
        allProductRV.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        ShopAdapter shopAdapter = new ShopAdapter(products, getContext(), false);
        allProductRV.setAdapter(shopAdapter);
    }

//    private void setProductRecylerView(List<Product> products) {
//        allProductRV.setHasFixedSize(true);
//        allProductRV.setLayoutManager(new GridLayoutManager(getActivity(), 2));
//
//
//    }


//    private void setProductRecylerView(List<Product> products) {
//        allProductRV.setHasFixedSize(true);
//        allProductRV.setLayoutManager(new GridLayoutManager(getActivity(), 2));
//        ShopAdapter shopAdapter = new ShopAdapter(products, getContext());
//        allProductRV.setAdapter(shopAdapter);
//    }
////    private void setProductRecylerView(List<Product> products) {
//        allProductRV.setHasFixedSize(true);
//        allProductRV.setLayoutManager(new GridLayoutManager(getActivity(), 2));
//        ShopAdapter shopAdapter = new ShopAdapter(products, getContext());
//        allProductRV.setAdapter(shopAdapter);
//
//
//    }


}