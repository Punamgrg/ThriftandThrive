package com.example.thriftandthrive.singleProductPage;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.thriftandthrive.R;
import com.example.thriftandthrive.api.ApiClient;
import com.example.thriftandthrive.api.response.Product;
import com.example.thriftandthrive.api.response.RegisterResponse;
import com.example.thriftandthrive.api.response.Slider;
import com.example.thriftandthrive.home.fragments.home.adapters.SliderAdapter;
import com.example.thriftandthrive.utils.SharedPrefUtils;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SingleProductActivity extends AppCompatActivity {
    public static String key = "pKey";
    public static String DATA_KEY = "ds";
    public static String SINGLE_DATA_KEY = "sds";
    Product product;
    SliderView imageSlider;
    ImageView backIV;
    TextView name, price, desc, oldPrice;
    LinearLayout addToCartLL;
    boolean isAdding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        getWindow().setStatusBarColor(Color.WHITE);

        setContentView(R.layout.activity_single_product);
       backIV = findViewById(R.id.backIV);
        imageSlider = findViewById(R.id.imageSlider);
        name = findViewById(R.id.productNameTV);
        price = findViewById(R.id.productPriceTV);
        oldPrice = findViewById(R.id.productOldPriceTV);
        desc = findViewById(R.id.decTV);
        addToCartLL = findViewById(R.id.addToCartLL);

        setOnclickListners();
        if (getIntent().getSerializableExtra(key) != null) {
            product = (Product) getIntent().getSerializableExtra(key);
            setProduct(product);
        }

    }

    private void setProduct(Product product) {
        setSliders(product.getImages());
        name.setText(product.getName());

        if (product.getDiscountPrice() == 0|| product.getDiscountPrice()==null) {
            price.setText("Rs. " + product.getPrice());
            oldPrice.setVisibility(View.INVISIBLE);

        }else {
            price.setText("Rs. " + product.getDiscountPrice());
            oldPrice.setText("Rs. " + product.getPrice());

        }        desc.setText(product.getDescription());
    }

    private void setSliders(List<String> images) {
        List<Slider> sliders = new ArrayList<>();
        for (int i = 0; i < images.size(); i++) {
            Slider slider = new Slider();
            slider.setImage(images.get(i));
            sliders.add(slider);

        }
        SliderAdapter sliderAdapter= new SliderAdapter(sliders, this, false);
        sliderAdapter.setClickLister(new SliderAdapter.OnSliderClickLister() {
            @Override
            public void onSliderClick(int position, Slider slider) {

            }
        });
        imageSlider.setSliderAdapter(sliderAdapter);
        imageSlider.setIndicatorAnimation(IndicatorAnimationType.WORM); //set indicator animation by using IndicatorAnimationType. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        imageSlider.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        imageSlider.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        imageSlider.setIndicatorUnselectedColor(Color.GRAY);

    }

    private void setOnclickListners() {
        backIV.setOnClickListener(v -> finish());


        addToCartLL.setOnClickListener(v -> {

            if (!isAdding) {
                isAdding = true;

                String key = SharedPrefUtils.getString(this, getString(R.string.api_key));
                Call<RegisterResponse> cartCall = ApiClient.getClient().addToCart(key, product.getId());
                cartCall.enqueue(new Callback<RegisterResponse>() {
                    @Override
                    public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                        isAdding = false;

                    }

                    @Override
                    public void onFailure(Call<RegisterResponse> call, Throwable t) {

                        isAdding = false;
                    }
                });
            } else {
                Toast.makeText(getApplicationContext(), "Adding Already!!", Toast.LENGTH_SHORT).show();
            }

        });
    }


}

