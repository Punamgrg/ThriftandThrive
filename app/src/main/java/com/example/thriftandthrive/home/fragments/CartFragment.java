package com.example.thriftandthrive.home.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.thriftandthrive.R;
import com.example.thriftandthrive.api.ApiClient;
import com.example.thriftandthrive.api.response.AllProductResponse;
import com.example.thriftandthrive.api.response.Product;
import com.example.thriftandthrive.utils.SharedPrefUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CartFragment extends Fragment {
    RecyclerView allProductRV;
    List<Product> products;
    TextView totalPriceTv;
    SwipeRefreshLayout swipeRefresh;
    LinearLayout addtoCartLL;
    AllProductResponse allProductResponse;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cart, container, false);
    }

    @Override

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        allProductRV = view.findViewById(R.id.allProductRV);
        totalPriceTv = view.findViewById(R.id.totalPriceTv);
        addtoCartLL = view.findViewById(R.id.addToCartLL);
        swipeRefresh = view.findViewById(R.id.swipeRefresh);

    }
    private void getCartItems() {

        String key = SharedPrefUtils.getString(getActivity(), getString(R.string.api_key));
        Call<AllProductResponse> cartItemsCall = ApiClient.getClient().getMyCart(key);
        cartItemsCall.enqueue(new Callback<AllProductResponse>() {
            @Override
            public void onResponse(Call<AllProductResponse> call, Response<AllProductResponse> response) {
                swipeRefresh.setRefreshing(false);
                if (response.isSuccessful()) {
                    if (!response.body().getError()) {
                        allProductResponse = response.body();
                        products = response.body().getProducts();
                        loadCartList();
                        setPrice();
                    }
                }
            }

            @Override
            public void onFailure(Call<AllProductResponse> call, Throwable t) {
                swipeRefresh.setRefreshing(false);
            }
        });
    }

    private void setPrice() {
    }

    private void loadCartList() {
        allProductRV.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        allProductRV.setLayoutManager(layoutManager);


    }
}





