package com.example.thriftandthrive.categoryActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thriftandthrive.R;
import com.example.thriftandthrive.api.ApiClient;
import com.example.thriftandthrive.api.response.AllProductResponse;
import com.example.thriftandthrive.api.response.Category;
import com.example.thriftandthrive.api.response.Product;
import com.example.thriftandthrive.home.fragments.home.adapters.ShopAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryActivity extends AppCompatActivity {

    public static String CATEGORY_DATA_KEY = "cdk";
    Category category;
    RecyclerView allProductsRV;
    ImageView emptyIV;
    ProgressBar loadingProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        if (getIntent().getSerializableExtra(CATEGORY_DATA_KEY) == null)
            finish();
        allProductsRV = findViewById(R.id.allProductRV);
        loadingProgress = findViewById(R.id.loadingProgress);
        emptyIV = findViewById(R.id.emptyIV);


        category = (Category) getIntent().getSerializableExtra(CATEGORY_DATA_KEY);
        getSupportActionBar().setTitle(category.getName());
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getCategoryOnline();
    }

    private void getCategoryOnline() {
        Call<AllProductResponse> getProductsByCategory = ApiClient.getClient().getProductsByCategory(category.getId());
        getProductsByCategory.enqueue(new Callback<AllProductResponse>() {
            @Override
            public void onResponse(Call<AllProductResponse> call, Response<AllProductResponse> response) {
                if (response.isSuccessful()){
                    if (!response.body().getError()){
                        if(response.body().getProducts().size() == 0)
                            showEmptyLayout();
                        else
                            ShowCategoriesProducts(response.body().getProducts());
                    }
                }

            }

            @Override
            public void onFailure(Call<AllProductResponse> call, Throwable t) {
                Toast.makeText(CategoryActivity.this, "An Unknown Error Occoured", Toast.LENGTH_SHORT).show();

            }
        });


    }

    private void ShowCategoriesProducts(List<Product> products) {
        allProductsRV.setHasFixedSize(true);
        GridLayoutManager layoutManager= new GridLayoutManager(this, 2);
        allProductsRV.setLayoutManager(layoutManager);
        ShopAdapter shopAdapter = new ShopAdapter(products, this, false);
        allProductsRV.setAdapter(shopAdapter);
    }

    private void showEmptyLayout() {
        emptyIV.setVisibility(View.VISIBLE);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}