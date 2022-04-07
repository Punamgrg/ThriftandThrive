package com.example.thriftandthrive.checkout;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thriftandthrive.R;
import com.example.thriftandthrive.api.ApiClient;
import com.example.thriftandthrive.api.response.Address;
import com.example.thriftandthrive.api.response.AllProductResponse;
import com.example.thriftandthrive.api.response.Product;
import com.example.thriftandthrive.api.response.RegisterResponse;
import com.example.thriftandthrive.checkout.address.AddressActivity;
import com.example.thriftandthrive.checkout.orderComplete.OrderCompleteActivity;
import com.example.thriftandthrive.home.fragments.home.adapters.ShopAdapter;
import com.example.thriftandthrive.utils.SharedPrefUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CheckoutActivity extends AppCompatActivity {
    public static String CHECK_OUT_PRODUCTS = "sd";
    RecyclerView allProductRV;
    AllProductResponse allProductResponse;
    ImageView backIv;
    RecyclerView allProductsRV;
    LinearLayout addressLL, checkOutLL;
    Address address;
    TextView emptyAddressTv, cityStreetTV, provinceTV, totalTV, subTotalTV, shippingTV, totalPriceTv, discountTV;
    List<Product> products;
    double subTotalPrice = 0;
    double shippingCharge = 100;
    int p_type = 1;
    String p_ref = "COD";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        getWindow().setStatusBarColor(Color.WHITE);
        setContentView(R.layout.activity_checkout);
        backIv = findViewById(R.id.backIv);
        emptyAddressTv = findViewById(R.id.emptyAddressTv);
        addressLL = findViewById(R.id.addressLL);
        checkOutLL = findViewById(R.id.checkOutLL);
        cityStreetTV = findViewById(R.id.cityStreetTV);
        provinceTV = findViewById(R.id.provinceTV);
        allProductsRV = findViewById(R.id.allProductsRV);
        totalTV = findViewById(R.id.totalTV);
        subTotalTV = findViewById(R.id.subTotalTV);
        shippingTV = findViewById(R.id.shippingTV);
        totalPriceTv = findViewById(R.id.totalPriceTv);
        discountTV = findViewById(R.id.discountTV);
        setClickListners();
        allProductResponse = (AllProductResponse) getIntent().getSerializableExtra(CHECK_OUT_PRODUCTS);
        products = allProductResponse.getProducts();
        loadCartList();
    }

    private void setClickListners() {
        backIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        addressLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CheckoutActivity.this, AddressActivity.class);
                startActivityForResult(intent, 1);
            }
        });
        emptyAddressTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CheckoutActivity.this, AddressActivity.class);
                startActivityForResult(intent, 1);
            }
        });
        checkOutLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (address != null) {
                    checkOut(subTotalPrice);
                } else {
                    Toast.makeText(CheckoutActivity.this, "Please Select A Address", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void loadCartList() {

        allProductsRV.setHasFixedSize(true);
        allProductsRV.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        ShopAdapter shopAdapter = new ShopAdapter(products, this, true);
        shopAdapter.setRemoveEnabled(false);
        allProductsRV.setAdapter(shopAdapter);
        setPrice();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {

            assert data != null;
            if (data.getSerializableExtra(AddressActivity.ADDRESS_SELECTED_KEY) != null) {
                showSelectedAddress((Address) data.getSerializableExtra(AddressActivity.ADDRESS_SELECTED_KEY));

            }
        }
    }

    private void showSelectedAddress(Address address) {
        address = address;
        emptyAddressTv.setVisibility(View.GONE);
        cityStreetTV.setText(address.getCity() + " " + address.getStreet());
        provinceTV.setText(address.getProvince());
        addressLL.setVisibility(View.VISIBLE);
    }

    private void setPrice() {

        double discount = 0;
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getDiscountPrice() != 0 || products.get(i).getDiscountPrice() != null) {
                subTotalPrice = subTotalPrice + products.get(i).getDiscountPrice();
                discount = discount + products.get(i).getPrice() - products.get(i).getDiscountPrice();
            } else
                subTotalPrice = subTotalPrice + products.get(i).getPrice();
        }
        subTotalTV.setText("Rs. " + (subTotalPrice));
        totalTV.setText("Rs. " + (subTotalPrice + shippingCharge));
        totalPriceTv.setText("( Rs. " + subTotalPrice + " )");
        shippingTV.setText("Rs. " + shippingCharge);
        discountTV.setText("-" + "Rs. " + discount);


    }

    private void checkOut(double finalPrice) {
        String key = SharedPrefUtils.getString(this, getString(R.string.api_key));
        Call<RegisterResponse> orderCall = ApiClient.getClient().order(key, p_type, address.getId(), p_ref);
        orderCall.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                if (response.isSuccessful()) {
                    Intent intent = new Intent(CheckoutActivity.this, OrderCompleteActivity.class);
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {

            }
        });
    }
}